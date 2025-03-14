import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DevicesComponent } from './devices.component';
import { DevicesRoutingModule } from './devices-routing.module';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatButtonModule } from '@angular/material/button';


@NgModule({
  imports: [
    CommonModule,
    DevicesRoutingModule,
    MatListModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatSlideToggleModule
  ],
  declarations: [DevicesComponent]
})
export class DevicesModule { }
