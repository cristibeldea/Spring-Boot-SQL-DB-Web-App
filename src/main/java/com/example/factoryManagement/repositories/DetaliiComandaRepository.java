package com.example.factoryManagement.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.factoryManagement.entities.Detalii_Comanda;
import com.example.factoryManagement.entities.Detalii_Produs;

import java.util.List;

public interface DetaliiComandaRepository extends JpaRepository<Detalii_Comanda, Integer> {
    @Query(value = "SELECT * FROM Detalii_Comanda", nativeQuery = true)
    List<Detalii_Comanda> getAllDetaliiComanda();

    @Query("UPDATE Detalii_Comanda d SET d.cantitate = :cantitate WHERE d.comanda.idComanda = :idComanda AND d.produs.idProdus = :idProdus")
    @Modifying
    @Transactional
    void updateDetaliiComanda(@Param("idComanda") Integer idComanda,
                              @Param("idProdus") Integer idProdus,
                              @Param("cantitate") Integer cantitate);

    @Query(value = "INSERT INTO detalii_comanda (id_comanda, id_produs, cantitate) VALUES (:idComanda, :idProdus, :cantitate)", nativeQuery = true)
    @Modifying
    @Transactional
    void insertDetaliiComanda(@Param("idComanda") Integer idComanda,
                              @Param("idProdus") Integer idProdus,
                              @Param("cantitate") Integer cantitate);

    @Query(value = "SELECT * FROM Detalii_Comanda WHERE ID_Comanda LIKE (:interogare)" +
            " OR ID_Produs LIKE (:interogare)" +
            " OR LOWER(Cantitate) LIKE LOWER(:interogare)", nativeQuery = true)
    List<Detalii_Comanda> gasesteDupa(@Param("interogare") String interogare);

    @Query(value = "DELETE FROM Detalii_Comanda WHERE ID_Comanda = :idComanda AND ID_Produs = :idProdus", nativeQuery = true)
    @Modifying
    @Transactional
    void stergeDupaId(@Param("idComanda") Integer idComanda,
                      @Param("idProdus") Integer idProdus);

}
