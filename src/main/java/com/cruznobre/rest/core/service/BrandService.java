package com.cruznobre.rest.core.service;

import com.cruznobre.rest.core.entity.Brand;
import com.cruznobre.rest.core.exception.PersistenceExceptionCustom;
import com.cruznobre.rest.core.persistence.BrandDAO;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Stateless
public class BrandService {

    @Inject
    private BrandDAO dao;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Brand> listAll(Integer page, Integer size) throws PersistenceException, PersistenceExceptionCustom {
        return dao.listAll(page, size);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Brand get(Long id) throws PersistenceException, PersistenceExceptionCustom {
        return dao.get(id);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Long getTotal() throws PersistenceExceptionCustom {
        return dao.getTotal();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Brand insert(@NotNull Brand entity) throws PersistenceExceptionCustom {
        return dao.save(entity);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Brand update(Brand entity) throws PersistenceExceptionCustom {
        Brand persitedEntity = dao.get(entity.getId());

        if(persitedEntity == null){
          throw new PersistenceExceptionCustom("Registro não encontrado");
        }
        return dao.save(entity);
    }

    public void delete(Long id) throws PersistenceExceptionCustom {
        Brand entity = dao.get(id);
        if(entity != null){
            dao.delete(entity);
        }
    }
}
