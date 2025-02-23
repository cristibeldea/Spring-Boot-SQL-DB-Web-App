package com.example.loginAndWelcome.entities;

import java.io.Serializable;
import java.util.Objects;

public class DetaliiComandaId implements Serializable {
    private int comanda;
    private int produs;
    public DetaliiComandaId() {}

    public DetaliiComandaId(int comanda, int produs) {
        this.comanda = comanda;
        this.produs = produs;
    }

    public int getComanda() {
        return comanda;
    }

    public void setComanda(int comanda) {
        this.comanda = comanda;
    }

    public int getProdus() {
        return produs;
    }

    public void setProdus(int produs) {
        this.produs = produs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetaliiComandaId that = (DetaliiComandaId) o;
        return Objects.equals(comanda, that.comanda) &&
                Objects.equals(produs, that.produs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comanda, produs);
    }
}

