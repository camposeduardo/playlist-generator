import { Component } from '@angular/core';
import { RecommendationService } from '../../services/recommendation.service';
import { Track } from '../../interfaces/Track';
import { CommonModule } from '@angular/common';
import { PlaylistService } from '../../services/playlist.service';

@Component({
  selector: 'result',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './result.component.html',
  styleUrl: './result.component.css'
})
export class ResultComponent {

  tracks: Track[] | undefined;

  playerIdStatus: string | null = null;

  constructor(private recommendationService: RecommendationService) { }

  ngOnInit() {
    this.recommendationService.recommendations.subscribe({
      next: (data) => {
        if (data) {
          this.tracks = data;
        }
      }
    })
  }

  togglePlay(audioId: string) {
    const audioPlayer = document.getElementById(audioId) as HTMLAudioElement;
    if (audioPlayer) {
      if (audioPlayer.paused) {
        audioPlayer.play();
        this.playerIdStatus = audioId;
      } else {
        audioPlayer.pause();
        this.playerIdStatus = null;
      }
    }
  }

}
