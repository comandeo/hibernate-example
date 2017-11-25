package me.mitja.example.ui;

import me.mitja.example.service.PaymentProfileService;

import java.awt.*;

public class MainScene {

    private final MainViewModel model;

    private final MainViewController controller;

    private final MainView view;

    private final MainWindow mainWindow;

    public MainScene(PaymentProfileService service, MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        model = new MainViewModel(service);
        view = new MainView(model);
        controller = new MainViewController(view, model);
    }

    public void present() {
        mainWindow.setContentPane(view.mainPanel);
        mainWindow.pack();
    }
}
