package com.edu.fpt.hoursemanager.management.building.service;

import com.edu.fpt.hoursemanager.management.building.entity.Building;

public interface UtilitiesManageService {
    boolean addUtilitiesToBuilding(Building building, String roles, String utilities);

}
