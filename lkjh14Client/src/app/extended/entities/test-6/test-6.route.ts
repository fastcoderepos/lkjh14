import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { CanDeactivateGuard } from 'src/app/common/shared';
import { AuthGuard } from 'src/app/core/auth-guard';
import { Test6DetailsExtendedComponent, Test6ListExtendedComponent, Test6NewExtendedComponent } from './';

const routes: Routes = [
  { path: '', component: Test6ListExtendedComponent, canDeactivate: [CanDeactivateGuard], canActivate: [AuthGuard] },
  {
    path: ':id',
    component: Test6DetailsExtendedComponent,
    canDeactivate: [CanDeactivateGuard],
    canActivate: [AuthGuard],
  },
  { path: 'new', component: Test6NewExtendedComponent, canActivate: [AuthGuard] },
];

export const test6Route: ModuleWithProviders = RouterModule.forChild(routes);
