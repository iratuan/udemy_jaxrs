package com.cruznobre.rest.core.service;

import com.cruznobre.rest.core.entity.Brand;
import com.cruznobre.rest.core.exception.PersistenceExceptionCustom;
import com.cruznobre.rest.core.persistence.BrandDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Stateless
public class BrandService {

    @Inject
    private BrandDAO dao;

    public List<Brand> listAll(Integer page, Integer size) throws PersistenceException, PersistenceExceptionCustom {
        return dao.listAll(page, size);
    }

    public Brand get(Long id) throws PersistenceException, PersistenceExceptionCustom {
        return dao.get(id);
    }

    public Brand insert(@NotNull Brand entity) throws PersistenceExceptionCustom {
        return dao.save(entity);
    }

    public Brand update(Brand entity) throws PersistenceExceptionCustom {
        return dao.save(entity);
    }

    public void delete(Long id) throws PersistenceExceptionCustom {
        Brand entity = dao.get(id);
        if(entity != null){
            dao.delete(entity);
        }
    }
}
