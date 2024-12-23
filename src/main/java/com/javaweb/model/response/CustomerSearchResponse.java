package com.javaweb.model.response;

import com.javaweb.model.dto.AbstractDTO;

import java.util.Date;

public class CustomerSearchResponse extends AbstractDTO {
    private String name;
    private String phone;
    private String email;
    private String demand;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public CustomerSearchResponse setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerSearchResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDemand() {
        return demand;
    }

    public CustomerSearchResponse setDemand(String demand) {
        this.demand = demand;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public CustomerSearchResponse setStatus(String status) {
        this.status = status;
        return this;
    }

}
