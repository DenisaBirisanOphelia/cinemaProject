import { ComponentFixture, TestBed } from '@angular/core/testing';
import {CinemaSeatsComponent} from "./CinemaSeats.component";


describe('CinemaSeatsComponent', () => {
  let component: CinemaSeatsComponent;
  let fixture: ComponentFixture<CinemaSeatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CinemaSeatsComponent ]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CinemaSeatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

