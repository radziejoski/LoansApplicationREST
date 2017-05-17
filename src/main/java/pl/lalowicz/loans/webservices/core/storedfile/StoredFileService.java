package pl.lalowicz.loans.webservices.core.storedfile;

import java.util.List;

/**
 * Created by radoslaw.lalowicz on 2017-05-08.
 */
public interface StoredFileService {

    void create(StoredFile file);

    void delete(Long id);

    StoredFile get(Long id);

    void updateAll(List<StoredFile> soredFiles);

    void createAll(List<StoredFile> soredFiles);

    List<StoredFile> search(StoredFileQuery query);

}
