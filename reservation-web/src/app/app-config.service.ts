import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {
  
  private appConfig: any;

  constructor(private http: HttpClient) { }

  loadAppConfig() {

    return this.http.get('/assets/config.json').toPromise()
    .then(
        data => {
          this.appConfig = data;
        });
  }

  // This is an example property ... you can make it however you want.
  get apiConfig() {

    if (!this.appConfig) {
      return this.appConfig;
    }

    return this.appConfig;
  }

}
