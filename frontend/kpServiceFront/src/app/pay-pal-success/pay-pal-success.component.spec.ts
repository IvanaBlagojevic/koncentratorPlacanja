import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PayPalSuccessComponent } from './pay-pal-success.component';

describe('PayPalSuccessComponent', () => {
  let component: PayPalSuccessComponent;
  let fixture: ComponentFixture<PayPalSuccessComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PayPalSuccessComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PayPalSuccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
