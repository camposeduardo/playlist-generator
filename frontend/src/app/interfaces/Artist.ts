import { Image } from "./Image";

export interface Artist {
  id: string;
  name: string;
  genres: string[];
  images: Image[];
}
