'use strict';



/**

*/
class PageForm {

    pageSize:Number;

    page:Number;
    constructor() {

    }

    formObject({pageSize,page}):PageForm{
        this.pageSize = pageSize;
        this.page = page;
        return this;
    }

    static of({pageSize,page}):PageForm{
        return new PageForm().formObject({pageSize,page});
    }

    static form(pageSize:Number,page:Number):PageForm{
        return new PageForm().formObject({pageSize,page});
    }
}

export default PageForm;