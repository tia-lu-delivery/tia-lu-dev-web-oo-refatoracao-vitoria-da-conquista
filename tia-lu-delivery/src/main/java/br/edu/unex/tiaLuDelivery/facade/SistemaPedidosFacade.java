package br.edu.unex.tiaLuDelivery.facade;

import br.edu.unex.tiaLuDelivery.model.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class SistemaPedidosFacade {

    private static SistemaPedidosFacade instance;

    private final Map<Long, Customer> customers = new HashMap<>();
    private final Map<Long, MenuItem> menuItems = new HashMap<>();
    private final Map<Long, Order> orders = new HashMap<>();

    private final AtomicLong customerSeq = new AtomicLong(1);
    private final AtomicLong menuItemSeq = new AtomicLong(1);
    private final AtomicLong orderSeq = new AtomicLong(1);

    private SistemaPedidosFacade() {}

    public static synchronized SistemaPedidosFacade getInstance() {
        if (instance == null) {
            instance = new SistemaPedidosFacade();
        }
        return instance;
    }

    public Customer registerCustomer(String name) {
        long id = customerSeq.getAndIncrement();
        Customer c = new Customer(name);
        customers.put(id, c);
        return c;
    }

    public Optional<Customer> findCustomerById(long id) {
        return Optional.ofNullable(customers.get(id));
    }

    public List<Customer> listCustomers() {
        return new ArrayList<>(customers.values());
    }

    public MenuItem addMenuItem(String name, BigDecimal unitPrice, int amount) {
        MenuItem mi = new MenuItem(name, unitPrice, amount);
        long id = menuItemSeq.getAndIncrement();
        menuItems.put(id, mi);
        return mi;
    }

    public Optional<MenuItem> findMenuItemById(long id) {
        return Optional.ofNullable(menuItems.get(id));
    }

    public List<MenuItem> listMenuItems() {
        return new ArrayList<>(menuItems.values());
    }

    public Order createOrder(Customer customer) {
        Order order = new Order(customer);
        long id = orderSeq.getAndIncrement();
        orders.put(id, order);
        return order;
    }

    public Optional<Order> findOrderById(long id) {
        return Optional.ofNullable(orders.get(id));
    }

    public List<Order> listOrders() {
        return new ArrayList<>(orders.values());
    }

    public OrderItem addItemToOrder(Order order, MenuItem menuItem, int amount) {
        if (order == null) throw new IllegalArgumentException("Order is null");
        if (menuItem == null) throw new IllegalArgumentException("MenuItem is null");
        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");

        OrderItem oi = new OrderItem(order, menuItem, amount);
        order.addItem(oi);
        return oi;
    }

    public void changeOrderStatus(Order order, OrderStatus newStatus) {
        if (order == null) throw new IllegalArgumentException("Order is null");
        if (newStatus == null) throw new IllegalArgumentException("Status is null");
        order.setStatus(newStatus);
    }

    public boolean removeMenuItem(long id) {
        return menuItems.remove(id) != null;
    }

    public boolean removeCustomer(long id) {
        return customers.remove(id) != null;
    }

    public boolean removeOrder(long id) {
        return orders.remove(id) != null;
    }
}
