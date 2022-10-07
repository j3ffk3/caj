import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { MenuItem } from 'primeng/api';
import { HttpClient } from '@angular/common/http';
import { AppConfigService } from '../app-config.service';

@Component({
  selector: 'app-stopping-pattern',
  templateUrl: './stopping-pattern.component.html',
  styleUrls: ['./stopping-pattern.component.css']
})
export class StoppingPatternComponent implements OnInit {

  /**
   * patterns
   * @memberof AppComponent
   */
  patterns: any = [];

  /**
   * items
   * @type {MenuItem[]}
   * @memberof StoppingPatternComponent
   */
  items: MenuItem[] = [];

  /**
   * items
   * @type {MenuItem[]}
   * @memberof StoppingPatternComponent
   */
  items2: MenuItem[] = [];

  constructor(appService: AppService,private http: HttpClient,appConfigService:AppConfigService) {
    appService.pageTittle = '停站模式'
    appService.breadcrumb = [
      { label: '首頁' },
      { label: '參數主檔' },
      { label: '停站模式' }
    ];

    this.http.get(appConfigService.apiConfig.masterFileUrl+'/api/master-files/stoppings').subscribe((item:any)=>{
      console.log(item.data);
      this.patterns=item.data.map((x: any) => {
        x.stops=x.stops.map((y: any) => {
          y.label=y.stationName;
          return y;
        })
        return x;
      });
      console.log(this.patterns)
    });
  }

  ngOnInit(): void {

  }

}
