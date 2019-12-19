import { TestBed } from '@angular/core/testing';

import { BuyerInfoService } from './buyer-info.service';

describe('BuyerInfoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BuyerInfoService = TestBed.get(BuyerInfoService);
    expect(service).toBeTruthy();
  });
});
