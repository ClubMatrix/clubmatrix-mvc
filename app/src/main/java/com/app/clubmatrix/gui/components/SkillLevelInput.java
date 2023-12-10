package com.app.clubmatrix.gui.components;

import com.app.clubmatrix.models.SkillLevelType;

import javax.swing.*;

public class SkillLevelInput extends JComboBox<SkillLevelType> {

    public SkillLevelInput() {
        super(SkillLevelType.values());
    }
}