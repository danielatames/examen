import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CitaMedica, CrearCitaPayload } from '../models/cita.model';

@Injectable({
  providedIn: 'root'
})
export class CitaService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/v1/citas';

  getCitas(): Observable<CitaMedica[]> {
    return this.http.get<CitaMedica[]>(this.apiUrl);
  }

  getCitasPorPrioridad(prioridad: string): Observable<CitaMedica[]> {
    return this.http.get<CitaMedica[]>(`${this.apiUrl}/prioridad/${prioridad}`);
  }

  crearCita(payload: CrearCitaPayload): Observable<CitaMedica> {
    return this.http.post<CitaMedica>(this.apiUrl, payload);
  }

  actualizarEstado(id: number, nuevoEstado: string): Observable<CitaMedica> {
    return this.http.patch<CitaMedica>(`${this.apiUrl}/${id}/estado`, { estado: nuevoEstado });
  }
}