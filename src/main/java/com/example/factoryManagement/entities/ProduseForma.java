package com.example.factoryManagement.entities;

import java.util.ArrayList;
import java.util.List;

public class ProduseForma {
    private List<Produs> produse = new ArrayList<>();

    public List<Produs> getProduse() {
        return produse;
    }
    public void setProduse(List<Produs> produse) {
        this.produse = produse;
    }
}
