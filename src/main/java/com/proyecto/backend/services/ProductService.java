package com.proyecto.backend.services;

import com.proyecto.backend.models.Product;
import com.proyecto.backend.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    @Autowired
    IProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Product not found"));
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProductById(long id){
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}
