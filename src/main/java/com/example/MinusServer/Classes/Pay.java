package com.example.MinusServer.Classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pay {
    private Double Payment;
    private long StatusId;
    private long ProductId;

}
