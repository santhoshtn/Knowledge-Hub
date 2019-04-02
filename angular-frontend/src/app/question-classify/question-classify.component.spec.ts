import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionClassifyComponent } from './question-classify.component';

describe('QuestionClassifyComponent', () => {
  let component: QuestionClassifyComponent;
  let fixture: ComponentFixture<QuestionClassifyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuestionClassifyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuestionClassifyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
