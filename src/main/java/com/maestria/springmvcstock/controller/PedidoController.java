package com.maestria.springmvcstock.controller;


import lombok.RequiredArgsConstructor;

import com.maestria.springmvcstock.model.Pedido;
import com.maestria.springmvcstock.service.PedidoService;
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pedidos/")
public class PedidoController {
        private final PedidoService pedidoService;
        @GetMapping
        public ResponseEntity<List<Pedido>> getAllPedidos() {
            List<Pedido> tasks = pedidoService.getAllPedidos();
            return ResponseEntity.ok(tasks);
        }
    
        @GetMapping("/{id}")
        public ResponseEntity<Pedido> getProductById(@PathVariable Long id) {
                Pedido task = pedidoService.getPedidoById(id);
            return ResponseEntity.ok(task);
        }
    
        @PostMapping
        public ResponseEntity<Pedido> createProduct(@RequestBody Pedido ped) {
                Pedido createdTask = pedidoService.createPedido(ped);
            return ResponseEntity.status(201).body(createdTask);
        }
    
        @PutMapping("/{id}")
        public ResponseEntity<Pedido> updateProduct(@PathVariable Long id, @RequestBody Pedido ped) {
                Pedido updatedTask = pedidoService.updatePedido(id, ped);
            return ResponseEntity.ok(updatedTask);
        }
    
        // @PatchMapping("/{id}")
        // public ResponseEntity<Task> partialUpdateTask(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        //     Task updatedTask = productoService.partialUpdate(id, updates);
        //     return ResponseEntity.ok(updatedTask);
        // }
    
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
                pedidoService.deletePedido(id);
            return ResponseEntity.noContent().build();
        }
    
        @RequestMapping(value = "", method = RequestMethod.OPTIONS)
        public ResponseEntity<Void> options() {
            return ResponseEntity.ok().header("Allow", "GET, POST, PUT, DELETE, PATCH, OPTIONS").build();
        }
}
