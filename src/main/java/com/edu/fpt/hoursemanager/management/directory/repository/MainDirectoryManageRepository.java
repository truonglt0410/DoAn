package com.edu.fpt.hoursemanager.management.directory.repository;

import com.edu.fpt.hoursemanager.management.directory.entity.MainDirectory;
import com.edu.fpt.hoursemanager.management.directory.model.response.DirectoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainDirectoryManageRepository extends JpaRepository<MainDirectory, Long> {
    @Query("select new com.edu.fpt.hoursemanager.management.directory.model.response.DirectoryResponse(r.code, d.name, d.url, md.name, md.imageName)" +
            " from MainDirectory md left join md.directories d inner join d.roles r where r.deleted = false and r.code in :roles")
    List<DirectoryResponse> getAllDirectoryByRoles(@Param("roles") List<String> roles);

}
