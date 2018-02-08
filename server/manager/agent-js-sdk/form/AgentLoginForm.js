'use strict';



/**

*/
class AgentLoginForm {

    id:Number;

    password:String;
    constructor() {

    }

    formObject({id,password}):AgentLoginForm{
        this.id = id;
        this.password = password;
        return this;
    }

    static of({id,password}):AgentLoginForm{
        return new AgentLoginForm().formObject({id,password});
    }

    static form(id:Number,password:String):AgentLoginForm{
        return new AgentLoginForm().formObject({id,password});
    }
}

export default AgentLoginForm;