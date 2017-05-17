package pl.lalowicz.loans.webservices.core.customer;

import java.util.Optional;

/**
 * Created by radoslaw.lalowicz on 2017-05-06.
 */
public class CustomerQuery {
    private Optional<Long> id = Optional.empty();
    private Optional<LoanType> loanType = Optional.empty();
    private Optional<String> fiscalCode = Optional.empty();

    public static Builder builder() {
        return new Builder(new CustomerQuery());
    }

    public void apply(Applier applier) {
        id.ifPresent(applier::withId);
        loanType.ifPresent(applier::withLoanType);
        fiscalCode.ifPresent(applier::withFiscalCode);
    }

    public interface Applier {
        void withId(Long id);

        void withLoanType(LoanType loanType);

        void withFiscalCode(String fiscalCode);
    }

    public static class Builder {
        private final CustomerQuery query;

        Builder(CustomerQuery query) {
            this.query = query;
        }

        public CustomerQuery build() {
            return query;
        }

        public Builder withLoanType(LoanType loanType) {
            query.loanType = Optional.ofNullable(loanType);
            return this;
        }

        public Builder withFiscalCode(String fiscalCode) {
            query.fiscalCode = Optional.ofNullable(fiscalCode);
            return this;
        }
    }
}
