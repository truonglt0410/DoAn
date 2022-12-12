package com.edu.fpt.hoursemanager.management.contact.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import com.edu.fpt.hoursemanager.management.account.entity.Account;
import com.edu.fpt.hoursemanager.management.contract.entity.RenterRoom;
import com.edu.fpt.hoursemanager.management.timesheet.entity.TimeSheet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact extends EntityCommon {
    private String fullName;
    private String phone;
    private Date dob;
    private Boolean gender;
    private String address;
    private Boolean type;

    @OneToOne(mappedBy = "contact")
    private Account account;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    private Collection<RenterRoom> renterRooms;

    public Contact(String fullName, String phone, Date dob, Boolean gender, String address) {
        this.fullName = fullName;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
    }

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    private List<TimeSheet> timeSheet;
}
