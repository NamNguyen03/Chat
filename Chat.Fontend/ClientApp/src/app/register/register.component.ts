import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterRequestModel } from '../api-clients/model/user-models';
import { UserClientService } from '../api-clients/user/user-client.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  public formRegister!: FormGroup;
  public isStart: boolean = true;
  public messageErrorRePassword: string ='';

  constructor(private formBuilder: FormBuilder,
    private userClientService: UserClientService) { }

  ngOnInit(): void {
    this.createFromRegister();
  }

  createFromRegister() {
    this.formRegister = this.formBuilder.group({
        username: ['', [Validators.required, Validators.email]],
        fullName: ['', Validators.required],
        password: ['', [Validators.required, Validators.minLength(6)]],
        rePassword: ['', [Validators.required, Validators.minLength(6)]]
    })
  }

  register(){
    this.isStart = false;
    let username = this.formRegister.controls['username'].value;
    let password = this.formRegister.controls['password'].value;
    let rePassword = this.formRegister.controls['rePassword'].value;
    let fullName = this.formRegister.controls['fullName'].value;

    if(password !=  rePassword){
      this.messageErrorRePassword = 'Repeat password not equals password';
    }else{
      this.messageErrorRePassword = '';
    }

    if(this.formRegister.valid && password ==  rePassword){
      let user = new RegisterRequestModel(username, password, fullName);
      this.userClientService.register(user).subscribe(rs => console.log(rs));
    } 
  } 

}
