package mj
{
    import com.isnowfox.core.SingletonError;
    import com.isnowfox.core.locale.Locale;

    import laya.net.Loader;
    import laya.utils.Handler;

    /**
     * @author zuoge85@gmail.com on 16/10/5.
     */
    public class GlobalResource
    {
        public static const instance:GlobalResource = new GlobalResource();

        private static const localeJson:Object = {
            "room.alreadyCreateRoom": "已经创建了房间,请勿重新创建",
            "room.createRoomSuccess": "房间创建成功",
            "room.createRoomError": "房间创建失败,请重试",
//            "room.alreadyJoinRoom": "已经进入房间",
            "room.errorRoomCheckId": "不存在的房间!",
            "room.full": "房间满了",
            "room.noGold": "房卡不够创建房间",
            "room.alreadyExitRoom": "已经退出房间!",
            "room.alreadyDelRoom": "已经解散房间!",
            "room.delRoom": "房间被解散!",
            "room.cannotExitRoom": "已经开始，不能退出！!",
            "room.cannotDelRoom": "已经开始，不能解散！!",
            "room.notEnoughUser": "在线人数不够，不能开始！!",
            "room.endRoom": "游戏结束",
            "error": "{0}"
        };

        public const locale:Locale = new Locale();

        public function GlobalResource()
        {
            if (instance != null)
            {
                throw new SingletonError("UserManager 是单例模式");
            }
        }


        public function init():void
        {
            locale.init(localeJson);
        }

        public function initOther(complete:Handler, progress:Handler):void
        {
            var assets:Array = [];
            assets.push({
                url: "res/atlas/base.json",
                type: Loader.ATLAS
            });
            assets.push({
                url: "res/atlas/ui/home.json",
                type: Loader.ATLAS
            });
            assets.push({
                url: "res/atlas/ui/game.json",
                type: Loader.ATLAS
            });
            assets.push({
                url: "res/atlas/base/number/lost.json",
                type: Loader.ATLAS
            });
            assets.push({
                url: "res/atlas/base/number/time.json",
                type: Loader.ATLAS
            });
            assets.push({
                url: "res/atlas/base/number/win.json",
                type: Loader.ATLAS
            });
            assets.push({
                url: "res/atlas/movie/shazi.json",
                type: Loader.ATLAS
            });
            assets.push({
                url: "res/atlas/ui/majiang.json",
                type: Loader.ATLAS
            });
            assets.push({
                url: "res/atlas/movie/wait.json",
                type: Loader.ATLAS
            });

            Laya.loader.load(assets, complete, progress);
        }
    }
}
