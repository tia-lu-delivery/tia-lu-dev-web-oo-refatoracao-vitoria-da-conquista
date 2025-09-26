package br.edu.unex.tiaLuDelivery.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {

    private static long sequence = 0;

    private final long id;
    private String name;
    private String phoneNumber;
    private final List<Order> orders;

    public Customer(final String name) {
        this.id = ++sequence;
        this.name = name;
        this.orders = new ArrayList<>();
    }

    public Customer(final String name, final String phoneNumber) {
        this(name);
        this.phoneNumber = phoneNumber;
    }

    public Order createOrder() {
        Order order = new Order(this);
        this.orders.add(order);
        return order;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Customer customer = (Customer) object;
        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(phoneNumber, customer.phoneNumber) && Objects.equals(orders, customer.orders);
    }

    @Override
    public String toString() {
        return "br.com.unex.tiaLuDelivery.model.Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", orders=" + orders +
                '}';
    }
}
