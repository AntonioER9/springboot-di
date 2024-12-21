package com.antonio.springboot.di.app.springboot_di.repositories;

import java.util.Arrays;
import java.util.List;

import com.antonio.springboot.di.app.springboot_di.models.Product;

public class ProductRepository {

  List<Product> data;

  public ProductRepository() {
    this.data = Arrays.asList(
        new Product(1L, "Computer", 1000L),
        new Product(2L, "Mouse", 20L),
        new Product(3L, "Keyboard", 50L),
        new Product(4L, "Monitor", 200L),
        new Product(5L, "Headphones", 100L));
  }

  public List<Product> findAll() {
    return data;
  }

  public Product findById(Long id) {
    return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
  }
}
