package com.edu.fpt.hoursemanager.management.timesheet.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import com.edu.fpt.hoursemanager.management.contact.entity.Contact;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheet extends EntityCommon {

    private Date time;
    private boolean checkDone;
    private String note;

    @ManyToOne
    @JoinColumn(name = "work_id")
    @JsonIgnore
    private Work work;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "contact_id")
    private Contact contact;
}
