import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayAdminHomeComponent } from './display-admin-home.component';

describe('DisplayAdminHomeComponent', () => {
  let component: DisplayAdminHomeComponent;
  let fixture: ComponentFixture<DisplayAdminHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayAdminHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayAdminHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
