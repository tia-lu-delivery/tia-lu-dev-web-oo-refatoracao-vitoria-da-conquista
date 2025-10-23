package br.edu.unex.tiaLuDelivery.model.state;

import br.edu.unex.tiaLuDelivery.model.OrderStatus;

public class DeliveredState implements OrderState {
    @Override
    public OrderStatus status() { return OrderStatus.DELIVERED; }
}
