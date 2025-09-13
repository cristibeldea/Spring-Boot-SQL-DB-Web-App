package com.example.factoryManagement.entities;

import java.util.ArrayList;
import java.util.List;

public class AngajatiForma {
    private List<Angajat> angajati = new ArrayList<>();

    public List<Angajat> getAngajati() {
        return angajati;
    }

    public void setAngajati(List<Angajat> angajati) {
        this.angajati = angajati;
    }
}