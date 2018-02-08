package mj.scene
{
    import laya.display.Sprite;
    import laya.display.Text;
    import laya.events.Event;
    import laya.maths.Rectangle;
    import laya.resource.Texture;
    import laya.ui.Image;
    import laya.utils.Browser;
    import laya.utils.Handler;

    import mj.manager.UiManager;
    import mj.manager.UserManager;
    import mj.model.User;
    import mj.net.Net;
    import mj.net.message.login.RoomHistoryList;
    import mj.ui.*;
    import mj.ui.dialog.Dialog;
    import mj.ui.dialog.DialogSize;
    import mj.ui.dialog.SettingDialog;
    import mj.ui.dialog.UserDialog;
    import mj.ui.dialog.Wait;
    import mj.utils.LayaUtils;

    import ui.test.MainUI;

    /**
     * @author zuoge85@gmail.com on 16/10/12.
     */
    public final class MainScene extends MainUI implements GameScene
    {
        private var noticeRect:Rectangle = new Rectangle();

        private var defaultHeadIconSkin:String;
        private var nHeadWidth:Number;
        private var nHeadHeight:Number;
        private var nHeadIconLeft:Number;
        private var nHeadIconTop:Number;

        private var nNoticeWidth:Number;
        private var nNoticeHeight:Number;
        private var noticeWidth:Number;

        public function MainScene()
        {
            Laya.stage.on(Event.RESIZE, this, layout);

            defaultHeadIconSkin = userHeadIcon.skin;
            nHeadWidth = userHeadIcon.width;
            nHeadHeight = userHeadIcon.height;

            nHeadIconLeft = userHeadIcon.x;
            nHeadIconTop = userHeadIcon.y;

            nNoticeWidth = noticeLabel.width;
            nNoticeHeight = noticeLabel.height;

            userHeadIconOut.mask = userHeadMask;

            layout();

            crateBtn.on(Event.CLICK, this, onCrateClick);
            joinBtn.on(Event.CLICK, this, onJoinClick);

            Laya.timer.frameLoop(1, this, noticeScroll);

            LayaUtils.handlerButton(quitBtn);
            LayaUtils.handlerButton(settingBtn);
            LayaUtils.handlerButton(historyBtn);

            quitBtn.on(Event.CLICK, this, onBack);

            historyBtn.on(Event.CLICK, this, function ():void
            {
                Wait.show();
                Net.instance.write(new RoomHistoryList());
            });


            noticeLabel.on(Event.CLICK, this, function ():void
            {
                Dialog.showMessage(
                        UiManager.instance.sysSetting.radio,
                        null, DialogSize.LARGE, "公告"
                );
            });

            addFangkaSprite.on(Event.CLICK, this, function ():void
            {
                Dialog.showMessage(UiManager.instance.sysSetting.payInfo, null, DialogSize.MEDIUM)
            });

            userHeadOut.on(Event.CLICK, this, function ():void
            {
                var user:User = UserManager.instance.user;
                UserDialog.showDialog(user.name, user.avatar, user.ip, user.id, null, null);
            });

            changeRadio(UiManager.instance.sysSetting.radio);
            changeNewNotice();

            settingBtn.on(Event.CLICK, this, function ():void
            {
                SettingDialog.showDialog();
            });

        }

        public function onBack():void
        {
            Dialog.confirm("是否退出登录用户", Handler.create(this, function ():void
            {
                UserManager.quitUser()
            }));
        }

        private static function onCrateClick(e:Event):void
        {
            UserManager.instance.showCreateDialog();
        }

        private static function onJoinClick(e:Event):void
        {
            UserManager.instance.showJoinDialog();
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


            this.size(width, height);

            noticeWidth = nNoticeWidth * mianRatio;
        }

        private var noticeScrollValue:Number = 0;

        private function noticeScroll():void
        {
            if (noticeScrollValue > noticeWidth)
            {
                noticeScrollValue = -noticeWidth;
            }
            //noticeMask
//            noticeLabel.mask = noticeMask;
            noticeScrollValue += (Laya.timer.delta / 10);
            noticeRect.setTo(noticeScrollValue, 0, nNoticeWidth, nNoticeHeight + 20);
            noticeLabel.scrollRect = noticeRect;
            noticeLabel.pivotX = noticeScrollValue;
        }

        override public function destroy(destroyChild:Boolean = true):void
        {
            super.destroy(destroyChild);
            Laya.timer.clear(this, noticeScroll);
            Laya.stage.off(Event.RESIZE, this, layout);
        }

        public function getDisplayObject():Sprite
        {
            return this;
        }

        public function update(user:User):void
        {
            userName.text = user.name;

            var image:Image = userHeadIcon;
            var maskImage:Image = userHeadMask;

            if (user.avatar)
            {
                image.skin = defaultHeadIconSkin;
                Laya.loader.load(user.avatar, Handler.create(this, function ():void
                {
                    var t:Texture = Laya.loader.getRes(user.avatar);
                    image.skin = user.avatar;
                    var radioW:Number = nHeadWidth / t.width;
                    var radioH:Number = nHeadHeight / t.height;
                    var radio:Number = radioW > radioH ? radioW : radioH;

                    image.width = t.width;
                    image.height = t.height;
                    image.scale(radio, radio);
                    maskImage.visible = false;
                    image.pivotX = -(nHeadWidth - (t.width * radio)) / 2 + nHeadIconLeft;
                    image.pivotY = -(nHeadHeight - (t.height * radio)) / 2 + nHeadIconTop;

                    userHeadIconOut.mask = maskImage;
                }));
            }
            else
            {
                image.skin = defaultHeadIconSkin;
            }

            userId.text = "ID:" + user.id;
            gold.text = "房卡:" + user.gold.toString();
        }

        public function init():void
        {
//            RoomHistoryListDialog.showDialog();
        }

        public function updateGold(goldValue:Number):void
        {
            gold.text = "房卡:" + goldValue.toString();
        }

        public function changeRadio(radio:String):void
        {
            noticeLabel.text = radio.replace("\n", "");
        }

        public function changeNewNotice():void
        {
            var notice:String = UiManager.instance.sysSetting.notice;
            newNotice.text = notice;


            newNotice.overflow = Text.HIDDEN;
            newNotice.offAll(Event.CLICK);
            newNotice.on(Event.CLICK, this, function ():void
            {
                Dialog.showMessage(
                        notice,
                        null, DialogSize.LARGE, "健康游戏公告"
                );
            });
        }
    }
}
