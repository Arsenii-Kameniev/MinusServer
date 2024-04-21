package com.example.MinusServer.Models;


import jakarta.persistence.*;

@Entity
@Table(name="user")
public class MyUser {

    @Id
    @GeneratedValue
    private Integer Id;
    private Integer Level;
    private String nickName;
    private String Password;
    private Integer Age;

    public MyUser(Integer id, Integer level, String name, String password, Integer age, Card card){
        this.Id = id;
        this.Level = level;
        this.nickName = name;
        this.Password = password;
        this.Age = age;
        this.Card=card;
    }
    public MyUser(){

    }

    public void setLevel(Integer level) {
        Level = level;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public void setNickName(String name) {
        nickName = name;
    }
    public Integer getLevel(){
        return Level;

    }

    public Integer getAge() {
        return Age;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPassword() {
        return Password;
    }

    @ManyToOne
    @JoinColumn(name="card_Id")
    private Card Card;

    public void setCard(Card card) {
        this.Card = card;
    }

    public Card getCard() {
        return Card;
    }
    //    @Column(name="ISBN_NUMBER")
//    private String isbn;
//

}
