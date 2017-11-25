package me.mitja.example.api;

import me.mitja.example.domain.PaymentProfile;

import java.util.List;

public interface PaymentProfileApi {
    List<PaymentProfile> getAll();
}
