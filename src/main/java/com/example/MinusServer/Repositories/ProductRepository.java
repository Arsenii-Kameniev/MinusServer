package com.example.MinusServer.Repositories;

import com.example.MinusServer.Models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
//    Product findByName(String Name);
}
