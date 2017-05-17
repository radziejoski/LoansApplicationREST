package pl.lalowicz.loans.webservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import pl.lalowicz.loans.webservices.core.customer.*;
import pl.lalowicz.loans.webservices.core.storedfile.StoredFile;
import pl.lalowicz.loans.webservices.core.storedfile.StoredFileQuery;
import pl.lalowicz.loans.webservices.core.storedfile.StoredFileService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by radoslaw.lalowicz on 2017-05-11.
 */
@RestController
@Transactional
public class LoansWebServiceController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private StoredFileService storedFileService;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> listAllCustomers(@RequestParam(value = "type", required = false) LoanType loanType, @RequestParam(value = "code", required = false) String fiscalCode) {
        List<Customer> customers = customerService.search(CustomerQuery.builder().withLoanType(loanType).withFiscalCode(fiscalCode).build());
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        Customer customer = customerService.getById(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
        Customer created = customerService.create(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(customer.getId()).toUri());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerBean customer) {
        Optional<Customer> current = customerService.findById(id);
        if (!current.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer forUpdate = current.get();
        forUpdate.setRates(customer.getRates());
        forUpdate.setTotalAmount(customer.getTotalAmount());

        customerService.update(forUpdate);
        return new ResponseEntity<>(forUpdate, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String uploadFile(
            @RequestParam("file") MultipartFile[] files,
            @RequestParam("totalAmount") String totalAmount,
            @RequestParam("rates") String rates,
            @RequestParam("id") String id) {

        Optional<Customer> forUpdate = customerService.findById(Long.decode(id));
        if (!forUpdate.isPresent()) {
            return "not exists";
        }
        Customer customer = forUpdate.get();
        customer.setTotalAmount(totalAmount);
        customer.setRates(Integer.decode(rates));

        customerService.update(customer);

        storeFiles(files, customer);

        return "step3";
    }

    @RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
    public String uploadFiles(@RequestParam("file") MultipartFile[] files, @RequestParam("id") String id) {
        Optional<Customer> customer = customerService.findById(Long.decode(id));
        if (!customer.isPresent()) {
            return "not exists";
        }
        storeFiles(files, customer.get());

        return "success";
    }

    @RequestMapping(value = "/downloadFile/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("id") Long id) throws IOException {
        StoredFile storedFile = storedFileService.get(id);
        String filePath = storedFile.getPath() + "/" + storedFile.getFileName();
        File result = new File(filePath);

        if (result.exists()) {
            InputStream inputStream = new FileInputStream(result);

            byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Content-disposition", "attachment; filename=" + "file.pdf");
            responseHeaders.add("Content-Type", "application/pdf");

            return new ResponseEntity(out, responseHeaders, HttpStatus.OK);
        } else {
            return new ResponseEntity("File Not Found", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/files/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StoredFile>> getFiles(@PathVariable("id") Long id) {
        StoredFileQuery query = StoredFileQuery.builder(id).build();
        List<StoredFile> result = storedFileService.search(query);
        ResponseEntity<List<StoredFile>> customerResponseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        return customerResponseEntity;
    }

    @RequestMapping(value = "/deleteFile/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFile(@PathVariable("id") Long id) {
        storedFileService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void storeFiles(MultipartFile[] files, Customer customer) {
        List<StoredFile> filesList = new ArrayList<>();
        String originalFilename = null;
        String pathToFile = System.getProperty("user.home") + "\\";

        for (MultipartFile file : files) {
            StoredFile storedFile = new StoredFile();
            try {
                byte[] bytes = file.getBytes();
                originalFilename = file.getOriginalFilename();
                Path path = Paths.get(pathToFile + originalFilename);
                Files.write(path, bytes);
                storedFile.setFileName(originalFilename);
                storedFile.setPath(pathToFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            storedFile.setCustomer(customer);
            filesList.add(storedFile);
        }
        if (!filesList.isEmpty()) {
            storedFileService.createAll(filesList);
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
