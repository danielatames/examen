
package com.medtriage.model;

import jakarta.persistence.*;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 80)
    private String especialidad;

    @Column(nullable = false)
    private Boolean disponible = true;

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Doctor(Long id, String nombre, String especialidad, Boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.disponible = disponible;
    }

    public Doctor() {
    }

    
}