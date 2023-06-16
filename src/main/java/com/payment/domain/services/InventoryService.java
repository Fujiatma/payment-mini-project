package com.payment.domain.services;

import com.payment.domain.helper.IDGenerator;
import com.payment.domain.model.entities.Inventory;
import com.payment.domain.model.entities.Payment;
import com.payment.domain.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory createInventory(Inventory inventory) {
        inventory.setItemId(IDGenerator.generateID());
        return inventoryRepository.save(inventory);
    }
}