package me.mitja.example.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PaymentProfile {
    private final String id;

    private final String description;

    private final Address address;

    private List<PaymentMethod> paymentMethods;

    public PaymentProfile(String id, String description, Address address, List<PaymentMethod> paymentMethods) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.paymentMethods = paymentMethods;
    }

    public void addPaymentMethod(PaymentMethod paymentMethod) {
        paymentMethods.add(paymentMethod);
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Address getAddress() {
        return address;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return Collections.unmodifiableList(paymentMethods);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentProfile that = (PaymentProfile) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
