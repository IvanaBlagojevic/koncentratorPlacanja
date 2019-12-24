import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PayPalErrorComponent } from './pay-pal-error.component';

describe('PayPalErrorComponent', () => {
  let component: PayPalErrorComponent;
  let fixture: ComponentFixture<PayPalErrorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PayPalErrorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PayPalErrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
