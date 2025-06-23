package com.example.judicialprocessmanagementapi.controller;

import com.example.judicialprocessmanagementapi.model.Audiencia;
import com.example.judicialprocessmanagementapi.service.AudienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/agenda")
public class AgendaController {

    @Autowired
    private AudienciaService audienciaService;

    @GetMapping
    public List<Audiencia> getAgenda(@RequestParam String comarca, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dia) {
        return audienciaService.findByComarcaAndDay(comarca, dia);
    }
}
