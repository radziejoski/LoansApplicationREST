package pl.lalowicz.loans.webservices.core.storedfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by radoslaw.lalowicz on 2017-05-08.
 */
@Service("storedFileService")
@Transactional
public class StoredFileServiceImpl implements StoredFileService {

    @Autowired
    private StoredFileDao fileDao;

    @Override
    public void create(StoredFile file) {
        fileDao.create(file);
    }

    @Override
    public StoredFile get(Long id) {
        return fileDao.get(id);
    }

    @Override
    public void updateAll(List<StoredFile> soredFiles) {
        fileDao.updateAll(soredFiles);
    }

    @Override
    public void createAll(List<StoredFile> soredFiles) {
        fileDao.createAll(soredFiles);
    }

    @Override
    public List<StoredFile> search(StoredFileQuery query) {
        return fileDao.search(query);
    }

    @Override
    public void delete(Long id) {
        fileDao.delete(id);
    }

}
