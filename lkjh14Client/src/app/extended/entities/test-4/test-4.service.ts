import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Test4Service } from 'src/app/entities/test-4/test-4.service';
@Injectable({
  providedIn: 'root',
})
export class Test4ExtendedService extends Test4Service {
  constructor(protected httpclient: HttpClient) {
    super(httpclient);
    this.url += '/extended';
  }
}
