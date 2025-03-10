import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DevicesComponent } from './devices.component';
import { DevicesRoutingModule } from './devices-routing.module';

@NgModule({
  imports: [
    CommonModule,
    DevicesRoutingModule,
  ],
  declarations: [DevicesComponent]
})
export class DevicesModule { }
