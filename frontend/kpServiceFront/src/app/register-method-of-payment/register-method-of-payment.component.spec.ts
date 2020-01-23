import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterMethodOfPaymentComponent } from './register-method-of-payment.component';

describe('RegisterMethodOfPaymentComponent', () => {
  let component: RegisterMethodOfPaymentComponent;
  let fixture: ComponentFixture<RegisterMethodOfPaymentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterMethodOfPaymentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterMethodOfPaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
