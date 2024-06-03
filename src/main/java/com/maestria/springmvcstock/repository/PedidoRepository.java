package com.maestria.springmvcstock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
 

import com.maestria.springmvcstock.model.Pedido;
public interface PedidoRepository extends JpaRepository<Pedido, Long>  {
    
}
