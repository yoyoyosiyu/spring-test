package com.huayutech.springpersistence.domain.association;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@Embeddable
public class EmployeeAddressId implements Serializable {

    Long employeeId;

    Long addressId;


    protected EmployeeAddressId() {

    }


    public EmployeeAddressId(Long employeeId, Long addressId) {

        this.employeeId = employeeId;
        this.addressId = addressId;
    }

}
