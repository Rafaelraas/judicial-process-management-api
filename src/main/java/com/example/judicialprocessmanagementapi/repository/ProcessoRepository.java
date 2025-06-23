package com.example.judicialprocessmanagementapi.repository;

import com.example.judicialprocessmanagementapi.model.Processo;
import com.example.judicialprocessmanagementapi.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
    List<Processo> findByStatus(Status status);
    List<Processo> findByComarca(String comarca);
}
