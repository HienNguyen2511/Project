import { TestBed } from '@angular/core/testing';

import { MyAppFormService } from './my-app-form.service';

describe('Luv2ShopFormService', () => {
  let service: MyAppFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MyAppFormService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
