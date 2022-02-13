import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user/user.service';

@Component({
  selector: 'app-nav-top',
  templateUrl: './nav-top.component.html',
  styleUrls: ['./nav-top.component.scss']
})
export class NavTopComponent implements OnInit {

  fullName: string = '';

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.$fullName.subscribe(rs => this.fullName = rs);
  }

}
