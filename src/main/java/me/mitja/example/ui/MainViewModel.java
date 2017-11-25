package me.mitja.example.ui;

import me.mitja.example.service.PaymentProfileService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

class MainViewModel extends Observable {

    class PaymentProfileModel {
        final String id;

        final String description;

        final String firstName;

        final String lastName;

        PaymentProfileModel(String id, String description, String firstName, String lastName) {
            this.id = id;
            this.description = description;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    private final PaymentProfileService service;

    private List<PaymentProfileModel> paymentProfiles = new ArrayList<>();

    MainViewModel(PaymentProfileService service) {
        this.service = service;
    }

    void refresh() {
        service.getAll().thenAccept(paymentProfiles -> {
            this.paymentProfiles = paymentProfiles.stream().map(paymentProfile -> {
                return new PaymentProfileModel(
                        paymentProfile.getId(),
                        paymentProfile.getDescription(),
                        paymentProfile.getAddress() == null ? null : paymentProfile.getAddress().getFirstName(),
                        paymentProfile.getAddress() == null ? null : paymentProfile.getAddress().getLastName()
                );
            }).collect(Collectors.toList());
            setChanged();
            notifyObservers();
        });
    }

    public List<PaymentProfileModel> getPaymentProfiles() {
        return Collections.unmodifiableList(paymentProfiles);
    }

}
