package com.edu.fpt.hoursemanager.management.roomservice.repository;

import com.edu.fpt.hoursemanager.management.roomservice.entity.Service;
import com.edu.fpt.hoursemanager.management.roomservice.model.response.ServiceAccountResponse;
import com.edu.fpt.hoursemanager.management.roomservice.model.response.ServiceBuildingResponse;
import com.edu.fpt.hoursemanager.management.roomservice.model.response.ServiceResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    @Query("select new com.edu.fpt.hoursemanager.management.roomservice.model.response.ServiceResponse(s.id,s.name,s.price,s.typePayment) from Service s where s.deleted = false ")
    List<ServiceResponse> getAllServiceAllBuilding();

    @Query("select new com.edu.fpt.hoursemanager.management.roomservice.model.response.ServiceResponse(s.id,s.name,s.price,s.typePayment,b.id) from Service s inner join s.roomServices rs inner join rs.room r inner join r.building b where s.deleted = false ")
    List<ServiceResponse> getAllService();

    @Query("select new com.edu.fpt.hoursemanager.management.roomservice.model.response.ServiceResponse(s.id,s.name,s.price,s.typePayment,s.id) from Service s left join s.roomServices rs inner join rs.room r inner join r.building b where s.deleted = false and r.deleted = false and r.id = :id")
    List<ServiceResponse> getAllServiceByRoom(@Param("id") Long id);

    @Query("select new com.edu.fpt.hoursemanager.management.roomservice.model.response.ServiceResponse(s.id,s.name,s.price,s.typePayment,b.id) from Service s inner join s.roomServices rs inner join rs.room r inner join r.building b where s.deleted = false and s.id = :id")
    ServiceResponse getAllServiceById(@Param("id") Long id);

    @Query("select s from Service s where s.id = :id")
    Service getServiceById(@Param("id") Long id);

    @Query("select new com.edu.fpt.hoursemanager.management.roomservice.model.response.ServiceBuildingResponse(s.id, s.name, s.price, b.id)" +
            " from Service s inner join s.building b where b.id = :id and s.deleted = false ")
    List<ServiceBuildingResponse> getAllServiceByBuilding(@Param("id") Long id);

    @Query("select new com.edu.fpt.hoursemanager.management.roomservice.model.response.ServiceAccountResponse(s.id, s.name, s.price, b.id, a.email) " +
            "from Service s inner join s.building b inner join b.accounts a where a.email = :email and s.deleted = false ")
    List<ServiceAccountResponse> getAllServiceByAccount(@Param("email") String email);

    @Query("select new com.edu.fpt.hoursemanager.management.roomservice.model.response.ServiceResponse(s.id,s.name,s.price,s.typePayment,b.id) from Service s inner join s.roomServices rs inner join rs.room r inner join r.building b where s.deleted = false and b.id = :id")
    ServiceResponse getAllServiceByIdBuilding(@Param("id") Long id);
}
