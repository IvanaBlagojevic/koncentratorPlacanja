import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuyJournalComponent } from './buy-journal.component';

describe('BuyJournalComponent', () => {
  let component: BuyJournalComponent;
  let fixture: ComponentFixture<BuyJournalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuyJournalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuyJournalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
