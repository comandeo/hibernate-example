package me.mitja.example.service;

import me.mitja.example.api.PaymentProfileApi;
import me.mitja.example.domain.PaymentProfile;
import me.mitja.example.persistence.PaymentProfileRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PaymentProfileServiceImpl implements PaymentProfileService {

    private final PaymentProfileRepository repository;

    private final PaymentProfileApi api;

    public PaymentProfileServiceImpl(PaymentProfileRepository repository, PaymentProfileApi api) {
        this.repository = repository;
        this.api = api;
    }

    @Override
    public CompletableFuture<List<PaymentProfile>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            List<PaymentProfile> apiResults = api.getAll();
            if (!apiResults.isEmpty()) {
                return apiResults;
            }
            return repository.getAll();
        });
    }
}
