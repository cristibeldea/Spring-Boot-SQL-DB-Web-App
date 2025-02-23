package com.example.loginAndWelcome.services;
import com.example.loginAndWelcome.entities.*;
import com.example.loginAndWelcome.repositories.AngajatRepository;
import com.example.loginAndWelcome.repositories.ClientRepository;
import com.example.loginAndWelcome.repositories.FabricaRepository;
import com.example.loginAndWelcome.repositories.ProdusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class Verificari {

    private FabricaRepository FabricaRepo;
    private ClientRepository ClientRepo;

    @Autowired
    public Verificari(FabricaRepository fabricaRepo, ClientRepository clientRepo){
        FabricaRepo = fabricaRepo;
        ClientRepo = clientRepo;
        this.fabriciExistente = FabricaRepo.getAllFabrica();
        this.clientiExistenti = ClientRepo.getAllClient();
    }
    List<Fabrica> fabriciExistente;
    List<Client> clientiExistenti;

    String[] optiuniValideSex = {"M", "F", ""};
    private static final String TELEFON_REGEX = "^(\\+40)?(07[0-9]{8}|0251[0-9]{6}|0351[0-9]{6})$";
//            "^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$";
    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String DATA_REGEX =
            "^(0[1-9]|[12][0-9]|3[01])[./-](0[1-9]|1[0-2])[./-](19[0-9]{2}|20[0-9]{2})$";


    public boolean checkFabriciValide(List<Fabrica> fabrici){
        for( Fabrica fabrica: fabrici){
            if(!checkTelefon(fabrica) || !checkEmail(fabrica)){
                return false;
            }
        }
        return true;
    }

    public boolean checkTelefon(Fabrica fabrica){
        String telefon = fabrica.getTelefon();
        if(Objects.equals(telefon, "")){
            return true;
        }
        else if(telefon == null){
            return false;
        }

        if(telefon.matches(TELEFON_REGEX)){
            return true;
        }
//        if(!(telefon.startsWith("07") || telefon.startsWith("0251") || telefon.startsWith("0351"))){
//            return false;
//        }
//        if(telefon.length()!=10){
//            return false;
//        }
//        for(int i=0; i<telefon.length(); i++){
//            if(!Character.isDigit(telefon.charAt(i))){
//                return false;
//            }
//        }

        return false;
    }

    public boolean checkEmail(Fabrica fabrica){
        String email = fabrica.getEmail();
        if(Objects.equals(email, "")){
            return true;
        }
        else if(email == null){
            return false;
        }

        if(email.matches(EMAIL_REGEX)){
            return true;
        }

        return false;
    }

    public boolean checkAngajatiValizi(List<Angajat> angajati){
        for (Angajat angajat: angajati){
            if(!checkDataAngajarii(angajat) || !checkAngajatFabricaExistenta(angajat) || !checkGen(angajat)){
                return false;
            }
        }
        return true;
    }

    public boolean checkDataAngajarii(Angajat angajat){
        if(Objects.equals(angajat.getDataAngajarii(), "")){
            return true;
        }
        if((angajat.getDataAngajarii()).matches(DATA_REGEX)){
            System.out.println("DATA CORECTA!");
            return true;
        }
        else {
            System.out.println("DATA !INCORECTA!");
            return false;
        }
    }

    public boolean checkAngajatFabricaExistenta(Angajat angajat){
        for(Fabrica fabrica: fabriciExistente){
            if(Objects.equals(angajat.getIdFabrica(), fabrica.getIdFabrica())){
                return true;
            }
        }
        return false;
    }

    public boolean checkGen(Angajat angajat){
        if (Arrays.asList(optiuniValideSex).contains(angajat.getSex())) {
            return true;
        }
        return false;
    }

    public boolean checkProduseValide(List<Produs> produse){
        for(Produs produs: produse){
            if(!checkDataFabricarii(produs) || !checkProdusFabricaExistenta(produs)){
                return false;
            }
        }
        return true;
    }

    public boolean checkDataFabricarii(Produs produs){
        System.out.println("DATA FABRICARII:");
        System.out.println(produs.getDataFabricarii());
        if(Objects.equals(produs.getDataFabricarii(), "")){
            return true;
        }

        if((produs.getDataFabricarii()).matches(DATA_REGEX)){
            System.out.println("DATA PRODUS CORECTA!");
            return true;
        }
        else {
            System.out.println("DATA PRODUS !INCORECTA!");
            return false;
        }
    }

    public boolean checkProdusFabricaExistenta(Produs produs){
        for(Fabrica fabrica: fabriciExistente){
            if(Objects.equals(produs.getIdFabrica(), fabrica.getIdFabrica())){
                return true;
            }
        }
        return false;
    }

    public boolean checkComenziValide(List<Comanda> comenzi){
        for(Comanda comanda: comenzi){
            if(!checkDataComenzii(comanda) || !checkComandaClientExistent(comanda)){
                return false;
            }
        }

        return true;
    }

    public boolean checkDataComenzii(Comanda comanda){
        if(Objects.equals(comanda.getDataComenzii(), "")){
            return true;
        }

        if((comanda.getDataComenzii()).matches(DATA_REGEX)){
            System.out.println("DATA COMANDA CORECTA!");
            return true;
        }
        else {
            System.out.println("DATA COMANDA !INCORECTA!");
            return false;
        }
    }

    public boolean checkComandaClientExistent(Comanda comanda){
        for(Client client: clientiExistenti){
            if(Objects.equals(client.getIdClient(), comanda.getIdClient())){
                return true;
            }
        }
        return false;
    }

    public boolean checkClientiValizi(List<Client> clienti){
        for(Client client: clienti){
            if(!checkGenClient(client) || !checkEmailClient(client) || !checkTelefonClient(client)){
                return false;
            }
        }
        return true;

    }

    public boolean checkGenClient(Client client){
        if (Arrays.asList(optiuniValideSex).contains(client.getSex())) {
            return true;
        }
        return false;
    }

    public boolean checkEmailClient(Client client){
        String email = client.getEmail();
        if(Objects.equals(email, "")){
            return true;
        }
        else if(email == null){
            return false;
        }

        if(email.matches(EMAIL_REGEX)){
            return true;
        }

        return false;
    }

    public boolean checkTelefonClient(Client client){
        String telefon = client.getTelefon();
        if(Objects.equals(telefon, "")){
            return true;
        }
        else if(telefon == null){
            return false;
        }

        if(telefon.matches(TELEFON_REGEX)){
            return true;
        }

        return false;
    }

}
