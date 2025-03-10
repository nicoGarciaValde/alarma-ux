/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { TopAddBarComponent } from './top-add-bar.component';

describe('TopAddBarComponent', () => {
  let component: TopAddBarComponent;
  let fixture: ComponentFixture<TopAddBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TopAddBarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TopAddBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
