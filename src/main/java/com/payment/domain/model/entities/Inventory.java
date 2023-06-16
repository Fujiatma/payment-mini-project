package com.payment.domain.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory")
@DynamicInsert
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String itemId;

    @Column(name = "item_name")
    private String itemName;

    private int quantity;

    @Column(name = "price", nullable = false)
    private double price;
}
