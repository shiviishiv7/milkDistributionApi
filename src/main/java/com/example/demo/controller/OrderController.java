package com.example.demo.controller;

import com.example.demo.dao.OrderRepository;
import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Order;
import com.example.demo.exception.OrderDateNotFound;
import com.example.demo.exception.OrderNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {


      @Autowired
      private OrderRepository orderRepository;

      @GetMapping("/updateStatus/{id}")
      public ResponseEntity<?> getOrderById(@PathVariable int id) throws OrderNotFound {
            Order order = orderRepository.findById(id).orElse(null);
            if(order==null)throw new OrderNotFound("Order Not found "+id);
            return ResponseEntity.ok(order);
      }

      @GetMapping("/")
      public ResponseEntity<?> getAllOrder() {
            List<Order> all = orderRepository.findAll();
            return ResponseEntity.ok(all);
      }
      @PostMapping("/add")
      public ResponseEntity<?> addOrder(@RequestBody OrderDto orderDto) {
            Order order = Order.builder()
                    .milkQuantityInLitres(orderDto.getMilkQuantityInLitres())
                    .orderStatus(orderDto.getOrderStatus())
                    .customerName(orderDto.getCustomerName())
                    .deliveryDate(orderDto.getDeliveryDate())
                    .paymentMethod(orderDto.getPaymentMethod())
                    .pricePerLitre(orderDto.getPricePerLitre())
                    .shippingAddress(orderDto.getShippingAddress())
                    .build();
             order = orderRepository.save(order);
             return ResponseEntity.ok(order);
      }
      @PutMapping("/update/{id}")
      public ResponseEntity<?> updateOrderById(@RequestBody OrderDto orderDto,@PathVariable int id) throws OrderNotFound {
            Order order = orderRepository.findById(id).orElse(null);
            if(order==null)throw new OrderNotFound("Order Not found "+id);
            order.setOrderStatus(orderDto.getOrderStatus());
            order.setCustomerName(orderDto.getCustomerName());
            order.setDeliveryDate(orderDto.getDeliveryDate());
            order.setPricePerLitre(orderDto.getPricePerLitre());
            order.setPaymentMethod(orderDto.getPaymentMethod());
            order.setMilkQuantityInLitres(orderDto.getMilkQuantityInLitres());
            order.setShippingAddress(orderDto.getShippingAddress());
            Order save = orderRepository.save(order);
            return ResponseEntity.ok(save);
      }

      @DeleteMapping("/delete/{id}")
      public ResponseEntity<?> deleteById(@PathVariable int id) throws OrderNotFound {
            Order order = orderRepository.findById(id).orElse(null);
            if(order==null)throw new OrderNotFound("Order Not found "+id);
            orderRepository.deleteById(id);
            Map<String, String> map = new HashMap<>();
            map.put("message","Order Details Deleted");
            return ResponseEntity.ok(map);
      }
      @GetMapping("/checkCapacity/{date}")
      public ResponseEntity<?> deleteById( @PathVariable @Valid @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) throws   OrderDateNotFound {
            Order order = orderRepository.findByDeliveryDate(date).orElse(null);
            if(order==null)throw new OrderDateNotFound("Order Not found "+date.toString());
            Map<String, String> map = new HashMap<>();
            map.put("message","Unsold milk on Date "+(date.toString())+" was "+order.getMilkQuantityInLitres()+"L");
            return ResponseEntity.ok(map);
      }

}
