'use strict';



/**
 * @author  zuoge85 on 15/6/17.
*/
class LoginTokenModel {

    token:String;

    name:String;
    constructor() {

    }

    formObject({token,name}):LoginTokenModel{
        this.token = token;
        this.name = name;
        return this;
    }

    static of({token,name}):LoginTokenModel{
        return new LoginTokenModel().formObject({token,name});
    }

    static form(token:String,name:String):LoginTokenModel{
        return new LoginTokenModel().formObject({token,name});
    }
}

export default LoginTokenModel;