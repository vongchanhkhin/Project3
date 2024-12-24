package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.converter.CustomerConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.StatusType;
import com.javaweb.enums.TransactionType;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.ITransactionService;
import com.javaweb.service.IUserService;
import com.javaweb.service.impl.UserService;
import com.javaweb.utils.DisplayTagUtils;
import com.javaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {
    @Autowired
    private IUserService userService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private MessageUtils messageUtils;

    @Autowired
    private CustomerConverter customerConverter;

    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView customerList(@ModelAttribute("modelSearchRequest") CustomerSearchRequest model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/list");

        DisplayTagUtils.of(request, model);

        if (SecurityUtils.getAuthorities().contains(SystemConstant.STAFF_ROLE)) {
            Long staffId = SecurityUtils.getPrincipal().getId();
            model.setStaffId(staffId);
            // lấy tất cả khách hàng từ DB hoặc lấy các khách hàng theo CustomerSearchRequest mà nhân viên đó phụ trách
            List<CustomerSearchResponse> result = customerService.getAllCustomers(model);

            List<CustomerSearchResponse> modifiedResult = new ArrayList<>();
            for (CustomerSearchResponse customerSearchResponse : result) {
                if (StringUtils.isNotEmpty(customerSearchResponse.getStatus())) {
                    for (Map.Entry<String, String> entry : StatusType.type().entrySet()) {
                        if (customerSearchResponse.getStatus().equals(entry.getKey())) {
                            customerSearchResponse.setStatus(entry.getValue());
                            break;
                        }
                    }
                }
                modifiedResult.add(customerSearchResponse);
            }

            model.setListResult(modifiedResult);
            model.setTotalItems(customerService.countTotalItems());

            mav.addObject("customerResultList", model);
        } else {
            // lấy tất cả khách hàng từ DB hoặc lấy các khách hàng theo BuildingSearchRequest
            List<CustomerSearchResponse> result = customerService.getAllCustomers(model);

            List<CustomerSearchResponse> modifiedResult = new ArrayList<>();
            for (CustomerSearchResponse customerSearchResponse : result) {
                if (StringUtils.isNotEmpty(customerSearchResponse.getStatus())) {
                    for (Map.Entry<String, String> entry : StatusType.type().entrySet()) {
                        if (customerSearchResponse.getStatus().equals(entry.getKey())) {
                            customerSearchResponse.setStatus(entry.getValue());
                            break;
                        }
                    }
                }
                modifiedResult.add(customerSearchResponse);
            }

            model.setListResult(modifiedResult);
            model.setTotalItems(customerService.countTotalItems());

            mav.addObject("customerResultList", model);
        }

        mav.addObject("staffList", userService.getStaff());
        initMessageResponse(mav, request);

        return mav;
    }

    @RequestMapping(value = "/admin/customer-edit", method = RequestMethod.GET)
    public ModelAndView customerEdit(@ModelAttribute("customerEdit") CustomerDTO customerDTO, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("statusTypes", StatusType.type());

        return mav;
    }

    @RequestMapping(value = "/admin/customer-edit-{id}", method = RequestMethod.GET)
    public ModelAndView customerEdit(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        //xuong DB tim Customer theo id
        CustomerDTO result = customerService.getCustomerById(id);

        //lay tat ca transaction tu DB thoe customerId
        List<TransactionDTO> transactionDTOS = transactionService.getTransactionsByCustomerId(id);

        mav.addObject("customerEdit", result);
        mav.addObject("transactions", transactionDTOS);
        mav.addObject("statusTypes", StatusType.type());
        mav.addObject("transactionTypes", TransactionType.type());

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
