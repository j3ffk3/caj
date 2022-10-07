import { AppConfigService } from './../app-config.service';
import { AppService } from './../app.service';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

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

  /**
   * fateApiUrl
   * @type {*}
   * @memberof FareComponent
   */
  fareApiUrl:any;

  constructor(appService: AppService,private http: HttpClient,appConfigService:AppConfigService) {
    appService.pageTittle = '票價表'
    appService.breadcrumb = [
      { label: '首頁' },
      { label: '票價' },
      { label: '票價表' }
    ];

    
    this.farePlans = [
      { name: '標準全額', code: 'A' },
      { name: '商務全額', code: 'B' }
    ]

    this.fareApiUrl=appConfigService.apiConfig.fareUrl+'/api/fare/';
    this.http.get(this.fareApiUrl+'A').subscribe((item:any)=>{
      this.fare=item.data;
    });

  }

  ngOnInit(): void {

  }

  changeFareTable(){
    console.log(this.selectedFarePlans.code);
    this.http.get(this.fareApiUrl+this.selectedFarePlans.code).subscribe((item:any)=>{
      this.fare=item.data;
    });
  }

}
