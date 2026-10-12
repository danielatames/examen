import { Component, inject, OnInit, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CitaService } from '../../services/cita.service';
import { CitaMedica } from '../../models/cita.model';

@Component({
  selector: 'app-cita-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cita-list.html',
  styleUrl: './cita-list.css',
})
export class CitaList implements OnInit {
  private citaService = inject(CitaService);

  citas = signal<CitaMedica[]>([]);
  filtroPrioridad = signal<string>('TODAS');

  citasFiltradas = computed(() => {
    const filtro = this.filtroPrioridad();
    if (filtro === 'TODAS') {
      return this.citas();
    }
    return this.citas().filter((cita) => cita.nivelPrioridad === filtro);
  });

  ngOnInit(): void {
    this.cargarCitas();
  }

  cargarCitas(): void {
    this.citaService.getCitas().subscribe({
      next: (data: CitaMedica[]) => this.citas.set(data),
      error: (err: unknown) => console.error('Error al obtener citas:', err),
    });
  }

  cambiarFiltro(event: Event): void {
    const selectElement = event.target as HTMLSelectElement;
    this.filtroPrioridad.set(selectElement.value);
  }

  cambiarEstado(id: number, nuevoEstado: string): void {
    this.citaService.actualizarEstado(id, nuevoEstado).subscribe({
      next: () => this.cargarCitas(),
      error: (err: unknown) => console.error('Error al actualizar el estado:', err),
    });
  }
}