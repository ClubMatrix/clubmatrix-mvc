package com.app.clubmatrix.gui.windows.member;

import com.app.clubmatrix.gui.windows.member.panels.DependentManagementPanel;
import com.app.clubmatrix.services.DependentService;
import com.app.clubmatrix.services.MemberService;

import javax.swing.*;
import java.awt.*;

public class MemberWindow extends JPanel {

    private DependentManagementPanel dependentManagementPanel;

    public MemberWindow(DependentService dependentService, MemberService memberService) {
        setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();

        dependentManagementPanel = new DependentManagementPanel(dependentService, memberService, "Unknown");
        tabbedPane.addTab("Dependents", dependentManagementPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    public void setUsername(String username) {
        dependentManagementPanel.setUsername(username);
    }
}