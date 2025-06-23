package com.example.judicialprocessmanagementapi.service;

import com.example.judicialprocessmanagementapi.model.Processo;
import com.example.judicialprocessmanagementapi.model.Status;
import com.example.judicialprocessmanagementapi.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    public List<Processo> findAll() {
        return processoRepository.findAll();
    }

    public Processo findById(Long id) {
        return processoRepository.findById(id).orElse(null);
    }

    public List<Processo> findByStatus(Status status) {
        return processoRepository.findByStatus(status);
    }

    public List<Processo> findByComarca(String comarca) {
        return processoRepository.findByComarca(comarca);
    }

    public Processo save(Processo processo) {
        return processoRepository.save(processo);
    }

    public void deleteById(Long id) {
        processoRepository.deleteById(id);
    }
}
