import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }

  searchArtists(artist: string) {
    return this.http.get<any>(`${environment.apiUrl}/search`, {params: {
      artist: artist
    }});
  }
}
