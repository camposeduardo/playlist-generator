import { Component, EventEmitter, Output } from '@angular/core';
import { RecommendationService } from '../../services/recommendation.service';
import { Track } from '../../interfaces/Track';
import { CommonModule } from '@angular/common';

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
        // Display something when doesn't have data
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
