package com.edu.fpt.hoursemanager.management.contract.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends EntityCommon {

    private String name;
    private String color;
    private String numberPlate;
    private String type;

    public Vehicle(String name, String color, String numberPlate, String type) {
        this.name = name;
        this.color = color;
        this.numberPlate = numberPlate;
        this.type = type;
    }

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;
}
