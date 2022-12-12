package com.edu.fpt.hoursemanager.management.timesheet.repository;

import com.edu.fpt.hoursemanager.management.timesheet.entity.TimeSheet;
import com.edu.fpt.hoursemanager.management.timesheet.model.response.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long> {

    @Query("select new com.edu.fpt.hoursemanager.management.timesheet.model.response.TimeSheetResponse(t.time,t.checkDone,t.note,w.name) from TimeSheet t inner join Work w where w.deleted = false and t.deleted = false ")
    List<TimeSheetResponse> lsTimeSheetResponse();

    @Query("select new com.edu.fpt.hoursemanager.management.timesheet.model.response.GetTimeSheetResponse(w.id,w.name,t.checkDone,t.note) from TimeSheet t inner join t.work w inner join t.contact c inner join c.account a where w.deleted = false and t.deleted = false and a.email = :email and t.time = :time")
    List<GetTimeSheetResponse> lsGetTimeSheetResponse(@Param("email") String email, @Param("time") String time);

    @Query("select distinct new com.edu.fpt.hoursemanager.management.timesheet.model.response.GetTimeSheetAccountResponse(a.id,a.email) from TimeSheet t inner join t.contact c inner join c.account a where t.deleted = false and t.time = :time")
    List<GetTimeSheetAccountResponse> lsGetAllAccountInTimeSheet(@Param("time") String time);

    @Query("select new com.edu.fpt.hoursemanager.management.timesheet.model.response.GetTimeSheetAccountResponse(a.id,a.email) from TimeSheet t inner join t.contact c inner join c.account a where t.deleted = false and a.email = :email and t.time = :time")
    List<GetTimeSheetAccountResponse> lsGetAllTimeInTimeSheet(@Param("email") String email, @Param("time") String time);

    @Query("select new com.edu.fpt.hoursemanager.management.timesheet.model.response.GetTimeSheetFloorResponse(t.time,c.id,c.fullName,w.id,w.name,t.checkDone,t.note) from TimeSheet t inner join t.contact c inner join t.work w where t.deleted = false and w.deleted = false and c.deleted = false and t.time >= :timeStart and t.time <= :timeEnd")
    List<GetTimeSheetFloorResponse> lsGetAllFloor(@Param("timeStart") Date timeStart,@Param("timeEnd") Date timeEnd);

    @Query("select new com.edu.fpt.hoursemanager.management.timesheet.model.response.GetTimeSheetFloorResponse(t.time,c.id,c.fullName,w.id,w.name,t.checkDone,t.note) from TimeSheet t inner join t.contact c inner join t.work w inner join c.account a where t.deleted = false and w.deleted = false and c.deleted = false and t.time >= :timeStart and t.time <= :timeEnd and a.email = :email")
    List<GetTimeSheetFloorResponse> lsGetAllTimeByAccount(@Param("timeStart") Date timeStart,@Param("timeEnd") Date timeEnd,@Param("email") String email);
}
