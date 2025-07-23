package com.gleb.product_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gleb.product_service.dto.ProductRequest;
import com.gleb.product_service.dto.ProductResponse;
import com.gleb.product_service.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Tag(name = "Product", description = "Product management APIs")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Create a new product",
        description = "Creates a product and returns the created product data",
        responses = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "403", description = "Forbidden - not authorized to create a product")
        }
    )
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "Get all products",
        description = "Returns a list of all products",
        responses = {
            @ApiResponse(responseCode = "200", description = "List of products returned"),
            @ApiResponse(responseCode = "403", description = "Forbidden - not authorized to view products")
        }
    )
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
}
