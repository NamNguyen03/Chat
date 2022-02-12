import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { apiInfo } from 'src/app/environment/environment';
import { RegisterRequestModel, RegisterResponseModel } from '../model/user-models';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class UserClientService {
  private apiEndpoint = `${apiInfo.url}users`;
  private apiRegister = `${this.apiEndpoint}/register`;

  constructor(protected httpClient: HttpClient) { }

  register(user: RegisterRequestModel): Observable<RegisterResponseModel> {
    return this.httpClient.post<RegisterResponseModel>(this.apiRegister, user);
  }
  
}
