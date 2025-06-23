package com.example.judicialprocessmanagementapi.service;

import com.example.judicialprocessmanagementapi.model.Audiencia;
import com.example.judicialprocessmanagementapi.model.Processo;
import com.example.judicialprocessmanagementapi.model.Status;
import com.example.judicialprocessmanagementapi.repository.AudienciaRepository;
import com.example.judicialprocessmanagementapi.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AudienciaService {

    @Autowired
    private AudienciaRepository audienciaRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    public List<Audiencia> findAll() {
        return audienciaRepository.findAll();
    }

    public Audiencia findById(Long id) {
        return audienciaRepository.findById(id).orElse(null);
    }

    public Audiencia save(Audiencia audiencia) {
        Processo processo = processoRepository.findById(audiencia.getProcesso().getId()).orElse(null);
        if (processo == null || processo.getStatus() == Status.ARQUIVADO || processo.getStatus() == Status.SUSPENSO) {
            throw new IllegalArgumentException("Não é possível agendar audiência para processos arquivados ou suspensos.");
        }

        if (isWeekend(audiencia.getDataHora().toLocalDate())) {
            throw new IllegalArgumentException("Não é possível agendar audiências aos finais de semana.");
        }

        LocalDateTime start = audiencia.getDataHora().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime end = audiencia.getDataHora().withHour(23).withMinute(59).withSecond(59);
        List<Audiencia> existingAudiencias = audienciaRepository.findByVaraAndLocalAndDataHoraBetween(processo.getVara(), audiencia.getLocal(), start, end);
        if (!existingAudiencias.isEmpty()) {
            throw new IllegalArgumentException("Já existe uma audiência agendada para esta vara e local neste dia.");
        }

        return audienciaRepository.save(audiencia);
    }

    public void deleteById(Long id) {
        audienciaRepository.deleteById(id);
    }

    public List<Audiencia> findByComarcaAndDay(String comarca, LocalDate day) {
        LocalDateTime start = day.atStartOfDay();
        LocalDateTime end = day.atTime(23, 59, 59);
        return audienciaRepository.findByComarcaAndDataHoraBetween(comarca, start, end);
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }
}
