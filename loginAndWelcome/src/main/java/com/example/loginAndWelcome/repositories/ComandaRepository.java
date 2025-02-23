package com.example.loginAndWelcome.repositories;

import com.example.loginAndWelcome.entities.Comanda;
import com.example.loginAndWelcome.entities.Detalii_Produs;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComandaRepository extends JpaRepository<Comanda, Integer> {
    @Query(value = "SELECT * FROM Comanda", nativeQuery = true)
    List<Comanda> getAllComanda();

//    @Query("UPDATE Comanda c SET c.dataComenzii = :dataComenzii, c.total = :total, c.status = :status, c.client.idClient = :idClient WHERE c.idComanda = :idComanda")
//    @Modifying
//    @Transactional
//    void updateComanda(@Param("idComanda") Integer idComanda,
//                       @Param("dataComenzii") String dataComenzii,
//                       @Param("total") Double total,
//                       @Param("status") String status,
//                       @Param("idClient") Integer idClient);

    @Query("UPDATE Comanda c SET c.dataComenzii = :dataComenzii, c.total = :total, c.status = :status, c.client = (SELECT cl FROM Client cl WHERE cl.idClient = :idClient) WHERE c.idComanda = :idComanda")
    @Modifying
    @Transactional
    void updateComanda(@Param("idComanda") Integer idComanda,
                       @Param("dataComenzii") String dataComenzii,
                       @Param("total") Double total,
                       @Param("status") String status,
                       @Param("idClient") Integer idClient);


    @Query(value = "INSERT INTO Comanda (Data_comenzii, Total, Status, ID_Client) VALUES (:dataComenzii, :total, :status, :idClient)", nativeQuery = true)
    @Modifying
    @Transactional
    void insertComanda(@Param("dataComenzii") String dataComenzii,
                       @Param("total") Double total,
                       @Param("status") String status,
                       @Param("idClient") Integer idClient);

    @Query(value = "SELECT * FROM Comanda WHERE ID_Comanda LIKE (:interogare)" +
            " OR LOWER(Data_Comenzii) LIKE LOWER(:interogare)" +
            " OR LOWER(Total) LIKE LOWER(:interogare)" +
            " OR LOWER(Status) LIKE LOWER(:interogare)" +
            " OR ID_Client LIKE (:interogare)", nativeQuery = true)
    List<Comanda> gasesteDupa(@Param("interogare") String interogare);

    @Query(value = "DELETE FROM Comanda WHERE ID_Comanda = :id", nativeQuery = true)
    @Modifying
    @Transactional
    void stergeDupaId(@Param("id") Integer id);

}
