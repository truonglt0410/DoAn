package com.edu.fpt.hoursemanager.management.directory.controller;


import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.directory.service.DirectionManageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/authentication")
public class DirectoryController {
    @Autowired
    private DirectionManageServices directionManageServices;

    //************************************
    // Find Building by Account id
    //************************************
    @GetMapping("/get-all-by-account-id")
    public ResponseEntity<ResponseModels> getAllBuildingByAccountById()  {
        return directionManageServices.getAllDirectoryByRole();
    }
}
