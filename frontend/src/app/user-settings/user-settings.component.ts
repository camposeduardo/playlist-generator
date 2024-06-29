import { Component, OnInit } from '@angular/core';
import { UserProfile } from '../interfaces/UserProfile';

@Component({
  selector: 'app-user-settings',
  standalone: true,
  imports: [],
  templateUrl: './user-settings.component.html',
  styleUrl: './user-settings.component.css'
})
export class UserSettingsComponent implements OnInit {

  userProfile: UserProfile | undefined;

  ngOnInit() {
    this.userProfile = JSON.parse(localStorage.getItem('user')!);
  }

}
