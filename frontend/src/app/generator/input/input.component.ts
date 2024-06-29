import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Component, EventEmitter, Output } from '@angular/core';
import { SearchService } from '../../services/search.service';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Artist } from '../../interfaces/Artist';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { DialogArtistsComponent } from './dialog-artists/dialog-artists.component';
import { RecommendationService } from '../../services/recommendation.service';
import { Track } from '../../interfaces/Track';

@Component({
  selector: 'app-input',
  standalone: true,
  imports: [HttpClientModule, CommonModule, ReactiveFormsModule, MatDialogModule],
  templateUrl: './input.component.html',
  styleUrl: './input.component.css'
})
export class InputComponent {
  constructor(private http: HttpClient,
    private searchService: SearchService,
    private recommendationService: RecommendationService,
    private dialog: MatDialog
  ) { }

  singleArtist: Artist | undefined = undefined;
  artists: Artist[] | undefined = undefined;

  @Output() showResults = new EventEmitter();

  playlistForm!: FormGroup;

  ngOnInit() {
    this.playlistForm = new FormGroup({
      artist: new FormControl(null, [Validators.required]),
      genre: new FormControl(null, [Validators.required]),
      quantity: new FormControl(null, [Validators.required]),
      playlistName: new FormControl(null, [Validators.required]),
      description: new FormControl(null, [Validators.required]),
    });
  }

  openDialog() {
    const dialogRef = this.dialog.open(DialogArtistsComponent, { data: this.artists });
    dialogRef.afterClosed().subscribe(res => {
      if (res) {
        this.singleArtist = res.data;
        this.playlistForm.patchValue({
          artist: res.data.name
        });
      }
    })
  }

  searchRelatedArtists(artist: string) {
    this.searchService.searchArtists(artist).subscribe({
      next: (response) => {
        this.artists = response['artists']['items'];
        this.openDialog();
      }
    });
  }

  generateMusicRecommendation() {
    this.recommendationService.generateRecommendations(this.singleArtist!.id, this.singleArtist!.genres[0]).subscribe({
      next: (response) => {
        console.log(response);
      }
    });
  }

}
