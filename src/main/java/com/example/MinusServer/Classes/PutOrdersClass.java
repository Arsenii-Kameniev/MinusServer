package com.example.MinusServer.Classes;

import com.example.MinusServer.Models.MyUser;
import com.example.MinusServer.Models.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PutOrdersClass {
    private int Id;
    private Orders Orders;

}
