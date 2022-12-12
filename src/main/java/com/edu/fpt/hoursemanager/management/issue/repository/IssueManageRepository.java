package com.edu.fpt.hoursemanager.management.issue.repository;

import com.edu.fpt.hoursemanager.management.issue.entity.Issue;
import com.edu.fpt.hoursemanager.management.issue.model.response.GetIssueByAccountResponse;
import com.edu.fpt.hoursemanager.management.issue.model.response.IssueResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueManageRepository extends JpaRepository<Issue, Long> {

    @Query("select new com.edu.fpt.hoursemanager.management.issue.model.response.IssueResponse(i.id, i.name, i.createdDate, i.status, i.price, i.description, r.id, r.name, b.id, b.name, i.imageName) " +
            "from Issue i inner join i.asset a " +
            "inner join a.room r inner join r.building b where i.deleted = false and b.id = :id")
    List<IssueResponse> getAllIssueByBuildingId(@Param("id") Long id);

    @Query("select new com.edu.fpt.hoursemanager.management.issue.model.response.IssueResponse(i.id, i.name, i.createdDate, i.status, i.price, i.description, r.id, r.name, b.id, b.name, i.imageName) " +
            "from Issue i inner join i.asset a  inner join a.room r inner join r.building b " +
            "where i.deleted = false and b.id = :id and i.status = :status")
    List<IssueResponse> getAllIssueByBuildingIdAndStatus(@Param("id") Long id, @Param("status") String status);

    @Query("select new com.edu.fpt.hoursemanager.management.issue.model.response.IssueResponse(i.id, i.name, i.createdDate, i.status, i.price, i.description, r.id, r.name, b.id, b.name, i.imageName) " +
            "from Issue i inner join i.asset a  inner join a.room r inner join r.building b " +
            "where i.deleted = false and r.id = :id ")
    List<IssueResponse> getAllIssueByRoom(@Param("id") Long id);

    @Query("select new com.edu.fpt.hoursemanager.management.issue.model.response.GetIssueByAccountResponse(i.id, i.name, i.createdDate, i.status, i.price, i.description, r.id, r.name, b.id, b.name, ac.email, i.imageName) " +
            "from Issue i inner join i.asset a  inner join a.room r inner join r.building b inner join b.accounts ac " +
            "where i.deleted = false and ac.email = :email")
    List<GetIssueByAccountResponse> getAllIssueByAccount(@Param("email") String email);

    @Query("select new com.edu.fpt.hoursemanager.management.issue.model.response.IssueResponse(i.id, i.name, i.createdDate, i.status, i.price, i.description, r.id, r.name, b.id, b.name, i.imageName) " +
            "from Issue i inner join i.asset a  inner join a.room r inner join r.building b " +
            "where i.deleted = false and i.id = :id")
    IssueResponse getDetailIssue(@Param("id") Long id);

    @Query("select i from Issue i inner join i.asset a  inner join a.room r inner join r.building b " +
            "where i.deleted = false and i.id = :id")
    Issue getIssueById(@Param("id") Long id);
}
