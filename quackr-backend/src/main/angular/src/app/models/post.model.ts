export interface PostResponse {
  id: number;
  authorUsername: string;
  content: string;
  publishedOn: string;
  authorName: string;
}

export interface PostRequest {
  content: string;
}
