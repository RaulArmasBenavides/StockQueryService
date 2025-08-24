package com.maestria.springmvcstock.controller;


import lombok.RequiredArgsConstructor;
import com.maestria.springmvcstock.model.Order;
import com.maestria.springmvcstock.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders/")
public class OrderController {
        private final OrderService pedidoService;
        @GetMapping
        public ResponseEntity<List<Order>> getAllOrders() {
            List<Order> tasks = pedidoService.getAllOrders();
            return ResponseEntity.ok(tasks);
        }
    
        @GetMapping("/{id}")
        public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
                Order task = pedidoService.getOrderById(id);
            return ResponseEntity.ok(task);
        }
    
        @PostMapping
        public ResponseEntity<Order> createOrder(@RequestBody Order ped) {
                Order createdTask = pedidoService.createOrder(ped);
            return ResponseEntity.status(201).body(createdTask);
        }
    
        @PutMapping("/{id}")
        public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order ped) {
                Order updatedTask = pedidoService.updateOrder(id, ped);
            return ResponseEntity.ok(updatedTask);
        }
    
        // @PatchMapping("/{id}")
        // public ResponseEntity<Task> partialUpdateTask(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        //     Task updatedTask = productoService.partialUpdate(id, updates);
        //     return ResponseEntity.ok(updatedTask);
        // }
    
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
                pedidoService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        }
    
        @RequestMapping(value = "", method = RequestMethod.OPTIONS)
        public ResponseEntity<Void> options() {
            return ResponseEntity.ok().header("Allow", "GET, POST, PUT, DELETE, PATCH, OPTIONS").build();
        }
}
