package com.example.MinusServer.Controllers;

import com.example.MinusServer.Classes.Pay;
import com.example.MinusServer.Classes.PutOrderStatusClass;
import com.example.MinusServer.Classes.StatusAdder;
import com.example.MinusServer.Models.MyUser;
import com.example.MinusServer.Models.OrderStatus;
import com.example.MinusServer.Models.Orders;
import com.example.MinusServer.Repositories.OrderStatusRepository;
import com.example.MinusServer.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class OrderStatusController {
    @Autowired
    private OrderStatusRepository OrderStatusRepos;
    @Autowired
    private UserRepository UserRepos;
    @GetMapping("/getOrderStatus")
    public ResponseEntity<OrderStatus> getOrderStatus(@RequestBody int id){
        Optional<OrderStatus> orderStatus = OrderStatusRepos.findById((long) id);

        if(orderStatus.isEmpty()){
            return  new ResponseEntity<OrderStatus>(orderStatus.get(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<OrderStatus>(orderStatus.get(), HttpStatus.OK);
    }
    @PostMapping("/postOrderStatus")
    public ResponseEntity<OrderStatus> postOrderStatus(@RequestBody OrderStatus orderStatus){
        try {
            OrderStatusRepos.save(orderStatus);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<OrderStatus>(orderStatus, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<OrderStatus>(orderStatus, HttpStatus.OK);
    }
    @PostMapping("/postOrderStatusAdmin")
    public ResponseEntity<OrderStatus> postOrderStatusAdmin(@RequestBody StatusAdder statusAdder){
        Optional<MyUser> user = UserRepos.findById(statusAdder.getPId());
        if(user.isEmpty() || user.get().getLevel()<=3
//                || !OrderStatusRepos.findByName(statusAdder.getType()).isEmpty()
        ){
            OrderStatus o = new OrderStatus();
            return new ResponseEntity<OrderStatus>(o, HttpStatus.BAD_REQUEST);
        }
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setType(statusAdder.getType());
        OrderStatusRepos.save(orderStatus);
        return new ResponseEntity<OrderStatus>(orderStatus, HttpStatus.OK);
    }


    @PutMapping("/putOrderStatus")
    public ResponseEntity<String> putOrderStatus(@RequestBody PutOrderStatusClass OrderStatusChanger){

        try {
            Optional<OrderStatus> TestOrderStatus = OrderStatusRepos.findById((long) OrderStatusChanger.getId());
            if(TestOrderStatus.isEmpty()){
                return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
            }
            OrderStatus ChangeOrderStatus = TestOrderStatus.get();
            ChangeOrderStatus.setType(OrderStatusChanger.getOrderStatus().getType());
            OrderStatusRepos.save(ChangeOrderStatus);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Good", HttpStatus.OK);
    }
    @DeleteMapping("/deleteOrderStatus")
    public ResponseEntity<String> deleteOrderStatus(@RequestBody int id){

        try {
            Optional<OrderStatus> TestOrderStatus = OrderStatusRepos.findById((long) id);
            if(TestOrderStatus.isEmpty()){
                return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
            }
            OrderStatus orderStatus = TestOrderStatus.get();
            OrderStatusRepos.delete(orderStatus);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Good", HttpStatus.OK);
    }
}
