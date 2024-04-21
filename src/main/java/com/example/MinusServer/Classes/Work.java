package com.example.MinusServer.Classes;

import com.example.MinusServer.Models.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Work {
    private String name;
    private String category;
    private Double fullPrice;
    private String orderStatus;
    private ArrayList<Orders> orders;


}
