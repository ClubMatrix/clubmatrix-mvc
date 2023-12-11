package com.app.clubmatrix.gui.components;

import com.app.clubmatrix.models.PositionType;
import javax.swing.*;

public class PositionInput extends JComboBox<PositionType> {

  public PositionInput() {
    super(PositionType.values());
  }
}
