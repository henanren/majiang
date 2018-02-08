package
{
    import com.isnowfox.core.console.Console;

    import laya.device.geolocation.Geolocation;
    import laya.device.geolocation.GeolocationInfo;
    import laya.display.Sprite;
    import laya.display.Stage;
    import laya.events.Event;
    import laya.ui.View;
    import laya.utils.Browser;
    import laya.utils.Handler;
    import laya.utils.Stat;
    import laya.webgl.WebGL;

    import mj.GlobalResource;
    import mj.manager.AudioManager;
    import mj.manager.CordovaManager;
    import mj.manager.UiManager;
    import mj.manager.UserManager;
    import mj.net.Net;
    import mj.scene.LoginScene;
    import mj.ui.Loading;
    import mj.ui.dialog.Dialog;
    import mj.ui.dialog.Wait;

    public class GameMain
    {
        public static var current:GameMain;
        private var isResComplete:Boolean = false;
        private var isNetComplete:Boolean = false;
        private var isGeolocationComplete:Boolean = false;


        private var _profile:String;

        public function GameMain()
        {
            current = this;

            Laya.init(Browser.width, Browser.height, WebGL);

            trace("Browser.clientWidth", Browser.clientWidth, "Browser.clientHeight", Browser.clientHeight);
            trace("Browser.width", Browser.width, "Browser.height", Browser.height);
            trace("Browser.pixelRatio", Browser.pixelRatio);
            trace("Browser.soundType", Browser.soundType);

            Laya.stage.alignH = Stage.ALIGN_LEFT;
            Laya.stage.alignV = Stage.ALIGN_TOP;
            Laya.stage.scaleMode = Stage.SCALE_FULL;
            Laya.stage.screenMode = Stage.SCREEN_HORIZONTAL;

            View.regComponent("Sprite", Sprite);

            var gameInit:* = __JS__("window.gameInit");
            if (gameInit)
            {
                gameInit(this, init);
            }
            else
            {
                init();
            }
            Laya.stage.on(Event.RESIZE, this, onResize);
        }

        private function onResize(e:Event = null):void
        {
            var width:Number = Laya.stage.width;
            var height:Number = Laya.stage.height;
            Console.log("onResize:{0},{1}", width, height);
        }

        private function init(profile:String = "bj"):void
        {
            this._profile = profile;
            trace(this);
            //初始化引擎
            Stat.show(0, 100);
            UiManager.instance.init();
            GlobalResource.instance.init();
            //加载引擎需要的资源
            Laya.loader.load([
                "ui/loading/bg.jpg",
                "ui/loading/progress.png",
                "ui/loading/progress$bar.png"
            ], Handler.create(this, startGame));
            Console.log("init end");
        }


        private function startGame():void
        {

            Console.log("startGame");
            isResComplete = false;
            isNetComplete = false;
            isGeolocationComplete = false;
            laya.ui.Dialog.closeAll();
            var serverUrl:String = __JS__("window.serverUrl");
            if (!serverUrl)
            {
                serverUrl = "ws://127.0.0.1:8010/g";
//                serverUrl = "ws://192.168.0.205:8010/g";
//                serverUrl = "ws://182.92.100.147:8012/g";
            }
//            Net.instance.init("ws://127.0.0.1:8010/g", function ():void
//            Net.instance.init("ws://192.168.0.205:8010/g", function ():void
//            Net.instance.init("ws://182.92.100.147:8011/g", function ():void
//            Net.instance.init("ws://182.92.100.147:8010/g", function ():void
//            Net.instance.init("ws://182.92.100.147:8012/g", function ():void
            Net.instance.init(serverUrl, function ():void
            {
                Console.log("onConnect");
                onConnect();
            });

            GlobalResource.instance.initOther(
                    Handler.create(this, onGlobalResourceComplete),
                    Handler.create(this, onGlobalResourceProgress, null, false)
            );


            if (__JS__("navigator&& navigator.geolocation"))
            {
                __JS__('navigator.geolocation.getCurrentPosition(this.updateCordovaGeoPosition.bind(this), this.onGeoError.bind(this))');
            }
            else
            {
                Geolocation.enableHighAccuracy = true;
                var successHandler:Handler = new Handler(this, updateGeoPosition);
                var errorHandler:Handler = new Handler(this, onGeoError);

                Geolocation.getCurrentPosition(successHandler, errorHandler);
            }


            AudioManager.instance.playStartBgm();
            Loading.instance.show();
            CordovaManager.instance.init();
        }


        private function onGlobalResourceProgress(progress:Number):void
        {
            Console.log("onGlobalResourceProgress:{0}", progress);
            Loading.instance.progress = progress;
        }

        private function updateCordovaGeoPosition(p:*):void
        {
            var geo:GeolocationInfo = new GeolocationInfo();
            geo.setPosition(p);
            UserManager.instance.updateGeo(geo);
        }

        // 更新设备位置
        private function updateGeoPosition(p:GeolocationInfo):void
        {
            UserManager.instance.updateGeo(p);
//            isGeolocationComplete = true;
//            goLoginScene();
        }

        private function onGeoError(e:*):void
        {
            console.log('onGeoError code: ' + e.code + '\n' + 'message: ' + e.message + '\n');
            if (e.code == Geolocation.TIMEOUT)
            {
                console.log("GEO:获取位置超时", e)
            }
            else if (e.code == Geolocation.POSITION_UNAVAILABLE)
            {
                console.log("GEO:位置不可用", e)
            }
            else if (e.code == Geolocation.PERMISSION_DENIED)
            {
                console.log("GEO:无权限", e)
            }
            Dialog.showMessage("获取位置失败！请打开位置权限！");
//            isGeolocationComplete = true;
//            goLoginScene();
        }

        private function onGlobalResourceComplete():void
        {
            Console.log("onGlobalResourceComplete");
            Loading.instance.progress = 1.0;
            isResComplete = true;
            goLoginScene();
        }

        private function onConnect():void
        {
            Console.log("onConnect");
            isNetComplete = true;
            goLoginScene();
        }

//        private var uuid:String;

        private function goLoginScene():void
        {
            if (isNetComplete && isResComplete)
            {
                UserManager.instance.checkLogin(Handler.create(this, function (success):void
                {
                    if(!success){
                        var res:* = Laya.loader.getRes("res/atlas/base.json");
                        var testLoginScene:LoginScene = new LoginScene();
                        UiManager.instance.goScene(testLoginScene);
                    }
//                    if (!UserManager.instance.geolocationInfo)
//                    {
//                        Dialog.showMessage("获取位置失败！请打开位置权限！");
//                    }
                }));
            }
        }

//        private function startLogin(uuid:String):void
//        {
//            Console.log("开始登陆!");
//            var login:Login = new Login();
//            login.openId = uuid;
//            Net.instance.write(login);
//        }


        public function restart():void
        {
            startGame();
        }


        public function get profile():String
        {
            return _profile;
        }

        public function quit():void
        {
            Dialog.confirm("是否退出游戏", Handler.create(this, function ():void
            {
                __JS__("if(navigator && navigator.app){navigator.app.exitApp();}")
            }));
        }
    }
}