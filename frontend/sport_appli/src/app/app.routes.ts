import { Routes } from '@angular/router';

export const appRoutes: Routes = [
  {
    path: 'creation-seance',
    loadComponent: () => import('./components/creation-seance/creation-seance').then(m => m.CreationSeanceComponent)
  },
   {
    path: '',
    loadComponent: () => import('./components/home/home').then(m => m.HomeComponent)
  },
];
