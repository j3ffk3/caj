import { Injectable } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  /**
   * webTittle
   * @type {string}
   * @memberof AppService
   */
  webTittle: string= 'CAJ';

  /**
   * pageTittle
   * @type {string}
   * @memberof AppService
   */
  pageTittle: string = '首頁';

  /**
   * breadcrumb
   * @type {MenuItem[]}
   * @memberof AppService
   */
  breadcrumb: MenuItem[] = [ { label: '首頁' }];

  constructor() { }
}
