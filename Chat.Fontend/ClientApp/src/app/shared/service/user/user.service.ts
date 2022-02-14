import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import jwt_decode from 'jwt-decode';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  private _$fullName: BehaviorSubject<string> = new BehaviorSubject('');
  public readonly $fullName: Observable<string> = this._$fullName.asObservable();
  private _$isLogin: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  public readonly $isLogin: Observable<boolean> = this._$isLogin.asObservable();

  constructor() { 
    this._getFullName();
    this.getTokenRemainingTime();
    this.$fullName.subscribe(fullName => {
      this._$isLogin.next('' != fullName );
    });
  }

  public setJWT(jwt: string): void{
    localStorage.setItem('jwt', jwt);
    this._getFullName();
  }

  private _getFullName(): void {
    if( this.getTokenRemainingTime() < 0 ){
      localStorage.setItem('jwt',''); 
      return;
    }

    let jwt = localStorage.getItem('jwt');    

    if(jwt == null || jwt == undefined) { 
      return;
    }
    let fullName = this._getDecodedJwt(jwt)?.fullName;   
    
    this._$fullName.next(fullName == undefined ? '' : fullName);
  }

  private _getDecodedJwt(jwt: string): {username: string, fullName: string, exp: number} | null {
    try {
      return jwt_decode(jwt);
    } catch (Error) {
      return null;
    }
  }

  getTokenRemainingTime(): number {
    let jwt = localStorage.getItem('jwt');
    if(jwt != null && jwt != undefined) {
      let exp = this._getDecodedJwt(jwt)?.exp;
      if(exp != null){
        let expires = new Date(exp*1000);
        return expires.getTime() - Date.now();
      }
    }
    return 0;
  }

  
}

