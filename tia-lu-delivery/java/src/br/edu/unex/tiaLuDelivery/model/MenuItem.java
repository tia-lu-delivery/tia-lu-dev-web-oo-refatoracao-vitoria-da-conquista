package br.edu.unex.tiaLuDelivery.model;

import java.math.BigDecimal;
import java.util.Objects;

public class MenuItem {

    private static long sequence = 0;

    private final long id;
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private int amount;

    public MenuItem(final String name, final BigDecimal unitPrice, final int amount) {
        this.id = ++sequence;
        this.name = name;
        this.unitPrice = unitPrice;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        if(unitPrice.compareTo(BigDecimal.ZERO) <= 0)
            throw  new IllegalArgumentException("Menu Item price cannot be zero or negative");
        this.unitPrice = unitPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        if(amount < 0)
            throw  new IllegalArgumentException("Available stock must be non-negative");
        this.amount = amount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MenuItem menuItem = (MenuItem) object;
        return id == menuItem.id && Objects.equals(name, menuItem.name) && Objects.equals(description, menuItem.description) && Objects.equals(unitPrice, menuItem.unitPrice);
    }

    @Override
    public String toString() {
        return "br.com.unex.tiaLuDelivery.model.MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
