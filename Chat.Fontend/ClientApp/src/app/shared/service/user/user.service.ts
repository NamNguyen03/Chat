import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import jwt_decode from 'jwt-decode';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  private _$fullName: BehaviorSubject<string> = new BehaviorSubject('');
  public readonly $fullName: Observable<string> = this._$fullName.asObservable();
  constructor() { 
    this._getFullName();
    
  }

  public setJWT(jwt: string): void{
    localStorage.setItem('jwt', jwt);
    this._getFullName();
  }

  private _getFullName(): void {
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
}

