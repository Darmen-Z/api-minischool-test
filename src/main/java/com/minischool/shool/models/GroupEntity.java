package com.minischool.shool.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity( name = "group_entity")
public class GroupEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Name could not be null!")
    private String name;

    @Min(value = 1)
    @Max(value = 4)
    @Digits(integer = 1, fraction = 0)
    @NotEmpty(message = "Course could not be empty.")
    private int course;


    @OneToMany( mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students = new ArrayList<>();


}
