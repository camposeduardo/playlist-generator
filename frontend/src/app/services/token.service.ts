import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(private http: HttpClient) { }

  getToken(): string | null{
    return localStorage.getItem('token');
  }

  requestAccessToken(code: string) {
    const verifier = localStorage.getItem("verifier")!.trim();
    const headers: HttpHeaders = new HttpHeaders().set('code', code).set('verifier', verifier);

    return this.http.get<any>(`${environment.apiUrl}/token`, {headers: headers}).pipe(map(response  => {
      localStorage.clear();
      localStorage.setItem("token", response.access_token);
      return response;
    }));;
  }
}
