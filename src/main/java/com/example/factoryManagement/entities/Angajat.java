package com.example.factoryManagement.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Angajat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Angajat")
    private int idAngajat;

    @Column(name = "Nume")
    private String nume;

    @Column(name = "Adresa")
    private String adresa;

    @Column(name = "Functie")
    private String functie;

    @Column(name = "Salariu")
    private double salariu;

    @Column(name = "Data_Angajarii")
    private String dataAngajarii;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ID_Fabrica", referencedColumnName = "ID_Fabrica")
    private Fabrica fabrica;

    @Column(name = "Sex")
    private String sex;

    public int getIdAngajat() {
        return idAngajat;
    }

    public void setIdAngajat(int idAngajat) {
        this.idAngajat = idAngajat;
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

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public double getSalariu() {
        return salariu;
    }

    public void setSalariu(double salariu) {
        this.salariu = salariu;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDataAngajarii() {
        return dataAngajarii;
    }

    public void setDataAngajarii(String dataAngajarii) {
        this.dataAngajarii = dataAngajarii;
    }

    public Integer getIdFabrica() {
        return fabrica.getIdFabrica();
    }

    public void setIdFabrica(Integer idFabrica) {
        this.fabrica.setIdFabrica(idFabrica);
    }

    public Fabrica getFabrica() { return fabrica;}

    public void setFabrica(Fabrica fabrica){ this.fabrica = fabrica;}
}
