package com.example.factoryManagement.entities;

import java.util.ArrayList;
import java.util.List;

public class ComenziForma {
    private List<Comanda> comenzi = new ArrayList<>();

    public List<Comanda> getComenzi() {
        return comenzi;
    }

    public void setComenzi(List<Comanda> comenzi) {
        this.comenzi = comenzi;
    }
}
