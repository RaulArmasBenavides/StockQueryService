package com.maestria.springmvcstock.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.maestria.springmvcstock.model.Supplier;
import com.maestria.springmvcstock.repository.SupplierRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService {
    @Autowired
    private SupplierRepository _SupplierRepository;

    public List<Supplier> getAllProveedores() {
        return _SupplierRepository.findAll();
    }

    public Supplier getProveedorById(Long id) {
        return _SupplierRepository.findById(id).orElse(null);
    }

    public Supplier createProveedor(Supplier proveedor) {
        return _SupplierRepository.save(proveedor);
    }

    public Supplier updateProveedor(Long id, Supplier sup) {
        Supplier existingProveedor = _SupplierRepository.findById(id).orElse(null);
        if (existingProveedor != null) {
            existingProveedor.setName(sup.getName());
            existingProveedor.setAddress(sup.getAddress());
            existingProveedor.setPhone(sup.getPhone());
            existingProveedor.setEmail(sup.getEmail());
            return _SupplierRepository.save(existingProveedor);
        }
        return null;
    }

    public void deleteProveedor(Long id) {
        _SupplierRepository.deleteById(id);
    }
}
