package com.minischool.shool.dto;

import com.minischool.shool.models.Grade;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudentDtoForGroup {

    private int id;
    private String firstname;
    private String lastname;
//    private List<Grade> gradeList = new ArrayList<>();


}
