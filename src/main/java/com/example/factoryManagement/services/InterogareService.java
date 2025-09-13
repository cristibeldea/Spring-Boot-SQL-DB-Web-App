package com.example.factoryManagement.services;

import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Map;

@Service
public class InterogareService {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Query 1: Produse fabricate de fabrici cu mai mult de 5 angajați.
     */
    @Transactional
    public List<Object[]> getProduseCuMaiMultDe5Angajati() {
        String jpql = """
                SELECT P.numeProdus AS Numele_Produsului,
                 P.categorie AS Categoria_Produsului,
                  P.pret AS Pretul_Produslui,
                   F.nume AS Numele_Fabricii
                FROM Produs P 
                JOIN Fabrica F ON P.fabrica.idFabrica = F.idFabrica 
                WHERE EXISTS (
                    SELECT 1 
                    FROM Angajat A 
                    WHERE A.fabrica.idFabrica = F.idFabrica 
                    GROUP BY A.fabrica.idFabrica 
                    HAVING COUNT(A.idAngajat) > 5
                )
                ORDER BY P.numeProdus
                """;
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }

    /**
     * Query 2: Fabricile fără angajați cu salarii mai mari de 5000 de lei.
     */
    @Transactional
    public List<Object[]> getFabriciFaraSalariiMari() {
        String jpql = """
                SELECT F.nume, F.adresa, A.nume, A.salariu 
                FROM Fabrica F 
                LEFT JOIN Angajat A ON F.idFabrica = A.fabrica.idFabrica 
                WHERE NOT EXISTS (
                    SELECT 1 
                    FROM Angajat A2 
                    WHERE A2.fabrica.idFabrica = F.idFabrica AND A2.salariu > 5000
                )
                ORDER BY F.nume
                """;
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }

    /**
     * Query 3: Numărul total de produse fabricate în fabricile cu cel puțin 3 angajați.
     */
    @Transactional
    public Integer getNumarProduseFabriciCuTreiAngajati() {
        String jpql = """
                SELECT COUNT(P.idProdus) 
                FROM Produs P 
                WHERE P.fabrica.idFabrica IN (
                    SELECT A.fabrica.idFabrica 
                    FROM Angajat A 
                    GROUP BY A.fabrica.idFabrica 
                    HAVING COUNT(A.idAngajat) >= 3
                )
                """;
        Query query = entityManager.createQuery(jpql);

        Long result = (Long) query.getSingleResult();
        return result != null ? result.intValue() : 0;
    }

    /**
     * Query 4: Produse cu preț mai mare decât media prețurilor produselor.
     */
    @Transactional
    public List<Object[]> getProdusePestePretulMediu() {
        String jpql = """
                SELECT P.numeProdus, P.pret 
                FROM Produs P 
                WHERE P.pret > (
                    SELECT AVG(P2.pret) 
                    FROM Produs P2
                )
                ORDER BY P.pret DESC
                """;
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }

    /**
     * Query 5: Numărul total de produse fabricate de fiecare fabrică.
     */
    @Transactional
    public List<Object[]> getNumarProduseFabrici() {
        String jpql = """
                SELECT F.nume, COUNT(P.idProdus) 
                FROM Fabrica F 
                JOIN Produs P ON F.idFabrica = P.fabrica.idFabrica 
                GROUP BY F.nume
                ORDER BY COUNT(P.idProdus) DESC
                """;
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }

    /**
     * Query 6: Lista produselor și fabricile care le-au fabricat.
     */
    @Transactional
    public List<Object[]> getProduseSiFabrici() {
        String jpql = """
                SELECT P.numeProdus, P.categorie, F.nume 
                FROM Produs P 
                JOIN Fabrica F ON P.fabrica.idFabrica = F.idFabrica 
                ORDER BY P.numeProdus
                """;
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }

    /**
     * Query 7: Comenzile și clienții care le-au plasat.
     */
    @Transactional
    public List<Object[]> getComenziSiClienti() {
        String jpql = """
                SELECT C.idComanda, C.dataComenzii, Cl.nume, Cl.email 
                FROM Comanda C 
                JOIN Client Cl ON C.client.idClient = Cl.idClient 
                ORDER BY C.dataComenzii DESC
                """;
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }

    /**
     * Query 8: Fabricile și angajații lor, inclusiv fabricile fără angajați.
     */
    @Transactional
    public List<Object[]> getFabriciSiAngajati() {
        String jpql = """
                SELECT F.nume, F.adresa, A.nume, A.functie 
                FROM Fabrica F 
                LEFT JOIN Angajat A ON F.idFabrica = A.fabrica.idFabrica 
                ORDER BY F.nume
                """;
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }

    @Transactional
    public List<Object[]> totalSalariiPerFabrica() {
        String jpql = """
                SELECT\s
                    F.nume AS Nume_Fabrica,\s
                    SUM(A.salariu) AS Total_Salarii
                FROM\s
                    Fabrica F
                JOIN\s
                    Angajat A ON F.idFabrica = A.fabrica.idFabrica
                GROUP BY\s
                    F.nume
                ORDER BY\s
                    Total_Salarii DESC
                """;
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }

    @Transactional
    public List<Object[]> totalProdusePerCategorieFabrica() {
        String jpql = """
                SELECT\s
                    F.nume AS Nume_Fabrica,\s
                    P.categorie AS Categorie_Produs,\s
                    COUNT(P.idProdus) AS Numar_Produse
                FROM\s
                    Fabrica F
                JOIN\s
                    Produs P ON F.idFabrica = P.fabrica.idFabrica
                GROUP BY\s
                    F.nume, P.categorie
                ORDER BY\s
                    F.nume, P.categorie
                """;
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();


    }
    @Transactional
    public List<Object[]> getProduseCuPretMaiMareDecatMediaSiPrag(Double limitaPret) {
        String sqlQuery =
                "SELECT p.numeProdus, p.categorie, p.pret " +
                         "FROM Produs p " +
                        "WHERE p.pret > :limitaPret "  +
                        "AND p.pret > (SELECT AVG(p2.pret) FROM Produs p2)";

        Query query = entityManager.createQuery(sqlQuery);
        query.setParameter("limitaPret", limitaPret);
        return query.getResultList();
    }
    }
