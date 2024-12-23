package com.javaweb.service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBuildingService {
    ResponseDTO getAllStaffInAssignmentBuilding(Long buildingId);
    List<BuildingSearchResponse> getAllBuildings(BuildingSearchRequest buildingSearchRequest);
    void addOrUpdateBuilding(BuildingDTO buildingDTO);
    void deleteBuildings(Long[] ids);
    int countTotalItems();
    void addAssignmentStaff(AssignmentBuildingDTO assignmentBuildingDTO);
}
