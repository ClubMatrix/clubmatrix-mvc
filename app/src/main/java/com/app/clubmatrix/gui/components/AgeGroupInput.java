package com.app.clubmatrix.gui.components;

import com.app.clubmatrix.models.AgeGroupType;
import javax.swing.*;

public class AgeGroupInput extends JComboBox<AgeGroupType> {

  public AgeGroupInput() {
    super(AgeGroupType.values());
  }
}
