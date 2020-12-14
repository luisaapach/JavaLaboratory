/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package DAO;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public abstract class DataRepository<T,ID extends Serializable> implements Serializable
{
    protected Class<T> entityClass;

    @PersistenceContext
    private EntityManager em;

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
    }
    public void update(T entity) {
        em.merge(entity);
    }
    public void remove(T entity) {
        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        em.remove(entity);
    }
    public T refresh(T entity) {
        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        em.refresh(entity);
        return entity;
    }

    public T findById(ID id) {
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

    public DataRepository() {
    }
}
