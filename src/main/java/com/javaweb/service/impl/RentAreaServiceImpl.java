package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.IRentAreaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentAreaServiceImpl implements IRentAreaService {
    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addRentArea(BuildingDTO buildingDTO) {
        RentAreaEntity rentAreaEntity = new RentAreaEntity();
        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity.setId(buildingDTO.getId());

        rentAreaEntity.setBuilding(buildingEntity);

        for(String str : buildingDTO.getRentArea().split(",")){
            rentAreaEntity.setValue(Long.valueOf(str));
            rentAreaRepository.save(rentAreaEntity);
        }
    }
}
