package com.example.judicialprocessmanagementapi.controller;

import com.example.judicialprocessmanagementapi.model.Audiencia;
import com.example.judicialprocessmanagementapi.service.AudienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/audiencias")
public class AudienciaController {

    @Autowired
    private AudienciaService audienciaService;

    @GetMapping
    public List<Audiencia> getAllAudiencias() {
        return audienciaService.findAll();
    }

    @GetMapping("/{id}")
    public Audiencia getAudienciaById(@PathVariable Long id) {
        return audienciaService.findById(id);
    }

    @PostMapping
    public Audiencia createAudiencia(@RequestBody Audiencia audiencia) {
        return audienciaService.save(audiencia);
    }

    @PutMapping("/{id}")
    public Audiencia updateAudiencia(@PathVariable Long id, @RequestBody Audiencia audiencia) {
        audiencia.setId(id);
        return audienciaService.save(audiencia);
    }

    @DeleteMapping("/{id}")
    public void deleteAudiencia(@PathVariable Long id) {
        audienciaService.deleteById(id);
    }
}
