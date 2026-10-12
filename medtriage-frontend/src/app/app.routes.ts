import { Routes } from '@angular/router';
import { CitaList } from './components/cita-list/cita-list';
import { CitaForm } from './components/cita-form/cita-form';

export const routes: Routes = [
  { path: '', redirectTo: 'citas', pathMatch: 'full' },
  { path: 'citas', component: CitaList },
  { path: 'nueva-cita', component: CitaForm }
];