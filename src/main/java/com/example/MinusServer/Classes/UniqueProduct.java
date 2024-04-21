package com.example.MinusServer.Classes;

import com.example.MinusServer.Models.Category;
import com.example.MinusServer.Models.MyUser;
import com.example.MinusServer.Models.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UniqueProduct {
    private int id;
    private String name;
    private String company;
    private Double fullPrice;
    private String categoryName;

}
