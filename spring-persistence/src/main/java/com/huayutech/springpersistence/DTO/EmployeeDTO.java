package com.huayutech.springpersistence.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Setter
@Getter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO {


    Optional<String> firstName;

    Optional<String> middleName;

    Optional<String> lastName;


}
