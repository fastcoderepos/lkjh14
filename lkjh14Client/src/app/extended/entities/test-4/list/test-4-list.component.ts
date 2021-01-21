import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { MatDialog } from '@angular/material';

import { Router, ActivatedRoute } from '@angular/router';

import { Test4ExtendedService } from '../test-4.service';
import { Test4NewExtendedComponent } from '../new/test-4-new.component';
import { Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';

import { GlobalPermissionService } from 'src/app/core/global-permission.service';
import { Test4ListComponent } from 'src/app/entities/test-4/index';

@Component({
  selector: 'app-test-4-list',
  templateUrl: './test-4-list.component.html',
  styleUrls: ['./test-4-list.component.scss'],
})
export class Test4ListExtendedComponent extends Test4ListComponent implements OnInit {
  title: string = 'Test4';
  constructor(
    public router: Router,
    public route: ActivatedRoute,
    public global: Globals,
    public dialog: MatDialog,
    public changeDetectorRefs: ChangeDetectorRef,
    public pickerDialogService: PickerDialogService,
    public test4Service: Test4ExtendedService,
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
      test4Service,
      errorService,
      globalPermissionService
    );
  }

  ngOnInit() {
    super.ngOnInit();
  }

  addNew() {
    super.addNew(Test4NewExtendedComponent);
  }
}
