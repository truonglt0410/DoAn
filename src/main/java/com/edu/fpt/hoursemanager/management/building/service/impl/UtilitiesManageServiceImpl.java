package com.edu.fpt.hoursemanager.management.building.service.impl;

import com.edu.fpt.hoursemanager.management.building.entity.Building;
import com.edu.fpt.hoursemanager.management.building.entity.Utilities;
import com.edu.fpt.hoursemanager.management.building.repository.BuildingManageRepository;
import com.edu.fpt.hoursemanager.management.building.repository.UtilitiesManageRepository;
import com.edu.fpt.hoursemanager.management.building.service.UtilitiesManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UtilitiesManageServiceImpl implements UtilitiesManageService {
    @Autowired
    private UtilitiesManageRepository utilitiesManageRepository;

    @Autowired
    private BuildingManageRepository buildingManageRepository;

    public List<String> stringToList(String name) {
        List<String> list = new ArrayList<>();
        String[] listName = name.split(",");
        for (int i = 0; i < listName.length; i++) {
            if (!listName[i].isEmpty()) {
                list.add(listName[i]);
            }
        }
        return list;
    }

    // check utilities exist in database
    public List<Utilities> checkExistUtilities(String roles, String utilities) {
        List<Utilities> utilitiesAddToBuilding = new ArrayList<>();
        List<String> listRoles = stringToList(roles);
        List<String> listUtilities = stringToList(utilities);
        for (String s : listRoles) {
            Utilities u = utilitiesManageRepository.checkExist(s);
            if( u != null){
                utilitiesAddToBuilding.add(u);
            }else {
                Utilities util = new Utilities();
                util.setName(s);
                util.setType(true);
                util.setCreatedDate(LocalDate.now());
                utilitiesManageRepository.save(util);
                utilitiesAddToBuilding.add(util);
            }
        }
        for (String s : listUtilities) {
            Utilities u = utilitiesManageRepository.checkExist(s);
            if( u != null){
                utilitiesAddToBuilding.add(u);
            }else {
                Utilities util = new Utilities();
                util.setName(s);
                util.setType(false);
                util.setCreatedDate(LocalDate.now());
                utilitiesManageRepository.save(util);
                utilitiesAddToBuilding.add(util);
            }
        }
        return utilitiesAddToBuilding;
    }

    @Override
    public boolean addUtilitiesToBuilding(Building building, String roles, String utilities) {
        boolean saveRules = false;
        List<Utilities> utilitiesAddToBuilding = checkExistUtilities(roles, utilities);
        try {
            if (utilitiesAddToBuilding != null) {
                building.setUtilities(utilitiesAddToBuilding);
                saveRules = true;
            }
        } catch (Exception e) {
            throw new RuntimeException("Utilities Services Exception :" + e.getMessage());
        }
        return saveRules;
    }

}
