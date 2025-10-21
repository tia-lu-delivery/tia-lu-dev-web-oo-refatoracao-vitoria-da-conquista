package br.edu.unex.tiaLuDelivery.builder;

import java.util.ArrayList;
import java.util.List;

import br.edu.unex.tiaLuDelivery.model.Customer;
import br.edu.unex.tiaLuDelivery.model.MenuItem;
import br.edu.unex.tiaLuDelivery.model.Order;
import br.edu.unex.tiaLuDelivery.model.OrderItem;
import br.edu.unex.tiaLuDelivery.model.OrderStatus;

/**
 * Refatoração: Padrão Builder aplicado para construção de pedidos.
 * O Builder separa a lógica de construção do Pedido, tornando o processo seguro,
 * legível e sustentável para futuras evoluções do sistema.
 *
 * Exemplo:
 *   Order pedido = OrderBuilder.builder()
 *       .withCustomer(cliente)
 *       .addItem(menuItem1, 2)
 *       .addItem(menuItem2, 1)
 *       .build();
 */
public class OrderBuilder {
    private Customer customer;
    private final List<OrderItem> items = new ArrayList<>();

    // Construtor público para facilitar o uso por iniciantes
    public OrderBuilder() {}

    // Define o cliente do pedido
    public OrderBuilder setCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo!");
        }
        this.customer = customer;
        return this;
    }

    // Adiciona um item ao pedido
    public OrderBuilder addItem(MenuItem menuItem, int quantidade) {
        if (menuItem == null) {
            throw new IllegalArgumentException("Item não pode ser nulo!");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva!");
        }
        items.add(new OrderItem(null, menuItem, quantidade));
        return this;
    }

    // Cria o pedido
    public Order build() {
        if (customer == null) {
            throw new IllegalStateException("Cliente deve ser definido!");
        }
        if (items.isEmpty()) {
            throw new IllegalStateException("Adicione pelo menos um item!");
        }
        Order order = new Order(customer);
        for (OrderItem item : items) {
            order.addItem(item.getMenuItem(), item.getAmount());
        }
        order.setStatus(OrderStatus.ACCEPTED);
        return order;
    }
}
