package com.example.loginAndWelcome.repositories;

import com.example.loginAndWelcome.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query(value = "SELECT * FROM Users", nativeQuery = true)
    List<Users> getAllUsers();

//    @Query("UPDATE Produs p SET p.numeProdus = :numeProdus, p.categorie = :categorie, p.pret = :pret, p.fabrica.idFabrica = :idFabrica, p.dataFabricarii = :dataFabricarii WHERE p.idProdus = :idProdus")
//    @Modifying
//    @Transactional
//    void updateProdus(
//            @Param("idProdus") Integer idProdus,
//            @Param("numeProdus") String numeProdus,
//            @Param("categorie") String categorie,
//            @Param("pret") Double pret,
//            @Param("idFabrica") Integer idFabrica,
//            @Param("dataFabricarii") java.time.LocalDate dataFabricarii
//    );
//
//    @Query(value = "INSERT INTO produs (Nume_Produs, Categorie, Pret, ID_Fabrica, Data_Fabricarii) VALUES (:numeProdus, :categorie, :pret, :idFabrica, :dataFabricarii)", nativeQuery = true)
//    @Modifying
//    @Transactional
//    void insertProdus(
//            @Param("numeProdus") String numeProdus,
//            @Param("categorie") String categorie,
//            @Param("pret") Double pret,
//            @Param("idFabrica") Integer idFabrica,
//            @Param("dataFabricarii") java.time.LocalDate dataFabricarii
//    );
}
