package me.mitja.example.domain;

import java.util.Objects;

public class PaymentMethod {
    private final String id;

    private final String description;

    private final PaymentMethodType type;

    public PaymentMethod(String id, String description, PaymentMethodType type) {
        this.id = id;
        this.description = description;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public PaymentMethodType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethod that = (PaymentMethod) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
