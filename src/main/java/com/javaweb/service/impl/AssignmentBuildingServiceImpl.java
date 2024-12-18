package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.service.IAssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//@Service
//@Transactional
public class AssignmentBuildingServiceImpl {
//    @Autowired
//    private AssignmentBuildingRepository assignmentBuildingRepository;
//
//    @Override
//    public void addAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
//        assignmentBuildingRepository.deleteByBuildingId(assignmentBuildingDTO.getBuildingId());
//
//        BuildingEntity buildingEntity = new BuildingEntity();
//        buildingEntity.setId(assignmentBuildingDTO.getBuildingId());
//
//        List<UserEntity> staffs = new ArrayList<>();
//        for (Long staffId : assignmentBuildingDTO.getStaffs()) {
//            UserEntity staff = new UserEntity();
//            staff.setId(staffId);
//            staffs.add(staff);
//        }
//
//        for (UserEntity staff : staffs) {
//            AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
//            assignmentBuildingEntity.setBuilding(buildingEntity);
//            assignmentBuildingEntity.setStaff(staff);
//            assignmentBuildingRepository.save(assignmentBuildingEntity);
//        }
//    }
}
