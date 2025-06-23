package com.example.judicialprocessmanagementapi.repository;

import com.example.judicialprocessmanagementapi.model.Audiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AudienciaRepository extends JpaRepository<Audiencia, Long> {

    @Query("SELECT a FROM Audiencia a JOIN a.processo p WHERE p.vara = :vara AND a.local = :local AND a.dataHora BETWEEN :start AND :end")
    List<Audiencia> findByVaraAndLocalAndDataHoraBetween(@Param("vara") String vara, @Param("local") String local, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT a FROM Audiencia a JOIN a.processo p WHERE p.comarca = :comarca AND a.dataHora BETWEEN :start AND :end")
    List<Audiencia> findByComarcaAndDataHoraBetween(@Param("comarca") String comarca, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
