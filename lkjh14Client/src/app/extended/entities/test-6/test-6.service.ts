import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Test6Service } from 'src/app/entities/test-6/test-6.service';
@Injectable({
  providedIn: 'root',
})
export class Test6ExtendedService extends Test6Service {
  constructor(protected httpclient: HttpClient) {
    super(httpclient);
    this.url += '/extended';
  }
}
