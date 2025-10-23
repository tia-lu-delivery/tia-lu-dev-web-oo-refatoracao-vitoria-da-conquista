package br.edu.unex.tiaLuDelivery.model.state;

import br.edu.unex.tiaLuDelivery.model.Order;
import br.edu.unex.tiaLuDelivery.model.OrderStatus;

public class PreparingState implements OrderState {
    @Override
    public OrderStatus status() { return OrderStatus.PREPARING; }

    @Override
    public void finishPreparation(Order o) { o.setState(new WaitingForCourierState()); }

    @Override
    public void cancel(Order o) { o.setState(new CancelledState()); }
}
