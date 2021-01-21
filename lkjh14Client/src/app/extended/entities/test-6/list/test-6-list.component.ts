import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { MatDialog } from '@angular/material';

import { Router, ActivatedRoute } from '@angular/router';

import { Test6ExtendedService } from '../test-6.service';
import { Test6NewExtendedComponent } from '../new/test-6-new.component';
import { Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';

import { GlobalPermissionService } from 'src/app/core/global-permission.service';
import { Test6ListComponent } from 'src/app/entities/test-6/index';

@Component({
  selector: 'app-test-6-list',
  templateUrl: './test-6-list.component.html',
  styleUrls: ['./test-6-list.component.scss'],
})
export class Test6ListExtendedComponent extends Test6ListComponent implements OnInit {
  title: string = 'Test6';
  constructor(
    public router: Router,
    public route: ActivatedRoute,
    public global: Globals,
    public dialog: MatDialog,
    public changeDetectorRefs: ChangeDetectorRef,
    public pickerDialogService: PickerDialogService,
    public test6Service: Test6ExtendedService,
    public errorService: ErrorService,
    public globalPermissionService: GlobalPermissionService
  ) {
    super(
      router,
      route,
      global,
      dialog,
      changeDetectorRefs,
      pickerDialogService,
      test6Service,
      errorService,
      globalPermissionService
    );
  }

  ngOnInit() {
    super.ngOnInit();
  }

  addNew() {
    super.addNew(Test6NewExtendedComponent);
  }
}
