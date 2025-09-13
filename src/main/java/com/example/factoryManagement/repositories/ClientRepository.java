package com.example.factoryManagement.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.factoryManagement.entities.Client;
import com.example.factoryManagement.entities.Detalii_Produs;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query(value = "SELECT * FROM Client", nativeQuery = true)
    List<Client> getAllClient();

    @Query("UPDATE Client c SET c.nume = :nume, c.adresa = :adresa, c.telefon = :telefon, c.sex = :sex WHERE c.idClient = :idClient")
    @Modifying
    @Transactional
    void updateClient(@Param("idClient") Integer idClient,
                      @Param("nume") String nume,
                      @Param("adresa") String adresa,
                      @Param("telefon") String telefon,
                      @Param("sex") String sex);

    @Query(value = "INSERT INTO client (nume, adresa, telefon, sex) VALUES (:nume, :adresa, :telefon, :sex)", nativeQuery = true)
    @Modifying
    @Transactional
    void insertClient(@Param("nume") String nume,
                      @Param("adresa") String adresa,
                      @Param("telefon") String telefon,
                      @Param("sex") String sex);

    @Query(value = "SELECT * FROM Client WHERE ID_Client LIKE (:interogare)" +
            " OR LOWER(Nume) LIKE LOWER(:interogare)" +
            " OR LOWER(Adresa) LIKE LOWER(:interogare)" +
            " OR LOWER(Telefon) LIKE LOWER(:interogare)" +
            " OR LOWER(Sex) LIKE LOWER(:interogare)", nativeQuery = true)
    List<Client> gasesteDupa(@Param("interogare") String interogare);

    @Query(value = "DELETE FROM Client WHERE ID_Client = :id", nativeQuery = true)
    @Modifying
    @Transactional
    void stergeDupaId(@Param("id") Integer id);

}

