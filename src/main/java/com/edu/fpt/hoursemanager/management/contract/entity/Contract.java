package com.edu.fpt.hoursemanager.management.contract.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract extends EntityCommon{
    private Date fromDate;
    private Date toDate;
    private Double deposit;
    private Double roomRate;
    private int roomPaymentCycle;

    public Contract(Date fromDate, Date toDate, Double deposit, Double roomRate, int roomPaymentCycle) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.deposit = deposit;
        this.roomRate = roomRate;
        this.roomPaymentCycle = roomPaymentCycle;
    }

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private Collection<Vehicle> vehicles;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    private Collection<RenterRoom> renterRooms;
}
