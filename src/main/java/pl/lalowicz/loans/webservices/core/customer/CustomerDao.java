package pl.lalowicz.loans.webservices.core.customer;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.lalowicz.loans.webservices.core.AbstractDao;

import java.util.List;
import java.util.Optional;

/**
 * Created by radoslaw.lalowicz on 2017-05-05.
 */
@Repository("customerDao")
public class CustomerDao extends AbstractDao {

    public Customer create(Customer customer) {
        return persist(customer);
    }

    public Optional<Customer> findById(Long id) {
        return find(Customer.class, id);
    }

    public List<Customer> search(CustomerQuery query) {
        final Criteria criteria = createCriteria(Customer.class);
        query.apply(CustomerQueryApplier.of(criteria));
        return list(criteria);
    }

    public Customer getById(Long id) {
        return (Customer) getSession().get(Customer.class, id);
    }

    public void update(Customer customer) {
        merge(customer);
    }

    private static final class CustomerQueryApplier implements CustomerQuery.Applier {
        private final Criteria criteria;

        public static final CustomerQueryApplier of(Criteria criteria) {
            return new CustomerQueryApplier(criteria);
        }

        private CustomerQueryApplier(Criteria criteria) {
            this.criteria = criteria;
        }

        @Override
        public void withId(Long id) {
            criteria.add(Restrictions.eq(Customer.PROPERTY_ID, id));
        }

        @Override
        public void withLoanType(LoanType loanType) {
            criteria.add(Restrictions.eq(Customer.PROPERTY_LOAN_TYPE, loanType));
        }

        @Override
        public void withFiscalCode(String fiscalCode) {
            criteria.add(Restrictions.eq(Customer.PROPERTY_FISCAL_CODE, fiscalCode));
        }
    }
}
