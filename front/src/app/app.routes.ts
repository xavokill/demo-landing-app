import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { StepOneComponent } from './components/step-one/step-one.component';
import { StepTwoComponent } from './components/step-two/step-two.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { StepThreeComponent } from './components/step-three/step-three.component';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },

  { path: 'home', component: HomeComponent },
  { path: 'step-one', component: StepOneComponent },
  { path: 'step-two', component: StepTwoComponent },
  { path: 'step-three', component: StepThreeComponent },

  { path: '**', component: NotFoundComponent },
];
