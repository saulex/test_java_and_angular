import { TestBed } from '@angular/core/testing';

import { InterceptorPostService } from './interceptor-post.service';

describe('InterceptorPostService', () => {
  let service: InterceptorPostService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InterceptorPostService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
