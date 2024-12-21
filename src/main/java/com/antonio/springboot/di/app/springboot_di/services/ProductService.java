package com.antonio.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import com.antonio.springboot.di.app.springboot_di.models.Product;
import com.antonio.springboot.di.app.springboot_di.repositories.ProductRepository;

public class ProductService {

  private ProductRepository repository = new ProductRepository();

  public List<Product> findAll() {
    return repository.findAll().stream().map(p -> {
      Double priceTax = p.getPrice() * 1.21;
      // Product newProduct = new Product(p.getId(), p.getName(),
      // priceTax.longValue());
      Product newProduct = (Product) p.clone();
      newProduct.setPrice(priceTax.longValue());
      return newProduct;
    }).collect(Collectors.toList());
  }

  public Product findById(Long id) {
    return repository.findById(id);
  }

}
