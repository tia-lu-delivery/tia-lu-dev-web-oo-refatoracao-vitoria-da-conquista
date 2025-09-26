package br.edu.unex.tiaLuDelivery.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class OrderItem {

    private final Order order;
    private final MenuItem menuItem;
    private int amount;

    public OrderItem(final Order order, final MenuItem menuItem, int amount) {
        this.order = order;
        this.menuItem = menuItem;
        setAmount(amount);
    }

    public BigDecimal subtotal() {
        return menuItem.getUnitPrice()
                .multiply(BigDecimal.valueOf(amount))
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    public Order getOrder() {
        return order;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {

        if(amount <= 0)
            throw new IllegalArgumentException("You must order at least one unit.");

        if(amount > this.getMenuItem().getAmount())
            throw new IllegalArgumentException("Insufficient stock to sell that amount");

        this.amount = amount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OrderItem orderItem = (OrderItem) object;
        return amount == orderItem.amount && Objects.equals(order, orderItem.order) && Objects.equals(menuItem, orderItem.menuItem);
    }

    @Override
    public String toString() {
        return "br.com.unex.tiaLuDelivery.model.OrderItem{" +
                "order=" + order +
                ", menuItem=" + menuItem +
                ", amount=" + amount +
                '}';
    }
}
