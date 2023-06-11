import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { PostsComponent } from './components/posts/posts.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { AuthGuardService as AuthGuard } from './services/auth/auth-guard.service';

const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent, canActivate: [AuthGuard] },
  { path: 'user', component: UserDetailsComponent, canActivate: [AuthGuard] },
  { path: 'post', component: PostsComponent, canActivate: [AuthGuard] },
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
