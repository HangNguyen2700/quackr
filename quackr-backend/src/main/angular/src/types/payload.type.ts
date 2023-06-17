import { Role } from "src/enums/user.enum";

export type SuccessResponse<T> = {
  status: number;
  message: string;
  data: T;
}

export type Token = {
  sub: string;
  role: Role;
}
