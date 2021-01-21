import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { CanDeactivateGuard } from 'src/app/common/shared';
import { AuthGuard } from 'src/app/core/auth-guard';
import { Test4DetailsExtendedComponent, Test4ListExtendedComponent, Test4NewExtendedComponent } from './';

const routes: Routes = [
  { path: '', component: Test4ListExtendedComponent, canDeactivate: [CanDeactivateGuard], canActivate: [AuthGuard] },
  {
    path: ':id',
    component: Test4DetailsExtendedComponent,
    canDeactivate: [CanDeactivateGuard],
    canActivate: [AuthGuard],
  },
  { path: 'new', component: Test4NewExtendedComponent, canActivate: [AuthGuard] },
];

export const test4Route: ModuleWithProviders = RouterModule.forChild(routes);
