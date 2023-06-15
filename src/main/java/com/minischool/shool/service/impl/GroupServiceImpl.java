package com.minischool.shool.service.impl;

import com.minischool.shool.dto.GroupDto;
import com.minischool.shool.dto.GroupDtoForStudent;
import com.minischool.shool.dto.StudentDto;
import com.minischool.shool.dto.StudentDtoForGroup;
import com.minischool.shool.exceptions.GroupNotFoundException;
import com.minischool.shool.models.GroupEntity;
import com.minischool.shool.models.Student;
import com.minischool.shool.repository.GroupRepository;
import com.minischool.shool.repository.StudentRepository;
import com.minischool.shool.service.GroupService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    private StudentRepository studentRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, StudentRepository studentRepository) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<GroupDto> getAllGroups(boolean isOrder) {
        if (isOrder){
            List<GroupEntity> groups = groupRepository.orderGroupsByName();
            return groups.stream().map(group -> mapToDto(group)).collect(Collectors.toList());
        }
        else {
            List<GroupEntity> groups = groupRepository.findAll();
            return groups.stream().map(group -> mapToDto(group)).collect(Collectors.toList());
        }
    }

    @Override
    public GroupDto getGroup(int id) {
        GroupEntity group = groupRepository.findById(id).orElseThrow(
                () -> new GroupNotFoundException("Group not found!"));
        return mapToDto(group);
    }

    @Override
    public GroupDto createGroup(GroupDto groupDto) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setCourse(groupDto.getCourse());
        groupEntity.setName(groupDto.getName());

        GroupEntity newGroupEntity = groupRepository.save(groupEntity);

        GroupDto groupDtoResponse = new GroupDto();
        groupDtoResponse.setId(newGroupEntity.getId());
        groupDtoResponse.setName(newGroupEntity.getName());
        groupDtoResponse.setCourse(newGroupEntity.getCourse());

        return groupDtoResponse;
    }

    @Override
    public GroupDto updateGroup(int id, GroupDto groupDto) {
        GroupEntity group = groupRepository.findById(id).orElseThrow(
                () -> new GroupNotFoundException("Group could not be updated!"));

        group.setName(groupDto.getName());
        group.setCourse(groupDto.getCourse());

        GroupEntity updatedGroup = groupRepository.save(group);
        return mapToDto(updatedGroup);
    }

    @Override
    public void deleteGroup(int id) {
        GroupEntity group = groupRepository.findById(id).orElseThrow(
                () -> new GroupNotFoundException("Group not found!"));
        groupRepository.delete(group);
    }

    @Override
    public List<GroupDto> deleteGroupGetAll(int id) {
        GroupEntity group = groupRepository.findById(id).orElseThrow(
                () -> new GroupNotFoundException("Group not found!"));
        groupRepository.delete(group);
        List<GroupEntity> groupEntities = groupRepository.findAll();

        List<GroupDto> groupDtos = new ArrayList<>(groupEntities.stream().map(this::mapToDto).toList());

        return groupDtos;
    }


    private GroupDto mapToDto(GroupEntity group){
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setCourse(group.getCourse());
        groupDto.setName(group.getName());

        List<Student> students = new ArrayList<>();
        students.addAll(group.getStudents());

        List<StudentDtoForGroup> studentDtos = new ArrayList<>();
        studentDtos.addAll(students.stream().map(student -> mapToDto(student)).collect(Collectors.toList()));


        groupDto.setStudentList(studentDtos);
        groupDto.setStudentList(group.getStudents().stream().map(student -> mapToDto(student)).collect(Collectors.toList()));
        return groupDto;
    }

    public StudentDtoForGroup mapToDto(Student student){
        StudentDtoForGroup studentDto = new StudentDtoForGroup();
        studentDto.setId(student.getId());
        studentDto.setFirstname(student.getFirstname());
        studentDto.setLastname(student.getLastname());
        return studentDto;
    }

    private GroupDtoForStudent mapToDtoForStudentDto(GroupEntity group){
        GroupDtoForStudent groupDto = new GroupDtoForStudent();
        groupDto.setId(group.getId());
        groupDto.setCourse(group.getCourse());
        groupDto.setName(group.getName());
        return groupDto;
    }

    private GroupEntity mapToEntity(GroupDto groupDto){
        GroupEntity group = new GroupEntity();
        group.setCourse(groupDto.getCourse());
        group.setName(groupDto.getName());
        return group;
    }
}
