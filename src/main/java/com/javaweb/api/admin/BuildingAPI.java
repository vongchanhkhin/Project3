package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
@Transactional
public class BuildingAPI {
    @Autowired
    IBuildingService buildingService;

    @PostMapping
    public ResponseEntity<Object> addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO) {
        try {
            buildingService.addOrUpdateBuilding(buildingDTO);
            return ResponseEntity.ok(buildingDTO);
        }
        catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @DeleteMapping("/{ids}")
    public void deleteBuilding(@PathVariable Long[] ids) {
        //xuong DB xoa building theo id gui ve
        System.out.println("ok");
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO getStaffs(@PathVariable Long id){
        ResponseDTO result = buildingService.getAllStaffInAssignmentBuilding(id);
        return result;
    }

    @PostMapping("/assignment")
    public void updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        System.out.println("ok");
    }
}
