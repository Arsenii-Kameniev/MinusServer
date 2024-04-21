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

import java.util.Optional;

@Controller
public class OrdersController {
    @Autowired
    private OrdersRepository OrdersRepos;
    @Autowired
    private OrderStatusRepository OrderStatusRepos;
    @Autowired
    private ProductRepository ProductRepos;
    @GetMapping("/getOrders")
    public ResponseEntity<Orders> getOrders(@RequestBody int id){
        Optional<Orders> orders = OrdersRepos.findById((long) id);

        if(orders.isEmpty()){
            return  new ResponseEntity<Orders>(orders.get(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Orders>(orders.get(), HttpStatus.OK);

    }
    @PostMapping("/postOrders")
    public ResponseEntity<Orders> postOrders(@RequestBody Orders orders){
        try{
            OrdersRepos.save(orders);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<Orders>(orders, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Orders>(orders, HttpStatus.OK);
    }
    @PostMapping("/postPay")
    public ResponseEntity<Orders> postPay(@RequestBody Pay pay){
        Optional<OrderStatus> orderStatus = OrderStatusRepos.findById(pay.getStatusId());
        Optional<Product> product = ProductRepos.findById(pay.getProductId());
        if(orderStatus.isEmpty() || product.isEmpty()){
            Orders o = new Orders();
            return new ResponseEntity<Orders>(o, HttpStatus.BAD_REQUEST);
        }
        Orders orders = new Orders();
        orders.setPayment(pay.getPayment());
        orders.setStatus(orderStatus.get());
        orders.setProduct(product.get());
        product.get().setPartlyPrice(product.get().getPartlyPrice()+pay.getPayment());
        OrdersRepos.save(orders);
        ProductRepos.save(product.get());
        return new ResponseEntity<Orders>(orders, HttpStatus.OK);
    }
    @PatchMapping("/patchOrders")
    public ResponseEntity<String> patchOrders(@RequestBody PatchClass changer){
        try {
            Optional<Orders> TestOrders = OrdersRepos.findById((long) changer.getId());
            if(TestOrders.isEmpty()){
                return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
            }
            Orders orders = TestOrders.get();
            changer.setType(changer.getType().toLowerCase());
            if(changer.getType().equals("payment")){
                orders.setPayment(Double.valueOf(changer.getPatch()));
            }
            else if(changer.getType().equals("status")){
                OrderStatus orderStatus = new OrderStatus();
                orderStatus.setType(changer.getPatch());
                orders.setStatus(orderStatus);
            }
            OrdersRepos.save(orders);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Good", HttpStatus.OK);
    }
    @PutMapping("/putOrders")
    public ResponseEntity<String> putOrders(@RequestBody PutOrdersClass OrdersChanger){
        try {
            Optional<Orders> TestOrders = OrdersRepos.findById((long) OrdersChanger.getId());
            if(TestOrders.isEmpty()){
                return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
            }
            Orders ChangeOrders = TestOrders.get();
            ChangeOrders.setPayment(OrdersChanger.getOrders().getPayment());
            ChangeOrders.setStatus(OrdersChanger.getOrders().getStatus());
            OrdersRepos.save(ChangeOrders);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Good", HttpStatus.OK);
    }
    @DeleteMapping("/deleteOrders")
    public ResponseEntity<String> deleteOrders(@RequestBody int id){
        Optional<Orders> TestOrders = OrdersRepos.findById((long) id);
        if(TestOrders.isEmpty()){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }
        Orders orders = TestOrders.get();
        Product product = orders.getProduct();
        product.setPartlyPrice(product.getPartlyPrice()-orders.getPayment());
        OrdersRepos.delete(orders);
        ProductRepos.save(product);
        return new ResponseEntity<String>("Good", HttpStatus.OK);
    }
}
