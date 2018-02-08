'use strict';

import PayForm from './../form/PayForm'
import PageModel from './../model/PageModel'
import UserModel from './../model/UserModel'
import AbstractApi from './../AbstractApi'


/**
 * @author  zuoge85 on 15/6/11.
*/
class UserApi extends AbstractApi {



   /**
    * 测试
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>user/list/{page}/{pageSize}</b>
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
                    "GET", "user/list/{page}/{pageSize}", _path, null
                );
    }



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>user/{userId}/level/{level}</b>
    * <ul>
    * <li><b>PathVariable:</b> Number userId</li>
    * <li><b>PathVariable:</b> Number level</li>
    * <li><b>Model:</b> void</li>
    * <li>需要登录</li>
    * </ul>
    * </div>
    * @see Number
    * @see Number

    */
    changelevel(userId:Number, level:Number):Promise<void> {
        var _path = {};
        _path["userId"] = userId;
        _path["level"] = level;
        return super._request(
                    "POST", "user/{userId}/level/{level}", _path, null
                );
    }



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>user/pay</b>
    * <ul>
    * <li><b>Form:</b>PayFormpay</li>
    * <li><b>Model:</b> UserModel</li>
    * <li>需要登录</li>
    * </ul>
    * </div>
    * @see UserModel
    * @see PayForm

    */
    pay(form:PayForm):Promise<UserModel> {
        var _path = null;
        return super._request(
                    "POST", "user/pay", _path, form
                );
    }

}
export default UserApi;