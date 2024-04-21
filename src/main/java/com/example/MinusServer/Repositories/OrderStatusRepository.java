package com.example.MinusServer.Repositories;

import com.example.MinusServer.Models.OrderStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderStatusRepository extends CrudRepository<OrderStatus, Long> {
//    OrderStatus findByName(String Name);
//    Optional<OrderStatus> findByName(String Name);
}
