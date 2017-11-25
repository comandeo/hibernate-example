package me.mitja.example.service;

import me.mitja.example.domain.PaymentProfile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PaymentProfileService {
    CompletableFuture<List<PaymentProfile>> getAll();
}
