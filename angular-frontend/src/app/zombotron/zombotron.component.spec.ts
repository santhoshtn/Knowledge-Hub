import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ZombotronComponent } from './zombotron.component';

describe('ZombotronComponent', () => {
  let component: ZombotronComponent;
  let fixture: ComponentFixture<ZombotronComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ZombotronComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ZombotronComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
