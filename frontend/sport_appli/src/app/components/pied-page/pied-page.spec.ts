import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PiedPage } from './pied-page';

describe('PiedPage', () => {
  let component: PiedPage;
  let fixture: ComponentFixture<PiedPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PiedPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PiedPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
