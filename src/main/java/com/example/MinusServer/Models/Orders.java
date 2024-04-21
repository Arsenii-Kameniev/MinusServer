package com.example.MinusServer.Models;

import jakarta.persistence.*;

@Entity
@Table(name="Orders")
public class Orders {
    @Id
    @GeneratedValue
    private Integer Id;
    private Double Payment;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name="order_status_id")
    private OrderStatus status;
    public Orders(Integer id, Double payment, OrderStatus statusX, Product productX){
        this.Id=id;
        this.Payment=payment;
        this.status=statusX;
        this.product=productX;
    }
    public Orders(){

    }
    public void setProduct(Product productX){
        this.product=productX;
    }
    public Product getProduct(){
        return product;
    }
    public void setPayment(Double payment) {
        Payment = payment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Double getPayment() {
        return Payment;
    }

    public void setStatus(OrderStatus statusX) {
        this.status = statusX;
    }

    public Integer getId() {
        return Id;
    }
}
