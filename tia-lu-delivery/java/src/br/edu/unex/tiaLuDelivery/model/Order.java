package br.edu.unex.tiaLuDelivery.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {

    private static long sequence = 0;

    private final long id;
    private final Customer customer;
    private final List<OrderItem> items;
    private OrderStatus status;

    public Order(final Customer customer){
        this.id = ++sequence;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.AWAITING_MERCHANT_ACCEPTANCE;
    }

    public void addItem(final MenuItem menuItem, final int amount) {
        if(menuItem == null)
            throw new IllegalArgumentException("The selected menu item can not be empty or null");
        items.add(new OrderItem(this, menuItem, amount));
    }

    public void updateItemAmount(final MenuItem menuItem, final int amount) {
        this.items.stream()
                .filter( item -> item.getMenuItem().equals(menuItem) )
                .findFirst()
                .ifPresent(orderItem -> orderItem.setAmount(amount));
    }

    public BigDecimal total() {
        return this.items.stream()
                .map(OrderItem::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Customer getCustomer() {
        return customer;
    }

    public long getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Order order = (Order) object;
        return id == order.id && Objects.equals(items, order.items) && status == order.status;
    }

    @Override
    public String toString() {
        return "br.com.unex.tiaLuDelivery.model.Order{" +
                "id=" + id +
                ", items=" + items +
                ", status=" + status +
                '}';
    }
}
