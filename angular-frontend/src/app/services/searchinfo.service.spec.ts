import { TestBed } from '@angular/core/testing';

import { SearchinfoService } from './searchinfo.service';

describe('SearchinfoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchinfoService = TestBed.get(SearchinfoService);
    expect(service).toBeTruthy();
  });
});
