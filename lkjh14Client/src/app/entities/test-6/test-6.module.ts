import { NgModule } from '@angular/core';

import { Test6DetailsComponent, Test6ListComponent, Test6NewComponent } from './';
import { test6Route } from './test-6.route';

import { SharedModule } from 'src/app/common/shared';
import { GeneralComponentsModule } from 'src/app/common/general-components/general.module';

const entities = [Test6DetailsComponent, Test6ListComponent, Test6NewComponent];
@NgModule({
  declarations: entities,
  exports: entities,
  imports: [test6Route, SharedModule, GeneralComponentsModule],
})
export class Test6Module {}
