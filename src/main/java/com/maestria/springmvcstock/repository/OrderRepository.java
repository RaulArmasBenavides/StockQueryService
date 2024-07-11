package com.maestria.springmvcstock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
 

import com.maestria.springmvcstock.model.Order;
public interface OrderRepository extends JpaRepository<Order, Long>  {
    
}
