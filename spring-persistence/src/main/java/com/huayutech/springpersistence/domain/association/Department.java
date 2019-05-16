package com.huayutech.springpersistence.domain.association;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Department {

    @Id
    Long id;

    @NotNull
    @Column(unique = true)
    String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Employee> employees = new HashSet<>();


}
