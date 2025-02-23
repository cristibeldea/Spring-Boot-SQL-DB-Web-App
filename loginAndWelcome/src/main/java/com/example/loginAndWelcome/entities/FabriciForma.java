package com.example.loginAndWelcome.entities;

import java.util.ArrayList;
import java.util.List;

public class FabriciForma {
    private List<Fabrica> fabrici = new ArrayList<>();

    public List<Fabrica> getFabrici() {
        return fabrici;
    }

    public void setFabrici(List<Fabrica> fabrici) {
        this.fabrici = fabrici;
    }
}
