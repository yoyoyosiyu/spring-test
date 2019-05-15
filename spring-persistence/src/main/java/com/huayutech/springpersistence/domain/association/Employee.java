package com.huayutech.springpersistence.domain.association;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Employee {

    @Id
    Long id;

    @Column
    String firstName;

    @Column
    String middleName;

    @Column
    String lastName;

    @JsonIgnoreProperties("employees")
    @ManyToOne(fetch = FetchType.LAZY)
    Department department;

    protected  Employee() {

    }

    public Employee(Department department, String firstName, String middleName, String lastName) {

        this.department = department;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;

    }

}
