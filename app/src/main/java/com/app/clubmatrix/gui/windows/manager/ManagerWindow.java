package com.app.clubmatrix.gui.windows.manager;

import com.app.clubmatrix.gui.windows.manager.panels.*;
import com.app.clubmatrix.services.ActivityService;
import com.app.clubmatrix.services.EmployeeService;
import com.app.clubmatrix.services.MemberService;

import javax.swing.*;
import java.awt.*;

public class ManagerWindow extends JPanel {

    public ManagerWindow(MemberService memberService, EmployeeService employeeService, ActivityService activityService) {
        setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Activities", new ActivityManagementPanel(activityService));
        tabbedPane.addTab("Cafeteria", new CafeteriaManagementPanel());
        tabbedPane.addTab("Classes", new ClassManagementPanel());
        tabbedPane.addTab("Employees", new EmployeeManagementPanel(employeeService));
        tabbedPane.addTab("Employees overview", new EmployeeOverViewPanel());
        tabbedPane.addTab("Enrollment", new EnrollmentManagementPanel());
        tabbedPane.addTab("Members", new MemberManagementPanel(memberService));
        tabbedPane.addTab("Notifications", new NotificationManagementPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }
}