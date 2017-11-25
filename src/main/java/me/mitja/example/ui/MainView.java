package me.mitja.example.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

class MainView extends JFrame implements Observer {
    JPanel mainPanel;
    JTable table;
    JToolBar toolBar;
    JButton refreshButton;

    private final MainViewModel model;
    private final DefaultTableModel tableModel;

    MainView(MainViewModel model) throws HeadlessException {
        this.model = model;
        String[] columnNames = {"Description", "First Name", "Last Name"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table.setModel(tableModel);
        model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        tableModel.setRowCount(0);
        model.getPaymentProfiles().forEach(paymentProfileModel -> {
            tableModel.addRow(new Object[]{
                    paymentProfileModel.description,
                    paymentProfileModel.firstName,
                    paymentProfileModel.lastName
            });
        });
    }
}
