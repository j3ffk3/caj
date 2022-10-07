import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';

@Component({
  selector: 'app-station',
  templateUrl: './station.component.html',
  styleUrls: ['./station.component.css']
})
export class StationComponent implements OnInit {

  /**
   * stations
   * @memberof AppComponent
   */
   stations: any = [];

  constructor(appService: AppService) {
    appService.pageTittle = '車站設定'
    appService.breadcrumb = [
      { label: '首頁' },
      { label: '參數主檔' },
      { label: '車站設定' }
    ];
  }

  ngOnInit(): void {
     this.stations=[
      {a:'1',b:'南港',c:'Nankang',d:'NAK',e:'1',f:'1'},
      {a:'2',b:'台北',c:'Taipei',d:'TPE',e:'2',f:'1'},
      {a:'3',b:'板橋',c:'Banqiao',d:'BAN',e:'3',f:'1'},
      {a:'4',b:'桃園',c:'Taoyuan',d:'1',e:'4',f:'1'},
      {a:'5',b:'新竹',c:'Hsinchu',d:'1',e:'5',f:'1'},
      {a:'6',b:'苗栗',c:'Miaoli',d:'1',e:'6',f:'1'},
      {a:'7',b:'台中',c:'Taichung',d:'1',e:'7',f:'1'},
      {a:'8',b:'彰化',c:'Changhua',d:'1',e:'8',f:'1'},
      {a:'9',b:'雲林',c:'Yunlin',d:'1',e:'9',f:'1'},
      {a:'10',b:'嘉義',c:'Chiayi',d:'1',e:'10',f:'1'},
      {a:'11',b:'台南',c:'Tainan',d:'1',e:'11',f:'1'},
      {a:'12',b:'左營',c:'Zuoying',d:'1',e:'12',f:'1'}
     ]
  }

}
