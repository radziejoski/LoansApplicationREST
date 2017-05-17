package pl.lalowicz.loans.webservices.core.customer;

import java.util.List;
import java.util.Optional;

/**
 * Created by radoslaw.lalowicz on 2017-05-05.
 */
public interface CustomerService {

    Customer create(Customer customer);

    void update(Customer customer);

    Optional<Customer> findById(Long id);

    List<Customer> search(CustomerQuery query);

    Customer getById(Long id);

}
