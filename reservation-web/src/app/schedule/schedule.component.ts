import { Component, OnInit } from '@angular/core';
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

  constructor(appService: AppService) {
    appService.pageTittle = '列車時刻表'
    appService.breadcrumb = [
      { label: '首頁' },
      { label: '時刻表' },
      { label: '列車時刻表' }
    ];
  }

  ngOnInit(): void {
    this.schedule = [
      { a: 1000, b: '北上', c: 'SMTWTFS', d: '120', e: '站站停' },
      { a: 1000, b: '北上', c: 'SMTWTFS', d: '120', e: '站站停' },
      { a: 1000, b: '北上', c: 'SMTWTFS', d: '120', e: '站站停' },
      { a: 1000, b: '南下', c: 'SMTWTFS', d: '120', e: '站站停' },
      { a: 1000, b: '南下', c: 'SMTWTFS', d: '120', e: '站站停' },
      { a: 1000, b: '南下', c: 'SMTWTFS', d: '120', e: '站站停' }
    ]

    this.scheduleDetail=[
      { a: '南港', b: '起站', c: '00:00' },
      { a: '台北', b: '00:00', c: '00:00' },
      { a: '板橋', b: '00:00', c: '00:00' },
      { a: '桃園', b: '00:00', c: '00:00' },
      { a: '新竹', b: '00:00', c: '00:00' },
      { a: '苗栗', b: '00:00', c: '00:00' },
      { a: '台中', b: '00:00', c: '00:00' },
      { a: '彰化', b: '00:00', c: '00:00' },
      { a: '雲林', b: '00:00', c: '00:00' },
      { a: '嘉義', b: '00:00', c: '00:00' },
      { a: '台南', b: '00:00', c: '00:00' },
      { a: '左營', b: '00:00', c: '迄站' },
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
  }

  showDialog() {
    this.displayDialog = true;
  }

  showAppendDialog() {
    this.displayAppendDialog = true;
  }

}
