import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PomodoroComponent } from './pomodoro.component';

const routes: Routes = [{ path: '', component: PomodoroComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class PomodoroRoutingModule { }
