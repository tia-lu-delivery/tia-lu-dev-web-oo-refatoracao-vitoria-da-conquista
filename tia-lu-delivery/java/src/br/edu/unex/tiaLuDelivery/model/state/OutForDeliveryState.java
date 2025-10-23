package br.edu.unex.tiaLuDelivery.model.state;

import br.edu.unex.tiaLuDelivery.model.Order;
import br.edu.unex.tiaLuDelivery.model.OrderStatus;

public class OutForDeliveryState implements OrderState {
    @Override
    public OrderStatus status() { return OrderStatus.OUT_FOR_DELIVERY; }

    @Override
    public void cancel(Order o) { o.setState(new CancelledState()); }
}
