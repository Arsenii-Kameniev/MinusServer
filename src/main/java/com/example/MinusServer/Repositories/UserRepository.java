package com.example.MinusServer.Repositories;

import com.example.MinusServer.Models.MyUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<MyUser,Long> {

    Optional<MyUser> findByNickName(String NickName);

}
