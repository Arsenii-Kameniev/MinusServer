package com.example.MinusServer.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="OrderStatus")
public class OrderStatus {
    @Id
    @GeneratedValue
    private Integer Id;
    private String Type;
    public OrderStatus(Integer id, String type){
        this.Id=id;
        this.Type=type;
    }
    public OrderStatus(){

    }
    @OneToMany(mappedBy = "status")
    private List<Orders> orders = new ArrayList<>();

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Integer getId() {
        return Id;
    }
}
