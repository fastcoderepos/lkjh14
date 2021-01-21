import { NgModule } from '@angular/core';

import {
  Test4ExtendedService,
  Test4DetailsExtendedComponent,
  Test4ListExtendedComponent,
  Test4NewExtendedComponent,
} from './';
import { Test4Service } from 'src/app/entities/test-4';
import { Test4Module } from 'src/app/entities/test-4/test-4.module';
import { test4Route } from './test-4.route';

import { SharedModule } from 'src/app/common/shared';
import { GeneralComponentsExtendedModule } from 'src/app/common/general-components/extended/general-extended.module';

const entities = [Test4DetailsExtendedComponent, Test4ListExtendedComponent, Test4NewExtendedComponent];
@NgModule({
  declarations: entities,
  exports: entities,
  imports: [test4Route, Test4Module, SharedModule, GeneralComponentsExtendedModule],
  providers: [{ provide: Test4Service, useClass: Test4ExtendedService }],
})
export class Test4ExtendedModule {}
