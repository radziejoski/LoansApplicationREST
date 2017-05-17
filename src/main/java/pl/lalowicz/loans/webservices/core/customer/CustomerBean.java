package pl.lalowicz.loans.webservices.core.customer;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by radoslaw.lalowicz on 2017-05-15.
 */
public class CustomerBean {

    private Long id;
    private String name;
    private String surname;
    private String fiscalCode;
    private String address;
    private String totalAmount;
    private int phone;
    private int rates;
    private Date birthDate;
    private LoanType loanType;
    private List<File> filesList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getRates() {
        return rates;
    }

    public void setRates(int rates) {
        this.rates = rates;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public List<File> getFilesList() {
        return filesList;
    }

    public void setFilesList(List<File> filesList) {
        this.filesList = filesList;
    }
}
