import { Image } from "./Image";
export interface UserProfile {
  id: string;
  display_name: string;
  email: string;
  country: string;
  followers: {
    total: string;
  };
  images: Image[];

}
