package com.huayutech.springpersistence.web;

import com.huayutech.springpersistence.domain.association.Department;
import com.huayutech.springpersistence.utils.RandomNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {


    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @PostMapping
    public Department doCreateDepartment(@RequestParam String name) {

        EntityManager em = entityManagerFactory.createEntityManager();

        Department department = new Department();

        department.setId(RandomNumber.random());
        department.setName(name);

        em.getTransaction().begin();
        em.persist(department);
        em.getTransaction().commit();

        return department;

    }

    @GetMapping
    public List<Department> doGetDepartments() {
        List<Department> departments = entityManager.createQuery("select d from Department d", Department.class).getResultList();
        return departments;
    }

}
