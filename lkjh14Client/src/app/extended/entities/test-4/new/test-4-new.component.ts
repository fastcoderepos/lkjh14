import { Component, OnInit, Inject } from '@angular/core';
import { Test4ExtendedService } from '../test-4.service';

import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';
import { MatDialogRef, MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { GlobalPermissionService } from 'src/app/core/global-permission.service';

import { Test4NewComponent } from 'src/app/entities/test-4/index';

@Component({
  selector: 'app-test-4-new',
  templateUrl: './test-4-new.component.html',
  styleUrls: ['./test-4-new.component.scss'],
})
export class Test4NewExtendedComponent extends Test4NewComponent implements OnInit {
  title: string = 'New Test4';
  constructor(
    public formBuilder: FormBuilder,
    public router: Router,
    public route: ActivatedRoute,
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<Test4NewComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public global: Globals,
    public pickerDialogService: PickerDialogService,
    public test4ExtendedService: Test4ExtendedService,
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
      test4ExtendedService,
      errorService,
      globalPermissionService
    );
  }

  ngOnInit() {
    super.ngOnInit();
  }
}
