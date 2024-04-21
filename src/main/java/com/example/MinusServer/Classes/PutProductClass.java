package com.example.MinusServer.Classes;

import com.example.MinusServer.Models.MyUser;
import com.example.MinusServer.Models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PutProductClass {
    private int Id;
    private Product Product;

}
