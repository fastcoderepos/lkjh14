import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

import { Test4ExtendedService } from '../test-4.service';
import { Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';

import { GlobalPermissionService } from 'src/app/core/global-permission.service';
import { Test4DetailsComponent } from 'src/app/entities/test-4/index';

@Component({
  selector: 'app-test-4-details',
  templateUrl: './test-4-details.component.html',
  styleUrls: ['./test-4-details.component.scss'],
})
export class Test4DetailsExtendedComponent extends Test4DetailsComponent implements OnInit {
  title: string = 'Test4';
  parentUrl: string = 'test4';
  //roles: IRole[];
  constructor(
    public formBuilder: FormBuilder,
    public router: Router,
    public route: ActivatedRoute,
    public dialog: MatDialog,
    public global: Globals,
    public test4ExtendedService: Test4ExtendedService,
    public pickerDialogService: PickerDialogService,
    public errorService: ErrorService,
    public globalPermissionService: GlobalPermissionService
  ) {
    super(
      formBuilder,
      router,
      route,
      dialog,
      global,
      test4ExtendedService,
      pickerDialogService,
      errorService,
      globalPermissionService
    );
  }

  ngOnInit() {
    super.ngOnInit();
  }
}
