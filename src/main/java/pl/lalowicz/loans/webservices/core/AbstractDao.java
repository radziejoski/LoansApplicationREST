package pl.lalowicz.loans.webservices.core;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on 2017-05-05.
 */
public abstract class AbstractDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Criteria createCriteria(Class<?> entityClass) {
        return getSession().createCriteria(entityClass);
    }

    protected <T> List<T> list(Criteria criteria) {
        return criteria.list();
    }

    protected <T> Optional<T> find(Class<T> entityClass, Long id) {
        T result = (T) getSession().get(entityClass, id);
        return Optional.ofNullable(result);
    }

    protected <T> T get(Class<T> entityClass, Long id) {
        T result = (T) getSession().get(entityClass, id);
        return result;
    }

    protected <T> T merge(T entity) {
        T result = (T) getSession().merge(entity);
        return result;
    }

    protected <T> void delete(Class<T> entityClass, Long id) {
        T entity = get(entityClass, id);
        getSession().delete(entity);
    }

    public <T> T persist(T entity) {
        getSession().persist(entity);
        return entity;
    }
}
