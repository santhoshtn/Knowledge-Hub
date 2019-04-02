import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IntentNodesComponent } from './intent-nodes.component';

describe('IntentNodesComponent', () => {
  let component: IntentNodesComponent;
  let fixture: ComponentFixture<IntentNodesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IntentNodesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IntentNodesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
