'use strict';



/**

*/
class PageModel {

    list:Object[];

    count:Number;

    page:Number;

    pageSize:Number;
    constructor() {

    }

    formObject({list,count,page,pageSize}):PageModel{
        this.list = list;
        this.count = count;
        this.page = page;
        this.pageSize = pageSize;
        return this;
    }

    static of({list,count,page,pageSize}):PageModel{
        return new PageModel().formObject({list,count,page,pageSize});
    }

    static form(list:Object[],count:Number,page:Number,pageSize:Number):PageModel{
        return new PageModel().formObject({list,count,page,pageSize});
    }
}

export default PageModel;