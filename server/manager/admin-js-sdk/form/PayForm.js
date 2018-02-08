'use strict';



/**

*/
class PayForm {

    id:Number;

    gold:Number;
    constructor() {

    }

    formObject({id,gold}):PayForm{
        this.id = id;
        this.gold = gold;
        return this;
    }

    static of({id,gold}):PayForm{
        return new PayForm().formObject({id,gold});
    }

    static form(id:Number,gold:Number):PayForm{
        return new PayForm().formObject({id,gold});
    }
}

export default PayForm;