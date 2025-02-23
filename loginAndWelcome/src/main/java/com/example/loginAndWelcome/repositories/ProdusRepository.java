package com.example.loginAndWelcome.repositories;

import com.example.loginAndWelcome.entities.Angajat;
import com.example.loginAndWelcome.entities.Produs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProdusRepository extends JpaRepository<Produs, Integer> {

    @Query(value = "SELECT * FROM produs", nativeQuery = true)
    List<Produs> getAllProdus();

    @Query("UPDATE Produs p SET p.numeProdus = :numeProdus, p.categorie = :categorie, p.pret = :pret, p.fabrica.idFabrica = :idFabrica, p.dataFabricarii = :dataFabricarii WHERE p.idProdus = :idProdus")
    @Modifying
    @Transactional
    void updateProdus(
            @Param("idProdus") Integer idProdus,
            @Param("numeProdus") String numeProdus,
            @Param("categorie") String categorie,
            @Param("pret") Double pret,
            @Param("idFabrica") Integer idFabrica,
            @Param("dataFabricarii") String dataFabricarii
    );

    @Query(value = "INSERT INTO produs (Nume_Produs, Categorie, Pret, ID_Fabrica, Data_Fabricarii) VALUES (:numeProdus, :categorie, :pret, :idFabrica, :dataFabricarii)", nativeQuery = true)
    @Modifying
    @Transactional
    void insertProdus(
            @Param("numeProdus") String numeProdus,
            @Param("categorie") String categorie,
            @Param("pret") Double pret,
            @Param("idFabrica") Integer idFabrica,
            @Param("dataFabricarii") String dataFabricarii
    );

    @Query(value = "SELECT * FROM Produs WHERE ID_Produs LIKE (:interogare)" +
            " OR LOWER(Nume_Produs) LIKE LOWER(:interogare)" +
            " OR LOWER(Categorie) LIKE LOWER(:interogare)" +
            " OR LOWER(Pret) LIKE LOWER(:interogare)" +
            " OR LOWER(ID_Fabrica) LIKE LOWER(:interogare)" +
            " OR LOWER(Data_Fabricarii) LIKE LOWER(:interogare)", nativeQuery = true)
    @Modifying
    @Transactional
    List<Produs> gasesteDupa(@Param("interogare") String interogare);

    @Query(value = "DELETE FROM Produs p WHERE p.idProdus= :id")
    @Modifying
    @Transactional
    void stergeDupaId(@Param("id") Integer id);

    @Query(value = "SELECT p.ID_Fabrica " +
            "FROM Produs p " +
            "INNER JOIN Fabrica f ON p.ID_fabrica = f.ID_fabrica",
            nativeQuery = true)
    List<Integer> gasesteProduseCuIdFabricaValid();
}

