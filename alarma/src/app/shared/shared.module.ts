import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TopAddBarComponent } from './components/top-add-bar/top-add-bar.component';
import { NavigationRailComponent } from './components/navigation-rail/navigation-rail.component';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';

@NgModule({
  declarations: [
    TopAddBarComponent,
    NavigationRailComponent,

  ],
  imports: [
    CommonModule,
    MatToolbarModule,
    MatIconModule,
    MatSidenavModule,
    MatButtonModule
  ],
  exports: [
    TopAddBarComponent,
    NavigationRailComponent
  ]
})
export class SharedModule { }

