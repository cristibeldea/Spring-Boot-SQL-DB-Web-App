package com.example.loginAndWelcome.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Fabrica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Fabrica")
    private Integer idFabrica;

    @Column(name="Nume")
    private String nume;

    @Column(name = "Adresa")
    private String adresa;

    @Column(name = "Telefon")
    private String telefon;

    @Column(name ="Email")
    private String email;

//    @OneToMany(mappedBy = "fabrica")
//    private List<Produs> produse;
//
//    @OneToMany(mappedBy = "fabrica")
//    private List<Angajat> angajati;

    public Integer getIdFabrica(){
        return this.idFabrica;
    }

    public void setIdFabrica(Integer idFabrica) {
        this.idFabrica = idFabrica;
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
    
}
