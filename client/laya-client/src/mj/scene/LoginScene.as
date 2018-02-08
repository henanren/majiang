package mj.scene
{
    import laya.display.Sprite;
    import laya.events.Event;
    import laya.utils.Browser;
    import laya.utils.Handler;

    import mj.manager.UiManager;
    import mj.manager.UserManager;
    import mj.ui.*;
    import mj.ui.dialog.Dialog;
    import mj.ui.dialog.DialogSize;
    import mj.ui.dialog.PhoneDialog;
    import mj.ui.dialog.Wait;

    import ui.test.LoginUI;

    /**
     * @author zuoge85@gmail.com on 16/10/12.
     */
    public final class LoginScene extends LoginUI implements GameScene
    {
        private var isHasWeixinLogin:Boolean;
        private var buttonsSpriteY:Number;

        public function LoginScene()
        {
            Laya.stage.on(Event.RESIZE, this, layout);
            agreeCheck.selected = true;

            agreeCheck.clickHandler = new Handler(this, function ():void
            {
                var disabled:Boolean = !agreeCheck.selected;
                smsLoginBtn.disabled = disabled;
                anonymousLoginBtn.disabled = disabled;
                weixinLoginBtn.disabled = disabled;
            }, [agreeCheck]);

            isHasWeixinLogin = __JS__("window.weixinLogin != null");

            {
                var disabled:Boolean = !agreeCheck.selected;
                smsLoginBtn.disabled = disabled;
                anonymousLoginBtn.disabled = disabled;
                weixinLoginBtn.disabled = disabled;
            }

            buttonsSpriteY = buttonsSprite.y;
            smsLoginBtn.on(Event.CLICK, this, onSmsClick);
            anonymousLoginBtn.on(Event.CLICK, this, onAnonymousClick);
            weixinLoginBtn.on(Event.CLICK, this, onWeixinClick);
            agreeLink.on(Event.CLICK, this, function ()
            {
                Dialog.showMessage(
                        UiManager.instance.sysSetting.agreement,
                        null, DialogSize.LARGE, "用户协议"
                );
            });
            layout();
        }

        private static function onAnonymousClick(e:Event):void
        {
            Wait.show("登录中！");
            UserManager.instance.checkAnonymousLogin(Handler.create(null, function ():void
            {
                Wait.close();
            }));
        }

        private static function onWeixinClick(e:Event):void
        {
            Wait.show("微信登陆中！");
            __JS__("window.weixinLogin(LoginScene.onWeixinSuccess)");
        }

        private static function onWeixinSuccess(userEncrypt:String):void
        {
            console.log("微信登录返回:", userEncrypt);
            if (userEncrypt)
            {
                UserManager.instance.checkUserEncryptLogin(userEncrypt, Handler.create(null, function ():void
                {
                    Wait.close();
                }))
            }
            else
            {
                Wait.close();
            }
        }

        private static function onSmsClick(e:Event):void
        {
            PhoneDialog.showDialog(Handler.create(null, function (phone:String, code:String):void
            {
                //开始登陆
                Wait.show("登录中！");
                UserManager.instance.checkSmsLogin(phone, code, Handler.create(null, function ():void
                {
                    Wait.close();
                }))
            }));
        }

        private var mianRatio:Number;

        private function layout():void
        {
            var nWidth:Number = UiHelps.DESIGN_WIDTH;
            var nHeight:Number = UiHelps.DESIGN_HEIGHT;

            var bWidth:Number = Browser.width;
            var bHeight:Number = Browser.height;
            var wRatio:Number = bWidth / nWidth;
            var hRatio:Number = bHeight / nHeight;
            var ratio:Number = wRatio > hRatio ? wRatio : hRatio;

            bg.scale(ratio, ratio);
            var width:Number = nWidth * ratio;
            var height:Number = nHeight * ratio;

            //ui 内容的缩放模式不一样, 保证内容都能显示为主
            mianRatio = wRatio > hRatio ? hRatio : wRatio;
            mainSprite.scale(mianRatio, mianRatio);
            var mianWidth:Number = nWidth * mianRatio;
            var mianHeight:Number = nHeight * mianRatio;

            mainSprite.x = -(mianWidth - bWidth) / 2;
            mainSprite.y = -(mianHeight - bHeight) / 3;


            if (!isHasWeixinLogin)
            {
                buttonsSprite.y = buttonsSpriteY - weixinLoginBtn.height;
                weixinLoginBtn.visible = false;
            }
            this.size(width, height);

        }

        override public function destroy(destroyChild:Boolean = true):void
        {
            super.destroy(destroyChild);
            Laya.stage.off(Event.RESIZE, this, layout);
        }

        public function getDisplayObject():Sprite
        {
            return this;
        }

        public function init():void
        {
        }
    }
}
