package com.example.CaseStudy.service;


import com.example.CaseStudy.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findById(Long productId);

    void deleteById(Long productId);

}
