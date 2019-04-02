import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WebresultcardComponent } from './webresultcard.component';

describe('WebresultcardComponent', () => {
  let component: WebresultcardComponent;
  let fixture: ComponentFixture<WebresultcardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WebresultcardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WebresultcardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
