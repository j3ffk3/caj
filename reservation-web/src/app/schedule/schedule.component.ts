import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AppConfigService } from '../app-config.service';
import { AppService } from '../app.service';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {

  /**
   * schedule
   * @memberof AppComponent
   */
  schedule: any = [];

  /**
   * scheduleDetail
   * @type {*}
   * @memberof ScheduleComponent
   */
  scheduleDetail: any = [];

  /**
   * displayDialog
   * @type {boolean}
   * @memberof ScheduleComponent
   */
  displayDialog: boolean = false;

  /**
   * displayAppendDialog
   * @type {boolean}
   * @memberof ScheduleComponent
   */
  displayAppendDialog:boolean=false;

  /**
   * trainNo
   * @type {*}
   * @memberof ScheduleComponent
   */
  trainNo:any;

  /**
   * direction
   * @type {*}
   * @memberof ScheduleComponent
   */
  direction:any;

  /**
   * selectedDirection
   * @type {*}
   * @memberof ScheduleComponent
   */
  selectedDirection:any;

  /**
   * stoppingPattern
   * @type {*}
   * @memberof ScheduleComponent
   */
  stoppingPattern:any;

  /**
   * selectedStoppingPattern
   * @type {*}
   * @memberof ScheduleComponent
   */
  selectedStoppingPattern:any;

  /**
   * checkedValues
   * @type {*}
   * @memberof ScheduleComponent
   */
   checkedValues:any;

   /**
    * stoppingPattern
    * @type {*}
    * @memberof ScheduleComponent
    */
   stoppingPatternList: any[] = [];

   


  constructor(public appService: AppService,private http: HttpClient,private appConfigService:AppConfigService) {
    appService.pageTittle = '列車時刻表'
    appService.breadcrumb = [
      { label: '首頁' },
      { label: '時刻表' },
      { label: '列車時刻表' }
    ];

    this.direction=[
      {name: '北上', code: 'N'},
      {name: '南下', code: 'S'}
    ];

    this.stoppingPattern=[
      {name: '直達', code: 'A'},
      {name: '跳蛙式', code: 'B'},
      {name: '站站停', code: 'C'},
    ]

    this.http.get(this.appConfigService.apiConfig.masterFileUrl+'/api/master-files/stoppings/A').subscribe((item:any)=>{
      this.stoppingPatternList=item.data.stops.map((item:any)=>{
        const obj={
          station: item.stationName,
          arrivalTime: '00:00',
          departureTime: '00:00'
        }
        this.stoppingPatternList.push(obj);
        return obj
      });
    })

    this.http.get(this.appConfigService.apiConfig.scheduleUrl+'/api/schedule').subscribe((item:any)=>{
      this.schedule=item.data;
      console.log(item.data);
    })
  }

  ngOnInit(): void {
    
  }

  showDialog(data:any) {
    this.scheduleDetail=data;
    this.displayDialog = true;
  }

  showAppendDialog() {
    this.displayAppendDialog = true;
  }

  changeDepartureInput(){
    this.http.get(this.appConfigService.apiConfig.masterFileUrl+'/api/master-files/stoppings/'+this.selectedStoppingPattern.code).subscribe((item:any)=>{
      this.stoppingPatternList=item.data.stops.map((item:any)=>{
        const obj={
          station: item.stationName,
          arrivalTime: '00:00',
          departureTime: '00:00'
        }
        this.stoppingPatternList.push(obj)
        return obj
      });
    })
    
  }


  createSchedule(){
    const obj={
      trainNo: this.trainNo,
      direction: this.selectedDirection.name,
      stoppingPattern: this.selectedStoppingPattern.name,
      freq: '',
      departures: this.stoppingPatternList
    }

    this.checkedValues.forEach((item:any) => {
      if(obj.freq){
        obj.freq=obj.freq+','+item;
      }else{
        obj.freq=item
      }
      
    });

    this.http.post(this.appConfigService.apiConfig.scheduleUrl+'/api/schedule',obj).subscribe(item=>{
      console.log(item);
      this.http.get(this.appConfigService.apiConfig.scheduleUrl+'/api/schedule').subscribe((item:any)=>{
        this.schedule=item.data;
        console.log(item.data);
      })
    });

    this.displayAppendDialog=false
  }

}
