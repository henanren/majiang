'use strict';



/**

*/
class LoginForm {

    name:String;

    password:String;
    constructor() {

    }

    formObject({name,password}):LoginForm{
        this.name = name;
        this.password = password;
        return this;
    }

    static of({name,password}):LoginForm{
        return new LoginForm().formObject({name,password});
    }

    static form(name:String,password:String):LoginForm{
        return new LoginForm().formObject({name,password});
    }
}

export default LoginForm;