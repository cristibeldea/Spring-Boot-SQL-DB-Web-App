package com.example.loginAndWelcome.repositories;

import com.example.loginAndWelcome.entities.Angajat;
import com.example.loginAndWelcome.entities.Fabrica;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AngajatRepository extends JpaRepository<Angajat, Integer> {

    @Query(value = "SELECT * FROM Angajat", nativeQuery = true)
    List<Angajat> getAllAngajat();

//    @Query("UPDATE Angajat a SET a.nume = :nume, a.adresa = :adresa, a.functie = :functie, a.salariu = :salariu, a.dataAngajarii = :dataAngajarii, a.fabrica.idFabrica = :idFabrica, a.sex = :sex WHERE a.idAngajat = :idAngajat")
//    @Modifying
//    @Transactional
//    void updateAngajat(@Param("idAngajat") Integer idAngajat,
//                       @Param("nume") String nume,
//                       @Param("adresa") String adresa,
//                       @Param("functie") String functie,
//                       @Param("salariu") Double salariu,
//                       @Param("dataAngajarii") String dataAngajarii,
//                       @Param("idFabrica") Integer idFabrica,
//                       @Param("sex") String sex);

    @Query("UPDATE Angajat a SET a.nume = :nume, a.adresa = :adresa, a.functie = :functie, a.salariu = :salariu, a.dataAngajarii = :dataAngajarii, a.fabrica = (SELECT f FROM Fabrica f WHERE f.idFabrica = :idFabrica), a.sex = :sex WHERE a.idAngajat = :idAngajat")
    @Modifying
    @Transactional
    void updateAngajat(@Param("idAngajat") Integer idAngajat,
                       @Param("nume") String nume,
                       @Param("adresa") String adresa,
                       @Param("functie") String functie,
                       @Param("salariu") Double salariu,
                       @Param("dataAngajarii") String dataAngajarii,
                       @Param("idFabrica") Integer idFabrica,
                       @Param("sex") String sex);

    @Query(value = "INSERT INTO angajat (nume, adresa, functie, salariu, data_angajarii, id_fabrica, sex) VALUES (:nume, :adresa, :functie, :salariu, :dataAngajarii, :idFabrica, :sex)", nativeQuery = true)
    @Modifying
    @Transactional
    void insertAngajat(@Param("nume") String nume,
                       @Param("adresa") String adresa,
                       @Param("functie") String functie,
                       @Param("salariu") Double salariu,
                       @Param("dataAngajarii") String dataAngajarii,
                       @Param("idFabrica") Integer idFabrica,
                       @Param("sex") String sex);

    @Query(value = "SELECT * FROM Angajat WHERE ID_Angajat LIKE (:interogare)" +
            " OR LOWER(Nume) LIKE LOWER(:interogare)" +
            " OR LOWER(Adresa) LIKE LOWER(:interogare)" +
            " OR LOWER(Functie) LIKE LOWER(:interogare)" +
            " OR LOWER(Salariu) LIKE LOWER(:interogare)" +
            " OR LOWER(Data_Angajarii) LIKE LOWER(:interogare)" +
            " OR ID_Fabrica LIKE (:interogare)" +
            " OR LOWER(Sex) LIKE LOWER(:interogare)", nativeQuery = true)
    @Modifying
    @Transactional
    List<Angajat> gasesteDupa(@Param("interogare") String interogare);

    @Query(value = "DELETE FROM Angajat a WHERE a.idAngajat = :id")
    @Modifying
    @Transactional
    void stergeDupaId(@Param("id") Integer id);

    @Query(value = "SELECT a.ID_Fabrica " +
            "FROM Angajat a " +
            "INNER JOIN Fabrica f ON a.ID_fabrica = f.ID_fabrica",
            nativeQuery = true)
    List<Integer> gasesteAngajatiCuIdFabricaValid();
}