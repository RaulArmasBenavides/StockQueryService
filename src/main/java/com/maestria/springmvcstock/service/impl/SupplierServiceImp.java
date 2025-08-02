


package com.maestria.springmvcstock.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.maestria.springmvcstock.model.Supplier;
import com.maestria.springmvcstock.repository.SupplierRepository;
import com.maestria.springmvcstock.service.SupplierService;


@Service
public class SupplierServiceImp implements SupplierService {
     private final SupplierRepository supplierRepository;

    public SupplierServiceImp(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    public Supplier createSupplier(Supplier proveedor) {
        return supplierRepository.save(proveedor);
    }

    public Supplier updateSupplier(Long id, Supplier sup) {
        Supplier existingSupplier = supplierRepository.findById(id).orElse(null);
        if (existingSupplier != null) {
            existingSupplier.setName(sup.getName());
            existingSupplier.setAddress(sup.getAddress());
            existingSupplier.setPhone(sup.getPhone());
            existingSupplier.setEmail(sup.getEmail());
            return supplierRepository.save(existingSupplier);
        }
        return null;
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
