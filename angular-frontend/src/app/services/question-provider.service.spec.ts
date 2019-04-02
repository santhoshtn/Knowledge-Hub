import { TestBed } from '@angular/core/testing';

import { QuestionProviderService } from './question-provider.service';

describe('QuestionProviderService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: QuestionProviderService = TestBed.get(QuestionProviderService);
    expect(service).toBeTruthy();
  });
});
