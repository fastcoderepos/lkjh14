import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { MatDialog } from '@angular/material';

import { ITest4 } from '../itest-4';
import { Test4Service } from '../test-4.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Test4NewComponent } from '../new/test-4-new.component';
import { BaseListComponent, Globals, listColumnType, PickerDialogService, ErrorService } from 'src/app/common/shared';
import { GlobalPermissionService } from 'src/app/core/global-permission.service';

@Component({
  selector: 'app-test-4-list',
  templateUrl: './test-4-list.component.html',
  styleUrls: ['./test-4-list.component.scss'],
})
export class Test4ListComponent extends BaseListComponent<ITest4> implements OnInit {
  title = 'Test4';
  constructor(
    public router: Router,
    public route: ActivatedRoute,
    public global: Globals,
    public dialog: MatDialog,
    public changeDetectorRefs: ChangeDetectorRef,
    public pickerDialogService: PickerDialogService,
    public test4Service: Test4Service,
    public errorService: ErrorService,
    public globalPermissionService: GlobalPermissionService
  ) {
    super(router, route, dialog, global, changeDetectorRefs, pickerDialogService, test4Service, errorService);
  }

  ngOnInit() {
    this.entityName = 'Test4';
    this.setAssociation();
    this.setColumns();
    this.primaryKeys = ['id'];
    super.ngOnInit();
  }

  setAssociation() {
    this.associations = [];
  }

  setColumns() {
    this.columns = [
      {
        column: 'id',
        searchColumn: 'id',
        label: 'id',
        sort: true,
        filter: true,
        type: listColumnType.Number,
      },
      {
        column: 'actions',
        label: 'Actions',
        sort: false,
        filter: false,
        type: listColumnType.String,
      },
    ];
    this.selectedColumns = this.columns;
    this.displayedColumns = this.columns.map((obj) => {
      return obj.column;
    });
  }
  addNew(comp) {
    if (!comp) {
      comp = Test4NewComponent;
    }
    super.addNew(comp);
  }
}
