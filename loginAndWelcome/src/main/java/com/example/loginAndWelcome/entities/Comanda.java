package com.example.loginAndWelcome.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Comanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Comanda")
    private Integer idComanda;

    @Column(name = "Data_Comenzii")
    private String dataComenzii;

    @Column(name = "Total")
    private double total;


    @Column(name = "Status")
    private String status;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ID_Client", referencedColumnName = "ID_Client")
    private Client client;

//    @OneToMany(mappedBy = "comanda")
//    private List<DetaliiComanda> detaliiComenzi;

    public Integer getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(Integer idComanda) {
        this.idComanda = idComanda;
    }

    public String getDataComenzii() {
        return dataComenzii;
    }

    public void setDataComenzii(String dataComenzii) {
        this.dataComenzii = dataComenzii;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getIdClient() {
        return client.getIdClient();
    }

    public void setIdClient(Integer idClient) {
        this.client.setIdClient(idClient);
    }

//    public List<DetaliiComanda> getDetaliiComenzi() {
//        return detaliiComenzi;
//    }
//
//    public void setDetaliiComenzi(List<DetaliiComanda> detaliiComenzi) {
//        this.detaliiComenzi = detaliiComenzi;
//    }
}

