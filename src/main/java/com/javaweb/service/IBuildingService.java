package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;

import java.util.List;

public interface IBuildingService {
    ResponseDTO getAllStaffInAssignmentBuilding(Long buildingId);
    List<BuildingEntity> getAllBuildings();
}
