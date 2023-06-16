package com.payment.domain.repositories;

import com.payment.domain.model.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Query("SELECT i FROM Inventory i WHERE i.itemName = :itemName")
    Inventory findByItemName(@Param("itemName") String itemName);
}
