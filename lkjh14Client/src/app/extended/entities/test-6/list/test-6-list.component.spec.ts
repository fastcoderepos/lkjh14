import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ChangeDetectorRef, NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterTestingModule } from '@angular/router/testing';

import { EntryComponents, TestingModule } from 'src/testing/utils';
import {
  Test6ExtendedService,
  Test6DetailsExtendedComponent,
  Test6ListExtendedComponent,
  Test6NewExtendedComponent,
} from '../';
import { ITest6 } from 'src/app/entities/test-6';
import { ListFiltersComponent, ServiceUtils } from 'src/app/common/shared';
import { ListComponent, DetailsComponent, NewComponent, FieldsComp } from 'src/app/common/general-components';

describe('Test6ListExtendedComponent', () => {
  let fixture: ComponentFixture<Test6ListExtendedComponent>;
  let component: Test6ListExtendedComponent;
  let el: HTMLElement;

  describe('Unit tests', () => {
    beforeEach(async(() => {
      TestBed.configureTestingModule({
        declarations: [Test6ListExtendedComponent, ListComponent],
        imports: [TestingModule],
        providers: [Test6ExtendedService, ChangeDetectorRef],
        schemas: [NO_ERRORS_SCHEMA],
      }).compileComponents();
    }));

    beforeEach(() => {
      fixture = TestBed.createComponent(Test6ListExtendedComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  });

  describe('Integration tests', () => {
    beforeEach(async(() => {
      TestBed.configureTestingModule({
        declarations: [
          Test6ListExtendedComponent,
          Test6NewExtendedComponent,
          NewComponent,
          Test6DetailsExtendedComponent,
          ListComponent,
          DetailsComponent,
          FieldsComp,
        ].concat(EntryComponents),
        imports: [
          TestingModule,
          RouterTestingModule.withRoutes([
            { path: 'test6', component: Test6ListExtendedComponent },
            { path: 'test6/:id', component: Test6DetailsExtendedComponent },
          ]),
        ],
        providers: [Test6ExtendedService, ChangeDetectorRef],
      }).compileComponents();
    }));

    beforeEach(() => {
      fixture = TestBed.createComponent(Test6ListExtendedComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  });
});
