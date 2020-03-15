package com.example.pss.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Role")
@Getter
@Setter


public class Role {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "roleName")
    private String roleName="user";
}
