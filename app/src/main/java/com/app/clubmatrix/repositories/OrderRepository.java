package com.app.clubmatrix.repositories;

import com.app.clubmatrix.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
