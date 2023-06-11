export type SuccessResponse<T> = {
  status: number;
  message: string;
  data: T;
}
