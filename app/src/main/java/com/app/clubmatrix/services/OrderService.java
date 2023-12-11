package com.app.clubmatrix.services;

import com.app.clubmatrix.models.Employee;
import com.app.clubmatrix.models.Member;
import com.app.clubmatrix.models.Order;
import com.app.clubmatrix.repositories.OrderRepository;
import com.app.clubmatrix.repositories.UserRepository;
import com.app.clubmatrix.services.dto.OrderRegistrationDTO;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private UserRepository userRepository;

  public Order registerOrder(OrderRegistrationDTO OrderDTO) {
    Order order = new Order();
    order.setAmount(OrderDTO.getAmount());

    Member member = Objects
      .requireNonNull(
        userRepository.findByUsername(OrderDTO.getMemberUsername()).orElse(null)
      )
      .getMember();
    order.setMember(member);

    Employee employee = Objects
      .requireNonNull(
        userRepository
          .findByUsername(OrderDTO.getEmployeeUsername())
          .orElse(null)
      )
      .getEmployee();
    order.setEmployee(employee);

    return orderRepository.save(order);
  }

  public Order updateOrder(Order Order) {
    return orderRepository.save(Order);
  }

  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  public void deleteOrder(Order order) {
    orderRepository.delete(order);
  }
}
