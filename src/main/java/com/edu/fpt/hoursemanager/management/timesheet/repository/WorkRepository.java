package com.edu.fpt.hoursemanager.management.timesheet.repository;

import com.edu.fpt.hoursemanager.management.room.model.response.GetRoomResponse;
import com.edu.fpt.hoursemanager.management.timesheet.entity.TimeSheet;
import com.edu.fpt.hoursemanager.management.timesheet.entity.Work;
import com.edu.fpt.hoursemanager.management.timesheet.model.response.WorkResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {
    @Query("select new com.edu.fpt.hoursemanager.management.timesheet.model.response.WorkResponse(w.id, w.name) from Work w where w.deleted = false ")
    List<WorkResponse> lsWorkResponse();
}
