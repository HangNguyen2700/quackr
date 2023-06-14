import { EventEmitter, Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedService<T> {
  private eventSubject = new Subject<T>();

  emitEvent(eventData: T) {
    this.eventSubject.next(eventData);
  }

  subscribeToEvent() {
    return this.eventSubject.asObservable();
  }
}
