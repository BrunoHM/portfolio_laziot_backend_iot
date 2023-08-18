package com.senai.laziot.user;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;


@Builder @Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "user")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "hashPassword")
    private String hashPassword;

    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "hashUniqueCode")
    private String hashUniqueCode;

    @Column(name = "active")
    private boolean active;

    public UsuarioEntity(String name, String lastname, String hashPassword, String email, String hashUniqueCode){
        this.name = name;
        this.lastname = lastname;
        this.hashPassword = hashPassword;
        this.email = email;
        this.hashUniqueCode = hashUniqueCode;
        this.active = true;
    }
}
