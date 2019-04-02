import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayD3Component } from './display-d3.component';

describe('DisplayD3Component', () => {
  let component: DisplayD3Component;
  let fixture: ComponentFixture<DisplayD3Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayD3Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayD3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
