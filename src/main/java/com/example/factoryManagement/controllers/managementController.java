package com.example.factoryManagement.controllers;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.factoryManagement.entities.*;
import com.example.factoryManagement.repositories.*;
import com.example.factoryManagement.services.InterogareService;
import com.example.factoryManagement.services.Verificari;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.thymeleaf.util.StringUtils.length;

@Controller
public class  managementController {
    private FabricaRepository FabricaRepo;

    private AngajatRepository AngajatRepo;

    private ProdusRepository ProdusRepo;

    private MaterialRepository MaterialRepo;

    private DetaliiProdusRepository DetProdusRepo;

    private DetaliiComandaRepository DetComandaRepo;

    private ComandaRepository ComandaRepo;

    private ClientRepository ClientRepo;

    private Verificari verificator;

    private InterogareService interogareService;

    @Autowired
    public managementController(InterogareService interogareService, Verificari verificator, FabricaRepository fabricaRepo, AngajatRepository angajatRepo, ProdusRepository produsRepo, MaterialRepository materialRepo, DetaliiProdusRepository detProdusRepo, DetaliiComandaRepository detComandaRepo, ComandaRepository comandaRepo, ClientRepository clientRepo) {
        FabricaRepo = fabricaRepo;
        AngajatRepo = angajatRepo;
        ProdusRepo = produsRepo;
        MaterialRepo = materialRepo;
        DetProdusRepo = detProdusRepo;
        DetComandaRepo = detComandaRepo;
        ComandaRepo = comandaRepo;
        ClientRepo = clientRepo;
        this.verificator = verificator;
        this.interogareService = interogareService;
    }

    List<Fabrica> fabriciLista;
    List<Angajat> angajatiLista;

    List<Produs> produseLista;

    List<Material> materialeLista;

    List<Detalii_Produs> detProdLista;

    List<Detalii_Comanda> detComLista;

    List<Comanda> comenziLista;

    List<Client> clientiLista;

    @GetMapping("/welcome")
    public String afiseazaPaginaWelcome(Model model) {
        if (model.containsAttribute("fabriciForma")) {
            FabriciForma fabriciForma = (FabriciForma) model.getAttribute("fabriciForma");
            model.addAttribute("fabriciForma", fabriciForma);
        } else {
            fabriciLista = FabricaRepo.getAllFabrica();
            FabriciForma fabriciForma = new FabriciForma();
            fabriciForma.setFabrici(fabriciLista);
            model.addAttribute("fabriciForma", fabriciForma);
        }

        if (model.containsAttribute("angajatiForma")) {
            AngajatiForma angajatiForma = (AngajatiForma) model.getAttribute("angajatiForma");
            model.addAttribute("angajatiForma", angajatiForma);
        } else {
            angajatiLista = AngajatRepo.getAllAngajat();
            AngajatiForma angajatiForma = new AngajatiForma();
            angajatiForma.setAngajati(angajatiLista);
            model.addAttribute("angajatiForma", angajatiForma);
        }

        if (model.containsAttribute("produseForma")) {
            ProduseForma produseForma = (ProduseForma) model.getAttribute("produseForma");
            model.addAttribute("produseForma", produseForma);
        } else {
            produseLista = ProdusRepo.getAllProdus();
            ProduseForma produseForma = new ProduseForma();
            produseForma.setProduse(produseLista);
            model.addAttribute("produseForma", produseForma);
        }
        if (model.containsAttribute("materialeForma")) {
            MaterialeForma materialeForma = (MaterialeForma) model.getAttribute("materialeForma");
            model.addAttribute("materialeForma", materialeForma);
        } else {
            materialeLista = MaterialRepo.getAllMaterial();
            MaterialeForma materialeForma = new MaterialeForma();
            materialeForma.setMateriale(materialeLista);
            model.addAttribute("materialeForma", materialeForma);
        }

        if (model.containsAttribute("comenziForma")) {
            ComenziForma comenziForma = (ComenziForma) model.getAttribute("comenziForma");
            model.addAttribute("comenziForma", comenziForma);
        } else {
            comenziLista = ComandaRepo.getAllComanda();
            ComenziForma comenziForma = new ComenziForma();
            comenziForma.setComenzi(comenziLista);
            model.addAttribute("comenziForma", comenziForma);
        }

        if (model.containsAttribute("clientiForma")) {
            ClientiForma clientiForma = (ClientiForma) model.getAttribute("clientiForma");
            model.addAttribute("clientiForma", clientiForma);
        } else {
            clientiLista = ClientRepo.getAllClient();
            ClientiForma clientiForma = new ClientiForma();
            clientiForma.setClienti(clientiLista);
            System.out.println("########################################");
            System.out.println("CLIENTI LISTA: ");
            for (Client client: clientiLista){
                System.out.println(client);
            }
            System.out.println("########################################");


            model.addAttribute("clientiForma", clientiForma);
        }

        return "welcome";
    }


    @PostMapping("/update-fabrici")
    @Transactional
    public String updateFabrici(RedirectAttributes redirectAttributes,
                                @ModelAttribute FabriciForma fabriciForma,
                                @RequestParam(value = "stergeIdFabrica", required = false) Integer stergeIdFabrica) {
        List<Fabrica> fabrici = fabriciForma.getFabrici();

        if (stergeIdFabrica != null) {
            redirectAttributes.addFlashAttribute("arata_StergeFabrica", true);
            redirectAttributes.addFlashAttribute("idDeStersFabrica", stergeIdFabrica);

            return "redirect:/welcome";
        }

        if (verificator.checkFabriciValide(fabrici)) {
            for (Fabrica fabrica : fabrici) {
                FabricaRepo.updateFabrica(
                        fabrica.getIdFabrica(),
                        fabrica.getNume(),
                        fabrica.getAdresa(),
                        fabrica.getTelefon(),
                        fabrica.getEmail()
                );
            }
        } else {
            redirectAttributes.addFlashAttribute("mesajFabricaGresita", "Una sau mai multe valori introduse nu sunt valide!");
            return "redirect:/welcome#titluFabrici";
        }

        return "redirect:/welcome#titluFabrici";
    }

    @PostMapping("/update-angajati")
    @Transactional
    public String updateAngajati(RedirectAttributes redirectAttributes,
                                 @ModelAttribute AngajatiForma angajatiForma,
                                 @RequestParam(required = false) Integer stergeIdAngajat) {
        List<Angajat> angajati = angajatiForma.getAngajati();

        if (stergeIdAngajat != null) {
            redirectAttributes.addFlashAttribute("arata_StergeAngajat", true);
            redirectAttributes.addFlashAttribute("idDeStersAngajat", stergeIdAngajat);

            return "redirect:/welcome";
        }
        if (verificator.checkAngajatiValizi(angajati)) {
            for (Angajat angajat : angajati) {
                AngajatRepo.updateAngajat(
                        angajat.getIdAngajat(),
                        angajat.getNume(),
                        angajat.getAdresa(),
                        angajat.getFunctie(),
                        angajat.getSalariu(),
                        angajat.getDataAngajarii(),
                        angajat.getIdFabrica(),
                        angajat.getSex()
                );
            }
        } else {
            redirectAttributes.addFlashAttribute("mesajAngajatGresit", "Una sau mai multe valori introduse nu sunt valide!");
            return "redirect:/welcome#titluAngajati";
        }

        return "redirect:/welcome#titluAngajati";
    }

    @PostMapping("/update-produse")
    @Transactional
    public String updateProduse(RedirectAttributes redirectAttributes,
                                @ModelAttribute ProduseForma produseForma,
                                @RequestParam(required = false) Integer stergeIdProdus) {
        List<Produs> produse = produseForma.getProduse();
        if (stergeIdProdus != null) {
            redirectAttributes.addFlashAttribute("arata_StergeProdus", true);
            redirectAttributes.addFlashAttribute("idDeStersProdus", stergeIdProdus);

            System.out.println("###############################");
            System.out.println("Produs ID to delete: " + stergeIdProdus);
            System.out.println("###############################");

            return "redirect:/welcome";
        }
        if (verificator.checkProduseValide(produse)) {
            for (Produs produs : produse) {
                ProdusRepo.updateProdus(
                        produs.getIdProdus(),
                        produs.getNumeProdus(),
                        produs.getCategorie(),
                        produs.getPret(),
                        produs.getIdFabrica(),
                        produs.getDataFabricarii()
                );
            }
        } else {
            redirectAttributes.addFlashAttribute("mesajProdusGresit", "Una sau mai multe valori introduse nu sunt valide!");
            return "redirect:/welcome#titluProduse";
        }

        return "redirect:/welcome#titluProduse";
    }

    @PostMapping("/update-materiale")
    @Transactional
    public String updateMateriale(RedirectAttributes redirectAttributes,
                                  @ModelAttribute MaterialeForma materialeForma,
                                  @RequestParam(required = false) Integer stergeIdMaterial) {
        List<Material> materiale = materialeForma.getMateriale();

        if (stergeIdMaterial != null) {
            redirectAttributes.addFlashAttribute("arata_StergeMaterial", true);
            redirectAttributes.addFlashAttribute("idDeStersMaterial", stergeIdMaterial);

            return "redirect:/welcome#titluMateriale";
        }

            for (Material material : materiale) {
                MaterialRepo.updateMaterial(
                        material.getIdMaterial(),
                        material.getNume(),
                        material.getDescriere()
                );
            }

        return "redirect:/welcome#titluMateriale";
    }


    @PostMapping("/update-comenzi")
    @Transactional
    public String updateComenzi(RedirectAttributes redirectAttributes,
                                @ModelAttribute ComenziForma comenziForma,
                                @RequestParam(required = false) Integer stergeIdComanda) {
        List<Comanda> comenzi = comenziForma.getComenzi();
        if (stergeIdComanda != null) {
            redirectAttributes.addFlashAttribute("arata_StergeComanda", true);
            redirectAttributes.addFlashAttribute("idDeStersComanda", stergeIdComanda);

            return "redirect:/welcome";
        }
        if (verificator.checkComenziValide(comenzi)) {
            for (Comanda comanda : comenzi) {
                ComandaRepo.updateComanda(
                        comanda.getIdComanda(),
                        comanda.getDataComenzii(),
                        comanda.getTotal(),
                        comanda.getStatus(),
                        comanda.getIdClient()
                );
            }
        } else {
            redirectAttributes.addFlashAttribute("mesajComandaGresita", "Una sau mai multe valori introduse nu sunt valide!");
            return "redirect:/welcome#titluComenzi";
        }

        return "redirect:/welcome#titluComenzi";
    }

    @PostMapping("/update-clienti")
    @Transactional
    public String updateClienti(RedirectAttributes redirectAttributes,
                                @ModelAttribute ClientiForma clientiForma,
                                @RequestParam(required = false) Integer stergeIdClient) {
        List<Client> clienti = clientiForma.getClienti();

        if (stergeIdClient != null) {
            redirectAttributes.addFlashAttribute("arata_StergeClient", true);
            redirectAttributes.addFlashAttribute("idDeStersClient", stergeIdClient);

            return "redirect:/welcome#titluClienti";
        }
        if (verificator.checkClientiValizi(clienti)) {
            for (Client client : clienti) {
                ClientRepo.updateClient(
                        client.getIdClient(),
                        client.getNume(),
                        client.getAdresa(),
                        client.getTelefon(),
                        client.getEmail()
                );
            }
        } else {
            redirectAttributes.addFlashAttribute("mesajClientGresit", "Una sau mai multe valori introduse nu sunt valide!");
            return "redirect:/welcome#titluClienti";
        }

        return "redirect:/welcome#titluClienti";
    }


    @PostMapping("/adauga-fabrica")
    @Transactional
    public String insertFabrica(@ModelAttribute("fabriciForma") FabriciForma fabriciForma, Model model, RedirectAttributes redirectAttributes) {
        Fabrica fabricaNoua = new Fabrica();
        fabricaNoua.setNume("");
        fabricaNoua.setAdresa("");
        fabricaNoua.setEmail("");
        fabricaNoua.setTelefon("");

        FabricaRepo.insertFabrica(fabricaNoua.getNume(), fabricaNoua.getAdresa(), fabricaNoua.getTelefon(), fabricaNoua.getEmail());

        List<Fabrica> updatedFabrici = FabricaRepo.getAllFabrica();
        fabriciForma.setFabrici(updatedFabrici);
        redirectAttributes.addFlashAttribute("fabriciForma", fabriciForma);
        updatedFabrici.forEach(f -> System.out.println("Fabrica: " + f.getNume()));

        return "redirect:/welcome#ultima-fabrica";
    }

    @PostMapping("/adauga-angajat")
    @Transactional
    public String insertAngajat(@ModelAttribute("angajatiForma") AngajatiForma angajatiForma, Model model, RedirectAttributes redirectAttributes) {
        Angajat angajatNou = new Angajat();
        angajatNou.setNume("");
        angajatNou.setAdresa("");
        angajatNou.setFunctie("");
        angajatNou.setSalariu(0);
        angajatNou.setDataAngajarii("");
        angajatNou.setFabrica(this.fabriciLista.get(0));
        angajatNou.setSex("");

        AngajatRepo.insertAngajat(angajatNou.getNume(),
                angajatNou.getAdresa(),
                angajatNou.getFunctie(),
                angajatNou.getSalariu(),
                angajatNou.getDataAngajarii(),
                angajatNou.getIdFabrica(),
                angajatNou.getSex());

        List<Angajat> updatedAngajati = AngajatRepo.getAllAngajat();
        angajatiForma.setAngajati(updatedAngajati);
        redirectAttributes.addFlashAttribute("angajatiForma", angajatiForma);

        return "redirect:/welcome#ultimul-angajat";
    }

    @PostMapping("/adauga-produs")
    @Transactional
    public String insertProdus(@ModelAttribute("produseForma") ProduseForma produseForma, Model model, RedirectAttributes redirectAttributes) {
        Produs produsNou = new Produs();
        produsNou.setNumeProdus("");
        produsNou.setCategorie("");
        produsNou.setPret(0);
        produsNou.setFabrica(this.fabriciLista.get(0));
        produsNou.setDataFabricarii("");

        ProdusRepo.insertProdus(produsNou.getNumeProdus(),
                produsNou.getCategorie(),
                produsNou.getPret(),
                produsNou.getIdFabrica(),
                produsNou.getDataFabricarii());

        List<Produs> updatedProduse = ProdusRepo.getAllProdus();
        produseForma.setProduse(updatedProduse);
        redirectAttributes.addFlashAttribute("produseForma", produseForma);
        updatedProduse.forEach(f -> System.out.println("Produs: " + f.getNumeProdus()));

        return "redirect:/welcome#ultimul-produs";
    }

    @PostMapping("/adauga-material")
    @Transactional
    public String insertMaterial(@ModelAttribute("materialeForma") MaterialeForma materialeForma, Model model, RedirectAttributes redirectAttributes) {
        Material materialNou = new Material();
        materialNou.setNume("");
        materialNou.setDescriere("");

        MaterialRepo.insertMaterial(materialNou.getNume(), materialNou.getDescriere());

        List<Material> updatedMateriale = MaterialRepo.getAllMaterial();
        materialeForma.setMateriale(updatedMateriale);
        redirectAttributes.addFlashAttribute("materialeForma", materialeForma);
        updatedMateriale.forEach(m -> System.out.println("Material: " + m.getNume()));

        return "redirect:/welcome#ultimul-material";
    }

    @PostMapping("/adauga-comanda")
    @Transactional
    public String insertComanda(@ModelAttribute("comenziForma") ComenziForma comenziForma, Model model, RedirectAttributes redirectAttributes) {
        Comanda comandaNoua = new Comanda();
        comandaNoua.setDataComenzii("");
        comandaNoua.setTotal(0.0);
        comandaNoua.setStatus("");
        comandaNoua.setClient(clientiLista.get(0));

        ComandaRepo.insertComanda(comandaNoua.getDataComenzii(), comandaNoua.getTotal(), comandaNoua.getStatus(), comandaNoua.getIdClient());

        List<Comanda> updatedComenzi = ComandaRepo.getAllComanda();
        comenziForma.setComenzi(updatedComenzi);
        redirectAttributes.addFlashAttribute("comenziForma", comenziForma);
        updatedComenzi.forEach(c -> System.out.println("Comanda: " + c.getStatus()));

        return "redirect:/welcome#ultima-comanda";
    }

    @PostMapping("/adauga-client")
    @Transactional
    public String insertClient(@ModelAttribute("clientiForma") ClientiForma clientiForma, Model model, RedirectAttributes redirectAttributes) {
        Client clientNou = new Client();
        clientNou.setNume("");
        clientNou.setAdresa("");
        clientNou.setTelefon("");
        clientNou.setSex("");

        ClientRepo.insertClient(clientNou.getNume(), clientNou.getAdresa(), clientNou.getTelefon(), clientNou.getSex());

        List<Client> updatedClienti = ClientRepo.getAllClient();
        clientiForma.setClienti(updatedClienti);
        redirectAttributes.addFlashAttribute("clientiForma", clientiForma);
        updatedClienti.forEach(cli -> System.out.println("Client: " + cli.getNume()));

        return "redirect:/welcome#ultimul-client";
    }


    @GetMapping("/cauta")
    public String search(
            @RequestParam(required = false) String cautare,
            @RequestParam String domeniu,
            RedirectAttributes redirectAttributes
    ) {
        System.out.println("DOMENIUL: " +domeniu);
        List<Fabrica> listaCautata_1 = new ArrayList<>();
        List<Angajat> listaCautata_2 = new ArrayList<>();
        List<Produs> listaCautata_3 = new ArrayList<>();
        List<Material> listaCautata_4 = new ArrayList<>();
        List<Detalii_Comanda> listaCautata_5 = new ArrayList<>();
        List<Detalii_Produs> listaCautata_6 = new ArrayList<>();
        List<Comanda> listaCautata_7 = new ArrayList<>();
        List<Client> listaCautata_8 = new ArrayList<>();
        List<Object[]> listaToate = new ArrayList<>();

        switch (domeniu) {
            case "Fabrici":
                if (cautare == null || cautare.trim().isEmpty()) {
                    listaCautata_1 = FabricaRepo.getAllFabrica();
                } else {
                    listaCautata_1 = FabricaRepo.gasesteDupa("%" + cautare + "%");
                }
                if (listaCautata_1.isEmpty()) {
                    redirectAttributes.addFlashAttribute("mesaj_niciunRezultat1", "Nu au fost găsite rezultate...");
                }
                break;

            case "Angajati":
                if (cautare == null || cautare.trim().isEmpty()) {
                    listaCautata_2 = AngajatRepo.getAllAngajat();
                } else {
                    listaCautata_2 = AngajatRepo.gasesteDupa("%" + cautare + "%");
                }
                if (listaCautata_2.isEmpty()) {
                    redirectAttributes.addFlashAttribute("mesaj_niciunRezultat2", "Nu au fost găsite rezultate...");
                }
                break;

            case "Produse":
                if (cautare == null || cautare.trim().isEmpty()) {
                    listaCautata_3 = ProdusRepo.getAllProdus();
                } else {
                    listaCautata_3 = ProdusRepo.gasesteDupa("%" + cautare + "%");
                }
                if (listaCautata_3.isEmpty()) {
                    redirectAttributes.addFlashAttribute("mesaj_niciunRezultat3", "Nu au fost găsite rezultate...");
                }
                break;

            case "Materiale":
                if (cautare == null || cautare.trim().isEmpty()) {
                    listaCautata_4 = MaterialRepo.getAllMaterial();
                } else {
                    listaCautata_4 = MaterialRepo.gasesteDupa("%" + cautare + "%");
                }
                if (listaCautata_4.isEmpty()) {
                    redirectAttributes.addFlashAttribute("mesaj_niciunRezultat4", "Nu au fost găsite rezultate...");
                }
                break;

            case "DetaliiComanda":
                if (cautare == null || cautare.trim().isEmpty()) {
                    listaCautata_5 = DetComandaRepo.getAllDetaliiComanda();
                } else {
                    listaCautata_5 = DetComandaRepo.gasesteDupa("%" + cautare + "%");
                }
                if (listaCautata_5.isEmpty()) {
                    redirectAttributes.addFlashAttribute("mesaj_niciunRezultat", "Nu au fost găsite rezultate...");
                }
                break;

            case "DetaliiProdus":
                if (cautare == null || cautare.trim().isEmpty()) {
                    listaCautata_6 = DetProdusRepo.getAllDetaliiProdus();
                } else {
                    listaCautata_6 = DetProdusRepo.gasesteDupa("%" + cautare + "%");
                }
                if (listaCautata_6.isEmpty()) {
                    redirectAttributes.addFlashAttribute("mesaj_niciunRezultat", "Nu au fost găsite rezultate...");
                }
                break;

            case "Comenzi":
                if (cautare == null || cautare.trim().isEmpty()) {
                    listaCautata_7 = ComandaRepo.getAllComanda();
                } else {
                    listaCautata_7 = ComandaRepo.gasesteDupa("%" + cautare + "%");
                }
                if (listaCautata_7.isEmpty()) {
                    redirectAttributes.addFlashAttribute("mesaj_niciunRezultat5", "Nu au fost găsite rezultate...");
                }
                break;

            case "Clienti":
                if (cautare == null || cautare.trim().isEmpty()) {
                    listaCautata_8 = ClientRepo.getAllClient();
                } else {
                    listaCautata_8 = ClientRepo.gasesteDupa("%" + cautare + "%");
                }
                if (listaCautata_8.isEmpty()) {
                    redirectAttributes.addFlashAttribute("mesaj_niciunRezultat6", "Nu au fost găsite rezultate...");
                }
                break;
            case "Toate":
                if (cautare == null || cautare.trim().isEmpty()) {
                    listaCautata_1 = FabricaRepo.getAllFabrica();
                    listaCautata_2 = AngajatRepo.getAllAngajat();
                    listaCautata_3 = ProdusRepo.getAllProdus();
                    listaCautata_4 = MaterialRepo.getAllMaterial();
                    listaCautata_5 = DetComandaRepo.getAllDetaliiComanda();
                    listaCautata_6 = DetProdusRepo.getAllDetaliiProdus();
                    listaCautata_7 = ComandaRepo.getAllComanda();
                    listaCautata_8 = ClientRepo.getAllClient();
                } else {
                    listaCautata_1 = FabricaRepo.gasesteDupa("%"+cautare+"%");
                    listaCautata_2 = AngajatRepo.gasesteDupa("%"+cautare+"%");
                    listaCautata_3 = ProdusRepo.gasesteDupa("%"+cautare+"%");
                    listaCautata_4 = MaterialRepo.gasesteDupa("%"+cautare+"%");
//                    listaCautata_5 = DetComandaRepo.gasesteDupa("%"+cautare+"%");
//                    listaCautata_6 = DetProdusRepo.gasesteDupa("%"+cautare+"%");
                    listaCautata_7 = ComandaRepo.gasesteDupa("%"+cautare+"%");
                    listaCautata_8 = ClientRepo.gasesteDupa("%"+cautare+"%");

                }
        if (listaCautata_1.isEmpty()) {
            redirectAttributes.addFlashAttribute("mesaj_niciunRezultat1", "Nu au fost găsite rezultate pentru Fabrici...");
        }
        if (listaCautata_2.isEmpty()) {
            redirectAttributes.addFlashAttribute("mesaj_niciunRezultat2", "Nu au fost găsite rezultate pentru Angajați...");
        }
        if (listaCautata_3.isEmpty()) {
            redirectAttributes.addFlashAttribute("mesaj_niciunRezultat3", "Nu au fost găsite rezultate pentru Produse...");
        }
        if (listaCautata_4.isEmpty()) {
            redirectAttributes.addFlashAttribute("mesaj_niciunRezultat4", "Nu au fost găsite rezultate pentru Materiale...");
        }
        if (listaCautata_7.isEmpty()) {
            redirectAttributes.addFlashAttribute("mesaj_niciunRezultat7", "Nu au fost găsite rezultate pentru Comenzi...");
        }
        if (listaCautata_8.isEmpty()) {
            redirectAttributes.addFlashAttribute("mesaj_niciunRezultat8", "Nu au fost găsite rezultate pentru Clienți...");
        }
        break;
            default:
                redirectAttributes.addFlashAttribute("error", "Domeniu necunoscut: " + domeniu);
                return "redirect:/welcome#error";
        }

        redirectAttributes.addFlashAttribute("cautare", cautare != null ? cautare : "");
        redirectAttributes.addFlashAttribute("domeniu", domeniu);

        switch (domeniu) {
            case "Fabrici":
                FabriciForma fabriciForma = new FabriciForma();
                fabriciForma.setFabrici(listaCautata_1);
                redirectAttributes.addFlashAttribute("fabriciForma", fabriciForma);
                return "redirect:/welcome#titluFabrici";

            case "Angajati":
                AngajatiForma angajatiForma = new AngajatiForma();
                angajatiForma.setAngajati(listaCautata_2);
                redirectAttributes.addFlashAttribute("angajatiForma", angajatiForma);
                return "redirect:/welcome#titluAngajati";

            case "Produse":
                ProduseForma produseForma = new ProduseForma();
                produseForma.setProduse(listaCautata_3);
                redirectAttributes.addFlashAttribute("produseForma", produseForma);
                return "redirect:/welcome#titluProduse";

            case "Materiale":
                MaterialeForma materialeForma = new MaterialeForma();
                materialeForma.setMateriale(listaCautata_4);
                redirectAttributes.addFlashAttribute("materialeForma", materialeForma);
                return "redirect:/welcome#titluMateriale";

            case "DetaliiComanda":
                DetaliiComenziForma detaliiComandaForma = new DetaliiComenziForma();
                detaliiComandaForma.setDetaliiComenzi(listaCautata_5);
                redirectAttributes.addFlashAttribute("detaliiComandaForma", detaliiComandaForma);
                return "redirect:/welcome#titluDetaliiComanda";

            case "DetaliiProdus":
                DetaliiProduseForma detaliiProdusForma = new DetaliiProduseForma();
                detaliiProdusForma.setDetaliiProduse(listaCautata_6);
                redirectAttributes.addFlashAttribute("detaliiProdusForma", detaliiProdusForma);
                return "redirect:/welcome#titluDetaliiProdus";

            case "Comenzi":
                ComenziForma comenziForma = new ComenziForma();
                comenziForma.setComenzi(listaCautata_7);
                redirectAttributes.addFlashAttribute("comenziForma", comenziForma);
                return "redirect:/welcome#titluComenzi";

            case "Clienti":
                ClientiForma clientiForma = new ClientiForma();
                clientiForma.setClienti(listaCautata_8);
                redirectAttributes.addFlashAttribute("clientiForma", clientiForma);
                return "redirect:/welcome#titluClienti";

            case "Toate":
                FabriciForma fabriciForma_1 = new FabriciForma();
                fabriciForma_1.setFabrici(listaCautata_1);
                AngajatiForma angajatiForma_1 = new AngajatiForma();
                angajatiForma_1.setAngajati(listaCautata_2);
                MaterialeForma materialeForma_1 = new MaterialeForma();
                materialeForma_1.setMateriale(listaCautata_4);
                ProduseForma produseForma_1 = new ProduseForma();
                produseForma_1.setProduse(listaCautata_3);
                ClientiForma clientiForma_1 = new ClientiForma();
                clientiForma_1.setClienti(listaCautata_8);
                ComenziForma comenziForma_1 = new ComenziForma();
                comenziForma_1.setComenzi(listaCautata_7);
                redirectAttributes.addFlashAttribute("fabriciForma", fabriciForma_1);
                redirectAttributes.addFlashAttribute("angajatiForma", angajatiForma_1);
                redirectAttributes.addFlashAttribute("produseForma", produseForma_1);
                redirectAttributes.addFlashAttribute("materialeForma", materialeForma_1);
                redirectAttributes.addFlashAttribute("comenziForma", comenziForma_1);
                redirectAttributes.addFlashAttribute("clientiForma", clientiForma_1);
                return "redirect:/welcome#titluFabrici";
        }

        return "redirect:/welcome#titluFabrici";
    }


    @PostMapping("/sterge-fabrica")
    public String stergeFabrica(@RequestParam(value = "idDeStersFabrica", required = true) Integer idDeStersFabrica, RedirectAttributes redirectAttributes){
        if (idDeStersFabrica != null) {
            System.out.println("###################################");
            System.out.println("stergeId inainte de intrare in Repo: " + idDeStersFabrica);
            System.out.println("###################################");

            FabricaRepo.stergeDupaId(idDeStersFabrica);
        }
        return "redirect:/welcome#titluFabrici";
    }

    @PostMapping("/sterge-angajat")
    public String stergeAngajat(@RequestParam(value = "idDeSters", required = true) Integer idDeSters, RedirectAttributes redirectAttributes){
        if (idDeSters != null) {
            System.out.println("###################################");
            System.out.println("stergeId inainte de intrare in Repo: " + idDeSters);
            System.out.println("###################################");
            AngajatRepo.stergeDupaId(idDeSters);
        }
        return "redirect:/welcome#titluAngajati";
    }

    @PostMapping("/sterge-produs")
    public String stergeProdus(@RequestParam(value = "idDeSters", required = true) Integer idDeSters, RedirectAttributes redirectAttributes){
        if (idDeSters != null) {
            System.out.println("###################################");
            System.out.println("stergeId inainte de intrare in Repo: " + idDeSters);
            System.out.println("###################################");
            ProdusRepo.stergeDupaId(idDeSters);
        }
        return "redirect:/welcome#titluProduse";
    }

    @PostMapping("/sterge-material")
    public String stergeMaterial(@RequestParam(value = "idDeSters", required = true) Integer idDeSters, RedirectAttributes redirectAttributes) {
        if (idDeSters != null) {
            System.out.println("###################################");
            System.out.println("stergeId Material înainte de intrare în Repo: " + idDeSters);
            System.out.println("###################################");
            MaterialRepo.stergeDupaId(idDeSters);
        }
        return "redirect:/welcome#titluMateriale";
    }

//    @PostMapping("/sterge-detalii-comanda")
//    public String stergeDetaliiComanda(@RequestParam(value = "idDeSters", required = true) Integer idDeSters, RedirectAttributes redirectAttributes) {
//        if (idDeSters != null) {
//            System.out.println("###################################");
//            System.out.println("stergeId DetaliiComanda înainte de intrare în Repo: " + idDeSters);
//            System.out.println("###################################");
//            DetComandaRepo.stergeDupaId(idDeSters);
//        }
//        return "redirect:/welcome";
//    }
//
//    @PostMapping("/sterge-detalii-produs")
//    public String stergeDetaliiProdus(@RequestParam(value = "idDeSters", required = true) Integer idDeSters, RedirectAttributes redirectAttributes) {
//        if (idDeSters != null) {
//            System.out.println("###################################");
//            System.out.println("stergeId DetaliiProdus înainte de intrare în Repo: " + idDeSters);
//            System.out.println("###################################");
//            DetProdusRepo.stergeDupaId(idDeSters);
//        }
//        return "redirect:/welcome";
//    }

    @PostMapping("/sterge-comanda")
    public String stergeComanda(@RequestParam(value = "idDeSters", required = true) Integer idDeSters, RedirectAttributes redirectAttributes) {
        if (idDeSters != null) {
            System.out.println("###################################");
            System.out.println("stergeId Comanda înainte de intrare în Repo: " + idDeSters);
            System.out.println("###################################");
            ComandaRepo.stergeDupaId(idDeSters);
        }
        return "redirect:/welcome#titluComenzi";
    }

    @PostMapping("/sterge-client")
    public String stergeClient(@RequestParam(value = "idDeSters", required = true) Integer idDeSters, RedirectAttributes redirectAttributes) {
        if (idDeSters != null) {
            System.out.println("###################################");
            System.out.println("stergeId Client înainte de intrare în Repo: " + idDeSters);
            System.out.println("###################################");
            ClientRepo.stergeDupaId(idDeSters);
        }
        return "redirect:/welcome#titluClienti";
    }

    @PostMapping("/interogari")
    public String getInterogareResults(@RequestParam(value = "stockThreshold", required = false) Double stockThreshold,
                                       @RequestParam String interogare, RedirectAttributes redirectAttributes) {
        List<Object[]> rezultate = null;
        String mesaj = "";
        String activeTable = "0";

        switch (interogare) {
            case "1":
                mesaj = "Produse fabricate de fabrici cu mai mult de 5 angajați";
                rezultate = interogareService.getProduseCuMaiMultDe5Angajati();
                redirectAttributes.addFlashAttribute("interogare1Results", rezultate);
                activeTable = "1";
                break;
            case "2":
                mesaj = "Fabricile fără angajați cu salarii mai mari de 5000 de lei";
                rezultate = interogareService.getFabriciFaraSalariiMari();
                redirectAttributes.addFlashAttribute("interogare2Results", rezultate);
                activeTable = "2";
                break;
            case "3":
                mesaj = "Numărul total de produse fabricate în fabricile cu cel puțin 3 angajați";
                Integer rezultat = interogareService.getNumarProduseFabriciCuTreiAngajati();
                redirectAttributes.addFlashAttribute("interogare3Results", rezultat);
                activeTable = "3";
                break;
            case "4":
                mesaj = "Produse cu preț mai mare decât media prețurilor produselor";
                rezultate = interogareService.getProdusePestePretulMediu();
                redirectAttributes.addFlashAttribute("interogare4Results", rezultate);
                activeTable = "4";
                break;
            case "5":
                mesaj = "Numărul total de produse fabricate de fiecare fabrică";
                rezultate = interogareService.getNumarProduseFabrici();
                redirectAttributes.addFlashAttribute("interogare5Results", rezultate);
                activeTable = "5";
                break;
            case "6":
                mesaj = "Lista produselor și fabricile care le-au fabricat";
                rezultate = interogareService.getProduseSiFabrici();
                redirectAttributes.addFlashAttribute("interogare6Results", rezultate);
                activeTable = "6";
                break;
            case "7":
                mesaj = "Comenzile și clienții care le-au plasat";
                rezultate = interogareService.getComenziSiClienti();
                redirectAttributes.addFlashAttribute("interogare7Results", rezultate);
                activeTable = "7";
                break;
            case "8":
                mesaj = "Fabricile și angajații lor, inclusiv fabricile fără angajați";
                rezultate = interogareService.getFabriciSiAngajati();
                redirectAttributes.addFlashAttribute("interogare8Results", rezultate);
                activeTable = "8";
                break;
            case "9":
                mesaj = "Totalul salariilor plătite de fiecare fabrică";
                rezultate = interogareService.totalSalariiPerFabrica();
                redirectAttributes.addFlashAttribute("interogare9Results", rezultate);
                activeTable = "9";
                break;
            case "10":
                mesaj = "Numărul de produse fabricate în fiecare categorie din fiecare fabrică";
                rezultate = interogareService.totalProdusePerCategorieFabrica();
                redirectAttributes.addFlashAttribute("interogare10Results", rezultate);
                activeTable = "10";
                break;

            case "11":
                if (stockThreshold == null) {
                    mesaj = "Pragul de pret nu a fost specificat.";
                } else {
                    mesaj = "Produse cu preț mai mare decât media și mai mare decat pragul de " + stockThreshold;

                    rezultate = interogareService.getProduseCuPretMaiMareDecatMediaSiPrag(stockThreshold);
                    redirectAttributes.addFlashAttribute("interogare11Results", rezultate);
                    activeTable = "11";
                }
                break;

            default:
                mesaj = "Selectează o interogare validă.";
        }
        
        redirectAttributes.addFlashAttribute("mesaj", mesaj);
        redirectAttributes.addFlashAttribute("activeTable", activeTable);
        redirectAttributes.addFlashAttribute("stockThreshold", stockThreshold);
        return "redirect:/welcome#interogare";
    }




}

