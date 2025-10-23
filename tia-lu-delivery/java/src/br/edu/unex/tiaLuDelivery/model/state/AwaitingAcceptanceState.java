package br.edu.unex.tiaLuDelivery.model.state;

import br.edu.unex.tiaLuDelivery.model.Order;
import br.edu.unex.tiaLuDelivery.model.OrderStatus;

public class AwaitingAcceptanceState implements OrderState {
    @Override
    public OrderStatus status() { return OrderStatus.AWAITING_MERCHANT_ACCEPTANCE; }

    @Override
    public void accept(Order o) { o.setState(new AcceptedState()); }

    @Override
    public void reject(Order o) { o.setState(new RejectedState()); }

    @Override
    public void cancel(Order o) { o.setState(new CancelledState()); }
}
