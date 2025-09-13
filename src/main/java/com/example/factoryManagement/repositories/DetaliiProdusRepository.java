package com.example.factoryManagement.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.factoryManagement.entities.Angajat;
import com.example.factoryManagement.entities.Detalii_Produs;

import java.util.List;

public interface DetaliiProdusRepository extends JpaRepository<Detalii_Produs, Integer> {
    @Query(value = "SELECT * FROM Detalii_Produs", nativeQuery = true)
    List<Detalii_Produs> getAllDetaliiProdus();

    @Query("UPDATE Detalii_Produs d SET d.cantitate = :cantitate WHERE d.material.idMaterial = :idMaterial AND d.produs.idProdus = :idProdus")
    @Modifying
    @Transactional
    void updateDetaliiProdus(@Param("idMaterial") Integer idMaterial,
                             @Param("idProdus") Integer idProdus,
                             @Param("cantitate") Integer cantitate);

    @Query(value = "INSERT INTO detalii_produs (id_material, id_produs, cantitate) VALUES (:idMaterial, :idProdus, :cantitate)", nativeQuery = true)
    @Modifying
    @Transactional
    void insertDetaliiProdus(@Param("idMaterial") Integer idMaterial,
                             @Param("idProdus") Integer idProdus,
                             @Param("cantitate") Integer cantitate);

    @Query(value = "SELECT * FROM Detalii_Produs WHERE ID_Material LIKE (:interogare)" +
            " OR ID_Produs LIKE (:interogare)" +
            " OR LOWER(Cantitate) LIKE LOWER(:interogare)", nativeQuery = true)
    List<Detalii_Produs> gasesteDupa(@Param("interogare") String interogare);

    @Query(value = "DELETE FROM Detalii_Produs WHERE ID_Material = :idMaterial AND ID_Produs = :idProdus", nativeQuery = true)
    @Modifying
    @Transactional
    void stergeDupaId(@Param("idMaterial") Integer idMaterial,
                      @Param("idProdus") Integer idProdus);


}
