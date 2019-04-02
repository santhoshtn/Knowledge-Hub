import { TestBed } from '@angular/core/testing';

import { QuestionPublisherService } from './question-publisher.service';

describe('QuestionPublisherService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: QuestionPublisherService = TestBed.get(QuestionPublisherService);
    expect(service).toBeTruthy();
  });
});
