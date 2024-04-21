package com.example.MinusServer.Classes;

import com.example.MinusServer.Models.Card;
import com.example.MinusServer.Models.MyUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PutCardClass {
    private int Id;
    private Card Card;

}
