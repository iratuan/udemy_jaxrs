package com.cruznobre.rest.core.persistence;

import com.cruznobre.rest.core.entity.Product;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;


@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductDAO extends GenericDAO<Product, Long> {

    public ProductDAO() {
        super(Product.class);
    }
}
