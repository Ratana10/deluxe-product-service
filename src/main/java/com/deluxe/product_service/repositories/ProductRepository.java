package com.deluxe.product_service.repositories;

import com.deluxe.product_service.entities.Category;
import com.deluxe.product_service.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}