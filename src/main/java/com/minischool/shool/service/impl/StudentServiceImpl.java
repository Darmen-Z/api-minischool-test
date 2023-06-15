package com.minischool.shool.service.impl;

import com.minischool.shool.dto.GradeDto;
import com.minischool.shool.dto.GroupDto;
import com.minischool.shool.dto.GroupDtoForStudent;
import com.minischool.shool.dto.StudentDto;
import com.minischool.shool.exceptions.GroupNotFoundException;
import com.minischool.shool.exceptions.StudentNotFoundException;
import com.minischool.shool.models.Grade;
import com.minischool.shool.models.GroupEntity;
import com.minischool.shool.models.Student;
import com.minischool.shool.repository.GroupRepository;
import com.minischool.shool.repository.StudentRepository;
import com.minischool.shool.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private GroupRepository groupRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        return students.stream().map(student -> mapToDto(student)).collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudent(int studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new StudentNotFoundException("Student could not be found!"));

        return mapToDto(student);
    }

    @Override
    public List<StudentDto> getStudentsByGroupId(int groupId) {
        Optional<GroupEntity> group = groupRepository.findById(groupId);

        List<Student> students = group.get().getStudents();

        List<StudentDto> studentDtos = new ArrayList<>();

        studentDtos.addAll(students.stream().map(student -> mapToDto(student)).collect(Collectors.toList()));
        return studentDtos;
    }

    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setFirstname(studentDto.getFirstname());
        student.setLastname(studentDto.getLastname());
        student.setGradeList(studentDto.getGradeList());

        GroupEntity group = groupRepository.findById(studentDto.getGroupId()).orElseThrow(
                () -> new GroupNotFoundException("Group could not be found!")
        );

        student.setGroup(group);

        Student newStudent = studentRepository.save(student);

        return mapToDto(newStudent);
    }

    @Override
    public StudentDto addStudentToGroup(int groupId, int studentId) {
        GroupEntity group = groupRepository.findById(groupId).orElseThrow(
                () -> new GroupNotFoundException("Group could not be found!")
        );

        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new StudentNotFoundException("Student could not be found!")
        );

        student.setGroup(group);

        Student updatedStudent = studentRepository.save(student);

        return mapToDto(updatedStudent);
    }

    @Override
    public StudentDto updatedStudent(int studentId, StudentDto studentDto) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new StudentNotFoundException("Student could not be found!")
        );

        student.setGroup(student.getGroup());
        student.setFirstname(studentDto.getFirstname());
        student.setLastname(studentDto.getLastname());
        student.setGradeList(studentDto.getGradeList());


        Student updatedStudent = studentRepository.save(student);
        return mapToDto(updatedStudent);
    }

    @Override
    public void deleteStudent(int studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new StudentNotFoundException("Student could not be found!")
        );

        studentRepository.delete(student);
    }

    @Override
    public List<StudentDto> deleteStudentGetList(int studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new StudentNotFoundException("Student could not be found!")
        );
        studentRepository.delete(student);
        List<Student> students = studentRepository.findAll();

        List<StudentDto> studentDtos = new ArrayList<>(students.stream().map(this::mapToDto).toList());

        return studentDtos;
    }

    @Override
    public StudentDto editStudentGrades(int studentId, List<GradeDto> gradeList) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new StudentNotFoundException("Student could not be found!")
        );

        student.setGradeList(gradeList.stream().map(gradeDto -> mapToEntity(gradeDto)).collect(Collectors.toList()));
        Student updatedStudent = studentRepository.save(student);

        return mapToDto(student);
    }


    public StudentDto mapToDto(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setFirstname(student.getFirstname());
        studentDto.setLastname(student.getLastname());
        studentDto.setGradeList(student.getGradeList());
        studentDto.setGroupId(student.getGroup().getId());
        return studentDto;
    }



    private GroupDtoForStudent mapToDto(GroupEntity group){

        GroupDtoForStudent groupDto = new GroupDtoForStudent();
        groupDto.setId(group.getId());
        groupDto.setCourse(group.getCourse());
        groupDto.setName(group.getName());
        return groupDto;
    }

    public Grade mapToEntity(GradeDto gradeDto){
        Grade grade = new Grade();
        grade.setValue(gradeDto.getValue());
        return grade;
    }




}
