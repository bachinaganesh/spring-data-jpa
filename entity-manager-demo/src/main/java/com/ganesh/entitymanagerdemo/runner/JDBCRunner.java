package com.ganesh.entitymanagerdemo.runner;

import com.ganesh.entitymanagerdemo.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class JDBCRunner implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Student student = Student.builder()
                .name("Ganesh")
                .gender("Male")
                .address("Chennai")
                .build();

        entityManager.persist(student);

        entityManager.detach(student);

        Student newStudent = entityManager.merge(student);
        log.info("New Student : {}", entityManager.contains(newStudent));
        log.info("Student : {}", entityManager.contains(student));

        student.setAddress("Delhi");


    }
}
