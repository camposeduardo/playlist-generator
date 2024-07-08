import { Artist } from "./Artist";
import { Album } from "./Album";

export interface Track {
  id: string;
  artists: Artist[];
  album: Album;
  name: string;
  preview_url: string;
  uri: string;
}
