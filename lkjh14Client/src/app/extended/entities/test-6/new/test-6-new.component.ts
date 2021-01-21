import { Component, OnInit, Inject } from '@angular/core';
import { Test6ExtendedService } from '../test-6.service';

import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';
import { MatDialogRef, MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { GlobalPermissionService } from 'src/app/core/global-permission.service';

import { Test6NewComponent } from 'src/app/entities/test-6/index';

@Component({
  selector: 'app-test-6-new',
  templateUrl: './test-6-new.component.html',
  styleUrls: ['./test-6-new.component.scss'],
})
export class Test6NewExtendedComponent extends Test6NewComponent implements OnInit {
  title: string = 'New Test6';
  constructor(
    public formBuilder: FormBuilder,
    public router: Router,
    public route: ActivatedRoute,
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<Test6NewComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public global: Globals,
    public pickerDialogService: PickerDialogService,
    public test6ExtendedService: Test6ExtendedService,
    public errorService: ErrorService,
    public globalPermissionService: GlobalPermissionService
  ) {
    super(
      formBuilder,
      router,
      route,
      dialog,
      dialogRef,
      data,
      global,
      pickerDialogService,
      test6ExtendedService,
      errorService,
      globalPermissionService
    );
  }

  ngOnInit() {
    super.ngOnInit();
  }
}
