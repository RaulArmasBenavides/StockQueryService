package com.maestria.springmvcstock.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data
public class PedidoItem {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Product producto;
    private int cantidad;

    // Getters y setters
}