package com.javaweb.controller.admin;


import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.BuildingType;
import com.javaweb.enums.DistrictCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import com.javaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller(value = "buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageUtils messageUtils;

    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("modelSearchRequest", model);

        DisplayTagUtils.of(request, model);

        if(SecurityUtils.getAuthorities().contains(SystemConstant.STAFF_ROLE)) {
            Long staffId = SecurityUtils.getPrincipal().getId();
            model.setStaffId(staffId);
            // lấy tất cả toà nhà từ DB hoặc lấy các toà nhà theo BuildingSearchRequest mà nhân viên đó phụ trách
            List<BuildingSearchResponse> result = buildingService.getAllBuildings(model);
            model.setListResult(result);
            model.setTotalItems(buildingService.countTotalItems());

            mav.addObject("buildingResultList", model);
        }
        else {
            // lấy tất cả toà nhà từ DB hoặc lấy các toà nhà theo BuildingSearchRequest
            List<BuildingSearchResponse> result = buildingService.getAllBuildings(model);
            model.setListResult(result);
            model.setTotalItems(buildingService.countTotalItems());

            mav.addObject("buildingResultList", model);
        }

        mav.addObject("staffList", userService.getStaff());
        mav.addObject("districts", DistrictCode.type());
        mav.addObject("typeCodes", BuildingType.type());


        initMessageResponse(mav, request);

        return mav;
    }

    @RequestMapping(value = "/admin/building-edit", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@ModelAttribute("buildingEdit") BuildingDTO buildingDTO, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districts", DistrictCode.type());
        mav.addObject("typeCodes", BuildingType.type());
        initMessageResponse(mav, request);

        return mav;
    }

    @RequestMapping(value = "/admin/building-edit-{id}", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        //xuong DB tim Building theo id
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
        List<String> typeCode = new ArrayList<>();
        for(String str : buildingEntity.getTypeCode().split(",")){
            typeCode.add(str);
        }
        String rentArea = buildingEntity.getRentAreaEntities().stream().map(i -> i.getValue().toString()).collect(Collectors.joining(","));
        buildingDTO.setRentArea(rentArea);
        buildingDTO.setTypeCode(typeCode);

        mav.addObject("buildingEdit", buildingDTO);
        mav.addObject("districts", DistrictCode.type());
        mav.addObject("typeCodes", BuildingType.type());
        return mav;
    }

    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = messageUtils.getMessage(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }
}
