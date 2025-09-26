package br.edu.unex.tiaLuDelivery.model;

public enum OrderStatus {
    AWAITING_MERCHANT_ACCEPTANCE,
    ACCEPTED,
    PREPARING,
    DONE,
    WAITING_FOR_COURIER,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELED,
    REJECTED
}
