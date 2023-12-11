package com.app.clubmatrix.gui.windows.manager.dialogs;

import com.app.clubmatrix.gui.components.Button;
import com.app.clubmatrix.gui.components.Dialog;
import com.app.clubmatrix.gui.components.SalaryInput;
import com.app.clubmatrix.models.Employee;
import com.app.clubmatrix.models.Member;
import com.app.clubmatrix.models.Order;
import com.app.clubmatrix.services.EmployeeService;
import com.app.clubmatrix.services.MemberService;
import com.app.clubmatrix.services.OrderService;
import com.app.clubmatrix.services.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditOrderDialog extends Dialog {

    private final OrderService orderService;
    private final MemberService memberService;
    private final EmployeeService employeeService;
    private final UserService userService;
    private final Order order;

    private JTextField amountField;
    private JComboBox<String> buyerField;
    private JComboBox<String> sellerField;
    private JButton saveButton;
    private JButton cancelButton;

    public EditOrderDialog(JFrame parent, OrderService orderService, MemberService memberService, EmployeeService employeeService, UserService userService, Order order) {
        super(parent, "Add Activity");
        this.orderService = orderService;
        this.memberService = memberService;
        this.employeeService = employeeService;
        this.userService = userService;
        this.order = order;
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

    private void loadOrderData() {
        amountField.setText(String.valueOf(order.getAmount()));
        buyerField.setSelectedItem(order.getMember().getUser().getUsername());
        sellerField.setSelectedItem(order.getEmployee().getUser().getUsername());
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
        order.setAmount(Integer.parseInt(amountField.getText().replaceAll("\\D", "")));

        Member buyer = userService.getUserByUsername((String) buyerField.getSelectedItem()).getMember();
        if (buyer == null) {
            JOptionPane.showMessageDialog(this, "Invalid buyer", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Employee seller = userService.getUserByUsername((String) sellerField.getSelectedItem()).getEmployee();
        if (seller == null) {
            JOptionPane.showMessageDialog(this, "Invalid seller", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        order.setMember(buyer);
        order.setEmployee(seller);

        try {
            orderService.updateOrder(order);
            JOptionPane.showMessageDialog(this, "Order added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(false);
    }
}