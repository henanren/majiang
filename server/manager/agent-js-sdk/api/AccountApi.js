'use strict';

import LoginForm from './../form/LoginForm'
import LoginTokenModel from './../model/LoginTokenModel'
import AbstractApi from './../AbstractApi'


/**
 * @author  zuoge85 on 15/6/11.
*/
class AccountApi extends AbstractApi {



   /**
    * 测试
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/login</b>
    * <ul>
    * <li><b>Form:</b>LoginFormlogin</li>
    * <li><b>Model:</b> LoginTokenModel</li>
    * </ul>
    * </div>
    * @see LoginTokenModel
    * @see LoginForm

    */
    login(form:LoginForm):Promise<LoginTokenModel> {
        var _path = null;
        return super._request(
                    "GET", "account/login", _path, form
                );
    }



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/test</b>
    * <ul>
    * <li><b>Model:</b> String</li>
    * </ul>
    * </div>
    * @see String

    */
    test():Promise<String> {
        var _path = null;
        return super._request(
                    "GET", "account/test", _path, null
                );
    }

}
export default AccountApi;