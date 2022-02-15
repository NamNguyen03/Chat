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

export class LoginRequestModel{
    username: string;
    password: string;

    constructor(username: string, password: string){
        this.username = username;
        this.password = password;
    }
}

export class LoginResponseModel{
    username!: string;
    fullName!: string;
    jwt!: string;
}

export class ProfileResponseModel{
    uuid!: string;
    username!: string;
    fullName!: string;
}