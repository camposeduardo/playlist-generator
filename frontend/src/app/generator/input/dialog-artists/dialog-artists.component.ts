import { Component, EventEmitter, Inject, Output } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogContent, MatDialogModule, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
import { Artist } from '../../../interfaces/Artist';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dialog-artists',
  standalone: true,
  imports: [MatDialogModule, MatDialogTitle, MatDialogContent, CommonModule],
  templateUrl: './dialog-artists.component.html',
  styleUrl: './dialog-artists.component.css'
})
export class DialogArtistsComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: Artist[],
  private dialogRef: MatDialogRef<DialogArtistsComponent>) {
  }

  closeDialog(artist: Artist) {
    if (artist == undefined) {
      this.dialogRef.close();
    } else {
      this.dialogRef.close({data: artist});
    }

  }

}
