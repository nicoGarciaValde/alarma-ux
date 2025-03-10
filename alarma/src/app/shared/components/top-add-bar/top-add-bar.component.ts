import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-top-add-bar',
  templateUrl: './top-add-bar.component.html',
  styleUrls: ['./top-add-bar.component.css']
})
export class TopAddBarComponent implements OnInit {

  title: any = 'Dispositivos - Pomodoto titulo test'
  constructor() { }

  ngOnInit() {

  }

  goBack(){}

}
