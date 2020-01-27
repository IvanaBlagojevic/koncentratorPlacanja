import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JournalSubscriptionComponent } from './journal-subscription.component';

describe('JournalSubscriptionComponent', () => {
  let component: JournalSubscriptionComponent;
  let fixture: ComponentFixture<JournalSubscriptionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JournalSubscriptionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JournalSubscriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
