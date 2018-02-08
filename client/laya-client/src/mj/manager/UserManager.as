package mj.manager
{
    import com.isnowfox.core.SingletonError;
    import com.isnowfox.core.console.Console;

    import laya.device.geolocation.GeolocationInfo;
    import laya.net.LocalStorage;
    import laya.utils.Handler;

    import mj.model.User;
    import mj.net.Net;
    import mj.net.message.game.GameChapterStart;
    import mj.net.message.game.GameJoinRoom;
    import mj.net.message.game.VoteDelStart;
    import mj.net.message.login.CreateRoom;
    import mj.net.message.login.DelRoom;
    import mj.net.message.login.ExitRoom;
    import mj.net.message.login.JoinRoom;
    import mj.net.message.login.Login;
    import mj.net.message.login.LoginRet;
    import mj.scene.MainScene;
    import mj.ui.dialog.CreateOptDialog;
    import mj.ui.dialog.Dialog;
    import mj.ui.dialog.JoinRoomDialog;
    import mj.ui.dialog.Wait;

    /**
     * @author zuoge85@gmail.com on 16/10/3.
     */
    public class UserManager
    {
        private static const LOGIN_TOKEN_NAME:String = "$_loginToken";


        public static const instance:UserManager = new UserManager();


        private var _user:User;
        private var _geolocationInfo:GeolocationInfo;

        public function UserManager()
        {
            if (instance != null)
            {
                throw new SingletonError("UserManager 是单例模式");
            }
        }

        public function loginHandler(msg:LoginRet):void
        {
            _user = new User(msg);

            var loginToken:String = msg.loginToken;
            setLoginToken(loginToken);
            //测试创建房间
            var mainScene:MainScene = UiManager.instance.goMain();
            mainScene.update(_user);
            if (msg.roomCheckId)
            {
                joinRoom(msg.roomCheckId);
            }
            else
            {
                var roomId:String = getUrlParam("roomId");
                if (roomId)
                {
                    joinRoom(roomId);
                }
            }
            if (checkLoginHandler)
            {
                checkLoginHandler.runWith([true]);
                checkLoginHandler = null;
            }
        }

        private static function getUrlParam(name:String):String
        {
            var reg:RegExp = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var search:String = __JS__("window.location.search");
            if (!search)
            {
                return null;
            }
            var r:Array = search.substr(1).match(reg);
            if (r != null)
            {
                return unescape(r[2]);
            }
            else
            {
                return null; //返回参数值
            }
        }


        public function updateGeo(geolocationInfo:GeolocationInfo):void
        {
            console.log("更新位置:", geolocationInfo);
            _geolocationInfo = geolocationInfo;
            Console.log("geolocationInfo:", geolocationInfo);
        }


        public function get geolocationInfo():GeolocationInfo
        {
            return _geolocationInfo;
        }

        public static function quitUser():void
        {
            clearLoginToken();
            GameMain.current.restart();
        }

        private var checkLoginHandler:Handler;

        /**
         *
         * @param handler 如果登录失败调用handler
         */
        public function checkLogin(handler:Handler):void
        {
            var userEncrypt:String = __JS__("window.userEncrypt");
            var loginToken:String = getLoginToken();
            if (loginToken || userEncrypt)
            {

                var login:Login = crateLogin();
                if (loginToken)
                {
                    login.openId = loginToken;
                    login.type = "TOKEN";
                }
                else
                {
                    login.code = userEncrypt;
                    login.type = "WEIXIN";
                }
                Wait.show("检查登录中！");
                Net.instance.write(login);
                checkLoginHandler = new Handler(this, function (result:Boolean):void
                {
                    Wait.close();
                    handler.runWith([result]);
                });
            }
            else
            {
                handler.runWith([false])
            }
        }

        public function checkUserEncryptLogin(userEncrypt:String, handler:Handler):void
        {
            var login:Login = crateLogin();
            login.code = userEncrypt;
            login.type = "WEIXIN";
            Net.instance.write(login);
            console.log("发送登录消息:", login);
            checkLoginHandler = handler;
        }

        public function checkAnonymousLogin(handler:Handler):void
        {
            var login:Login = crateLogin();
            login.type = "ANONYMOUS";
            Net.instance.write(login);

            console.log("发送登录消息:", login);
            checkLoginHandler = handler;
        }

        public function checkSmsLogin(phone:String, code:String, handler:Handler):void
        {
            var login:Login = crateLogin();
            login.openId = phone;
            login.code = code;
            login.type = "SMS";
            Net.instance.write(login);

            checkLoginHandler = handler;
        }

        public function loginError():void
        {
            if (checkLoginHandler)
            {
                checkLoginHandler.runWith([false]);
                checkLoginHandler = null;
            }
        }

        private var createDialog:Dialog;

        public function showCreateDialog():Dialog
        {
            createDialog = CreateOptDialog.showDialog(
                    Handler.create(this, onCreateDialog)
            );
        }

        private function onCreateDialog(options:Array):void
        {
            Net.instance.write(CreateRoom.create(GameMain.current.profile, options));
            Wait.show();
        }


        public function createRet(result:Boolean, roomCheckId:String):void
        {
            Wait.close();
            if (result)
            {
                createDialog.close();
                createDialog = null;
                joinRoom(roomCheckId);
            }
        }


        public function crateLogin():Login
        {
            var login:Login = new Login();
            if (geolocationInfo)
            {
                login.longitude = geolocationInfo.longitude.toString();
                login.longitude = geolocationInfo.latitude.toString();
            }
            return login;
        }


        private var joinDialog:Dialog;

        public function showJoinDialog():Dialog
        {
            joinDialog = JoinRoomDialog.showDialog(function (roomCheckId:String):void
            {
                joinRoom(roomCheckId);
            });
        }

        private function joinRoom(roomCheckId:String):void
        {
            Wait.show("等待进入房间!");
            Net.instance.write(JoinRoom.create(roomCheckId));
        }

        public function joinRet(result:Boolean):void
        {
            if (result)
            {
                //等待进入游戏
                Console.log("等待进入游戏!");
                if (joinDialog)
                {
                    joinDialog.close();
                    joinDialog = null;
                }
            }
            else
            {
                Wait.close();
            }
        }


        public static function startGame():void
        {
            Console.log("服务器准备就绪!");
            Net.instance.write(new GameJoinRoom());
        }

        public function joinRoomEnd():void
        {
            Wait.close();
        }


        public function get user():User
        {
            return _user;
        }

        public function delRoom():void
        {
            Dialog.confirm("是否解散房间？", Handler.create(this, function ():void
            {
                Net.instance.write(new DelRoom());
                Wait.show();
            }));
        }

        public function delVoteRoom():void
        {
            Dialog.confirm("是否发起解散房间投票？", Handler.create(this, function ():void
            {
                Net.instance.write(new VoteDelStart());
            }));
        }

//        public function delRoomRet():void
//        {
//            Wait.close();
//            UiManager.instance.goMain();
//        }

        public function quitRoom():void
        {
            Net.instance.write(new ExitRoom());
//            Wait.show();
        }


        public function exitRoomRet():void
        {
            Wait.close();
            UiManager.instance.goMain();
        }


        public function startChapter():void
        {
            Net.instance.write(new GameChapterStart());
            Wait.show();
        }


        private static var _loginToken:String;

        private function getLoginToken():String
        {
            if (_loginToken != null)
            {
                return _loginToken;
            }
            return LocalStorage.getItem(LOGIN_TOKEN_NAME);
        }

        private function setLoginToken(loginToken:String):void
        {
            _loginToken = loginToken;
            LocalStorage.setItem(LOGIN_TOKEN_NAME, loginToken);
        }


        private static function clearLoginToken():void
        {
            _loginToken = null;
            LocalStorage.clear();
            __JS__("window.userEncrypt = null");
        }

        public function pay(gold:int):void
        {
            if (gold > 0)
            {
                Dialog.showMessage("你充值 房卡：" + gold + " 成功!");
            }


            _user.gold += gold;
            if (UiManager.instance.currentScene && UiManager.instance.currentScene is MainScene)
            {
                var mainScene:MainScene = MainScene(UiManager.instance.currentScene)
                mainScene.updateGold(_user.gold);
            }
        }

    }
}
