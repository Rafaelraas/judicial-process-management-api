package com.example.judicialprocessmanagementapi.service;

import com.example.judicialprocessmanagementapi.model.Processo;
import com.example.judicialprocessmanagementapi.model.Status;
import com.example.judicialprocessmanagementapi.repository.ProcessoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProcessoServiceTest {

    @InjectMocks
    private ProcessoService processoService;

    @Mock
    private ProcessoRepository processoRepository;

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void testSaveProcessoWithInvalidNumber() {
        Processo processo = new Processo();
        processo.setNumeroProcesso("12345");
        processo.setStatus(Status.ATIVO);

        assertThrows(ConstraintViolationException.class, () -> {
            var violations = validator.validate(processo);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
            processoService.save(processo);
        });
    }

    @Test
    void testSaveProcessoWithValidNumber() {
        Processo processo = new Processo();
        processo.setNumeroProcesso("0000000-00.0000.0.00.0000");
        processo.setStatus(Status.ATIVO);

        processoService.save(processo);

        verify(processoRepository).save(processo);
    }
}
