import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Neo4jTermComponent } from './neo4j-term.component';

describe('Neo4jTermComponent', () => {
  let component: Neo4jTermComponent;
  let fixture: ComponentFixture<Neo4jTermComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Neo4jTermComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Neo4jTermComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
