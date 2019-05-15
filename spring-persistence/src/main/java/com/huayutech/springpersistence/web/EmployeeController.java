package com.huayutech.springpersistence.web;

import com.huayutech.springpersistence.domain.association.Department;
import com.huayutech.springpersistence.domain.association.Employee;
import com.huayutech.springpersistence.utils.RandomNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @PersistenceContext
    EntityManager entityManager;


    @Transactional
    @PostMapping
    public ResponseEntity doCreateEmployee(@RequestBody Employee employee) {

        employee.setId(RandomNumber.random());

        if (employee.getDepartment() != null && employee.getDepartment().getName() != null) {

            TypedQuery<Department> query = entityManager.createQuery("select d from Department d where d.name = :name", Department.class).setParameter("name", employee.getDepartment().getName());

            try {
                Department department = query.getSingleResult();
                employee.setDepartment(department);
            }
            catch (NoResultException ex) {
                return new ResponseEntity(String.format("未能找到%s部门", employee.getDepartment().getName()), HttpStatus.NOT_FOUND);
            }
        }

        entityManager.persist(employee);
        return new ResponseEntity(employee, HttpStatus.OK);

    }

    @Transactional
    @GetMapping
    public ResponseEntity doListAllEmployees() {
        TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e left join fetch e.department d", Employee.class);
        List<Employee> employees = query.getResultList();


//        TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e left join Department d on e.department=d", Employee.class);
//        List<Employee> employees = query.getResultList();


        return new ResponseEntity(employees, HttpStatus.OK);
    }


    @GetMapping("/{departmentId}")
    public ResponseEntity doListEmployeesByDepartment(@PathVariable Long departmentId) {
        TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e where e.department.id = :departmentId", Employee.class)
                .setParameter("departmentId", departmentId);

        List<Employee> employees = query.getResultList();

        return new ResponseEntity(employees, HttpStatus.OK);
    }

}
