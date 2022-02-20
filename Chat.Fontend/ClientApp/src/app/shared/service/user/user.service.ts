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

  public setJWT(jwt: string, fullName: string): void{
    localStorage.setItem('jwt', jwt);
    localStorage.setItem('fullName', fullName);
    this._getFullName();
  }

  private _getFullName(): void {
    let fullName = localStorage.getItem('fullName');
    
    if(!this.getTokenRemainingTime()){
      localStorage.setItem('fullName', '');
      this._$fullName.next('');
    }

    if('' != fullName && fullName != null && fullName != undefined) { 
      this._$fullName.next(fullName);
    }
   
  }

  private _getDecodedJwt(jwt: string): {username: string, fullName: string, exp: number} | null {
    try {
      return jwt_decode(jwt);
    } catch (Error) {
      return null;
    }
  }

  getTokenRemainingTime(): boolean {
    let jwt = localStorage.getItem('jwt');
    if(jwt != null && jwt != undefined) {
      let exp = this._getDecodedJwt(jwt)?.exp;
      if(exp != null){
        let expires = new Date(exp*1000);
        return (expires.getTime() - Date.now()) > 0;
      }
    }
    return false;
  }

  getJWT(): string{
    let jwt = localStorage.getItem('jwt');
    return jwt  == null ? '' : jwt ;
  }
}

