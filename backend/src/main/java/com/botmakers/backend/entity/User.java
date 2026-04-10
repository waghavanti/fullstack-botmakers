package com.botmakers.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data = generates getters, setters, toString automatically (Lombok)
// @Builder = lets you build objects like User.builder().name("John").build()
// @NoArgsConstructor = generates empty constructor
// @AllArgsConstructor = generates constructor with all fields
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users") // maps to "users" table in your database
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true) 
    private String email;

    @Column(nullable = false)
    private String password; // this will be stored encrypted, not plain text

    @Enumerated(EnumType.STRING) // saves "USER" or "ADMIN" as text in DB
    @Column(nullable = false)
    private Role role;
}