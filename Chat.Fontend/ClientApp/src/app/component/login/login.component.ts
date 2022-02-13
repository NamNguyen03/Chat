import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequestModel } from 'src/app/api-clients/model/user-models';
import { UserClientService } from 'src/app/api-clients/user/user-client.service';
import { UserService } from 'src/app/shared/service/user/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public formLogin!: FormGroup;
  public isStart: boolean = true;

  constructor(private formBuilder: FormBuilder,
    private userClientService: UserClientService,
    private route: Router,
    private userService: UserService) { }

  ngOnInit(): void {
    this.createFromRegister();
  }

  createFromRegister(): void {
    this.formLogin = this.formBuilder.group({
        username: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(6)]]
    })
  }

  login():void {
    this.isStart = false;
    let username = this.formLogin.controls['username'].value;
    let password = this.formLogin.controls['password'].value;

    if(this.formLogin.valid){
      this.userClientService.login(new LoginRequestModel(username, password))
        .subscribe(response => {
          this.userService.setJWT(response.jwt);
          this.route.navigate(['room-chat']);
        });
    }
  }
}
