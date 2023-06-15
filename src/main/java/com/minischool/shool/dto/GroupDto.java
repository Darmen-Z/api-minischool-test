package com.minischool.shool.dto;


import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class GroupDto {
    private int id;


    @NotNull(message = "Name could not be null!")
    private String name;

    @Min(value = 1)
    @Max(value = 4)
    @Digits(integer = 1, fraction = 0)
    @NotEmpty(message = "Course could not be empty.")
    private int course;


    private List<StudentDtoForGroup> studentList;
}
