import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterTestingModule } from '@angular/router/testing';
import { DetailsComponent, ListComponent, FieldsComp } from 'src/app/common/general-components';

import { TestingModule, EntryComponents } from 'src/testing/utils';
import { Test4ExtendedService, Test4DetailsExtendedComponent, Test4ListExtendedComponent } from '../';
import { ITest4 } from 'src/app/entities/test-4';
describe('Test4DetailsExtendedComponent', () => {
  let component: Test4DetailsExtendedComponent;
  let fixture: ComponentFixture<Test4DetailsExtendedComponent>;
  let el: HTMLElement;

  describe('Unit Tests', () => {
    beforeEach(async(() => {
      TestBed.configureTestingModule({
        declarations: [Test4DetailsExtendedComponent, DetailsComponent],
        imports: [TestingModule],
        providers: [Test4ExtendedService],
        schemas: [NO_ERRORS_SCHEMA],
      }).compileComponents();
    }));

    beforeEach(() => {
      fixture = TestBed.createComponent(Test4DetailsExtendedComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  });

  describe('Integration Tests', () => {
    beforeEach(async(() => {
      TestBed.configureTestingModule({
        declarations: [
          Test4DetailsExtendedComponent,
          Test4ListExtendedComponent,
          DetailsComponent,
          ListComponent,
          FieldsComp,
        ].concat(EntryComponents),
        imports: [
          TestingModule,
          RouterTestingModule.withRoutes([
            { path: 'test4', component: Test4DetailsExtendedComponent },
            { path: 'test4/:id', component: Test4ListExtendedComponent },
          ]),
        ],
        providers: [Test4ExtendedService],
      }).compileComponents();
    }));

    beforeEach(() => {
      fixture = TestBed.createComponent(Test4DetailsExtendedComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  });
});
