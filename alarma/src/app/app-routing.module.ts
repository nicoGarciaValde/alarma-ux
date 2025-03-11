import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'dispositivos', loadChildren: () => import('./features/devices/devices.module').then(m => m.DevicesModule) },
  { path: 'pomodoro', loadChildren: () => import('./features/pomodoro/pomodoro.module').then(m => m.PomodoroModule) },
  { path: '', redirectTo: 'dispositivos', pathMatch: 'full' },
  { path: '**', redirectTo: 'dispositivos' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
