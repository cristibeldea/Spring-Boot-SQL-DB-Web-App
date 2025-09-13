package com.example.factoryManagement.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.factoryManagement.entities.Fabrica;
import com.example.factoryManagement.entities.Material;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
    @Query(value = "SELECT * FROM Material", nativeQuery = true)
    List<Material> getAllMaterial();

    @Query("UPDATE Material m SET m.nume = :nume, m.descriere = :descriere WHERE m.idMaterial = :idMaterial")
    @Modifying
    @Transactional
    void updateMaterial(@Param("idMaterial") Integer idMaterial,
                        @Param("nume") String nume,
                        @Param("descriere") String descriere);

    @Query(value = "INSERT INTO material (nume, descriere) VALUES (:nume, :descriere)", nativeQuery = true)
    @Modifying
    @Transactional
    void insertMaterial(@Param("nume") String nume,
                        @Param("descriere") String descriere);

    @Query(value = "SELECT * FROM Material WHERE ID_Material LIKE (:interogare)" +
            " OR LOWER(Nume) LIKE LOWER(:interogare)" +
            " OR LOWER(Descriere) LIKE LOWER(:interogare)", nativeQuery = true)
    List<Material> gasesteDupa(@Param("interogare") String interogare);

    @Query(value = "DELETE FROM Material WHERE ID_Material = :id", nativeQuery = true)
    @Modifying
    @Transactional
    void stergeDupaId(@Param("id") Integer id);

}
