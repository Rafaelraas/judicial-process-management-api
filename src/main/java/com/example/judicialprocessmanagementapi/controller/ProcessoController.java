package com.example.judicialprocessmanagementapi.controller;

import com.example.judicialprocessmanagementapi.model.Processo;
import com.example.judicialprocessmanagementapi.model.Status;
import com.example.judicialprocessmanagementapi.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/processos")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @GetMapping
    public List<Processo> getAllProcessos(@RequestParam(required = false) Status status, @RequestParam(required = false) String comarca) {
        if (status != null) {
            return processoService.findByStatus(status);
        }
        if (comarca != null) {
            return processoService.findByComarca(comarca);
        }
        return processoService.findAll();
    }

    @GetMapping("/{id}")
    public Processo getProcessoById(@PathVariable Long id) {
        return processoService.findById(id);
    }

    @PostMapping
    public Processo createProcesso(@RequestBody Processo processo) {
        return processoService.save(processo);
    }

    @PutMapping("/{id}")
    public Processo updateProcesso(@PathVariable Long id, @RequestBody Processo processo) {
        processo.setId(id);
        return processoService.save(processo);
    }

    @DeleteMapping("/{id}")
    public void deleteProcesso(@PathVariable Long id) {
        processoService.deleteById(id);
    }
}
