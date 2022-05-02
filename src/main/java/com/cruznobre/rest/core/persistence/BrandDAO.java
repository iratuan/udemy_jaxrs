package com.cruznobre.rest.core.persistence;

import com.cruznobre.rest.core.entity.Brand;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;


@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BrandDAO extends GenericDAO<Brand, Long> {

    public BrandDAO() {
        super(Brand.class);
    }
}
