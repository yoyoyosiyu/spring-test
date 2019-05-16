package com.huayutech.springpersistence.web;

import com.huayutech.springpersistence.domain.association.*;
import com.huayutech.springpersistence.utils.RandomNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    @PostMapping("/{employeeId}/addresses")
    public ResponseEntity doAddAddressTo(@PathVariable Long employeeId, @RequestBody Address address){

        Employee employee = entityManager.find(Employee.class, employeeId);

        if (employee == null ) throw new EntityNotFoundException(String.format("未能找到#%d的雇员信息", employeeId));


        address.setId(RandomNumber.random());
        entityManager.persist(address);

        EmployeeAddress employeeAddress = new EmployeeAddress();

        employeeAddress.setId(new EmployeeAddressId(employee.getId(), address.getId()));

        //employeeAddress.setEmployee(employee);
        //employeeAddress.setAddress(address);

        entityManager.persist(employeeAddress);

        return new ResponseEntity(HttpStatus.OK);

    }

    @Transactional
    @GetMapping("/{employeeId}/addresses")
    public ResponseEntity doGetAddresses(@PathVariable Long employeeId) {
        Employee employee = entityManager.find(Employee.class, employeeId);
        if (employee == null ) throw new EntityNotFoundException(String.format("未能找到#%d的雇员信息", employeeId));

        List<Address> addresses = entityManager.createQuery("select a from Address a left join EmployeeAddress ea on ea.address = a where ea.employee = :employee", Address.class)
                .setParameter("employee", employee)
                .getResultList();

        return new ResponseEntity(addresses, HttpStatus.OK);
    }


    @Transactional
    @GetMapping
    public ResponseEntity doListAllEmployees() {
        TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e left join fetch e.department d", Employee.class);
        List<Employee> employees = query.getResultList();

        return new ResponseEntity(employees, HttpStatus.OK);
    }


    @GetMapping("/department/{departmentId}")
    public ResponseEntity doListEmployeesByDepartment(@PathVariable Long departmentId) {

        Department department = entityManager.find(Department.class, departmentId);

        if (department == null)
            throw new EntityNotFoundException(String.format("#%d部门不存在", departmentId));

        Set<Employee> employees = department.getEmployees();

        return new ResponseEntity(employees, HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity doGetEmployee(@PathVariable Long employeeId) {

        Employee employee = entityManager.find(Employee.class, employeeId);

        if (employee == null)
            new EntityNotFoundException(String.format("未能找到#%d的雇员信息", employeeId));

        String name = employee.getDepartment().getName();

        return new ResponseEntity(employee, HttpStatus.OK);

    }

}
