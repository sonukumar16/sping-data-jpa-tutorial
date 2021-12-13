package com.codebuffer.spring.data.jpa.tutorial.repository;

import com.codebuffer.spring.data.jpa.tutorial.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
