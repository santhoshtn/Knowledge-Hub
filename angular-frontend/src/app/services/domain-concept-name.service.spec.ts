import { TestBed } from '@angular/core/testing';

import { DomainConceptNameService } from './domain-concept-name.service';

describe('DomainConceptNameService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DomainConceptNameService = TestBed.get(DomainConceptNameService);
    expect(service).toBeTruthy();
  });
});
