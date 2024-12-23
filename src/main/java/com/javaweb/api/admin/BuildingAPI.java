package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.IAssignmentBuildingService;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private IBuildingService buildingService;

    @PostMapping
    public ResponseEntity<?> addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO) {
        try {
            buildingService.addOrUpdateBuilding(buildingDTO);
            return ResponseEntity.ok(buildingDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deleteBuilding(@PathVariable Long[] ids) {
        // Validate input
        if (ids == null || ids.length == 0) {
            return ResponseEntity.badRequest().body("No IDs provided");
        }
        //xuong DB xoa building theo id gui ve
        try {
            buildingService.deleteBuildings(ids);
            return ResponseEntity.noContent().build(); // HTTP 204: No Content
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO getStaffs(@PathVariable Long id) {
        ResponseDTO result = buildingService.getAllStaffInAssignmentBuilding(id);
        return result;
    }

    @PutMapping()
    public ResponseEntity<?> updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        try {
            buildingService.addAssignmentStaff(assignmentBuildingDTO);
            return ResponseEntity.ok(assignmentBuildingDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
