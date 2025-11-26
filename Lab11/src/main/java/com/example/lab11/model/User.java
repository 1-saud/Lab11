package com.example.lab11.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;


    @NotBlank
    @Column(unique = true , nullable = false)
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Email
    @Column(unique = true , nullable = false)
    private String email;


    @Column (nullable = false)
    private LocalDate registrationDate = LocalDate.now();



}
