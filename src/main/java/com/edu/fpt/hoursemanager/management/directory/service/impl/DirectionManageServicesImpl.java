package com.edu.fpt.hoursemanager.management.directory.service.impl;

import com.edu.fpt.hoursemanager.common.entity.AccountLoginCommon;
import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.account.entity.Role;
import com.edu.fpt.hoursemanager.management.account.repository.RoleRepository;
import com.edu.fpt.hoursemanager.management.directory.entity.Directory;
import com.edu.fpt.hoursemanager.management.directory.entity.MainDirectory;
import com.edu.fpt.hoursemanager.management.directory.model.response.DirectoryResponse;
import com.edu.fpt.hoursemanager.management.directory.model.response.MainDirectoryFilResponse;
import com.edu.fpt.hoursemanager.management.directory.model.response.MainDirectoryResponse;
import com.edu.fpt.hoursemanager.management.directory.model.response.SubDirectoryResponse;
import com.edu.fpt.hoursemanager.management.directory.repository.DirectoryManageRepository;
import com.edu.fpt.hoursemanager.management.directory.repository.MainDirectoryManageRepository;
import com.edu.fpt.hoursemanager.management.directory.service.DirectionManageServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class DirectionManageServicesImpl implements DirectionManageServices {
    @Autowired
    MainDirectoryManageRepository mainDirectoryManageRepository;
    @Autowired
    DirectoryManageRepository directoryManageRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public ResponseEntity<ResponseModels> getAllDirectoryByRole() {
        List<MainDirectoryFilResponse> mainDirectoryFilResponses = new ArrayList<>();
        try {
            Map<MainDirectoryResponse, List<SubDirectoryResponse>> mapAllDirectory = new HashMap<>();
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            List<String> roles = accountLoginCommon.getRoleList();
            List<DirectoryResponse> listDirect = directoryManageRepository.getAllDirectoryByRoles(roles);
            MainDirectoryResponse mainDirectoryResponse;
            SubDirectoryResponse subDirectoryResponse;
            for (DirectoryResponse list : listDirect) {
                mainDirectoryResponse = new MainDirectoryResponse(list.getMainDirectoryName(), list.getImageName());
                if (!mapAllDirectory.containsKey(mainDirectoryResponse)) {
                    subDirectoryResponse = new SubDirectoryResponse(list.getDirectoryName(), list.getDirectoryUrl());
                    List<SubDirectoryResponse> responseList = List.of(subDirectoryResponse);
                    mapAllDirectory.put(mainDirectoryResponse, responseList);
                } else {
                    List<SubDirectoryResponse> responseList1 = new ArrayList<>(mapAllDirectory.get(mainDirectoryResponse));
                    subDirectoryResponse = new SubDirectoryResponse(list.getDirectoryName(), list.getDirectoryUrl());
                    if (!responseList1.contains(subDirectoryResponse)) {
                        responseList1.add(subDirectoryResponse);
                    }
                    mapAllDirectory.put(mainDirectoryResponse, responseList1);
                }
            }
            MainDirectoryFilResponse response;
            for (Map.Entry<MainDirectoryResponse, List<SubDirectoryResponse>> entry : mapAllDirectory.entrySet()) {
                response = new MainDirectoryFilResponse();
                response.setName(entry.getKey().getName());
                response.setImageName(entry.getKey().getImageName());
                if (entry.getValue().size() == 1) {
                    for (SubDirectoryResponse value : entry.getValue()) {
                        if (value.getName() == null || value.getName().isEmpty()) {
                            response.setListSubDirectory(new ArrayList<>());
                        } else {
                            response.setListSubDirectory(entry.getValue());
                        }
                    }
                } else {
                    response.setListSubDirectory(entry.getValue());
                }
                mainDirectoryFilResponses.add(response);
            }

        } catch (Exception e) {
            return ResponseModels.error("Direction Manage Services Exception " + e.getMessage());
        }
        return ResponseModels.success(mainDirectoryFilResponses);
    }

    @Override
    public boolean addAllDirectory() {
        Role cn = roleRepository.findByCode("CN");
        Role kh = roleRepository.findByCode("KH");
        List<Role> roleCN = List.of(cn);
        List<Role> allRoles = List.of(cn, kh);

        MainDirectory buildingManage = new MainDirectory("Qu???n l?? nh??", "file_25-11-2022_10-24-50_home_manage.PNG");
        MainDirectory servicesManage = new MainDirectory("Qu???n l?? d???ch v???", "file_25-11-2022_10-25-12_quan_li_dich_vu.PNG");
        MainDirectory assetManage = new MainDirectory("Qu???n l?? t??i s???n", "file_27-11-2022_10-28-54_quan_li_tai_san.png");
        MainDirectory customerManage = new MainDirectory("Kh??ch thu??", "file_25-11-2022_10-26-09_khach_thue.PNG");
        MainDirectory depositManage = new MainDirectory("H??a ????n", "file_24-11-2022_05-43-15_uil_bill.png");
        MainDirectory settingManage = new MainDirectory("C??i ?????t", "file_25-11-2022_10-26-54_setting.PNG");
        MainDirectory riskManage = new MainDirectory("S??? c???", "file_25-11-2022_10-27-12_su_co.PNG");
        MainDirectory authorManage = new MainDirectory("Ph??n quy???n qu???n l??", "file_25-11-2022_10-27-27_phan_quyen_quan_ly.PNG");
        mainDirectoryManageRepository.saveAll(List.of(buildingManage, servicesManage, assetManage, customerManage, depositManage, settingManage, riskManage, authorManage));


        List<Directory> directoryList = List.of(new Directory("Nh??", roleCN, buildingManage), new Directory("Ph??ng", roleCN, buildingManage),
                new Directory("??i???n n?????c", allRoles, servicesManage), new Directory("H???p ?????ng", allRoles, servicesManage),
                new Directory(roleCN, assetManage),
                new Directory("Kh??ch thu?? ???? c???c", roleCN, customerManage),
                new Directory(roleCN, depositManage),
                new Directory("Th??ng tin c?? nh??n", allRoles, settingManage), new Directory("?????i m???t kh???u", allRoles, settingManage),
                new Directory(roleCN, riskManage),
                new Directory("Lo???i qu???n l??", roleCN, authorManage), new Directory("Danh s??ch qu???n l??", roleCN, authorManage));
        directoryManageRepository.saveAll(directoryList);
        return true;
    }


}
