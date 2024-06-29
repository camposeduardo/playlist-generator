import { Routes } from '@angular/router';
import { GeneratorComponent } from './generator/generator.component';
import { LoginComponent } from './login/login.component';
import { UserSettingsComponent } from './user-settings/user-settings.component';

export const routes: Routes = [
  {path: 'Generator' ,component: GeneratorComponent},
  {path: '' ,component: LoginComponent},
  {path: 'Settings' ,component: UserSettingsComponent},
];
