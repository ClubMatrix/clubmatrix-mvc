package com.app.clubmatrix.gui.windows.authentication;

import com.app.clubmatrix.gui.windows.authentication.panels.LoginPanel;
import com.app.clubmatrix.gui.windows.authentication.panels.RegistrationPanel;
import com.app.clubmatrix.services.AuthService;
import com.app.clubmatrix.services.EmployeeService;
import com.app.clubmatrix.services.MemberService;
import com.app.clubmatrix.services.dto.CredentialsDTO;
import com.app.clubmatrix.services.dto.EmployeeRegistrationDTO;
import com.app.clubmatrix.services.dto.MemberRegistrationDTO;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class AuthenticationWindow extends JPanel {

    private final AuthService authService;
    private final MemberService memberService;
    private final EmployeeService employeeService;
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private final Consumer<String> onLoginSuccessful;

    public AuthenticationWindow(AuthService authService, MemberService memberService, EmployeeService employeeService, Consumer<String> onLoginSuccessful) {
        this.authService = authService;
        this.memberService = memberService;
        this.employeeService = employeeService;
        this.onLoginSuccessful = onLoginSuccessful;

        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        LoginPanel loginPanel = new LoginPanel(this::onLoginAttempt);
        RegistrationPanel registrationPanel = new RegistrationPanel(this::onMemberRegistration, this::onEmployeeRegistration);

        cardPanel.add(loginPanel, "Login");
        cardPanel.add(registrationPanel, "Register");

        add(getPanel(), BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
    }

    private JPanel getPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton switchToRegisterButton = new JButton("Sign Up");
        switchToRegisterButton.addActionListener(e -> cardLayout.show(cardPanel, "Register"));
        buttonPanel.add(switchToRegisterButton);

        JButton switchToLoginButton = new JButton("Sign In");
        switchToLoginButton.addActionListener(e -> cardLayout.show(cardPanel, "Login"));
        buttonPanel.add(switchToLoginButton);
        return buttonPanel;
    }

    private void onLoginAttempt(CredentialsDTO credentials) {
        try {
            if (!authService.authenticateUser(credentials)) {
                System.out.println("Login failed.");
                JOptionPane.showMessageDialog(this, "Login failed. Please check your credentials and try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);

                return;
            }

            System.out.println("Login successful.");
            JOptionPane.showMessageDialog(this, "Login successful.", "Success", JOptionPane.INFORMATION_MESSAGE);

            onLoginSuccessful.accept(credentials.getUsername());
        } catch (Exception e) {
            System.err.println("An error occurred while trying to log in: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "An error occurred while trying to log in.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onMemberRegistration(MemberRegistrationDTO memberRegistrationDTO) {
        try {
            memberService.registerMember(memberRegistrationDTO);
            System.out.println("Registration successful.");
            JOptionPane.showMessageDialog(this, "Registration successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.err.println("An error occurred while trying to register: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "An error occurred while trying to register.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onEmployeeRegistration(EmployeeRegistrationDTO employeeRegistrationDTO) {
        try {
            employeeService.registerEmployee(employeeRegistrationDTO);
            System.out.println("Registration successful.");
            JOptionPane.showMessageDialog(this, "Registration successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.err.println("An error occurred while trying to register: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "An error occurred while trying to register.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}