import { NgModule } from '@angular/core';

import { Test4DetailsComponent, Test4ListComponent, Test4NewComponent } from './';
import { test4Route } from './test-4.route';

import { SharedModule } from 'src/app/common/shared';
import { GeneralComponentsModule } from 'src/app/common/general-components/general.module';

const entities = [Test4DetailsComponent, Test4ListComponent, Test4NewComponent];
@NgModule({
  declarations: entities,
  exports: entities,
  imports: [test4Route, SharedModule, GeneralComponentsModule],
})
export class Test4Module {}
