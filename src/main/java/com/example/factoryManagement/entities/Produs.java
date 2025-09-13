package com.example.factoryManagement.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Produs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Produs")
    private int idProdus;

    @Column(name = "Nume_Produs")
    private String numeProdus;

    @Column(name = "Categorie")
    private String categorie;

    @Column(name = "Pret")
    private double pret;


    @DateTimeFormat(pattern = "mm-dd-yyyy")
    @Column(name = "Data_Fabricarii")
    private String dataFabricarii;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ID_Fabrica", referencedColumnName = "ID_Fabrica")
    private Fabrica fabrica;


    // Getters and Setters
    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public String getDataFabricarii() {
        return dataFabricarii;
    }

    public void setDataFabricarii(String dataFabricarii) {
        this.dataFabricarii = dataFabricarii;
    }

    public int getIdFabrica() {
        return fabrica.getIdFabrica();
    }

    public void setIdFabrica(Integer idFabrica) {
        this.fabrica.setIdFabrica(idFabrica);
    }

    public Fabrica getFabrica() {return fabrica;}

    public void setFabrica(Fabrica fabrica){
        this.fabrica = fabrica;
    }

}

