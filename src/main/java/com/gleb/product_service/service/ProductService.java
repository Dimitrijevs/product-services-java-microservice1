package com.gleb.product_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gleb.product_service.dto.ProductRequest;
import com.gleb.product_service.dto.ProductResponse;
import com.gleb.product_service.entity.Product;
import com.gleb.product_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor

// creates a logger named log, used for log messages
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        productRepository.save(product);

        log.info("product created succesfully!");

        return new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                        product.getPrice());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                        product.getPrice()))
                .toList();
    }
}
