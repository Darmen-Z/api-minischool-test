package com.minischool.shool.service;

import com.minischool.shool.dto.GradeDto;
import com.minischool.shool.dto.StudentDto;
import com.minischool.shool.models.Grade;
import com.minischool.shool.models.Student;

import java.util.List;

public interface StudentService {

    List<StudentDto> getAllStudents();

    StudentDto getStudent(int studentId);

    List<StudentDto> getStudentsByGroupId(int groupId);

    StudentDto addStudent(StudentDto studentDto);

    StudentDto addStudentToGroup(int groupId, int studentId);

    StudentDto updatedStudent(int studentId, StudentDto studentDto);

    void deleteStudent(int studentId);

    List<StudentDto> deleteStudentGetList(int studentId);

    StudentDto editStudentGrades(int studentId, List<GradeDto> gradeList);

}
