package com.example.factoryManagement.entities;

import jakarta.persistence.*;

@Entity
@IdClass(DetaliiComandaId.class)
public class Detalii_Comanda {

    @Column(name = "Cantitate")
    private int cantitate;

    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ID_Comanda", referencedColumnName = "ID_Comanda")
    private Comanda comanda;

    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ID_Produs", referencedColumnName = "ID_Produs")
    private Produs produs;

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Integer getIdProdus(){ return produs.getIdProdus();}

    public void setIdProdus(Integer idProdus){
        this.produs.setIdProdus(idProdus);
    }

    public Integer getIdComanda() {
        return comanda.getIdComanda();
    }

    public void setIdComanda(Integer idcomanda) {
        this.comanda.setIdComanda(idcomanda);
    }

    public Produs getProdus() {
        return produs;
    }

    public void setProdus(Produs produs) {
        this.produs = produs;
    }
}
