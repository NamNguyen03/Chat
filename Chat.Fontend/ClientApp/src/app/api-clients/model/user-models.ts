export class RegisterRequestModel{
    username: string;
    fullName: string;
    password: string;

    constructor(username: string, password: string, fullName: string){
        this.username = username;
        this.fullName = fullName;
        this.password = password;
    }
}

export class RegisterResponseModel{
    username!: string;
    fullName!: string;
    password!: string;
    uuid!: string;
   
}