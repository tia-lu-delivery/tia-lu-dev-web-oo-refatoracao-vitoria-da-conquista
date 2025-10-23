package br.edu.unex.tiaLuDelivery.model.state;

import br.edu.unex.tiaLuDelivery.model.Order;
import br.edu.unex.tiaLuDelivery.model.OrderStatus;

public class WaitingForCourierState implements OrderState {
    @Override
    public OrderStatus status() { return OrderStatus.WAITING_FOR_COURIER; }

    @Override
    public void dispatch(Order o) { o.setState(new OutForDeliveryState()); }

    @Override
    public void cancel(Order o) { o.setState(new CancelledState()); }
}
