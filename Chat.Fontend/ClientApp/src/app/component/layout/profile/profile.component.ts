import { Component, OnInit } from '@angular/core';
import { UserClientService } from 'src/app/api-clients/user/user-client.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  public username: string = '';
  public fullName: string = '';

  constructor(private userClientService: UserClientService) { }

  ngOnInit(): void {
    this.getProfile();
  }

  getProfile(): void {
    this.userClientService.getMyProfile().subscribe(rs => {
      this.username = rs.username;
      this.fullName = rs.fullName;
    });
  }

}
