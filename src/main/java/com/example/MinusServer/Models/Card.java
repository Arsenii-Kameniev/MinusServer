package com.example.MinusServer.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Card")
public class Card {
    @Id
    @GeneratedValue
    private Integer Id;
    private String Company;
    private String Data;
    @Column(name="NumberOfCard")
    private String number;
    @Column(name="PersonName")
    private String Name;
    @Column(name="PersonSurname")
    private String Surname;
    private String CVV;
    public Card(int id, String company, String data, String number1, String name, String surname, String cvv){
        this.Id=id;
        this.Company=company;
        this.Data=data;
        this.number=number1;
        this.Name=name;
        this.Surname=surname;
        this.CVV=cvv;
    }
    public Card(){

    }
    @OneToMany(mappedBy = "Card")
    private List<MyUser> users = new ArrayList<>();

    public String getCVV() {
        return CVV;
    }

    public String getCompany() {
        return Company;
    }

    public String getData() {
        return Data;
    }

    public String getName() {
        return Name;
    }

    public String getNumber() {
        return number;
    }

    public String getSurname() {
        return Surname;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public void setData(String data) {
        Data = data;
    }

    public void setNumber(String number1) {
        number = number1;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public void setCVV(String cvv) {
        CVV = cvv;
    }
    public String getInfo(){
        String s;
        s = "Company: "+Company+". Data: " + Data + ". Number: "+number+ ". And Full name is " +Name+" "+Surname+". CVV is "+CVV;
        return s;
    }

    public Integer getId() {
        return Id;
    }
}
