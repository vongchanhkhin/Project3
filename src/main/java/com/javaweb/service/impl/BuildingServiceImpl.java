package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.converter.BuildingSearchResponseConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IRentAreaService;
import com.javaweb.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BuildingServiceImpl implements IBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Autowired
    private IRentAreaService rentAreaService;

    @Autowired
    private BuildingSearchResponseConverter buildingSearchResponseConverter;

    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;


    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Override
    public ResponseDTO getAllStaffInAssignmentBuilding(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        List<UserEntity> staff = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<AssignmentBuildingEntity> assignmentBuildingEntities = building.getAssignmentBuildingEntities();

        List<UserEntity> staffAssignment = new ArrayList<>();
        for (AssignmentBuildingEntity assignmentBuildingEntity : assignmentBuildingEntities) {
            UserEntity userEntity = assignmentBuildingEntity.getStaff();
            staffAssignment.add(userEntity);
        }

        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();

        for (UserEntity user : staff) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(user.getId());
            staffResponseDTO.setFullName(user.getFullName());

            if (staffAssignment.contains(user)) {
                staffResponseDTO.setChecked("checked");
            } else staffResponseDTO.setChecked("");

            staffResponseDTOS.add(staffResponseDTO);
        }

        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("Success");

        return responseDTO;
    }

    @Override
    public List<BuildingSearchResponse> getAllBuildings(BuildingSearchRequest buildingSearchRequest, Pageable pageable) {
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder, pageable);

        List<BuildingSearchResponse> result = new ArrayList<>();
        for (BuildingEntity buildingEntity : buildingEntities) {
            BuildingSearchResponse buildingSearchResponse = buildingSearchResponseConverter.toBuildingSearchResponse(buildingEntity);
            result.add(buildingSearchResponse);
        }

        return result;
    }

    @Override
    public void addOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        String typeCode = buildingDTO.getTypeCode().stream().map(i -> i.toString()).collect(Collectors.joining(","));
        buildingEntity.setTypeCode(typeCode);

        buildingRepository.save(buildingEntity);

        if (buildingDTO.getId() == null) {
            buildingDTO.setId(buildingEntity.getId());
        }
        if (StringUtils.check(buildingDTO.getRentArea())) {
            rentAreaService.addRentArea(buildingDTO);
        }
    }

    @Override
    public void deleteBuildings(Long[] ids) {
        rentAreaRepository.deleteByBuildingIdIn(ids);
        assignmentBuildingRepository.deleteByBuildingIdIn(ids);
        buildingRepository.deleteByIdIn(ids);
    }

    @Override
    public int countTotalItems() {
        return buildingRepository.countTotalItems();
    }
}
