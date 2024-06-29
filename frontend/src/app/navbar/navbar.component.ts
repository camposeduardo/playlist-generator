import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { UserProfile } from '../interfaces/UserProfile';

@Component({
  selector: 'navbar',
  standalone: true,
  imports: [RouterLink, CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  userProfile: UserProfile | undefined;

  constructor(private router: Router) {}

  ngDoCheck() {
    if (localStorage.getItem('user') != null) {
      this.userProfile = JSON.parse(localStorage.getItem('user')!);
    }
  }

  logout() {
    localStorage.clear();
    this.userProfile = undefined;
    this.router.navigate(['']);
  }

}
