import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { apiInfo } from 'src/app/environment/environment';
import { LoginRequestModel, LoginResponseModel, ProfileResponseModel, RegisterRequestModel, RegisterResponseModel } from '../model/user-models';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class UserClientService {
  private apiEndpoint = `${apiInfo.url}users`;
  private apiRegister = `${this.apiEndpoint}/register`;
  private apiLogin = `${apiInfo.url}token`;
  private apiProfile = `${this.apiEndpoint}/me`;

  constructor(protected httpClient: HttpClient) { }

  register(user: RegisterRequestModel): Observable<RegisterResponseModel> {
    return this.httpClient.post<RegisterResponseModel>(this.apiRegister, user);
  }
  
  login(loginModel: LoginRequestModel): Observable<LoginResponseModel> {
    return this.httpClient.post<LoginResponseModel>(this.apiLogin, loginModel);
  }

  getMyProfile(): Observable<ProfileResponseModel>{
    return this.httpClient.get<ProfileResponseModel>(this.apiProfile);
  }
}
