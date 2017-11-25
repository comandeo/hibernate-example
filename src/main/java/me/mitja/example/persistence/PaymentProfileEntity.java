package me.mitja.example.persistence;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "payment_profile")
class PaymentProfileEntity {

    @Id
    String id;

    String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    AddressEntity address;

    @OneToMany(mappedBy = "paymentProfile", fetch = FetchType.EAGER)
    List<PaymentMethodEntity> paymentMethods;
}
