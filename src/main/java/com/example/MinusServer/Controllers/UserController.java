package com.example.MinusServer.Controllers;

import com.example.MinusServer.Classes.*;
import com.example.MinusServer.Models.Card;

import com.example.MinusServer.Models.MyUser;

import com.example.MinusServer.Repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository UserRepos;
    @GetMapping("/getUser")
    public ResponseEntity<MyUser> getUser(@RequestBody long id){
        Optional<MyUser> myUser =  UserRepos.findById(id);
        if(myUser.isEmpty()){
            return new ResponseEntity<MyUser>(myUser.get(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<MyUser>(myUser.get(), HttpStatus.OK);
    }
    @PostMapping("/checkUser")
    public ResponseEntity<MyUser> checkUser(@RequestBody UserClass userClass){
        Optional<MyUser> myUser = UserRepos.findByNickName(userClass.getName());
        if(myUser.isEmpty()){
            MyUser user = new MyUser();
            return new ResponseEntity<MyUser>(user, HttpStatus.BAD_REQUEST);
        }
        MyUser user = myUser.get();
        if(user.getPassword().equals(userClass.getPassword())){
            return new ResponseEntity<MyUser>(user, HttpStatus.OK);
        }
        else {
            MyUser nullUser = new MyUser();
            return new ResponseEntity<MyUser>(nullUser, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/checkIfUserExist")
    public ResponseEntity<Boolean> checkIfUserExist(@RequestParam("name") String name){
        Optional<MyUser> myUser = UserRepos.findByNickName(name);
        if(myUser.isEmpty()){
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
    @PostMapping("/checkIf")
    public ResponseEntity<MyUser> checkIf(@RequestBody MyUser person){
        Optional<MyUser> myUser = UserRepos.findByNickName(person.getNickName());
        if(myUser.isEmpty()){
            postUser(person);
            return new ResponseEntity<MyUser>(person, HttpStatus.OK);
        }
        return new ResponseEntity<MyUser>(person, HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/getUserByNickName")
    public  ResponseEntity<MyUser> getUserByNickName(@RequestBody String nickName){
        Optional<MyUser> myUser = UserRepos.findByNickName(nickName);
        if(myUser.isEmpty()){
            return new ResponseEntity<MyUser>(myUser.get(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<MyUser>(myUser.get(), HttpStatus.OK);
    }
    @GetMapping("/getUserForCheck")
    public ResponseEntity<ForUserCheck> getUserForCheck(@RequestBody long id){
        Optional<MyUser> user = UserRepos.findById(id);
        ForUserCheck checker = new ForUserCheck();
        if(user.isEmpty()){
            return new ResponseEntity<ForUserCheck>(checker, HttpStatus.BAD_REQUEST);
        }
        checker.setName(user.get().getNickName());
        checker.setPassword(user.get().getPassword());
        return new ResponseEntity<ForUserCheck>(checker, HttpStatus.OK);
    }
    @GetMapping("/postUserByNickName")
    public  ResponseEntity<MyUser> postUserByNickName(@RequestParam("name") String nickName){
        Optional<MyUser> myUser = UserRepos.findByNickName(nickName);

        if(myUser.isEmpty()){
            MyUser noUser = new MyUser();
            return new ResponseEntity<MyUser>(noUser, HttpStatus.BAD_REQUEST);
        }
        MyUser user = myUser.get();
        return new ResponseEntity<MyUser>(user, HttpStatus.OK);
    }
    @PostMapping("/postUser")
    public ResponseEntity<String> postUser(@RequestBody MyUser user){
        try {
            UserRepos.save(user);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Good", HttpStatus.OK);
    }
    @PatchMapping("/patchUser")
    public ResponseEntity<String> patchUser(@RequestBody PatchClass changer){
        try {
            Optional<MyUser> TestMyUser =  UserRepos.findById((long) changer.getId());
            if(TestMyUser.isEmpty()){
                return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
            }
            MyUser myUser = TestMyUser.get();
            changer.setType(changer.getType().toLowerCase());
            if(changer.getType().equals("level")){
                myUser.setLevel(Integer.valueOf(changer.getPatch()));
            }
            else if(changer.getType().equals("nickName")){
                myUser.setNickName(changer.getPatch());
            }
            else if(changer.getType().equals("password")){
                myUser.setPassword(changer.getPatch());
            }
            else if(changer.getType().equals("age")){
                myUser.setAge(Integer.valueOf(changer.getPatch()));
            }
            UserRepos.save(myUser);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Good", HttpStatus.OK);
    }
    @PutMapping("/putUser")
    public ResponseEntity<String> putUser(@RequestBody PutUserClass UserChanger){
        try {
            Optional<MyUser> TestChangeUser =  UserRepos.findById((long) UserChanger.getId());
            if(TestChangeUser.isEmpty()){
                return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
            }
            MyUser ChangeUser = TestChangeUser.get();
            ChangeUser.setAge(UserChanger.getUser().getAge());
            ChangeUser.setPassword(UserChanger.getUser().getPassword());
            ChangeUser.setNickName(UserChanger.getUser().getNickName());
            ChangeUser.setLevel(UserChanger.getUser().getLevel());
            ChangeUser.setCard(UserChanger.getUser().getCard());
            UserRepos.save(ChangeUser);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Good", HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody int id){
        try {
            Optional<MyUser> TestMyUser =  UserRepos.findById((long) id);
            if(TestMyUser.isEmpty()){
                return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
            }
            MyUser myUser = TestMyUser.get();
            UserRepos.delete(myUser);
        }catch (IndexOutOfBoundsException e){
            return new ResponseEntity<String>("Bad", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Good", HttpStatus.OK);
    }
}
