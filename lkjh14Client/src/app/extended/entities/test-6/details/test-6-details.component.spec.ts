import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterTestingModule } from '@angular/router/testing';
import { DetailsComponent, ListComponent, FieldsComp } from 'src/app/common/general-components';

import { TestingModule, EntryComponents } from 'src/testing/utils';
import { Test6ExtendedService, Test6DetailsExtendedComponent, Test6ListExtendedComponent } from '../';
import { ITest6 } from 'src/app/entities/test-6';
describe('Test6DetailsExtendedComponent', () => {
  let component: Test6DetailsExtendedComponent;
  let fixture: ComponentFixture<Test6DetailsExtendedComponent>;
  let el: HTMLElement;

  describe('Unit Tests', () => {
    beforeEach(async(() => {
      TestBed.configureTestingModule({
        declarations: [Test6DetailsExtendedComponent, DetailsComponent],
        imports: [TestingModule],
        providers: [Test6ExtendedService],
        schemas: [NO_ERRORS_SCHEMA],
      }).compileComponents();
    }));

    beforeEach(() => {
      fixture = TestBed.createComponent(Test6DetailsExtendedComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  });

  describe('Integration Tests', () => {
    beforeEach(async(() => {
      TestBed.configureTestingModule({
        declarations: [
          Test6DetailsExtendedComponent,
          Test6ListExtendedComponent,
          DetailsComponent,
          ListComponent,
          FieldsComp,
        ].concat(EntryComponents),
        imports: [
          TestingModule,
          RouterTestingModule.withRoutes([
            { path: 'test6', component: Test6DetailsExtendedComponent },
            { path: 'test6/:id', component: Test6ListExtendedComponent },
          ]),
        ],
        providers: [Test6ExtendedService],
      }).compileComponents();
    }));

    beforeEach(() => {
      fixture = TestBed.createComponent(Test6DetailsExtendedComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  });
});
