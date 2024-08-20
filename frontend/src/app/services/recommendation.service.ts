import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Artist } from '../interfaces/Artist';
import { map } from 'rxjs/internal/operators/map';
import { Track } from '../interfaces/Track';

@Injectable({
  providedIn: 'root'
})
export class RecommendationService {

  private recommendationsSubject = new BehaviorSubject<Track[] | null>(null);
  recommendations = this.recommendationsSubject.asObservable();

  constructor(private http: HttpClient) { }

  generateRecommendations(artist: string, genre: string) {
    return this.http.get<any>(`${environment.apiUrl}/recommendation`, {params: {
      artistId: artist,
      genre: genre
    }}).pipe(
      map(response => {
        this.recommendationsSubject?.next(response.tracks);
        return response;
      }), (err) => {
        return err;
      }
    );
  }

  clearMusicRecommendations() {
    this.recommendationsSubject?.next([]);
  }
}
