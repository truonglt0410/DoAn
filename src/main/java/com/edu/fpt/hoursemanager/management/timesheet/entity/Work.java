package com.edu.fpt.hoursemanager.management.timesheet.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Work extends EntityCommon {

    private String name;

    @OneToMany(mappedBy = "work", cascade = CascadeType.ALL)
    private List<TimeSheet> timeSheet;
}
