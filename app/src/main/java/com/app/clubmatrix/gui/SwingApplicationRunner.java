package com.app.clubmatrix.gui;

import com.app.clubmatrix.gui.windows.authentication.AuthenticationWindow;
import com.app.clubmatrix.gui.windows.manager.ManagerWindow;
import com.app.clubmatrix.gui.windows.member.MemberWindow;
import com.app.clubmatrix.models.PositionType;
import com.app.clubmatrix.models.User;
import com.app.clubmatrix.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class SwingApplicationRunner
  extends java.awt.Component
  implements CommandLineRunner {

  @Autowired
  UserService userService;

  @Autowired
  AuthService authService;

  @Autowired
  MemberService memberService;

  @Autowired
  EmployeeService employeeService;

  @Autowired
  ActivityService activityService;

  @Autowired
  OrderService orderService;

  @Autowired
  DependentService dependentService;

  private CardLayout cardLayout;
  private JPanel cardPanel;
  private MemberWindow memberWindow;

  @Override
  public void run(String... args) {
    SwingUtilities.invokeLater(this::createAndShowGUI);
  }

  private void createAndShowGUI() {
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("Running in headless mode.");
      return;
    }

    JFrame frame = new JFrame("ClubMatrix");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    cardLayout = new CardLayout();
    cardPanel = new JPanel(cardLayout);

    cardPanel.add(
      new AuthenticationWindow(
        authService,
        memberService,
        employeeService,
        this::onSuccessfulLogin
      ),
      "Authentication"
    );
    cardPanel.add(
      new ManagerWindow(
        memberService,
        employeeService,
        activityService,
        orderService,
        userService
      ),
      "Administrative"
    );
    cardPanel.add(
      memberWindow = new MemberWindow(dependentService, memberService),
      "Member"
    );
    cardPanel.add(
      new JLabel("WIP"),
      "WIP"
    );

    frame.add(cardPanel, BorderLayout.CENTER);

    frame.pack();
    frame.setSize(400, 300);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  private void onSuccessfulLogin(String username) {
    User user = userService.getUserByUsername(username);

    if (user.getEmployee() != null) {
      onSuccessfulEmployeeLogin(user.getEmployee().getPosition());
      return;
    }

    onSuccessfulMemberLogin(username);
  }

  private void onSuccessfulMemberLogin(String username) {
    memberWindow.setUsername(username);
    cardLayout.show(cardPanel, "Member");
  }

  private void onSuccessfulEmployeeLogin(PositionType positionType) {
    switch (positionType) {
      case MANAGER:
        cardLayout.show(cardPanel, "Administrative");
        break;
      default:
        cardLayout.show(cardPanel, "WIP");
    }
  }
}
