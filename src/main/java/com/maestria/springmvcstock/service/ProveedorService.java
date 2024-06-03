package com.maestria.springmvcstock.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.maestria.springmvcstock.model.Proveedor;
import com.maestria.springmvcstock.repository.ProveedorRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    public Proveedor getProveedorById(Long id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    public Proveedor createProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public Proveedor updateProveedor(Long id, Proveedor proveedor) {
        Proveedor existingProveedor = proveedorRepository.findById(id).orElse(null);
        if (existingProveedor != null) {
            existingProveedor.setNombre(proveedor.getNombre());
            existingProveedor.setDireccion(proveedor.getDireccion());
            existingProveedor.setTelefono(proveedor.getTelefono());
            existingProveedor.setEmail(proveedor.getEmail());
            return proveedorRepository.save(existingProveedor);
        }
        return null;
    }

    public void deleteProveedor(Long id) {
        proveedorRepository.deleteById(id);
    }
}
