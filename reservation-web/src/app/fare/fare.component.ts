import { AppService } from './../app.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-fare',
  templateUrl: './fare.component.html',
  styleUrls: ['./fare.component.css']
})
export class FareComponent implements OnInit {

  /**
   * fare
   * @memberof AppComponent
   */
  fare: any = [];

  /**
   * farePlans
   * @type {*}
   * @memberof FareComponent
   */
  farePlans: any[] = [];

  /**
   * selectedFarePlans
   * @type {*}
   * @memberof FareComponent
   */
   selectedFarePlans: any;

  constructor(appService: AppService) {
    appService.pageTittle = '票價表'
    appService.breadcrumb = [
      { label: '首頁' },
      { label: '票價' },
      { label: '票價表' }
    ];
  }

  ngOnInit(): void {
    this.fare = [
      { a: '南港', b: '-', c: '100', d: '100', e: '100', f: '100', g: '100', h: '100', i: '100', j: '100', k: '100', l: '100', m: '100' },
      { a: '台北', b: '40', c: '-', d: '100', e: '100', f: '100', g: '100', h: '100', i: '100', j: '100', k: '100', l: '100', m: '100' },
      { a: '板橋', b: '70', c: '40', d: '-', e: '100', f: '100', g: '100', h: '100', i: '100', j: '100', k: '100', l: '100', m: '100' },
      { a: '桃園', b: '200', c: '160', d: '130', e: '-', f: '100', g: '100', h: '100', i: '100', j: '100', k: '100', l: '100', m: '100' },
      { a: '新竹', b: '330', c: '290', d: '260', e: '130', f: '-', g: '100', h: '100', i: '100', j: '100', k: '100', l: '100', m: '100' },
      { a: '苗栗', b: '480', c: '430', d: '400', e: '280', f: '140', g: '-', h: '100', i: '100', j: '100', k: '100', l: '100', m: '100' },
      { a: '台中', b: '750', c: '700', d: '670', e: '540', f: '410', g: '270', h: '-', i: '100', j: '100', k: '100', l: '100', m: '100' },
      { a: '彰化', b: '700', c: '100', d: '100', e: '100', f: '100', g: '100', h: '100', i: '-', j: '100', k: '100', l: '100', m: '100' },
      { a: '雲林', b: '800', c: '100', d: '100', e: '100', f: '100', g: '100', h: '100', i: '100', j: '-', k: '100', l: '100', m: '100' },
      { a: '嘉義', b: '900', c: '100', d: '100', e: '100', f: '100', g: '100', h: '100', i: '100', j: '100', k: '-', l: '100', m: '100' },
      { a: '台南', b: '1000', c: '100', d: '100', e: '100', f: '100', g: '100', h: '100', i: '100', j: '100', k: '100', l: '-', m: '100' },
      { a: '左營', b: '1100', c: '100', d: '100', e: '100', f: '100', g: '100', h: '100', i: '100', j: '100', k: '100', l: '100', m: '-' }
    ];

    this.farePlans=[
      {name: '標準全額', code: 'Y'},
      {name: '商務全額', code: 'J'}
    ]
  }

}
