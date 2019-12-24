import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BitcoinCancelComponent } from './bitcoin-cancel.component';

describe('BitcoinCancelComponent', () => {
  let component: BitcoinCancelComponent;
  let fixture: ComponentFixture<BitcoinCancelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BitcoinCancelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BitcoinCancelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
