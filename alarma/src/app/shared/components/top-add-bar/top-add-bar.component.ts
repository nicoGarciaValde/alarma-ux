import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-top-add-bar',
  templateUrl: './top-add-bar.component.html',
  styleUrls: ['./top-add-bar.component.css']
})
export class TopAddBarComponent implements OnInit {

  title: string = '';
  constructor(
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe((event: any) => {
      this.setTitleFromUrl(event.urlAfterRedirects);
    });
  }
  // Método para establecer el título dinámicamente
  setTitleFromUrl(url: string) {
    if (url.includes('/pomodoro')) {
      this.title = 'Pomodoro';
    } else if (url.includes('/dispositivos')) {
      this.title = 'Dispositivos';
    }
  }
  goBack() {
    this.router.navigate(['..'], { relativeTo: this.route });
  }
}
