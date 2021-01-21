import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

import { Test6ExtendedService } from '../test-6.service';
import { Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';

import { GlobalPermissionService } from 'src/app/core/global-permission.service';
import { Test6DetailsComponent } from 'src/app/entities/test-6/index';

@Component({
  selector: 'app-test-6-details',
  templateUrl: './test-6-details.component.html',
  styleUrls: ['./test-6-details.component.scss'],
})
export class Test6DetailsExtendedComponent extends Test6DetailsComponent implements OnInit {
  title: string = 'Test6';
  parentUrl: string = 'test6';
  //roles: IRole[];
  constructor(
    public formBuilder: FormBuilder,
    public router: Router,
    public route: ActivatedRoute,
    public dialog: MatDialog,
    public global: Globals,
    public test6ExtendedService: Test6ExtendedService,
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
      test6ExtendedService,
      pickerDialogService,
      errorService,
      globalPermissionService
    );
  }

  ngOnInit() {
    super.ngOnInit();
  }
}
