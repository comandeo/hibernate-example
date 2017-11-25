package me.mitja.example;

import me.mitja.example.api.PaymentProfileApi;
import me.mitja.example.api.PaymentProfileApiImpl;
import me.mitja.example.domain.Address;
import me.mitja.example.domain.PaymentMethod;
import me.mitja.example.domain.PaymentMethodType;
import me.mitja.example.domain.PaymentProfile;
import me.mitja.example.persistence.PaymentProfileRepository;
import me.mitja.example.persistence.PaymentProfileRepositoryImpl;
import me.mitja.example.service.PaymentProfileService;
import me.mitja.example.service.PaymentProfileServiceImpl;
import me.mitja.example.ui.MainScene;
import me.mitja.example.ui.MainWindow;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class App {

    private final PaymentProfileRepository repository;
    private final SessionFactory sessionFactory;
    private final PaymentProfileApi api;
    private final PaymentProfileService service;
    private final MainScene mainScene;
    private final MainWindow mainWindow;

    private App() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
            throw new IllegalStateException(e);
        }
        repository = new PaymentProfileRepositoryImpl(sessionFactory);
        mainWindow = new MainWindow();
        api = new PaymentProfileApiImpl();
        service = new PaymentProfileServiceImpl(repository, api);
        mainScene = new MainScene(service, mainWindow);
    }

    private void start() {
        fillDb();
        mainScene.present();
        mainWindow.setVisible(true);
    }

    private void fillDb() {
        Address address = new Address(
                "address1",
                "John",
                "Snow",
                "Main Street",
                "Winterfell",
                "12345"
        );
        PaymentMethod paymentMethod = new PaymentMethod("paymentMethod1", "Main card", PaymentMethodType.CREDIT_CARD);
        PaymentProfile paymentProfile = new PaymentProfile(
                "profile1",
                "**** **** **** 1234",
                address,
                new ArrayList<>(Collections.singletonList(paymentMethod))
        );
        repository.create(paymentProfile);
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.start();
    }
}
