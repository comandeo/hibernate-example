package me.mitja.example.persistence;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "address")
class AddressEntity {

    @Id
    String id;

    String firstName;

    String lastName;

    String streetAddress;

    String city;

    String postalCode;

    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    List<PaymentProfileEntity> paymentProfiles;
}
