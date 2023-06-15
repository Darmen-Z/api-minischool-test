package com.minischool.shool.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "student")
public class Student {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    private String firstname;
    private String lastname;

    @OneToMany (cascade = CascadeType.ALL)
    private List<Grade> gradeList = new ArrayList<>();

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "group.id")
    private GroupEntity group;

}
