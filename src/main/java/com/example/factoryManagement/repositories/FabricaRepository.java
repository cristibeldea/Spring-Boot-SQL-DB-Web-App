package com.example.factoryManagement.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.factoryManagement.entities.Fabrica;

import java.util.List;

public interface FabricaRepository extends JpaRepository<Fabrica, Integer> {

    @Query(value = "SELECT * FROM Fabrica", nativeQuery = true)
    List<Fabrica> getAllFabrica();

    @Query("UPDATE Fabrica f SET f.nume = :nume, f.adresa = :adresa, f.telefon = :telefon, f.email = :email WHERE f.idFabrica = :idFabrica")
    @Modifying
    @Transactional
    void updateFabrica(@Param("idFabrica") Integer idFabrica,
                       @Param("nume") String nume,
                       @Param("adresa") String adresa,
                       @Param("telefon") String telefon,
                       @Param("email") String email);

    @Query(value = "INSERT INTO fabrica (nume, adresa, telefon, email) VALUES (:nume, :adresa, :telefon, :email)", nativeQuery = true)
    @Modifying
    @Transactional
    void insertFabrica(@Param("nume") String nume,
                       @Param("adresa") String adresa,
                       @Param("telefon") String telefon,
                       @Param("email") String email);

    @Query(value = "SELECT * FROM Fabrica WHERE ID_Fabrica LIKE (:interogare)" +
            " OR LOWER(Nume) LIKE LOWER(:interogare)" +
            " OR LOWER(Adresa) LIKE LOWER(:interogare)" +
            " OR LOWER(Telefon) LIKE LOWER(:interogare)" +
            " OR LOWER(Email) LIKE LOWER(:interogare)", nativeQuery = true)
    @Modifying
    @Transactional
    List<Fabrica> gasesteDupa(@Param("interogare") String interogare);

    @Query(value = "DELETE FROM Fabrica f WHERE f.idFabrica = :id")
    @Modifying
    @Transactional
    void stergeDupaId(@Param("id") Integer id);

}



