package me.mitja.example.ui;

class MainViewController {

    private final MainView view;

    private final MainViewModel model;

    MainViewController(MainView view, MainViewModel model) {
        this.view = view;
        this.model = model;
        this.view.refreshButton.addActionListener(e -> {
            this.model.refresh();
        });
    }
}
