package com.app.clubmatrix.gui.windows.manager.panels.models;

import com.app.clubmatrix.models.Order;
import com.app.clubmatrix.utils.Format;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class OrderTable extends AbstractTableModel {

  private final List<Order> orders;
  private final String[] columnNames = {
    "ID",
    "Amount",
    "Member",
    "Employee",
    "Created At",
  };

  public OrderTable(List<Order> orders) {
    this.orders = orders;
  }

  @Override
  public int getRowCount() {
    return orders.size();
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Order order = orders.get(rowIndex);
    return switch (columnIndex) {
      case 0 -> order.getId();
      case 1 -> Format.formatMoney(order.getAmount());
      case 2 -> order.getMember().getName();
      case 3 -> order.getEmployee().getName();
      case 4 -> order.getCreatedAt();
      default -> null;
    };
  }

  @Override
  public String getColumnName(int column) {
    return columnNames[column];
  }

  public Order getOrderAt(int rowIndex) {
    return orders.get(rowIndex);
  }
}
