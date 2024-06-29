import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, async, map } from 'rxjs';
import { UserProfile } from '../interfaces/UserProfile';
import { environment } from '../../environments/environment.development';
import { LoginService } from '../services/login.service';
import { RedirectUrl } from '../interfaces/RedirectUrl';
import { TokenService } from '../services/token.service';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [HttpClientModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  params = new URLSearchParams(window.location.search);
  code = this.params.get("code");

  constructor(private router: Router,
    private route: ActivatedRoute,
    private http: HttpClient,
    private loginService: LoginService,
    private tokenService: TokenService) { }

  ngOnInit() {
    if (localStorage.getItem('token') !== null) {
      this.router.navigate(['Generator']);
    } else {
      this.checkForCode();
    }
  }

  checkForCode() {
    this.route.queryParams.subscribe(params => {
      const code = params['code'];

      if (code) {
        localStorage.setItem('code', code);
        this.getAccessToken(code).subscribe({
          next: (response: any) => {
            this.fetchProfile(response.access_token);
          }
        });
        window.history.replaceState({}, document.title, window.location.pathname);
        this.router.navigate(['Generator']);
      }
    });
  }


  redirectToAuthCodeFlow() {
    this.loginService.redirectToSpotifyAuthFlow();
  }

  getAccessToken(code: string) {
    return this.tokenService.requestAccessToken(code);
  }

  fetchProfile(token: string) {
    this.loginService.getUserProfileInfo().subscribe();
  }
}
