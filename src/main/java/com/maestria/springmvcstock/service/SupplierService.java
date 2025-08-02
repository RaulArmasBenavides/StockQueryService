package com.maestria.springmvcstock.service;

import com.maestria.springmvcstock.model.Supplier;
import java.util.List;

public interface SupplierService {
        List<Supplier> getAllSuppliers();

        Supplier getSupplierById(Long id);

        Supplier createSupplier(Supplier proveedor);

        Supplier updateSupplier(Long id, Supplier sup);

        void deleteSupplier(Long id);
}
