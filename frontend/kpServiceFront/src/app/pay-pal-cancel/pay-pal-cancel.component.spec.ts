import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PayPalCancelComponent } from './pay-pal-cancel.component';

describe('PayPalCancelComponent', () => {
  let component: PayPalCancelComponent;
  let fixture: ComponentFixture<PayPalCancelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PayPalCancelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PayPalCancelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
