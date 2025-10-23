package br.edu.unex.tiaLuDelivery.model.state;

import br.edu.unex.tiaLuDelivery.model.Order;
import br.edu.unex.tiaLuDelivery.model.OrderStatus;

public class AcceptedState implements OrderState {
    @Override
    public OrderStatus status() { return OrderStatus.ACCEPTED; }

    @Override
    public void startPreparation(Order o) { o.setState(new PreparingState()); }

    @Override
    public void cancel(Order o) { o.setState(new CancelledState()); }
}
