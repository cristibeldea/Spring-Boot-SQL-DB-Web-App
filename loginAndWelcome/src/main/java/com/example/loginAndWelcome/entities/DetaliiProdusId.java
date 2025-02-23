package com.example.loginAndWelcome.entities;

import java.io.Serializable;
import java.util.Objects;

public class DetaliiProdusId implements Serializable {
    private int produs;
    private int material;

    public DetaliiProdusId() {}

    public DetaliiProdusId(int produs, int material) {
        this.produs = produs;
        this.material = material;
    }

    public int getProdus() {
        return produs;
    }

    public void setProdus(int produs) {
        this.produs = produs;
    }

    public int getMaterial() {
        return material;
    }

    public void setMaterial(int material) {
        this.material = material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetaliiProdusId that = (DetaliiProdusId) o;
        return Objects.equals(produs, that.produs) &&
                Objects.equals(material, that.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produs, material);
    }
}
