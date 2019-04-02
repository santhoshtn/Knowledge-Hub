import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayIntentComponent } from './display-intent.component';

describe('DisplayIntentComponent', () => {
  let component: DisplayIntentComponent;
  let fixture: ComponentFixture<DisplayIntentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayIntentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayIntentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
