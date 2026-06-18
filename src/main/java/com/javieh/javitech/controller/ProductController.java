package com.javieh.javitech.controller;

import com.javieh.javitech.dto.ProductDTO;
import com.javieh.javitech.dto.ProductRequestDTO;
import com.javieh.javitech.entity.Product;
import com.javieh.javitech.service.ProductService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductRequestDTO request){
        ProductDTO created = productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

}
