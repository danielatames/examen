import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
standalone: true,
  imports: [RouterOutlet],
  template: `
    <h1>MedTriage Express</h1>
    <hr />
    <router-outlet></router-outlet>
  `
})
export class AppComponent {
  
}