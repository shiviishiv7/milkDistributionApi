package com.example.demo.dao;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findByDeliveryDate(LocalDate date);
}