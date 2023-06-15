package com.minischool.shool.dto;

import com.minischool.shool.models.Grade;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudentDto {

    private int id;
    private String firstname;
    private String lastname;
    private int groupId;
//    private GroupDtoForStudent group;
    private List<Grade> gradeList = new ArrayList<>();

}
