import { EventEmitter } from "@angular/core";

export type Dialog<T> = {
  dialogTitle: string;
  data?: T;
}