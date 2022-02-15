import { AuthGuard } from './../../shared/service/AuthGuard/auth.guard';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { RoomChatComponent } from './room-chat/room-chat.component';
import { LayoutComponent } from 'src/app/shared/component/layout/layout.component';

const layoutRoutes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children:[
      {
        path: 'room-chat',
        component: RoomChatComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'profile',
        component: ProfileComponent,
        canActivate: [AuthGuard]
      }
    ]
  }
  
]

@NgModule({

  imports: [
    CommonModule,
    RouterModule.forRoot(layoutRoutes)
  ],

  declarations: [
     ProfileComponent,
     RoomChatComponent

  ]
})
export class LayoutModule { }
