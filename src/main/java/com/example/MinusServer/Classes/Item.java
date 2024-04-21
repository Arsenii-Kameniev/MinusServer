package com.example.MinusServer.Classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String Name;
    private String Company;
    private Double fullPrice;
    private Integer categoryId;

}
