import { AppService } from './app.service';
import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { AppConfigService } from './app-config.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  /**
   * Web Tittle
   * @memberof AppComponent
   */
  title = 'CAJ';

  /**
   * Menu Items
   * @type {MenuItem[]}
   * @memberof AppComponent
   */
  menuItems: MenuItem[] = [];

  /**
   * breadcrumb
   * @type {MenuItem[]}
   * @memberof AppComponent
   */
  breadcrumb: MenuItem[] = [];

  constructor(public appService:AppService,private http: HttpClient,appConfigService:AppConfigService ){
    const webNameApiUrl=appConfigService.apiConfig.masterFileUrl+'/api/master-files/web-name';
    this.http.get(webNameApiUrl).subscribe((item:any)=>{
      console.log(item.data);
      this.appService.webTittle=item.data;
      this.title=item.data;
    });
  }

  ngOnInit(): void {
    //
    this.menuItems = [
      {
        label: '參數主檔',
        icon: 'pi pi-fw pi-database',
        items: [
          { label: '車站設定', routerLink: '/master-file/station' },
          { label: '停站模式', routerLink: '/master-file/stopping' }
        ]
      },
      {
        label: '票價',
        icon: 'pi pi-fw pi-ticket',
        items: [
          { label: '票價表' ,routerLink: '/fare' }
        ]
      }, {
        label: '時刻表',
        icon: 'pi pi-fw pi-calendar',
        items: [
          { label: '列車時刻表' ,routerLink: '/schedule' }
        ]
      }
    ];


    this.breadcrumb = [
      { label: '首頁' },
      { label: '參數設定' },
      { label: '車站設定' }
    ];
  }
}
