package com.example.factoryManagement.entities;

import java.util.ArrayList;
import java.util.List;

public class DetaliiComenziForma {
    private List<Detalii_Comanda> detaliiComenzi = new ArrayList<>();

    public List<Detalii_Comanda> getDetaliiComenzi() {
        return detaliiComenzi;
    }

    public void setDetaliiComenzi(List<Detalii_Comanda> detaliiComenzi) {
        this.detaliiComenzi = detaliiComenzi;
    }
}

