package com.example.MinusServer.Classes;

import com.example.MinusServer.Models.MyUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PutUserClass {
    private int Id;
    private MyUser User;

}
