package com.app.clubmatrix.gui.windows.manager;

import com.app.clubmatrix.gui.windows.manager.panels.*;
import com.app.clubmatrix.services.*;
import java.awt.*;
import javax.swing.*;

public class ManagerWindow extends JPanel {

  public ManagerWindow(
    MemberService memberService,
    EmployeeService employeeService,
    ActivityService activityService,
    OrderService orderService,
    UserService userService
  ) {
    setLayout(new BorderLayout());
    JTabbedPane tabbedPane = new JTabbedPane();

    tabbedPane.addTab(
      "Activities",
      new ActivityManagementPanel(activityService)
    );
    tabbedPane.addTab(
      "Cafeteria",
      new CafeteriaManagementPanel(
        orderService,
        memberService,
        employeeService,
        userService
      )
    );
    tabbedPane.addTab("Classes", new ClassManagementPanel());
    tabbedPane.addTab(
      "Employees",
      new EmployeeManagementPanel(employeeService)
    );
    tabbedPane.addTab("Employees overview", new EmployeeOverViewPanel());
    tabbedPane.addTab("Enrollment", new EnrollmentManagementPanel());
    tabbedPane.addTab("Members", new MemberManagementPanel(memberService));
    tabbedPane.addTab("Notifications", new NotificationManagementPanel());

    add(tabbedPane, BorderLayout.CENTER);
  }
}
