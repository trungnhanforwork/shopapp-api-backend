package com.example.shopapp.services;

import com.example.shopapp.dtos.ProductDTO;
import com.example.shopapp.models.Product;
import org.springframework.stereotype.Service;

@Service
public interface IProductService {
    Product createProduct(ProductDTO productDTO);
}
