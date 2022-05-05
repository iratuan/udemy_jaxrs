package com.cruznobre.rest.core.persistence;

import com.cruznobre.rest.core.entity.Product;
import com.cruznobre.rest.core.exception.PersistenceExceptionCustom;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

import java.util.List;


@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductDAO extends GenericDAO<Product, Long> {

    public ProductDAO() {
        super(Product.class);
    }

    public List<Product> listAllByBrand(Long brandId) throws PersistenceExceptionCustom {
        try {
            String jpql = "select p from Product p join fetch p.brand b where p.brand.id = :brandId ";
            TypedQuery<Product> query = this.getEntityManager().createQuery(jpql, Product.class);
            query.setParameter("brandId", brandId );
            return query.getResultList();
        } catch (PersistenceException pe) {
            throw new PersistenceExceptionCustom("Erro ao consultar no banco de dados ", pe);
        } catch (RuntimeException re) {
            throw new PersistenceExceptionCustom("Erro ao generalizado ao executar comando sql ", re);
        }
    }
}
