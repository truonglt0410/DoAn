package com.edu.fpt.hoursemanager.management.contract.reposiotry;

import com.edu.fpt.hoursemanager.management.contract.entity.Contract;
import com.edu.fpt.hoursemanager.management.contract.model.response.BasicContractResponse;
import com.edu.fpt.hoursemanager.management.contract.model.response.ContractResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractManageRepository extends JpaRepository<Contract, Long> {
    @Query("select new com.edu.fpt.hoursemanager.management.contract.model.response.BasicContractResponse(c.id, co.fullName, co.phone, rb.name,room.name) " +
            "from Contract c inner join c.renterRooms cr inner join cr.contact co inner join cr.room room inner join room.building rb inner join rb.accounts ac where c.deleted = false and ac.email = :email")
    List<BasicContractResponse> getAllContract(@Param("email") String email);

    @Query("select new com.edu.fpt.hoursemanager.management.contract.model.response.ContractResponse(c.id, c.fromDate, c.toDate, c.deposit,c.roomRate, c.roomPaymentCycle,co.id, co.fullName, co.phone, co.numberId, co.imageBefore, co.imageAfter, co.dob, room.id, room.name, rb.name) " +
            "from Contract c inner join c.renterRooms cr inner join cr.contact co inner join cr.room room inner join room.building rb where c.deleted = false and c.id = :id")
    List<ContractResponse> getDetailContract(@Param("id") Long id);

    @Query("select c from Contract c where c.deleted = false and c.id = :id")
    Contract getByContractId(@Param("id") Long id);

    @Query("select c from Contract c inner join c.renterRooms rr inner join rr.room r " +
            "where c.deleted = false and r.id = :id")
    Contract getContractByRoomId(@Param("id") Long id);
}
