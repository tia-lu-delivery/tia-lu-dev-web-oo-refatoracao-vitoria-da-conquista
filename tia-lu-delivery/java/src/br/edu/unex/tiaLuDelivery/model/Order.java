package br.edu.unex.tiaLuDelivery.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import br.edu.unex.tiaLuDelivery.model.state.*;

public class Order {

    private static long sequence = 0;

    private final long id;
    private final Customer customer;
    private final List<OrderItem> items;
    private OrderState state;

    public Order(final Customer customer){
        this.id = ++sequence;
        this.customer = customer;
        this.items = new ArrayList<>();
    this.state = new AwaitingAcceptanceState();
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

    public OrderStatus getStatus() { return state.status(); }

    public void setStatus(OrderStatus status) {
        // útil para testes ou ajustes manuais
        switch (status) {
            case AWAITING_MERCHANT_ACCEPTANCE -> this.state = new AwaitingAcceptanceState();
            case ACCEPTED -> this.state = new AcceptedState();
            case PREPARING -> this.state = new PreparingState();
            case DONE -> this.state = new WaitingForCourierState();
            case WAITING_FOR_COURIER -> this.state = new WaitingForCourierState();
            case OUT_FOR_DELIVERY -> this.state = new OutForDeliveryState();
            case DELIVERED -> this.state = new DeliveredState();
            case CANCELED -> this.state = new CancelledState();
            case REJECTED -> this.state = new RejectedState();
            default -> this.state = new AwaitingAcceptanceState();
        }
    }

    // Simple transition methods delegating to current state
    public void accept() { state.accept(this); }
    public void reject() { state.reject(this); }
    public void startPreparation() { state.startPreparation(this); }
    public void finishPreparation() { state.finishPreparation(this); }
    public void dispatch() { state.dispatch(this); }
    public void cancel() { state.cancel(this); }

    public OrderState getState() { return state; }
    public void setState(OrderState next) { this.state = next; }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Order order = (Order) object;
        return id == order.id && Objects.equals(items, order.items) && getStatus() == order.getStatus();
    }

    @Override
    public String toString() {
        return "br.com.unex.tiaLuDelivery.model.Order{" +
                "id=" + id +
                ", items=" + items +
                ", status=" + getStatus() +
                '}';
    }
}
