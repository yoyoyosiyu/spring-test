package com.huayutech.springpersistence.domain.association;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class EmployeeAddress {

    @EmbeddedId
    EmployeeAddressId id;


    @ManyToOne
    @JoinColumn(name = "employeeId", insertable = false, updatable = false)
    //@MapsId("employeeId")
    Employee employee;


    @OneToOne
    @JoinColumn(name = "addressId", insertable = false, updatable = false)
    //@MapsId("addressId")
    Address address;

}
