package me.mitja.example.domain;

public enum PaymentMethodType {
    PAYPAL("paypal"),
    CREDIT_CARD("credit_card"),
    UNKNOWN("unknown");

    private final String type;

    PaymentMethodType(String type) {
        this.type = type;
    }
}
