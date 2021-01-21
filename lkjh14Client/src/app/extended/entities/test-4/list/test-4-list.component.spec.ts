import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ChangeDetectorRef, NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterTestingModule } from '@angular/router/testing';

import { EntryComponents, TestingModule } from 'src/testing/utils';
import {
  Test4ExtendedService,
  Test4DetailsExtendedComponent,
  Test4ListExtendedComponent,
  Test4NewExtendedComponent,
} from '../';
import { ITest4 } from 'src/app/entities/test-4';
import { ListFiltersComponent, ServiceUtils } from 'src/app/common/shared';
import { ListComponent, DetailsComponent, NewComponent, FieldsComp } from 'src/app/common/general-components';

describe('Test4ListExtendedComponent', () => {
  let fixture: ComponentFixture<Test4ListExtendedComponent>;
  let component: Test4ListExtendedComponent;
  let el: HTMLElement;

  describe('Unit tests', () => {
    beforeEach(async(() => {
      TestBed.configureTestingModule({
        declarations: [Test4ListExtendedComponent, ListComponent],
        imports: [TestingModule],
        providers: [Test4ExtendedService, ChangeDetectorRef],
        schemas: [NO_ERRORS_SCHEMA],
      }).compileComponents();
    }));

    beforeEach(() => {
      fixture = TestBed.createComponent(Test4ListExtendedComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  });

  describe('Integration tests', () => {
    beforeEach(async(() => {
      TestBed.configureTestingModule({
        declarations: [
          Test4ListExtendedComponent,
          Test4NewExtendedComponent,
          NewComponent,
          Test4DetailsExtendedComponent,
          ListComponent,
          DetailsComponent,
          FieldsComp,
        ].concat(EntryComponents),
        imports: [
          TestingModule,
          RouterTestingModule.withRoutes([
            { path: 'test4', component: Test4ListExtendedComponent },
            { path: 'test4/:id', component: Test4DetailsExtendedComponent },
          ]),
        ],
        providers: [Test4ExtendedService, ChangeDetectorRef],
      }).compileComponents();
    }));

    beforeEach(() => {
      fixture = TestBed.createComponent(Test4ListExtendedComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  });
});
