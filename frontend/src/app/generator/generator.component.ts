import { Component, EventEmitter, Output } from '@angular/core';
import { InputComponent } from './input/input.component';
import { ResultComponent } from './result/result.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Track } from '../interfaces/Track';
import { CommonModule } from '@angular/common';
import { PlaylistService } from '../services/playlist.service';
import { RecommendationService } from '../services/recommendation.service';

@Component({
  selector: 'app-generator',
  standalone: true,
  imports: [InputComponent, ResultComponent, CommonModule],
  templateUrl: './generator.component.html',
  styleUrl: './generator.component.css'
})
export class GeneratorComponent {

  constructor(private recommendationService: RecommendationService,
    private playlistService: PlaylistService
  ) { }


}
