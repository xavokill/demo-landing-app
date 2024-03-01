import { TestBed } from '@angular/core/testing';

import { RenapoService } from './renapo.service';

describe('CurpService', () => {
  let service: RenapoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RenapoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
