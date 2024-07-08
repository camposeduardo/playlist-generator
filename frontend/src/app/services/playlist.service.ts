import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Track } from '../interfaces/Track';
import { UserProfile } from '../interfaces/UserProfile';
import { environment } from '../../environments/environment.development';
import { RecommendationService } from './recommendation.service';
import { Playlist } from '../interfaces/Playlist';

@Injectable({
  providedIn: 'root'
})
export class PlaylistService {

  constructor(private http: HttpClient, private recommendationService: RecommendationService) { }

  tracks: Track[] | undefined;

  generatePlaylist(playlist: Playlist) {

    let tracksSubject = this.recommendationService.recommendations.subscribe({
      next: (response) => {
        if (response) {
          this.tracks = response;
        }
      }
    });

    const createPlaylistRequest = {
      tracks: this.tracks,
      playlist: playlist
    }

    const user = JSON.parse(localStorage.getItem("user")!);
    const userId = user.id;

    return this.http.post<string>(`${environment.apiUrl}/playlist/generate`, createPlaylistRequest, {params: {userId: userId}}).subscribe();
  }
}
