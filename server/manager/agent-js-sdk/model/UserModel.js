'use strict';



/**
 * @author  zuoge85 on 15/6/17.
*/
class UserModel {

    id:Number;

	/**
	 * 昵称
	 */
    name:String;

    openId:String;

	/**
	 * 用户唯一uuid
	 */
    uuid:String;

	/**
	 * 用户头像地址
	 */
    avatar:String;

	/**
	 * 0:女生,1:男生,2:未知
	 */
    sex:Number;

    createDate:Date;

    updateDate:Date;

    version:Number;

    gold:Number;

    historyGold:Number;

    mobile:String;

    loginToken:String;

    level:Number;
    constructor() {

    }

    formObject({id,name,openId,uuid,avatar,sex,createDate,updateDate,version,gold,historyGold,mobile,loginToken,level}):UserModel{
        this.id = id;
        this.name = name;
        this.openId = openId;
        this.uuid = uuid;
        this.avatar = avatar;
        this.sex = sex;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.version = version;
        this.gold = gold;
        this.historyGold = historyGold;
        this.mobile = mobile;
        this.loginToken = loginToken;
        this.level = level;
        return this;
    }

    static of({id,name,openId,uuid,avatar,sex,createDate,updateDate,version,gold,historyGold,mobile,loginToken,level}):UserModel{
        return new UserModel().formObject({id,name,openId,uuid,avatar,sex,createDate,updateDate,version,gold,historyGold,mobile,loginToken,level});
    }

    static form(id:Number,name:String,openId:String,uuid:String,avatar:String,sex:Number,createDate:Date,updateDate:Date,version:Number,gold:Number,historyGold:Number,mobile:String,loginToken:String,level:Number):UserModel{
        return new UserModel().formObject({id,name,openId,uuid,avatar,sex,createDate,updateDate,version,gold,historyGold,mobile,loginToken,level});
    }
}

export default UserModel;