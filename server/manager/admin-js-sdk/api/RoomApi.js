'use strict';

import PageModel from './../model/PageModel'
import RoomModel from './../model/RoomModel'
import AbstractApi from './../AbstractApi'


/**
 * @author  zuoge85 on 15/6/11.
*/
class RoomApi extends AbstractApi {



   /**
    * 测试
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>room/list/{page}/{pageSize}</b>
    * <ul>
    * <li><b>PathVariable:</b> Number page</li>
    * <li><b>PathVariable:</b> Number pageSize</li>
    * <li><b>Model:</b> PageModel</li>
    * <li>需要登录</li>
    * </ul>
    * </div>
    * @see PageModel
    * @see Number
    * @see Number

    */
    list(page:Number, pageSize:Number):Promise<PageModel> {
        var _path = {};
        _path["page"] = page;
        _path["pageSize"] = pageSize;
        return super._request(
                    "GET", "room/list/{page}/{pageSize}", _path, null
                );
    }

}
export default RoomApi;