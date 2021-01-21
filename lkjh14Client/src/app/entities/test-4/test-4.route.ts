import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
// import { CanDeactivateGuard } from 'src/app/common/shared';
// import { AuthGuard } from 'src/app/core/auth-guard';

// import { Test4DetailsComponent, Test4ListComponent, Test4NewComponent } from './';

const routes: Routes = [
  // { path: '', component: Test4ListComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ] },
  // { path: ':id', component: Test4DetailsComponent, canDeactivate: [CanDeactivateGuard], canActivate: [ AuthGuard ] },
  // { path: 'new', component: Test4NewComponent, canActivate: [ AuthGuard ] },
];

export const test4Route: ModuleWithProviders = RouterModule.forChild(routes);
