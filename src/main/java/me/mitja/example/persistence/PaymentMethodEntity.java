package me.mitja.example.persistence;

import javax.persistence.*;

@Entity
@Table(name = "payment_method")
class PaymentMethodEntity {

    @Id
    String id;

    String description;

    String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_method_id")
    PaymentProfileEntity paymentProfile;
}
