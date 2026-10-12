package com.medtriage.service;

import com.medtriage.dto.CitaMedicaDTO;
import com.medtriage.dto.CrearCitaDTO;
import com.medtriage.model.CitaMedica;
import com.medtriage.model.Doctor;
import com.medtriage.model.Paciente;
import com.medtriage.repository.CitaMedicaRepository;
import com.medtriage.repository.DoctorRepository;
import com.medtriage.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CitaMedicaService {

    private final CitaMedicaRepository citaRepository;
    private final DoctorRepository doctorRepository;
    private final PacienteRepository pacienteRepository;

    public CitaMedicaService(CitaMedicaRepository citaRepository,DoctorRepository doctorRepository, PacienteRepository pacienteRepository) {
        this.citaRepository = citaRepository;
        this.doctorRepository = doctorRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public List<CitaMedicaDTO> obtenerTodas() {
        return citaRepository.findAllByOrderByFechaCitaDesc().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public List<CitaMedicaDTO> obtenerPorPrioridad(String prioridad) {
        return citaRepository.findByNivelPrioridadOrderByFechaCitaDesc(prioridad).stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public CitaMedicaDTO crearCita(CrearCitaDTO dto) {
        Doctor doctor = doctorRepository.findById(dto.getDoctorId()).orElseThrow(() -> new IllegalArgumentException("Doctor no encontrado " + dto.getDoctorId()));

        if (!Boolean.TRUE.equals(doctor.getDisponible())) {
            throw new IllegalStateException("El doctor seleccionado no está disponible.");
        }

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId()).orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado " + dto.getPacienteId()));

        CitaMedica cita = new CitaMedica();
        cita.setCodigoCita("CIT-2026-" + String.format("%04d", new Random().nextInt(2000)));
        cita.setPaciente(paciente);
        cita.setDoctor(doctor);
        cita.setNivelPrioridad(dto.getNivelPrioridad());
        cita.setMotivoConsulta(dto.getMotivoConsulta());
        cita.setMontoConsulta(dto.getMontoConsulta());
        cita.setEstado("PENDIENTE");
        cita.setFechaCita(LocalDateTime.now());

        CitaMedica guardada = citaRepository.save(cita);
        return convertirADTO(guardada);
    }

    public CitaMedicaDTO actualizarEstado(Long id, String nuevoEstado) {
        CitaMedica cita = citaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cita médica no encontrada " + id));
        cita.setEstado(nuevoEstado);
        CitaMedica actualizada = citaRepository.save(cita);
        return convertirADTO(actualizada);
    }

    private CitaMedicaDTO convertirADTO(CitaMedica cita) {
        return new CitaMedicaDTO(
                cita.getId(),
                cita.getCodigoCita(),
                cita.getPaciente().getNombreCompleto(),
                cita.getDoctor().getNombre(),
                cita.getNivelPrioridad(),
                cita.getMotivoConsulta(),
                cita.getEstado(),
                cita.getMontoConsulta(),
                cita.getFechaCita());
    }
}