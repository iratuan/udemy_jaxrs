package com.cruznobre.rest.core.persistence;

import jakarta.persistence.*;

import java.util.List;

import com.cruznobre.rest.core.exception.ExistentEntityInPersistenceException;
import com.cruznobre.rest.core.exception.PersistenceExceptionCustom;

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

    public List<T> listAll() throws PersistenceException, PersistenceExceptionCustom {
        try {
            StringBuilder jpqlSb = new StringBuilder();
            jpqlSb.append("select e from ")
                    .append(this.entityClass.getName())
                    .append(" e");
            return this.getEntityManager().createQuery(jpqlSb.toString(), this.entityClass).getResultList();
        } catch (PersistenceException pe) {
            throw new PersistenceExceptionCustom("Erro ao consultar no banco de dados ", pe);
        } catch (RuntimeException re) {
            throw new PersistenceExceptionCustom("Erro ao generalizado ao executar comando sql ", re);
        }
    }

    public T get(ID id) throws PersistenceException, PersistenceExceptionCustom {
        try {
            return this.getEntityManager().find(entityClass, id);
        } catch (PersistenceException pe) {
            throw new PersistenceExceptionCustom("Erro ao persistir objeto no banco de dados ", pe);
        } catch (RuntimeException re) {
            throw new PersistenceExceptionCustom("Erro ao generalizado ao executar comando sql ", re);
        }
    }

    public T save(T entity) throws PersistenceExceptionCustom {
        try {
            if (getEntityManager().contains(entity)) {
                throw new ExistentEntityInPersistenceException("Erro ao generalizado ao executar comando sql ");
            } else {
                entity = this.getEntityManager().merge(entity);
                return entity;
            }
        } catch (PersistenceException pe) {
            throw new PersistenceExceptionCustom("Erro ao persistir objeto no banco de dados ", pe);
        } catch (RuntimeException re) {
            throw new PersistenceExceptionCustom("Erro ao generalizado ao executar comando sql ", re);
        }
    }

    public void delete(T entity) throws PersistenceExceptionCustom {
        try {
            this.getEntityManager().remove(entity);
        } catch (PersistenceException pe) {
            throw new PersistenceExceptionCustom("Erro ao remover objeto no banco de dados ", pe);
        } catch (RuntimeException re) {
            throw new PersistenceExceptionCustom("Erro ao generalizado ao executar comando sql ", re);
        }
    }

}