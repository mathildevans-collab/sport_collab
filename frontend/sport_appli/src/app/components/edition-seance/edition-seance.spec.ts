import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditionSeance } from './edition-seance';

describe('EditionSeance', () => {
  let component: EditionSeance;
  let fixture: ComponentFixture<EditionSeance>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditionSeance]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditionSeance);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
