package com.example.loginAndWelcome.entities;

import java.util.ArrayList;
import java.util.List;

public class MaterialeForma {
    private List<Material> materiale = new ArrayList<>();

    public List<Material> getMateriale() {
        return materiale;
    }

    public void setMateriale(List<Material> materiale) {
        this.materiale = materiale;
    }
}

