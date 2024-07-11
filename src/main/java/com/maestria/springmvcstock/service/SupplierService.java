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

    public List<Supplier> getAllSuppliers() {
        return _SupplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        return _SupplierRepository.findById(id).orElse(null);
    }

    public Supplier createSupplier(Supplier proveedor) {
        return _SupplierRepository.save(proveedor);
    }

    public Supplier updateSupplier(Long id, Supplier sup) {
        Supplier existingSupplier = _SupplierRepository.findById(id).orElse(null);
        if (existingSupplier != null) {
            existingSupplier.setName(sup.getName());
            existingSupplier.setAddress(sup.getAddress());
            existingSupplier.setPhone(sup.getPhone());
            existingSupplier.setEmail(sup.getEmail());
            return _SupplierRepository.save(existingSupplier);
        }
        return null;
    }

    public void deleteSupplier(Long id) {
        _SupplierRepository.deleteById(id);
    }
}
