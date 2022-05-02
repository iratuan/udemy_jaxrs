package com.cruznobre.rest.core.service;

import java.util.List;

import com.cruznobre.rest.core.entity.Brand;
import com.cruznobre.rest.core.persistence.BrandDAO;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;

@Stateless
public class BrandService {

    @Inject
    private BrandDAO dao;
    

    public List<Brand> listAll(){
        return dao.listAll();
    }


    public Brand insert(@NotNull Brand brand) {
        return dao.save(brand);
    }
}
