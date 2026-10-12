package com.medtriage.service;

import com.medtriage.dto.CitaMedicaDTO;
import com.medtriage.dto.CrearCitaDTO;
import com.medtriage.model.CitaMedica;
import com.medtriage.model.Doctor;
import com.medtriage.model.Paciente;
import com.medtriage.repository.CitaMedicaRepository;
import com.medtriage.repository.DoctorRepository;
import com.medtriage.repository.PacienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class CitaMedicaServiceTest {

    @Mock
    private CitaMedicaRepository citaRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private CitaMedicaService citaService;

    @Test
    void crearCita_Exitoso() {
        Long doctorId = 1L;
        Long pacienteId = 1L;
        CrearCitaDTO dto = new CrearCitaDTO(pacienteId, doctorId, "ALTA", "Dolor", new BigDecimal("10000.00"));

        Doctor doctor = new Doctor(doctorId, "Fabian Quesada", "Medicina", true);
        Paciente paciente = new Paciente(pacienteId, "305740357", "Dani Tames", "dani@ucr.ac.cr", "11223344");

        CitaMedica citaGuardada = new CitaMedica();
        citaGuardada.setId(10L);
        citaGuardada.setCodigoCita("CIT-2026-B1B2");
        citaGuardada.setDoctor(doctor);
        citaGuardada.setPaciente(paciente);
        citaGuardada.setNivelPrioridad("ALTA");
        citaGuardada.setMotivoConsulta("Dolor");
        citaGuardada.setEstado("PENDIENTE");
        citaGuardada.setMontoConsulta(new BigDecimal("10000.00"));
        citaGuardada.setFechaCita(LocalDateTime.now());


        
        CitaMedicaDTO resultado = citaService.crearCita(dto);


        assertEquals("CIT-2026-B1B2", resultado.getCodigoCita());
        assertEquals("PENDIENTE", resultado.getEstado());
        assertEquals("Fabian Quesada", resultado.getNombreDoctor());
       
    }
}