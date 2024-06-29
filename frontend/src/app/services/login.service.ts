import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { RedirectUrl } from '../interfaces/RedirectUrl';
import { map } from 'rxjs';
import { UserProfile } from '../interfaces/UserProfile';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  redirectToSpotifyAuthFlow() {
    return this.http.get<RedirectUrl>(`${environment.apiUrl}/authorize`).pipe(map(response  => {
      document.location = response.url;
      localStorage.setItem("verifier", response.verifier);
    })
    ).subscribe();
  }

  getUserProfileInfo() {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.get<UserProfile>(`${environment.apiUrl}/profile`, { headers }).pipe(map((response: UserProfile) => {
        console.log(response)
        localStorage.setItem('user', JSON.stringify(response));
        return response;
      }));
  }
}
