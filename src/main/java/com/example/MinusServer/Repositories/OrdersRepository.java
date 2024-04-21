package com.example.MinusServer.Repositories;

import com.example.MinusServer.Models.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Long> {
//    Orders findByName(String Name);
}
