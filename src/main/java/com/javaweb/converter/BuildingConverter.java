package com.javaweb.converter;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.DistrictCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.utils.MapUtils;
import com.javaweb.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingEntity toBuildingEntity(BuildingDTO buildingDTO){
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        String typeCode = buildingDTO.getTypeCode().stream().map(i -> i.toString()).collect(Collectors.joining(","));
        buildingEntity.setTypeCode(typeCode);

        List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
        for(String str : buildingDTO.getRentArea().split(",")) {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setValue(Long.valueOf(str));
            rentAreaEntity.setBuilding(buildingEntity);
            rentAreaEntities.add(rentAreaEntity);
        }

        buildingEntity.setRentAreaEntities(rentAreaEntities);

        return buildingEntity;
    }

    public BuildingSearchBuilder toBuildingSearchBuilder(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                .setName(MapUtils.getObject(buildingSearchRequest.getName(), String.class))
                .setFloorArea(MapUtils.getObject(buildingSearchRequest.getFloorArea(), Long.class))
                .setDistrict(MapUtils.getObject(buildingSearchRequest.getDistrict(), String.class))
                .setWard(MapUtils.getObject(buildingSearchRequest.getWard(), String.class))
                .setStreet(MapUtils.getObject(buildingSearchRequest.getStreet(), String.class))
                .setNumberOfBasement(MapUtils.getObject(buildingSearchRequest.getNumberOfBasement(), Long.class))
                .setTypeCode(buildingSearchRequest.getTypeCode())
                .setDirection(MapUtils.getObject(buildingSearchRequest.getDirection(), String.class))
                .setLevel(MapUtils.getObject(buildingSearchRequest.getLevel(), Long.class))
                .setAreaFrom(MapUtils.getObject(buildingSearchRequest.getAreaFrom(), Long.class))
                .setAreaTo(MapUtils.getObject(buildingSearchRequest.getAreaTo(), Long.class))
                .setRentPriceFrom(MapUtils.getObject(buildingSearchRequest.getRentPriceFrom(), Long.class))
                .setRentPriceTo(MapUtils.getObject(buildingSearchRequest.getRentPriceTo(), Long.class))
                .setManagerName(MapUtils.getObject(buildingSearchRequest.getManagerName(), String.class))
                .setManagerPhone(MapUtils.getObject(buildingSearchRequest.getManagerPhone(), String.class))
                .setStaffId(MapUtils.getObject(buildingSearchRequest.getStaffId(), Long.class))
                .build();

        return buildingSearchBuilder;
    }

    public BuildingSearchResponse toBuildingSearchResponse(BuildingEntity buildingEntity) {
        BuildingSearchResponse buildingSearchResponse = modelMapper.map(buildingEntity, BuildingSearchResponse.class);

        List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreaEntities();
        String rentAreaValue = rentAreaEntities.stream().map(i -> i.getValue().toString()).collect(Collectors.joining(","));
        buildingSearchResponse.setRentArea(rentAreaValue);

        Map<String, String> districtCode = DistrictCode.type();
        String districtName = "";
        if(StringUtils.check(buildingEntity.getDistrict())) {
            districtName = districtCode.get(buildingEntity.getDistrict());
        }
        buildingSearchResponse.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + districtName);

        return buildingSearchResponse;
    }
}
