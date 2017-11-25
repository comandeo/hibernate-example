package me.mitja.example.api;

import me.mitja.example.domain.PaymentProfile;

import java.util.ArrayList;
import java.util.List;

public class PaymentProfileApiImpl implements PaymentProfileApi {
    @Override
    public List<PaymentProfile> getAll() {
        return new ArrayList<>();
    }
}
