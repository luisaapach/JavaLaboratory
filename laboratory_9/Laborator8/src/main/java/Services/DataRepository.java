package Services;


import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public abstract class DataRepository<T,ID extends Serializable> implements Serializable
{
    protected Class<T> entityClass;

    @PersistenceContext
    private EntityManager em;

    protected @Inject
    Event<T> dataEvent;

    protected DataRepository(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    @PostConstruct
    protected void init(){

    }

    public T newInstance(){
        try{
            return entityClass.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e){
            throw new RuntimeException("Instantiation Exception or Illegal Access : "+e.getMessage());
        }
    }

    public void persist(T entity) {
        em.persist(entity);
        dataEvent.fire(entity);
    }
    public void update(T entity) {
        em.merge(entity);
        dataEvent.fire(entity);
    }
    public void remove(T entity) {
        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        em.remove(entity);
        dataEvent.fire(entity);
    }
    public T refresh(T entity) {
        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        em.refresh(entity);
        return entity;
    }

    public T findById(BigDecimal id) {
        if (id == null) {
            return null;
        }
        return em.find(entityClass, id);
    }
    public List<T> findAll() {
        String qlString =
                "select e from " + entityClass.getSimpleName() + " e";
        return em.createQuery(qlString).getResultList();
    }

    public void clearCache() {
        em.getEntityManagerFactory().getCache().evictAll();
    }

    public T createEntity(InputStream is){
        try{
            ObjectInputStream ois = new ObjectInputStream(is);
            Object object = ois.readObject();
            return (T)object;
        }
        catch(Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    public DataRepository() {
    }
}
