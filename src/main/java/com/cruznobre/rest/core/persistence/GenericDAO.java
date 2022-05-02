package com.cruznobre.rest.core.persistence;


import jakarta.persistence.*;

import java.util.List;


public class GenericDAO<T, ID> {

    private final Class<T> entityClass;

    @PersistenceContext(name = "default")
    private EntityManager entityManager;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        return factory.createEntityManager();
    }

    public List<T> listAll() throws PersistenceException{
        StringBuilder jpqlSb = new StringBuilder();
        jpqlSb.append("select e from ")
                .append(this.entityClass.getName())
                .append(" e");
            return this.getEntityManager().createQuery(jpqlSb.toString(), this.entityClass).getResultList();
    }

    public T save(T entity){
        if(getEntityManager().contains(entity)){
            //lanca execption
            return null;
        }else{
            entity = this.getEntityManager().merge(entity);
            return entity;
        }
    } 
}