import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StoppingPatternComponent } from './stopping-pattern.component';

describe('StoppingPatternComponent', () => {
  let component: StoppingPatternComponent;
  let fixture: ComponentFixture<StoppingPatternComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StoppingPatternComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StoppingPatternComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
