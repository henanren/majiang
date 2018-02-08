'use strict';

import AgentLoginForm from './../form/AgentLoginForm'
import AbstractApi from './../AbstractApi'


/**
 * @author  zuoge85 on 15/6/11.
*/
class AgentAccountApi extends AbstractApi {



   /**
    * 测试
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>account/login</b>
    * <ul>
    * <li><b>Form:</b>AgentLoginFormlogin</li>
    * <li><b>Model:</b> LoginTokenModel</li>
    * </ul>
    * </div>
    * @see LoginTokenModel
    * @see AgentLoginForm

    */
    login(form:AgentLoginForm):Promise<LoginTokenModel> {
        var _path = null;
        return super._request(
                    "GET", "account/login", _path, form
                );
    }



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>admin/account/test</b>
    * <ul>
    * <li><b>Model:</b> String</li>
    * </ul>
    * </div>
    * @see String

    */
    test():Promise<String> {
        var _path = null;
        return super._request(
                    "GET", "admin/account/test", _path, null
                );
    }

}
export default AgentAccountApi;