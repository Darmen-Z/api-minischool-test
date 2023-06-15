package com.minischool.shool.repository;

import com.minischool.shool.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllStudentsByGroupId(int groupId);
}
