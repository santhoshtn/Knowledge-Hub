import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NavigConceptComponent } from './navig-concept.component';

describe('NavigConceptComponent', () => {
  let component: NavigConceptComponent;
  let fixture: ComponentFixture<NavigConceptComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NavigConceptComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavigConceptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
