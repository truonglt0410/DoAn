package com.edu.fpt.hoursemanager.management.directory.service;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import org.springframework.http.ResponseEntity;

public interface DirectionManageServices {
    ResponseEntity<ResponseModels> getAllDirectoryByRole() ;

//    List<MainDirectoryResponse> getMainDirectoryByRole();

    boolean addAllDirectory();
}
