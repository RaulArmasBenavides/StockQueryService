package com.maestria.springmvcstock.service;

import com.maestria.springmvcstock.controller.exception.ResourceNotFoundException;
import com.maestria.springmvcstock.model.Pedido;
import com.maestria.springmvcstock.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PedidoService {
    @Autowired
    private final PedidoRepository pedidoRepository;

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido createPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido updatePedido(Long id, Pedido pedido) {
        Pedido existingPedido = pedidoRepository.findById(id).orElse(null);
        if (existingPedido != null) {
            existingPedido.setFechaPedido(pedido.getFechaPedido());
            existingPedido.setEstado(pedido.getEstado());
            existingPedido.setProveedor(pedido.getProveedor());
            existingPedido.setProductos(pedido.getProductos());
            return pedidoRepository.save(existingPedido);
        }
        return null;
    }

    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    } 
}
