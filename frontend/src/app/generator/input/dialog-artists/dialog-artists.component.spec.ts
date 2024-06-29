import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogArtistsComponent } from './dialog-artists.component';

describe('DialogArtistsComponent', () => {
  let component: DialogArtistsComponent;
  let fixture: ComponentFixture<DialogArtistsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DialogArtistsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DialogArtistsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
