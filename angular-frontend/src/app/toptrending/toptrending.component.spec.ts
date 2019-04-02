import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ToptrendingComponent } from './toptrending.component';

describe('ToptrendingComponent', () => {
  let component: ToptrendingComponent;
  let fixture: ComponentFixture<ToptrendingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ToptrendingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ToptrendingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
