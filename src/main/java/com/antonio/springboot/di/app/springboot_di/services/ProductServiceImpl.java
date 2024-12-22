package com.antonio.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.antonio.springboot.di.app.springboot_di.models.Product;
import com.antonio.springboot.di.app.springboot_di.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

  @Value("${config.price.tax}")
  private Double tax;

  // @Autowired
  private Environment environment;

  private ProductRepository repository;

  public ProductServiceImpl(ProductRepository repository, Environment environment) {
    this.repository = repository;
    this.environment = environment;
  }

  @Override
  public List<Product> findAll() {
    return repository.findAll().stream().map(p -> {
      System.out.println("tax: " + environment.getProperty("config.price.tax"));
      // Double priceTax = p.getPrice() * environment.getProperty("config.price.tax",
      // Double.class);
      Double priceTax = p.getPrice() * tax;
      Product newProduct = (Product) p.clone();
      newProduct.setPrice(priceTax.longValue());
      return newProduct;
    }).collect(Collectors.toList());
  }

  @Override
  public Product findById(Long id) {
    return repository.findById(id);
  }

}
