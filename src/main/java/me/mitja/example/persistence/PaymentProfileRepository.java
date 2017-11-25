package me.mitja.example.persistence;

import me.mitja.example.domain.PaymentProfile;

import java.util.List;

public interface PaymentProfileRepository {
    List<PaymentProfile> getAll();
    void create(PaymentProfile paymentProfile);
}
