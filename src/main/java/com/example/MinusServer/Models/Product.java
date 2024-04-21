package com.example.MinusServer.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Product")
public class Product {
    @Id
    @GeneratedValue
    private Integer Id;

    private String name;
    private String Company;
    private Double partlyPrice;
    private Double fullPrice;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public Product(Integer id, String name, String company, Double price, Double partly, Category categoryX){
        this.Id=id;
        this.name=name;
        this.Company=company;
        this.fullPrice=price;
        this.partlyPrice=partly;
        this.category=categoryX;
    }
    @OneToMany(mappedBy = "product")
    private List<Orders> orders = new ArrayList<>();
    public Product(){

    }
    public void setCategory(Category categoryX){
        this.category=categoryX;
    }
    public Category getCategory(){
        return category;
    }
    public void setName(String name1) {
        name = name1;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public void setPrice(Double price) {
        fullPrice = price;
    }
    public void setPartlyPrice(Double price) {
        partlyPrice = price;
    }

    public String getCompany() {
        return Company;
    }

    public String getName() {
        return name;
    }

    public Double getFullPrice() {
        return fullPrice;
    }
    public Double getPartlyPrice() {
        return partlyPrice;
    }

    public Integer getId() {
        return Id;
    }
}