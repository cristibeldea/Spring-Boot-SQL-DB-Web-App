package com.example.factoryManagement.entities;

import java.util.ArrayList;
import java.util.List;

public class ClientiForma {
    private List<Client> clienti = new ArrayList<>();

    public List<Client> getClienti() {
        return clienti;
    }

    public void setClienti(List<Client> clienti) {
        this.clienti = clienti;
    }
}
