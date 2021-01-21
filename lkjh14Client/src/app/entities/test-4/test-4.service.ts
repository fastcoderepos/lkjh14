import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ITest4 } from './itest-4';
import { GenericApiService } from 'src/app/common/shared';

@Injectable({
  providedIn: 'root',
})
export class Test4Service extends GenericApiService<ITest4> {
  constructor(protected httpclient: HttpClient) {
    super(httpclient, 'test4');
  }
}
