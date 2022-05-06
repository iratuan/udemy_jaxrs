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

    public List<Product> listAll(String category, Integer page, Integer size) throws PersistenceException, PersistenceExceptionCustom {

        try {
            StringBuilder jpqlSb = new StringBuilder();
            jpqlSb.append("select p from Product p");

            if(category != null){
                jpqlSb.append(" where p.category like :category");
            }

            TypedQuery<Product> query = this.getEntityManager().createQuery(jpqlSb.toString(), Product.class);

            if(category != null){
                query.setParameter("category", "%"+ category.toLowerCase() +"%");
            }

            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
            System.out.println("category => " + category + " - " + query.toString());

            return query.getResultList();
        } catch (PersistenceException pe) {
            throw new PersistenceExceptionCustom("Erro ao consultar no banco de dados ", pe);
        } catch (RuntimeException re) {
            throw new PersistenceExceptionCustom("Erro ao generalizado ao executar comando sql ", re);
        }
    }

    public List<Product> listAllByBrand(Long brandId) throws PersistenceExceptionCustom {
        try {
            String jpql = "select p from Product p join fetch p.brand b where p.brand.id = :brandId ";
            TypedQuery<Product> query = this.getEntityManager().createQuery(jpql, Product.class);
            query.setParameter("brandId", brandId);
            return query.getResultList();
        } catch (PersistenceException pe) {
            throw new PersistenceExceptionCustom("Erro ao consultar no banco de dados ", pe);
        } catch (RuntimeException re) {
            throw new PersistenceExceptionCustom("Erro ao generalizado ao executar comando sql ", re);
        }
    }
}
