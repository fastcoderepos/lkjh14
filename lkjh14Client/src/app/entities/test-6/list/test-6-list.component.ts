import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { MatDialog } from '@angular/material';

import { ITest6 } from '../itest-6';
import { Test6Service } from '../test-6.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Test6NewComponent } from '../new/test-6-new.component';
import { BaseListComponent, Globals, listColumnType, PickerDialogService, ErrorService } from 'src/app/common/shared';
import { GlobalPermissionService } from 'src/app/core/global-permission.service';

@Component({
  selector: 'app-test-6-list',
  templateUrl: './test-6-list.component.html',
  styleUrls: ['./test-6-list.component.scss'],
})
export class Test6ListComponent extends BaseListComponent<ITest6> implements OnInit {
  title = 'Test6';
  constructor(
    public router: Router,
    public route: ActivatedRoute,
    public global: Globals,
    public dialog: MatDialog,
    public changeDetectorRefs: ChangeDetectorRef,
    public pickerDialogService: PickerDialogService,
    public test6Service: Test6Service,
    public errorService: ErrorService,
    public globalPermissionService: GlobalPermissionService
  ) {
    super(router, route, dialog, global, changeDetectorRefs, pickerDialogService, test6Service, errorService);
  }

  ngOnInit() {
    this.entityName = 'Test6';
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
        column: 'version',
        searchColumn: 'version',
        label: 'version',
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
      comp = Test6NewComponent;
    }
    super.addNew(comp);
  }
}
