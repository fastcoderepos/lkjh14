import { NgModule } from '@angular/core';

import {
  Test6ExtendedService,
  Test6DetailsExtendedComponent,
  Test6ListExtendedComponent,
  Test6NewExtendedComponent,
} from './';
import { Test6Service } from 'src/app/entities/test-6';
import { Test6Module } from 'src/app/entities/test-6/test-6.module';
import { test6Route } from './test-6.route';

import { SharedModule } from 'src/app/common/shared';
import { GeneralComponentsExtendedModule } from 'src/app/common/general-components/extended/general-extended.module';

const entities = [Test6DetailsExtendedComponent, Test6ListExtendedComponent, Test6NewExtendedComponent];
@NgModule({
  declarations: entities,
  exports: entities,
  imports: [test6Route, Test6Module, SharedModule, GeneralComponentsExtendedModule],
  providers: [{ provide: Test6Service, useClass: Test6ExtendedService }],
})
export class Test6ExtendedModule {}
