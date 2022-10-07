import { StoppingPatternComponent } from './stopping-pattern/stopping-pattern.component';
import { StationComponent } from './station/station.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { FareComponent } from './fare/fare.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'master-file/station', component: StationComponent },
  { path: 'master-file/stopping', component: StoppingPatternComponent },
  { path: 'fare', component: FareComponent },
  { path: 'schedule', component: ScheduleComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
