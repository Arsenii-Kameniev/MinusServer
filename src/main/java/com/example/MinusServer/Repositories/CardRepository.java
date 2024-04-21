package com.example.MinusServer.Repositories;

import com.example.MinusServer.Models.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CardRepository extends CrudRepository<Card, Long> {
    Optional<Card> findByNumber(String Number);
}
