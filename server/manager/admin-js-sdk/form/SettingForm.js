'use strict';



/**

*/
class SettingForm {

	/**
	 * 广告（首页）
	 */
    notice:String;

	/**
	 * 广播（跑马灯）
	 */
    radio:String;

	/**
	 * 充值信息
	 */
    payInfo:String;

	/**
	 * 用户协议
	 */
    agreement:String;

	/**
	 * 用户默认房卡
	 */
    initGold:Number;
    constructor() {

    }

    formObject({notice,radio,payInfo,agreement,initGold}):SettingForm{
        this.notice = notice;
        this.radio = radio;
        this.payInfo = payInfo;
        this.agreement = agreement;
        this.initGold = initGold;
        return this;
    }

    static of({notice,radio,payInfo,agreement,initGold}):SettingForm{
        return new SettingForm().formObject({notice,radio,payInfo,agreement,initGold});
    }

    static form(notice:String,radio:String,payInfo:String,agreement:String,initGold:Number):SettingForm{
        return new SettingForm().formObject({notice,radio,payInfo,agreement,initGold});
    }
}

export default SettingForm;