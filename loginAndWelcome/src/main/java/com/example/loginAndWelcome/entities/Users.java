package com.example.loginAndWelcome.entities;

import jakarta.persistence.*;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_User")
    private Integer idUser;

    @Column(name = "user_name")
    private String user;

    @Column(name = "password")
    private String password;

    @Column(name = "isAdmin")
    private boolean isAdmin;

    public Integer getIdUser(){
        return idUser;
    }

    public void setIdUser(Integer idUser){
        this.idUser = idUser;
    }

    public String getUsername(){
        return user;
    }

    public void setUsername(String user){
        this.user = user;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public boolean getIsAdmin(){
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }
}
