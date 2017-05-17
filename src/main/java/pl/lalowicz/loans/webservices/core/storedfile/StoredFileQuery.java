package pl.lalowicz.loans.webservices.core.storedfile;

/**
 * Created by radoslaw.lalowicz on 2017-05-08.
 */
public class StoredFileQuery {
    private Long customerId;

    public static Builder builder(Long customerId) {
        return new Builder(new StoredFileQuery(customerId));
    }

    private StoredFileQuery(Long customerId) {
        this.customerId = customerId;
    }

    public void apply(Applier applier) {
        applier.withCustomerFileId(customerId);
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public interface Applier {
        void withCustomerFileId(Long customerId);
    }

    public static class Builder {
        private final StoredFileQuery query;

        Builder(StoredFileQuery query) {
            this.query = query;
        }

        public StoredFileQuery build() {
            return query;
        }
    }
}
