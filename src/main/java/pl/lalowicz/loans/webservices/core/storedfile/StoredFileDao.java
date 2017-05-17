package pl.lalowicz.loans.webservices.core.storedfile;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.lalowicz.loans.webservices.core.AbstractDao;
import pl.lalowicz.loans.webservices.core.customer.Customer;

import java.util.List;

/**
 * Created by radoslaw.lalowicz on 2017-05-08.
 */
@Repository("storedFileDao")
public class StoredFileDao extends AbstractDao {

    public StoredFile create(StoredFile file) {
        return persist(file);
    }

    public void createAll(List<StoredFile> storedFiles) {
        storedFiles.stream().map(this::create).count();
    }

    public List<StoredFile> search(StoredFileQuery query) {
        final Criteria criteria = createCriteria(StoredFile.class);
        query.apply(FileQueryApplier.of(criteria));
        return list(criteria);
    }

    public StoredFile get(Long id) {
        return get(StoredFile.class, id);
    }

    public void updateAll(List<StoredFile> storedFiles) {
        storedFiles.stream().map(this::update).count();
    }

    public StoredFile update(StoredFile storedFile) {
        return merge(storedFile);
    }

    public void delete(Long id) {
        delete(StoredFile.class, id);
    }

    private static final class FileQueryApplier implements StoredFileQuery.Applier {
        private final Criteria criteria;

        public static final FileQueryApplier of(Criteria criteria) {
            return new FileQueryApplier(criteria);
        }

        private FileQueryApplier(Criteria criteria) {
            this.criteria = criteria;
        }

        @Override
        public void withCustomerFileId(Long customerId) {
            String propertyName = StoredFile.PROPERTY_CUSTOMER + "." + Customer.PROPERTY_ID;
            criteria.add(Restrictions.eq(propertyName, customerId));
        }

    }
}
