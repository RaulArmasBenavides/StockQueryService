package com.maestria.springmvcstock.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.maestria.springmvcstock.model.Supplier;
import com.maestria.springmvcstock.repository.SupplierRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;


public interface SupplierService {
        List<Supplier> getAllSuppliers();

        Supplier getSupplierById(Long id);

        Supplier createSupplier(Supplier proveedor);

        Supplier updateSupplier(Long id, Supplier sup);

        void deleteSupplier(Long id);
}
