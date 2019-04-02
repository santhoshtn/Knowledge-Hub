import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecommendationcardComponent } from './recommendationcard.component';

describe('RecommendationcardComponent', () => {
  let component: RecommendationcardComponent;
  let fixture: ComponentFixture<RecommendationcardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecommendationcardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommendationcardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
