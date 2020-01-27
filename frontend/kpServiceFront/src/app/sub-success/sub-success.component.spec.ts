import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubSuccessComponent } from './sub-success.component';

describe('SubSuccessComponent', () => {
  let component: SubSuccessComponent;
  let fixture: ComponentFixture<SubSuccessComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubSuccessComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubSuccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
