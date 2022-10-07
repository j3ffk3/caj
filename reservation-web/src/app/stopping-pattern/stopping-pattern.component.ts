import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { MenuItem } from 'primeng/api';

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

  constructor(appService: AppService) {
    appService.pageTittle = '停站模式'
    appService.breadcrumb = [
      { label: '首頁' },
      { label: '參數主檔' },
      { label: '停站模式' }
    ];
  }

  ngOnInit(): void {
    this.items = [
      { label: '南港' },
      { label: '台北' },
      { label: '板橋' },
      { label: '桃園' },
      { label: '新竹' },
      { label: '苗栗' },
      { label: '台中' },
      { label: '彰化' },
      { label: '雲林' },
      { label: '嘉義' },
      { label: '台南' },
      { label: '左營' }
    ];

    this.items2 = [
      { label: '南港' },
      { label: '台北' },
      { label: '板橋' },
      { label: '桃園' },
      { label: '嘉義' },
      { label: '台南' },
      { label: '左營' }
    ];

    this.patterns = [
      { a: 'A',
        b: '直達',
        c: [{ label: '南港' },
        { label: '台北' },
        { label: '板橋' },
        { label: '台中' },
        { label: '左營' }]},
      { a: 'B', b: '跳蛙式', c: [{ label: '南港' },
      { label: '台北' },
      { label: '板橋' },{ label: '台中' },
      { label: '彰化' },
      { label: '雲林' },
      { label: '嘉義' },
      { label: '台南' },
      { label: '左營' }] },
      {
        a: 'C',
        b: '站站停',
        c: [{ label: '南港' },
        { label: '台北' },
        { label: '板橋' },
        { label: '桃園' },
        { label: '新竹' },
        { label: '苗栗' },
        { label: '台中' },
        { label: '彰化' },
        { label: '雲林' },
        { label: '嘉義' },
        { label: '台南' },
        { label: '左營' }]
      }
    ]

  }

}
