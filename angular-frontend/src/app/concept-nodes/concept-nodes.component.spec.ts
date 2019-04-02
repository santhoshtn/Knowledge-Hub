import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConceptNodesComponent } from './concept-nodes.component';

describe('ConceptNodesComponent', () => {
  let component: ConceptNodesComponent;
  let fixture: ComponentFixture<ConceptNodesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConceptNodesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConceptNodesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
