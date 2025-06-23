package com.example.judicialprocessmanagementapi.service;

import com.example.judicialprocessmanagementapi.model.Audiencia;
import com.example.judicialprocessmanagementapi.model.Processo;
import com.example.judicialprocessmanagementapi.model.Status;
import com.example.judicialprocessmanagementapi.model.TipoAudiencia;
import com.example.judicialprocessmanagementapi.repository.AudienciaRepository;
import com.example.judicialprocessmanagementapi.repository.ProcessoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AudienciaServiceTest {

    @InjectMocks
    private AudienciaService audienciaService;

    @Mock
    private AudienciaRepository audienciaRepository;

    @Mock
    private ProcessoRepository processoRepository;

    @Test
    void testScheduleAudienciaForArchivedProcess() {
        Processo processo = new Processo();
        processo.setId(1L);
        processo.setStatus(Status.ARQUIVADO);

        Audiencia audiencia = new Audiencia();
        audiencia.setProcesso(processo);

        when(processoRepository.findById(1L)).thenReturn(Optional.of(processo));

        assertThrows(IllegalArgumentException.class, () -> audienciaService.save(audiencia));
    }

    @Test
    void testScheduleAudienciaOnWeekend() {
        Processo processo = new Processo();
        processo.setId(1L);
        processo.setStatus(Status.ATIVO);

        Audiencia audiencia = new Audiencia();
        audiencia.setProcesso(processo);
        LocalDateTime weekend = LocalDateTime.now();
        while (weekend.getDayOfWeek() != DayOfWeek.SATURDAY) {
            weekend = weekend.plusDays(1);
        }
        audiencia.setDataHora(weekend);

        when(processoRepository.findById(1L)).thenReturn(Optional.of(processo));

        assertThrows(IllegalArgumentException.class, () -> audienciaService.save(audiencia));
    }

    @Test
    void testScheduleOverlappingAudiencia() {
        Processo processo = new Processo();
        processo.setId(1L);
        processo.setStatus(Status.ATIVO);
        processo.setVara("1ª Vara Cível");

        Audiencia existingAudiencia = new Audiencia();
        LocalDateTime now = LocalDateTime.now();
        while (now.getDayOfWeek() == DayOfWeek.SATURDAY || now.getDayOfWeek() == DayOfWeek.SUNDAY) {
            now = now.plusDays(1);
        }
        existingAudiencia.setDataHora(now);
        existingAudiencia.setLocal("Sala 1");

        Audiencia newAudiencia = new Audiencia();
        newAudiencia.setProcesso(processo);
        newAudiencia.setDataHora(now);
        newAudiencia.setLocal("Sala 1");

        when(processoRepository.findById(1L)).thenReturn(Optional.of(processo));
        when(audienciaRepository.findByVaraAndLocalAndDataHoraBetween(any(), any(), any(), any())).thenReturn(Collections.singletonList(existingAudiencia));

        assertThrows(IllegalArgumentException.class, () -> audienciaService.save(newAudiencia));
    }
}
