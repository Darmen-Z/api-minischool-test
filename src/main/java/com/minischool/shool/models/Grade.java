package com.minischool.shool.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity ( name = "grades_list")
public class Grade {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private int value;

}
