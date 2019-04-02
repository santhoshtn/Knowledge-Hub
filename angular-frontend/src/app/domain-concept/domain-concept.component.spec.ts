import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DomainConceptComponent } from './domain-concept.component';

describe('DomainConceptComponent', () => {
  let component: DomainConceptComponent;
  let fixture: ComponentFixture<DomainConceptComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DomainConceptComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DomainConceptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
