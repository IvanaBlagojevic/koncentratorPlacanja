import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSubscriptionDetailsComponent } from './create-subscription-details.component';

describe('CreateSubscriptionDetailsComponent', () => {
  let component: CreateSubscriptionDetailsComponent;
  let fixture: ComponentFixture<CreateSubscriptionDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateSubscriptionDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateSubscriptionDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
