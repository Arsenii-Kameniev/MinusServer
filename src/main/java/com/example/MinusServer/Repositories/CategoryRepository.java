package com.example.MinusServer.Repositories;

import com.example.MinusServer.Models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
//    Category findByName(String Name);
    Optional<Category> findByName(String Name);
}
