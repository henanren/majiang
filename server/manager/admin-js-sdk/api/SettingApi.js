'use strict';

import SettingForm from './../form/SettingForm'
import SettingModel from './../model/SettingModel'
import AbstractApi from './../AbstractApi'


/**
 * @author  zuoge85 on 15/6/11.
*/
class SettingApi extends AbstractApi {



   /**
    * 测试
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>setting</b>
    * <ul>
    * <li><b>Model:</b> SettingModel</li>
    * <li>需要登录</li>
    * </ul>
    * </div>
    * @see SettingModel

    */
    get():Promise<SettingModel> {
        var _path = null;
        return super._request(
                    "GET", "setting", _path, null
                );
    }



   /**
    * 
    *
    * <div class='http-info'>http 说明：<b>Api Url:</b> <b>setting</b>
    * <ul>
    * <li><b>Form:</b>SettingFormchange</li>
    * <li><b>Model:</b> SettingModel</li>
    * <li>需要登录</li>
    * </ul>
    * </div>
    * @see SettingModel
    * @see SettingForm

    */
    change(form:SettingForm):Promise<SettingModel> {
        var _path = null;
        return super._request(
                    "POST", "setting", _path, form
                );
    }

}
export default SettingApi;