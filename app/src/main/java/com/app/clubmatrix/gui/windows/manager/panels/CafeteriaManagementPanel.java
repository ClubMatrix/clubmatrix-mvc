package com.app.clubmatrix.gui.windows.manager.panels;

import com.app.clubmatrix.gui.windows.manager.dialogs.AddOrderDialog;
import com.app.clubmatrix.gui.windows.manager.dialogs.EditOrderDialog;
import com.app.clubmatrix.gui.windows.manager.panels.models.OrderTable;
import com.app.clubmatrix.models.Order;
import com.app.clubmatrix.services.EmployeeService;
import com.app.clubmatrix.services.MemberService;
import com.app.clubmatrix.services.OrderService;
import com.app.clubmatrix.services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CafeteriaManagementPanel extends JPanel {

    private final OrderService orderService;
    private final MemberService memberService;
    private final EmployeeService employeeService;
    private final UserService userService;
    private JTable orderTable;

    public CafeteriaManagementPanel(OrderService orderService, MemberService memberService, EmployeeService employeeService, UserService userService) {
        this.orderService = orderService;
        this.memberService = memberService;
        this.employeeService = employeeService;
        this.userService = userService;
        initializePanel();
        loadOrders();
    }

    private void initializePanel() {
        setLayout(new BorderLayout());

        orderTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(orderTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Order");
        JButton editButton = new JButton("Edit Order");
        JButton deleteButton = new JButton("Delete Order");

        addButton.addActionListener(this::addOrder);
        editButton.addActionListener(this::editOrder);
        deleteButton.addActionListener(this::deleteOrder);

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadOrders() {
        List<Order> orders = orderService.getAllOrders();
        OrderTable orderTableModel = new OrderTable(orders);
        orderTable.setModel(orderTableModel);
    }

    private void addOrder(ActionEvent event) {
        AddOrderDialog addOrderDialog = new AddOrderDialog((JFrame) SwingUtilities.getWindowAncestor(this), orderService, memberService, employeeService);
        addOrderDialog.setVisible(true);
        loadOrders();
    }

    private void editOrder(ActionEvent event) {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow >= 0) {
            Order selectedOrder = ((OrderTable) orderTable.getModel()).getOrderAt(selectedRow);
            EditOrderDialog editOrderDialog = new EditOrderDialog((JFrame) SwingUtilities.getWindowAncestor(this), orderService, memberService, employeeService, userService, selectedOrder);
            editOrderDialog.setVisible(true);
            loadOrders(); // Reload orders to reflect changes
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order to edit.", "No Order Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteOrder(ActionEvent event) {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow >= 0) {
            Order selectedOrder = ((OrderTable) orderTable.getModel()).getOrderAt(selectedRow);

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete order: " + selectedOrder.getId() + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                orderService.deleteOrder(selectedOrder);
                loadOrders();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order to delete.", "No Order Selected", JOptionPane.WARNING_MESSAGE);
        }
    }
}