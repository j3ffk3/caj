import { AppConfigService } from './../app-config.service';
import { HttpClient } from '@angular/common/http';
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

  constructor(appService: AppService,private http: HttpClient,appConfigService:AppConfigService) {
    this.http.get(appConfigService.apiConfig.masterFileUrl+'/api/master-files/stations').subscribe((item:any)=>{
      console.log(item.data)
      this.stations=item.data;
    });
    appService.pageTittle = '車站設定'
    appService.breadcrumb = [
      { label: '首頁' },
      { label: '參數主檔' },
      { label: '車站設定' }
    ];
  }

  ngOnInit(): void {

  }

}
