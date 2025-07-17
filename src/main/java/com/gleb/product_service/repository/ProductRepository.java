package com.gleb.product_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gleb.product_service.entity.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
