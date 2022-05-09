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

    public List<Product> listAll(String category, Integer page, Integer size) throws PersistenceException, PersistenceExceptionCustom {
        return dao.listAll(category, page, size);
    }

    public Product get(Long id) throws PersistenceException, PersistenceExceptionCustom {
        return dao.get(id);
    }
    public Long getTotal() throws PersistenceExceptionCustom {
        return dao.getTotal();
    }


    public List<Product> listAllByBrand(Long id) throws PersistenceException, PersistenceExceptionCustom {
        return dao.listAllByBrand(id);
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
