'use strict';



/**
 * @author  zuoge85 on 15/6/17.
*/
class AgentLoginTokenModel {

    token:String;

    name:String;
    constructor() {

    }

    formObject({token,name}):AgentLoginTokenModel{
        this.token = token;
        this.name = name;
        return this;
    }

    static of({token,name}):AgentLoginTokenModel{
        return new AgentLoginTokenModel().formObject({token,name});
    }

    static form(token:String,name:String):AgentLoginTokenModel{
        return new AgentLoginTokenModel().formObject({token,name});
    }
}

export default AgentLoginTokenModel;