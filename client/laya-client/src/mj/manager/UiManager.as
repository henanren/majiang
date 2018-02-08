package mj.manager
{
    import com.isnowfox.core.SingletonError;
    import com.isnowfox.core.console.Console;

    import laya.display.Sprite;
    import laya.events.Event;
    import laya.ui.Dialog;

    import mj.net.message.login.SysSetting;
    import mj.scene.GameScene;
    import mj.scene.MainScene;
    import mj.ui.Loading;

    /**
     * ui 管理
     * @author zuoge85@gmail.com on 16/10/11.
     */
    public class UiManager
    {
        public static const instance:UiManager = new UiManager();

        //悬浮加载,tips等
        public const topLayer:Sprite = new Sprite();
        //对话框等,顶层
        public const dialogLayer:Sprite = new Sprite();
        public const maskLayer:Sprite = new Sprite();

        //场景层
        public const sceneLayer:Sprite = new Sprite();
        public var maskSprite:Sprite = new Sprite();

        public function UiManager()
        {
            if (instance != null)
            {
                throw new SingletonError("ResourceManager 是单例模式");
            }
        }

        public function init():void
        {
            Console.log("UiManager.init()");
            Laya.stage.addChild(sceneLayer);
            //**呵呵，这样laya 的Dialog就在top 下面了
            Dialog.manager;

            Laya.stage.addChild(maskLayer);
            Laya.stage.addChild(dialogLayer);
            Laya.stage.addChild(topLayer);
            Laya.stage.on(Event.RESIZE, this, onResize);
            onResize(null);
            maskSprite.on(Event.CLICK, this, function ():void
            {
            });
            maskLayer.mouseThrough = true;
        }

        private function onResize(e:Event = null):void
        {
            var width:Number = Laya.stage.width;
            var height:Number = Laya.stage.height;
            maskSprite.size(width,height);
            maskLayer.size(width,height);

            maskSprite.graphics.clear();
            maskSprite.graphics.drawRect(0, 0, width, height, UIConfig.popupBgColor);
            maskSprite.alpha = UIConfig.popupBgAlpha;
        }

        public function showMask():void
        {
            maskLayer.addChild(maskSprite);

            maskSprite.mouseEnabled = true;
            maskSprite.mouseThrough = false;
        }

        public function hideMask():void
        {
            maskSprite.removeSelf();
        }

        public function goMain():MainScene
        {
            var main:MainScene = new MainScene();
            if (UserManager.instance.user)
            {
                main.update(UserManager.instance.user);
            }
            goScene(main);
            return main;
        }

        private var _currentScene:GameScene;

        public function goScene(scene:GameScene):void
        {
            Loading.instance.hide();
            if (_currentScene)
            {
                _currentScene.destroy();
                sceneLayer.removeChild(_currentScene.getDisplayObject());
            }
            Dialog.closeAll();
            _currentScene = scene;
            scene.init();
            sceneLayer.addChild(scene.getDisplayObject());
        }


        public function get currentScene():GameScene
        {
            return _currentScene;
        }

        private var _sysSetting:SysSetting = SysSetting.create("", "", "", "");

        public function changeSysSetting(msg:SysSetting):void
        {
            if (msg == null)
            {
                msg = SysSetting.create("", "", "", "");
            }
            if (msg.payInfo == null)
            {
                msg.payInfo = ";"
            }
            if (msg.radio == null)
            {
                msg.radio = ";"
            }
            if (msg.agreement == null)
            {
                msg.agreement = ";"
            }
            if (msg.notice == null)
            {
                msg.notice = ";"
            }
            this._sysSetting = msg;

            if (currentScene && currentScene is MainScene)
            {
                MainScene(currentScene).changeRadio(msg.radio);
                MainScene(currentScene).changeNewNotice();
            }
        }


        public function get sysSetting():SysSetting
        {
            return _sysSetting ? _sysSetting : SysSetting.create("", "", "", "");
        }

        public function changeRadio(radio:String):void
        {
            if (radio == null)
            {
                radio = "";
            }
            if (_sysSetting)
            {
                _sysSetting.radio = radio;
            }

            if (currentScene && currentScene is MainScene)
            {
                MainScene(currentScene).changeRadio(radio);
            }
        }
    }
}
