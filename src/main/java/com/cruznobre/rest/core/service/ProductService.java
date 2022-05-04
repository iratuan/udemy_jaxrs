package com.cruznobre.rest.core.service;

import com.cruznobre.rest.core.entity.Product;
import com.cruznobre.rest.core.exception.PersistenceExceptionCustom;
import com.cruznobre.rest.core.persistence.ProductDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Stateless
public class ProductService {

    @Inject
    private ProductDAO dao;

    public List<Product> listAll() throws PersistenceException, PersistenceExceptionCustom {
        return dao.listAll();
    }

    public Product insert(@NotNull Product entity) throws PersistenceExceptionCustom {
        return dao.save(entity);
    }

    public Product update(Product entity) throws PersistenceExceptionCustom {
        return dao.save(entity);
    }

    public void delete(Long id) throws PersistenceExceptionCustom {
        Product entity = dao.get(id);
        if(entity != null){
            dao.delete(entity);
        }
    }
}
