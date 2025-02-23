package com.example.loginAndWelcome.entities;

import jakarta.persistence.*;

@Entity
@IdClass(DetaliiProdusId.class)
public class Detalii_Produs {

    @Column (name = "Cantitate_material")
    private int cantitate;

    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ID_Produs", referencedColumnName = "ID_Produs")
    private Produs produs;

    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ID_Material", referencedColumnName = "ID_Material")
    private Material material;

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public Produs getProdus() {
        return produs;
    }

    public void setProdus(Produs produs) {
        this.produs = produs;
    }

    public Integer getIdProdus() {
        return produs.getIdProdus();
    }

    public void setIdProdus(Integer Idprodus) {
        this.produs.setIdProdus(Idprodus);
    }

    public Integer getIdMaterial() {
        return material.getIdMaterial();
    }

    public void setIdMaterial(Integer idMaterial) {
        this.material.setIdMaterial(idMaterial);
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
