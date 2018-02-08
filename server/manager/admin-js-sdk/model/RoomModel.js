'use strict';



/**
 * @author  zuoge85@gmail.com on 2017/1/8.
*/
class RoomModel {

	/**
	 * 牌局id
	 */
    id:Number;

	/**
	 * 创建用户id
	 */
    createUserId:Number;

	/**
	 * 用户数
	 */
    userMax:Number;

	/**
	 * 牌局uuid
	 */
    uuid:String;

	/**
	 * 用户0
	 */
    user0:Number;

	/**
	 * 用户1
	 */
    user1:Number;

	/**
	 * 用户2
	 */
    user2:Number;

	/**
	 * 用户3
	 */
    user3:Number;

	/**
	 * 用户0
	 */
    userName0:String;

	/**
	 * 用户1
	 */
    userName1:String;

	/**
	 * 用户2
	 */
    userName2:String;

	/**
	 * 用户3
	 */
    userName3:String;

	/**
	 * 积分0
	 */
    score0:Number;

	/**
	 * 积分1
	 */
    score1:Number;

	/**
	 * 积分2
	 */
    score2:Number;

	/**
	 * 积分3
	 */
    score3:Number;

	/**
	 * 房间的check-id,进入id,可以重复,但是不允许同时活跃状态的id 相同
	 */
    roomCheckId:String;

    startDate:Date;

    endDate:Date;

    start:Boolean;

	/**
	 * 正常结束
	 */
    end:Boolean;

    version:Number;

    sceneId:Number;

    chapterNums:Number;

	/**
	 * 配置
	 */
    config:String;
    constructor() {

    }

    formObject({id,createUserId,userMax,uuid,user0,user1,user2,user3,userName0,userName1,userName2,userName3,score0,score1,score2,score3,roomCheckId,startDate,endDate,start,end,version,sceneId,chapterNums,config}):RoomModel{
        this.id = id;
        this.createUserId = createUserId;
        this.userMax = userMax;
        this.uuid = uuid;
        this.user0 = user0;
        this.user1 = user1;
        this.user2 = user2;
        this.user3 = user3;
        this.userName0 = userName0;
        this.userName1 = userName1;
        this.userName2 = userName2;
        this.userName3 = userName3;
        this.score0 = score0;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.roomCheckId = roomCheckId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.start = start;
        this.end = end;
        this.version = version;
        this.sceneId = sceneId;
        this.chapterNums = chapterNums;
        this.config = config;
        return this;
    }

    static of({id,createUserId,userMax,uuid,user0,user1,user2,user3,userName0,userName1,userName2,userName3,score0,score1,score2,score3,roomCheckId,startDate,endDate,start,end,version,sceneId,chapterNums,config}):RoomModel{
        return new RoomModel().formObject({id,createUserId,userMax,uuid,user0,user1,user2,user3,userName0,userName1,userName2,userName3,score0,score1,score2,score3,roomCheckId,startDate,endDate,start,end,version,sceneId,chapterNums,config});
    }

    static form(id:Number,createUserId:Number,userMax:Number,uuid:String,user0:Number,user1:Number,user2:Number,user3:Number,userName0:String,userName1:String,userName2:String,userName3:String,score0:Number,score1:Number,score2:Number,score3:Number,roomCheckId:String,startDate:Date,endDate:Date,start:Boolean,end:Boolean,version:Number,sceneId:Number,chapterNums:Number,config:String):RoomModel{
        return new RoomModel().formObject({id,createUserId,userMax,uuid,user0,user1,user2,user3,userName0,userName1,userName2,userName3,score0,score1,score2,score3,roomCheckId,startDate,endDate,start,end,version,sceneId,chapterNums,config});
    }
}

export default RoomModel;