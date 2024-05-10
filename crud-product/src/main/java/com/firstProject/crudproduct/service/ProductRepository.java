package com.firstProject.crudproduct.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstProject.crudproduct.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>  {

}
