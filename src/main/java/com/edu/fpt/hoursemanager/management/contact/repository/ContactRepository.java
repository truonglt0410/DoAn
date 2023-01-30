package com.edu.fpt.hoursemanager.management.contact.repository;

import com.edu.fpt.hoursemanager.management.contact.entity.Contact;
import com.edu.fpt.hoursemanager.management.contact.model.response.ContactRoleResponse;
import com.edu.fpt.hoursemanager.management.contact.model.response.GetContactAndAccountResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("select  c from Contact c inner  join c.account a where a.email = :email")
    Contact getAccountContactByEmail(@Param("email") String email);

    @Query("select new com.edu.fpt.hoursemanager.management.contact.model.response.ContactRoleResponse(c.id, c.fullName, c.phone, c.dob, c.gender, c.address, a.email, r.name, c.numberId, c.imageBefore, c.imageAfter) " +
            "from Contact c inner join c.account a inner join a.roles r where a.email = :email")
    List<ContactRoleResponse> getContactWithRole(@Param("email") String email);

    @Query("select new com.edu.fpt.hoursemanager.management.contact.model.response.GetContactAndAccountResponse(a.email,a.password,c.fullName,c.phone,c.dob,c.gender,c.address,c.type, c.numberId, c.imageBefore, c.imageAfter) from Contact c inner  join c.account a where a.email = :email and c.deleted = false and a.deleted = false ")
    GetContactAndAccountResponse getAccountAndContactByEmail(@Param("email") String email);
}
