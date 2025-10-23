package br.edu.unex.tiaLuDelivery.model.state;

import br.edu.unex.tiaLuDelivery.model.Order;
import br.edu.unex.tiaLuDelivery.model.OrderStatus;

// Simple State pattern interface for Order
public interface OrderState {
    OrderStatus status();

    default void accept(Order o) { invalid("accept"); }
    default void reject(Order o) { invalid("reject"); }
    default void startPreparation(Order o) { invalid("start preparation"); }
    default void finishPreparation(Order o) { invalid("finish preparation"); }
    default void dispatch(Order o) { invalid("dispatch"); }
    default void cancel(Order o) { invalid("cancel"); }

    private void invalid(String action) {
        throw new IllegalStateException("It's not possible to " + action + " when the order is '" + status() + "'.");
    }
}
