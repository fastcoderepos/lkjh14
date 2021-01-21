import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ITest6 } from './itest-6';
import { GenericApiService } from 'src/app/common/shared';

@Injectable({
  providedIn: 'root',
})
export class Test6Service extends GenericApiService<ITest6> {
  constructor(protected httpclient: HttpClient) {
    super(httpclient, 'test6');
  }
}
