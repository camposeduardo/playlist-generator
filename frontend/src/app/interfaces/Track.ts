import { Artist } from "./Artist";
import { Album } from "./Album";

export interface Track {
  artists: Artist[];
  duration: string;
  album: Album;
  name: string;
  preview_url: string;
  uri: string;
}
