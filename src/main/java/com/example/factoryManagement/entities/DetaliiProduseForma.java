package com.example.factoryManagement.entities;

import java.util.ArrayList;
import java.util.List;

public class DetaliiProduseForma {
    private List<Detalii_Produs> detaliiProduse = new ArrayList<>();

    public List<Detalii_Produs> getDetaliiProduse() {
        return detaliiProduse;
    }

    public void setDetaliiProduse(List<Detalii_Produs> detaliiProduse) {
        this.detaliiProduse = detaliiProduse;
    }
}
