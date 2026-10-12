import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CitaService } from '../../services/cita.service';
import { CrearCitaPayload } from '../../models/cita.model';

@Component({
  selector: 'app-cita-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cita-form.html',
  styleUrl: './cita-form.css',
})
export class CitaForm {
  private citaService = inject(CitaService);
  private router = inject(Router);

  nuevaCita: CrearCitaPayload = {
    pacienteId: 0,
    doctorId: 0,
    nivelPrioridad: 'MEDIA',
    motivoConsulta: '',
    montoConsulta: 0,
  };

  guardarCita(): void {
    this.citaService.crearCita(this.nuevaCita).subscribe({
      next: () => {
        this.router.navigate(['/citas']);
      },
      error: (err: unknown) => console.error('Error al guardar la cita:', err),
    });
  }
}