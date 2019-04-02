import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NavigIntentComponent } from './navig-intent.component';

describe('NavigIntentComponent', () => {
  let component: NavigIntentComponent;
  let fixture: ComponentFixture<NavigIntentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NavigIntentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavigIntentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
