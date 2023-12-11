package com.app.clubmatrix.gui.windows.manager.dialogs;

import com.app.clubmatrix.gui.components.Button;
import com.app.clubmatrix.gui.components.Dialog;
import com.app.clubmatrix.gui.components.SalaryInput;
import com.app.clubmatrix.services.EmployeeService;
import com.app.clubmatrix.services.MemberService;
import com.app.clubmatrix.services.OrderService;
import com.app.clubmatrix.services.dto.OrderRegistrationDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddOrderDialog extends Dialog {

    private final OrderService orderService;
    private final MemberService memberService;
    private final EmployeeService employeeService;

    private JTextField amountField;
    private JComboBox<String> buyerField;
    private JComboBox<String> sellerField;
    private JButton saveButton;
    private JButton cancelButton;

    public AddOrderDialog(JFrame parent, OrderService orderService, MemberService memberService, EmployeeService employeeService) {
        super(parent, "Add Activity");
        this.orderService = orderService;
        this.memberService = memberService;
        this.employeeService = employeeService;
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        amountField = new SalaryInput(20);
        buyerField = new JComboBox<>(memberService.getAllMembers().stream().map(m -> m.getUser().getUsername()).toArray(String[]::new));
        sellerField = new JComboBox<>(employeeService.getAllEmployees().stream().map(e -> e.getUser().getUsername()).toArray(String[]::new));

        saveButton = new Button("Save", this::saveActivity);
        cancelButton = new Button("Cancel", e -> setVisible(false));
    }

    private void setupLayout() {
        addComponent(new JLabel("Amount:"), 0, 0, 1);
        addComponent(amountField, 1, 0, 2);
        addComponent(new JLabel("Buyer:"), 0, 1, 1);
        addComponent(buyerField, 1, 1, 2);
        addComponent(new JLabel("Seller:"), 0, 2, 1);
        addComponent(sellerField, 1, 2, 2);
        addComponent(saveButton, 0, 4, 1);
        addComponent(cancelButton, 1, 4, 2);

        add(panel);
        pack();
    }

    private void saveActivity(ActionEvent event) {
        OrderRegistrationDTO registrationDTO = new OrderRegistrationDTO(
                Integer.parseInt(amountField.getText().replaceAll("\\D+", "")),
                (String) buyerField.getSelectedItem(),
                (String) sellerField.getSelectedItem()
        );

        try {
            orderService.registerOrder(registrationDTO);
            JOptionPane.showMessageDialog(this, "Order added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(false);
    }
}