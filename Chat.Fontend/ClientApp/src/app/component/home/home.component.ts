import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  textWelcomeTarget: string = 'Welcome To Chat!!!';
  textWelcome: string = '';
  disableImage2: boolean = false;

  constructor() { }

  ngOnInit(): void {
      for (let i = 0; i < this.textWelcomeTarget.length; i++){
        setTimeout(() => {
          this.textWelcome += this.textWelcomeTarget[i];
        }, 150*i);
      }
      setTimeout(() => {
        this.disableImage2 = true;
      }, 150*this.textWelcomeTarget.length);
  }

}
