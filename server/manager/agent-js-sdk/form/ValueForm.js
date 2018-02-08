'use strict';



/**

*/
class ValueForm {

    value:String;
    constructor() {

    }

    formObject({value}):ValueForm{
        this.value = value;
        return this;
    }

    static of({value}):ValueForm{
        return new ValueForm().formObject({value});
    }

    static form(value:String):ValueForm{
        return new ValueForm().formObject({value});
    }
}

export default ValueForm;