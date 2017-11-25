package me.mitja.example.persistence;

import me.mitja.example.domain.Address;
import me.mitja.example.domain.PaymentMethod;
import me.mitja.example.domain.PaymentMethodType;
import me.mitja.example.domain.PaymentProfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentProfileRepositoryImpl implements PaymentProfileRepository {

    private final SessionFactory sessionFactory;

    public PaymentProfileRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<PaymentProfile> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        TypedQuery<PaymentProfileEntity> query = session.createQuery("from PaymentProfileEntity", PaymentProfileEntity.class);
        List<PaymentProfileEntity> resultList = query.getResultList();
        transaction.commit();
        return resultList.stream().map(paymentProfileEntity -> {
            Address address = new Address(
                    paymentProfileEntity.address.id,
                    paymentProfileEntity.address.firstName,
                    paymentProfileEntity.address.lastName,
                    paymentProfileEntity.address.streetAddress,
                    paymentProfileEntity.address.city,
                    paymentProfileEntity.address.postalCode
            );
            List<PaymentMethod> paymentMethods = paymentProfileEntity.paymentMethods.stream().map(paymentMethodEntity -> {
                PaymentMethodType type = PaymentMethodType.UNKNOWN;
                switch (paymentMethodEntity.type) {
                    case "paypal":
                        type = PaymentMethodType.PAYPAL;
                        break;
                    case "credit_card":
                        type = PaymentMethodType.CREDIT_CARD;
                }
                return new PaymentMethod(
                        paymentMethodEntity.id,
                        paymentMethodEntity.description,
                        type

                );
            }).collect(Collectors.toList());
            return new PaymentProfile(paymentProfileEntity.id, paymentProfileEntity.description, address, paymentMethods);
        }).collect(Collectors.toList());
    }

    @Override
    public void create(PaymentProfile paymentProfile) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        PaymentProfileEntity paymentProfileEntity = new PaymentProfileEntity();
        paymentProfileEntity.id = paymentProfile.getId();
        paymentProfileEntity.description = paymentProfile.getDescription();
        if (paymentProfile.getAddress() != null) {
            AddressEntity addressEntity = new AddressEntity();
            addressEntity.id = paymentProfile.getAddress().getId();
            addressEntity.firstName = paymentProfile.getAddress().getFirstName();
            addressEntity.lastName = paymentProfile.getAddress().getLastName();
            addressEntity.streetAddress = paymentProfile.getAddress().getStreetAddress();
            addressEntity.city = paymentProfile.getAddress().getCity();
            addressEntity.postalCode = paymentProfile.getAddress().getPostalCode();
            session.save(addressEntity);
            paymentProfileEntity.address = addressEntity;
        }
        paymentProfileEntity.paymentMethods = paymentProfile.getPaymentMethods().stream().map(paymentMethod -> {
            PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
            paymentMethodEntity.id = paymentMethod.getId();
            paymentMethodEntity.type = paymentMethod.getType().name();
            paymentMethodEntity.description = paymentMethod.getDescription();
            session.save(paymentMethodEntity);
            return paymentMethodEntity;
        }).collect(Collectors.toList());
        session.save(paymentProfileEntity);
        transaction.commit();
    }
}
