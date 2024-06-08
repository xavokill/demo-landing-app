import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { StepOneComponent } from './components/step-one/step-one.component';
import { StepTwoComponent } from './components/step-two/step-two.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { StepThreeComponent } from './components/step-three/step-three.component';
import { StepFourComponent } from './components/step-four/step-four.component';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },

  { path: 'home/:key', component: HomeComponent },
  { path: 'step-one', component: StepOneComponent },
  { path: 'step-two', component: StepTwoComponent },
  { path: 'step-three', component: StepThreeComponent },
  { path: 'step-four', component: StepFourComponent },
  { path: 'error', component: NotFoundComponent },
  
  { path: '**', redirectTo: 'error', pathMatch: 'full'},
];
