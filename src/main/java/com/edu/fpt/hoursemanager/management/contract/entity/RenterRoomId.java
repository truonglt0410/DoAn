package com.edu.fpt.hoursemanager.management.contract.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class RenterRoomId implements Serializable {

    @Column(name = "room_id")
    private Long roomId;
    @Column(name = "contact_id")
    private Long contactId;
    @Column(name = "contract_id")
    private Long contractId;
}
