package com.example.MinusServer.Controllers;

import com.example.MinusServer.Classes.*;
import com.example.MinusServer.Models.*;
import com.example.MinusServer.Repositories.OrderStatusRepository;
import com.example.MinusServer.Repositories.OrdersRepository;
import com.example.MinusServer.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class WorkController {
    @Autowired
    private OrdersRepository OrdersRepos;
    @Autowired
    private OrderStatusRepository OrderStatusRepos;
    @Autowired
    private ProductRepository ProductRepos;
    @PostMapping("/postWork")
    public ResponseEntity<Iterable<Product>> postWork(){
        Iterable<Product> arr = ProductRepos.findAll();
        return new ResponseEntity<Iterable<Product>>(arr, HttpStatus.OK);
    }
}
