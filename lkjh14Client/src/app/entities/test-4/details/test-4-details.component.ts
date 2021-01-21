import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

import { Test4Service } from '../test-4.service';
import { ITest4 } from '../itest-4';
import { BaseDetailsComponent, Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';
import { GlobalPermissionService } from 'src/app/core/global-permission.service';

@Component({
  selector: 'app-test-4-details',
  templateUrl: './test-4-details.component.html',
  styleUrls: ['./test-4-details.component.scss'],
})
export class Test4DetailsComponent extends BaseDetailsComponent<ITest4> implements OnInit {
  title = 'Test4';
  parentUrl = 'test4';
  constructor(
    public formBuilder: FormBuilder,
    public router: Router,
    public route: ActivatedRoute,
    public dialog: MatDialog,
    public global: Globals,
    public test4Service: Test4Service,
    public pickerDialogService: PickerDialogService,
    public errorService: ErrorService,
    public globalPermissionService: GlobalPermissionService
  ) {
    super(formBuilder, router, route, dialog, global, pickerDialogService, test4Service, errorService);
  }

  ngOnInit() {
    this.entityName = 'Test4';
    this.setAssociations();
    super.ngOnInit();
    this.setForm();
    this.getItem();
    this.setPickerSearchListener();
  }

  setForm() {
    this.itemForm = this.formBuilder.group({
      id: [{ value: '', disabled: true }, Validators.required],
    });

    this.fields = [];
  }

  onItemFetched(item: ITest4) {
    this.item = item;
    this.itemForm.patchValue(item);
  }

  setAssociations() {
    this.associations = [];

    this.childAssociations = this.associations.filter((association) => {
      return association.isParent;
    });

    this.parentAssociations = this.associations.filter((association) => {
      return !association.isParent;
    });
  }

  onSubmit() {
    let test4 = this.itemForm.getRawValue();
    super.onSubmit(test4);
  }
}
