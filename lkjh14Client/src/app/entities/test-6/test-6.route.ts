import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
// import { CanDeactivateGuard } from 'src/app/common/shared';
// import { AuthGuard } from 'src/app/core/auth-guard';

// import { Test6DetailsComponent, Test6ListComponent, Test6NewComponent } from './';

const routes: Routes = [
  // { path: '', component: Test6ListComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ] },
  // { path: ':id', component: Test6DetailsComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ] },
  // { path: 'new', component: Test6NewComponent, canActivate: [ AuthGuard ] },
];

export const test6Route: ModuleWithProviders = RouterModule.forChild(routes);
