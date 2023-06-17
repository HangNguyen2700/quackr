export interface LoginRequest {
  username: string;
  password: string;
}

export interface SignUpRequest {
  username: string;
  password: string;
  displayname: string;
}

export interface AuthResponse {
  token: string;
}
