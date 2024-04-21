package com.example.MinusServer.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Category")
public class Category {
    @Id
    @GeneratedValue
    private Integer Id;
    private String name;
    public Category(Integer id, String name){
        this.Id=id;
        this.name=name;
    }
    public Category(){

    }
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return Id;
    }
}
