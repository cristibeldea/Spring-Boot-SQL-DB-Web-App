package com.example.loginAndWelcome.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Client")
    private Integer idClient;

    @Column(name = "Nume")
    private String nume;

    @Column(name = "Adresa")
    private String adresa;

    @Column(name = "Telefon")
    private String telefon;

    @Column(name = "Email")
    private String email;

    @Column(name = "Sex")
    private String sex;

//    @OneToMany(mappedBy = "client")
//    private List<Comanda> comenzi;

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

//    public List<Comanda> getComenzi() {
//        return comenzi;
//    }
//
//    public void setComenzi(List<Comanda> comenzi) {
//        this.comenzi = comenzi;
//    }
}

