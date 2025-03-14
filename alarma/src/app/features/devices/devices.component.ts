import { Component, OnInit } from '@angular/core';

interface Device {
  name: string;
  status: string;
  icon: string;
  alarms: Alarm[];
}

interface Alarm {
  type: string;
  time: string;
  icon: string;
  enabled: boolean;
}


@Component({
  selector: 'app-devices',
  templateUrl: './devices.component.html',
  styleUrls: ['./devices.component.css']
})

export class DevicesComponent implements OnInit {

  devices: Device[] = [
    { 
      name: 'Iphone Nicolas', 
      status: 'Desconectado', 
      icon: 'smartphone',
      alarms: [
        { type: 'Alarma 1', time: '08:00 PM', icon: 'alarm', enabled: true },
        { type: 'Alarma 2', time: '12:00 AM', icon: 'alarm', enabled: false },
        { type: 'Alarma 3', time: '18:00 AM', icon: 'alarm', enabled: true }
      ]
    },
    { 
      name: 'Ipad Nicolas', 
      status: 'Conectado', 
      icon: 'smartphone' ,
      alarms: [
        { type: 'Alarma 1', time: '08:00', icon: 'alarm', enabled: true },
        { type: 'Alarma 2', time: '12:00', icon: 'alarm', enabled: false },
        { type: 'Alarma 3', time: '18:00', icon: 'alarm', enabled: true }
      ]
    },
    { 
      name: 'Teléfono trabajo', 
      status: 'Desconectado', 
      icon: 'smartphone',
      alarms: [
        { type: 'Alarma 1', time: '08:00', icon: 'alarm', enabled: true },
        { type: 'Alarma 2', time: '12:00', icon: 'alarm', enabled: false },
        { type: 'Alarma 3', time: '18:00', icon: 'alarm', enabled: true }
      ]
    }
  ];
  selectedDevice?: Device;

  isAlertVisible = false;


  constructor() { 

  }

  ngOnInit() {

  }

  onSelect(device: any) {
    this.selectedDevice = device;
  }

  toggleAlert() {
    this.isAlertVisible = !this.isAlertVisible;
  }
}
