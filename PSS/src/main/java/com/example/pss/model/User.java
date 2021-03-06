package com.example.pss.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "User")
@Getter
@Setter


public class User {

    @Id
    @GeneratedValue
    @Column(name="iduser") //obowiazkowe
    private Integer iduser;

    @Column(name="companyName",nullable=false) //obowiazkowe
    private String companyName;

    @Column(name="companyAddress",nullable=false) //obowiazkowe
    private String companyAddress;

    @Column(name="comapnyNip",nullable=false) //obowiazkowe
    private String companyNip;

    @Column(name="name",nullable=false) //obowiazkowe
    private String name;

    @Column(name="lastName",nullable=false) //obowiazkowe
    private String lastName;

    @Column(name="email",nullable=false) //obowiazkowe
    private String email;

    @Column(name="password",nullable=false) //obowiazkowe
    private String password;

    @Column(name="status")
    private Integer status = 1;

    @Column(name="registrationDate")
    private LocalDate registrationDate = LocalDate.now();

    @Column(name="roleName") //relacja uzytkownikow do rol
    @ManyToMany
    private Set<Role> roleName;

    @OneToMany
    @JoinColumn(name="iddelegation") //relacja uzytkownika do delegacji
    private List<Delegation> delegation;
}
