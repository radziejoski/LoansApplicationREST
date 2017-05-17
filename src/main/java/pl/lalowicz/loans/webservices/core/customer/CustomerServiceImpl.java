package pl.lalowicz.loans.webservices.core.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by radoslaw.lalowicz on 2017-05-05.
 */
@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Customer create(Customer customer) {
        return customerDao.create(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerDao.findById(id);
    }

    @Override
    public List<Customer> search(CustomerQuery query) {
        return customerDao.search(query);
    }

    @Override
    public Customer getById(Long id) {
        return customerDao.getById(id);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }
}
