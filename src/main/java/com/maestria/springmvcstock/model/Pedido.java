package com.maestria.springmvcstock.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.UUID;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fechaPedido;
    private String estado;
    
    @ManyToOne
    private Proveedor proveedor;
    
    @OneToMany
    private List<Product> products;
}
