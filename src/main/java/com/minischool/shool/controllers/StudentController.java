package com.minischool.shool.controllers;


import com.minischool.shool.dto.GradeDto;
import com.minischool.shool.dto.StudentDto;
import com.minischool.shool.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    ResponseEntity<List<StudentDto>> getAllStudents(){
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    ResponseEntity<StudentDto> getStudent(@PathVariable(name = "id") int id){
        return new ResponseEntity<>(studentService.getStudent(id), HttpStatus.OK);
    }

    @PutMapping("/all/{id}/edit-grades")
    ResponseEntity<StudentDto> updateGrades(@PathVariable(name = "id") int studentId, @RequestBody List<GradeDto> gradeDtos){
        return new ResponseEntity<>(studentService.editStudentGrades(studentId, gradeDtos), HttpStatus.OK);
    }

    @PutMapping("/all/{id}/update")
    ResponseEntity<StudentDto> updateStudent(@PathVariable(name = "id") int studentId , @RequestBody StudentDto studentDto){
        return new ResponseEntity<>(studentService.updatedStudent(studentId, studentDto), HttpStatus.OK);
    }

//    @DeleteMapping("/all/{id}/delete")
//    ResponseEntity<String> deleteStudent(@PathVariable(name = "id") int studentId){
//        studentService.deleteStudent(studentId);
//        return new ResponseEntity<>("Student deleted successfully.", HttpStatus.OK);
//    }

    @DeleteMapping("/all/{id}/delete")
    ResponseEntity<List<StudentDto>> deleteStudent(@PathVariable(name = "id") int studentId){
        return new ResponseEntity<>(studentService.deleteStudentGetList(studentId), HttpStatus.OK);
    }

    @GetMapping("/all/group-{id}")
    ResponseEntity<List<StudentDto>> getStudentsByGroup(@PathVariable(name = "id") int id){
        return new ResponseEntity<>(studentService.getStudentsByGroupId(id), HttpStatus.OK);
    }

    @PostMapping("/all/create-student")
    ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto){
        System.out.println("CRASEFERg");
        return new ResponseEntity<>(studentService.addStudent(studentDto), HttpStatus.CREATED);
    }

    @PutMapping("/all/{id}/set-group")
    ResponseEntity<StudentDto> addStudentToGroup(@PathVariable(name = "id") int studentId,@RequestParam(name = "group_id") int groupId){
        return ResponseEntity.ok(studentService.addStudentToGroup(groupId, studentId));
    }

}
