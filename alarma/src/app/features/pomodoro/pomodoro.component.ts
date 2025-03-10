import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-pomodoro',
  templateUrl: './pomodoro.component.html',
  styleUrls: ['./pomodoro.component.css']
})
export class PomodoroComponent {

  pomodoroForm: FormGroup;
  tiempoRestante: number = 0;
  timer: any;
  isRunning = false;
  pausasRealizadas = 0;
  pomodorosCompletados = 0;
  modoActual: string = 'pomodoro';

  constructor(private fb: FormBuilder) {
    this.pomodoroForm = this.fb.group({
      tiempoPomodoro: ['', Validators.required],
      tiempoDescansoCorto: ['', Validators.required],
      tiempoDescansoLargo: ['', Validators.required],
      numeroRepeticiones: ['', Validators.required]
    });
  }

  iniciarPomodoro() {
    if (this.pomodoroForm.invalid) {
      return;
    }
    //Valida el estado del boton
    if (this.isRunning) {
      this.pausarPomodoro();
      return;
    }

    this.isRunning = true;

    // Inicializa el tiempo dependiendo del modo actual
    if (this.modoActual === 'pomodoro' && this.tiempoRestante === 0) {
      this.tiempoRestante = parseInt(this.pomodoroForm.value.tiempoPomodoro, 10) * 60;
    }
    else if (this.modoActual === 'short-break' && this.tiempoRestante === 0) {
      this.tiempoRestante = parseInt(this.pomodoroForm.value.tiempoDescansoCorto, 10) * 60;
    }
    else if (this.modoActual === 'long-break' && this.tiempoRestante === 0) {
      this.tiempoRestante = parseInt(this.pomodoroForm.value.tiempoDescansoLargo, 10) * 60;
    }

    //inicia el timer
    this.timer = setInterval(() => {
      if (this.tiempoRestante > 0) {
        this.tiempoRestante--;
      } else {
        clearInterval(this.timer);
        this.isRunning = false;
        this.cambiarModo();
      }
    }, 1000);
  }

  // Metodo usado para cambiar el modo (POMODORO, SHORT-TIME, LONG-TIME)
  cambiarModo() {
    const repeticiones = parseInt(this.pomodoroForm.value.numeroRepeticiones, 10);

    if (this.modoActual === 'pomodoro') {
      this.pomodorosCompletados++;

      //Valida que tipo de descanso debe aplicar
      if (this.pomodorosCompletados >= repeticiones) {
        this.mostrarAlertaDescanso('Inicio del tiempo de descanso largo', 'long-break');
      } else {
        this.mostrarAlertaDescanso('Inicio del tiempo de descanso corto', 'short-break');
      }
    }
    else if (this.modoActual === 'short-break') {
      this.modoActual = 'pomodoro';
      this.iniciarPomodoro();
    }
    else if (this.modoActual === 'long-break') {
      alert('FELICIDADES COMPLETASTE UN POMODORO');
      this.pausasRealizadas = 0;
      this.pomodorosCompletados = 0;
      this.modoActual = 'pomodoro';
    }
  }
  mostrarAlertaDescanso(mensaje: string, siguienteModo: string) {
    setTimeout(() => {
      alert(mensaje);
      this.modoActual = siguienteModo;
      this.iniciarPomodoro();
    }, 500);
  }

  pausarPomodoro() {
    if (this.isRunning) {
      this.isRunning = false;
      clearInterval(this.timer);
      this.pausasRealizadas++; // Incrementa el contador de pausas
    }
  }

  get minutos() {
    return Math.floor(this.tiempoRestante / 60);
  }

  get segundos() {
    return this.tiempoRestante % 60;
  }
}
