package view
{
    import laya.display.Sprite;
    import laya.events.Event;
    import laya.resource.Texture;
    import laya.ui.Image;
    import laya.utils.Browser;
    import laya.utils.Handler;

    import mj.manager.UiManager;
    import mj.model.PaiUtils;
    import mj.net.message.game.GameRoomInfo;
    import mj.net.message.game.GameUserInfo;
    import mj.ui.UiHelps;
    import mj.utils.LayaUtils;

    import ui.test.GameTableUI;

    /**
     * @author zuoge85@gmail.com on 2016/11/4.
     */
    public class GameTableView extends GameTableUI
    {
        protected static const PAI_NAME:String = "pai";

        protected static const H_PAI_SPACE:int = 4;
        protected static const V_PAI_SPACE:int = 0;

        protected static const MY_INDEX:int = 3;

        private var defaultHeadIconSkin:String;
        private var nHeadWidth:Number;
        private var nHeadHeight:Number;
        private var nHeadIconLeft:Number;
        private var nHeadIconTop:Number;

        private var nTingPaiBgWidth:Number;

        private var nUserHead0Height:Number;
        private var nUserHead1Left:Number;
        private var nUserHead2Height:Number;
        private var nUserHead3Top:Number;

        private var nUserHeadRatio:Number;
        private var nTurntableRatio:Number;

        private var nLogoBtnLeft:Number;
        private var nLogoBtnTop:Number;

        private var nDelRoomBtnLeft:Number;
        private var nDelRoomBtnTop:Number;

        private var nInviteBtnLeft:Number;
        private var nInviteBtnTop:Number;

        private var nShouPai3Ratio:Number;
        private var nShouPai2Ratio:Number;
        private var nShouPai1Ratio:Number;
        private var nShouPai0Ratio:Number;


        protected var nOutPaiMovieRatio:Number;

        protected var nOutPaiMovie0X:Number;
        protected var nOutPaiMovie0Y:Number;

        protected var nOutPaiMovie1X:Number;
        protected var nOutPaiMovie1Y:Number;

        protected var nOutPaiMovie2X:Number;
        protected var nOutPaiMovie2Y:Number;

        protected var nOutPaiMovie3X:Number;
        protected var nOutPaiMovie3Y:Number;

        protected var _isMyCreate:Boolean;

        protected var shouPaiWidth:Number;
        protected var shouPaiHeight:Number;
        protected var _isWaitOut:Boolean = false;

        public function GameTableView(isMyCreate:Boolean)
        {
            this._isMyCreate = isMyCreate;

            nTurntableRatio = turntable.scaleX;
            defaultHeadIconSkin = userHeadIcon0.skin;
            nHeadWidth = userHeadIcon0.width;
            nHeadHeight = userHeadIcon0.height;

            nHeadIconLeft = userHeadIcon0.x;
            nHeadIconTop = userHeadIcon0.y;

            nTingPaiBgWidth = tingPaiBg.width;

            nUserHeadRatio = userHead0.scaleX;
            nUserHead0Height = userHead0.height;
            nUserHead1Left = userHead1.x;
            nUserHead2Height = userHead2.height;
            nUserHead3Top = userHead3.y;

            nDelRoomBtnLeft = delRoomBtn.x;
            nDelRoomBtnTop = delRoomBtn.y;

            nLogoBtnLeft = logo.x;
            nLogoBtnTop = logo.y;

            nInviteBtnLeft = inviteBtn.x;
            nInviteBtnTop = inviteBtn.y;


            nShouPai3Ratio = shouPai3.scaleX;
            nShouPai2Ratio = shouPai2.scaleX;
            nShouPai1Ratio = shouPai1.scaleX;
            nShouPai0Ratio = shouPai0.scaleX;

            shouPaiWidth = shou3.width;
            shouPaiHeight = shou3.height;

            gameSprite.visible = false;

            for (var i:int = 0; i < 4; i++)
            {
                this["userHeadIconOut" + i].mask = this["userHeadMask" + i];
                setUserHeadVisible(i, false);

                var outPaiMovie:Sprite = this["outPaiMovie" + i];

                this["nOutPaiMovie" + i + "X"] = outPaiMovie.x;
                this["nOutPaiMovie" + i + "Y"] = outPaiMovie.y;
                outPaiMovie.visible = false;
            }

            nOutPaiMovieRatio = outPaiMovie0.scaleX;

            Laya.stage.on(Event.RESIZE, this, layout);
            Laya.stage.on(Event.ADDED, this, layout);

            initShou(3);
            initShou(2);
            initShou(1);
            initShou(0);
            layout();
        }


        public function layout():void
        {
            var bWidth:Number = Browser.width;
            var bHeight:Number = Browser.height;
            var ratio:Number = UiHelps.getGameRatio();
            //layout bg

            //先调整对话框长宽比例
            var bgWidth:Number = bWidth / ratio;
            var bgHeight:Number = bHeight / ratio;
            var wChange:Number = bgWidth - UiHelps.DESIGN_WIDTH;
            var hChange:Number = bgHeight - UiHelps.DESIGN_HEIGHT;


//            userHead0.y = (bgHeight - nUserHead0Height) / 2;
//
//            userHead1.x = nUserHead1Left + wChange;
////            userHead1.y = nUserHead1Top + hChange;
//
//            userHead2.y = (bgHeight - nUserHead2Height) / 2;
//            userHead3.y = nUserHead3Top + hChange;
//
            logo.x = nLogoBtnLeft + wChange / 2;
            logo.y = nLogoBtnTop + hChange / 2;

            delRoomBtn.x = nDelRoomBtnLeft + wChange / 2;
            delRoomBtn.y = nDelRoomBtnTop + hChange / 2;

            inviteBtn.x = nInviteBtnLeft + wChange / 2;
            inviteBtn.y = nInviteBtnTop + hChange / 2;


            bgImage.size(bgWidth, bgHeight);

            bg.scale(ratio, ratio);
            bg.size(bWidth, bHeight);
            waitSprite.scale(ratio, ratio);
            waitSprite.size(bWidth + 2000, bHeight + 2000);
            layoutGame();
            gameSprite.size(bWidth + 2000, bHeight + 2000);
            heads.size(bWidth + 2000, bHeight + 2000);
            heads.mouseThrough = true;
            gameSprite.mouseThrough = true;
            waitSprite.mouseThrough = true;
            movieSprite.mouseEnabled = false;

            for (var i:int = 0; i < gameSprite.numChildren; i++)
            {
                var node:Sprite = Sprite(gameSprite.getChildAt(i));
                node.mouseEnabled = false;
            }
            shouPai3.mouseEnabled = true;

            movieSprite.scale(ratio, ratio);
            this.size(bWidth + 2000, bHeight + 2000);
        }

        /**
         * 布局正面等！
         */
        private function layoutGame():void
        {


            var bWidth:Number = Browser.width;
            var bHeight:Number = Browser.height;
            //
            var ratioX:Number = UiHelps.getGameRatioX();
            var ratioY:Number = UiHelps.getGameRatioY();
            var ratio:Number = UiHelps.getGameRatio();

            var bgWidth:Number = bWidth / ratio;
            var bgHeight:Number = bHeight / ratio;
            var wChange:Number = bgWidth - UiHelps.DESIGN_WIDTH;
            var hChange:Number = bgHeight - UiHelps.DESIGN_HEIGHT;


            buttons.scale(ratio, ratio);
            buttons.x = bWidth - buttons.width * ratioX;
            console.log(buttons.x);
            //方向3 下面
            var shouPai3Ratio:Number = nShouPai3Ratio * ratioX;
            shouPai3.scale(shouPai3Ratio, shouPai3Ratio);
            shouPai3.x = (bWidth - (shouPai3.width * shouPai3Ratio)) / 2;
            var shouPai3Height:Number = shouPai3.height * shouPai3Ratio;
            shouPai3.y = (bHeight - (shouPai3Height));

            var headRatio:Number = ratio * nUserHeadRatio;
            userHead3.scale(headRatio, headRatio);
            userHead3.y = shouPai3.y - (userHead3.height * headRatio);
            var userHead3Height:Number = userHead3.height * headRatio;


            //听牌
            tingPaiSprite.scale(shouPai3Ratio, shouPai3Ratio);
            tingPaiSprite.x = shouPai3.x;
            tingPaiSprite.y = shouPai3.y + shouPai3Height * 0.5;
            tingPaiPai.removeSelf();

            //方向0 左边
            userHead0.scale(headRatio, headRatio);
            var userHead0H:Number = (userHead0.height * headRatio);
            var userHead0W:Number = (userHead0.width * headRatio);
            userHead0.y = (bHeight) / 2 - userHead0H - userHead0H * 0.25;

            var shouPai0Space:Number = bHeight - shouPai3Height;
            var shouPai0Height:Number = shouPai0Space * 0.85;
            var shouPai0Ratio:Number = shouPai0Height / shouPai0.height;
            var shouPai0Width:Number = shouPai0Ratio * shouPai0.width;
            shouPai0.scale(shouPai0Ratio, shouPai0Ratio);
            shouPai0.x = userHead0W;
            shouPai0.y = (shouPai0Space - shouPai0Height) / 2;

            //方向2 右边
            userHead2.scale(headRatio, headRatio);
            var userHead2H:Number = (userHead2.height * headRatio);
            var userHead2W:Number = (userHead2.width * headRatio);
            userHead2.x = bWidth - userHead2W;
            userHead2.y = (bHeight) / 2 - userHead2H;

            var shouPai2Width:Number = shouPai0Ratio * shouPai2.width;
            shouPai2.scale(-shouPai0Ratio, shouPai0Ratio);
            shouPai2.x = bWidth - userHead2W;// - (shouPai2.width * shouPai2Ratio);
            shouPai2.y = (shouPai0Space - shouPai0Height) / 2;


            //方向1 上面
            userHead1.scale(headRatio, headRatio);
            var userHead1Width:Number = (userHead1.width * headRatio);
            var userHead1Height:Number = (userHead1.height * headRatio);
//            userHead1.x = userHead2W + shouPai2Width;
            userHead1.x = nUserHead1Left * ratio;

            var shouPai1Space:Number = bWidth - userHead1Width - userHead2W - shouPai2Width - userHead0W - shouPai0Width;
            var shouPai1Width:Number = shouPai1Space * 0.85;
            var shouPai1Ratio:Number = shouPai1Width / shouPai1.width;
            var shouPai1Height:Number = shouPai1Ratio * shouPai1.height;

            shouPai1.scale(shouPai1Ratio, shouPai1Ratio);
            shouPai1.x = userHead2W + shouPai2Width + userHead1Width + (shouPai1Space - shouPai1Width) / 2;


            //处理输出区域
            //方向0 左边
//            out0.scale(ratio, ratio);
            var out0Space:Number = bHeight - userHead1Height - shouPai3Height;
            var out0Height:Number = out0Space * 0.9;
            var out0Ratio:Number = out0Height / out0.height;
            var out0Width:Number = out0Ratio * out0.width;

            out0.scale(out0Ratio, out0Ratio);
            out0.x = shouPai0Width + userHead0W;
            out0.y = userHead1Height + (out0Space - out0Height) / 2;

            //方向2 右边
            var out2Width:Number = out0Width;

            out2.scale(-out0Ratio, out0Ratio);

            out2.x = bWidth - userHead2W - shouPai2Width;
            out2.y = out0.y;

            //方向3 下面
            var out3Space:Number = bWidth - userHead2W - shouPai2Width - out2Width - userHead0W - shouPai0Width - out0Width;
            var out3Width:Number = out3Space * 0.95;
            var out3Ratio:Number = Math.min(out3Width / out3.width, out0Ratio);

            out3.scale(out3Ratio, out3Ratio);
            out3.x = (bWidth - (out3.width * out3Ratio)) / 2;
            out3.y = bHeight - shouPai3Height - (out3.height * out3Ratio);


            //方向1 上面
            out1.scale(out3Ratio, out3Ratio);

            out1.x = Math.max(userHead1.x + userHead1Width, (bWidth - (out1.width * out3Ratio)) / 2);
            out1.y = shouPai1Height;


            //中间转盘
            var turntableScale:Number = ratio * nTurntableRatio;
            turntable.scale(turntableScale, turntableScale);
            turntable.x = (bWidth - turntable.width * turntableScale) / 2;
            var tempY:Number = shouPai1Height + out1.height;
            turntable.y = tempY + (((bHeight - shouPai3Height - out3.height - tempY)) - turntable.height * turntableScale) / 2;

            trace("layoutGame", shouPai3.width);
        }

        private function initShou(index:int):void
        {
            this["chi" + index].removeSelf();
            this["anGang" + index].removeSelf();
            this["anGangHide" + index].removeSelf();
            this["mingGang" + index].removeSelf();
            this["shou" + index].removeSelf();
            this["fa" + index].removeSelf();


            this["userHeadOffline" + index].visible = false;
            this["zhuang" + index].visible = false;

            var outSprite:Sprite = this["out" + index];
            for (var i:int = 0; i < outSprite.numChildren; i++)
            {
                var child:Sprite = Sprite(outSprite.getChildAt(i));
                child.removeChildren();
            }
        }


        private function setUserHeadVisible(i:int, visible:Boolean):void
        {
            this["userId" + i].visible = visible;
            this["gold" + i].visible = visible;
            this["goldBg" + i].visible = visible;
            this["goldIcon" + i].visible = visible;
            this["userName" + i].visible = visible;
        }


        public function gameEnd(isEnd:Boolean):void
        {
            delRoomBtn.removeSelf();
            inviteBtn.offAll(Event.CLICK);

            var bWidth:Number = Browser.width;
            var ratio:Number = UiHelps.getGameRatio();
            //layout bg

            //先调整对话框长宽比例
            var bgWidth:Number = bWidth / ratio;
            inviteBtn.width += inviteBtn.width * 0.5;
            inviteBtn.x = (bgWidth - inviteBtn.width) / 2;
            inviteBtn.label = "(结束)返回大厅";
            inviteBtn.on(Event.CLICK, this, function ()
            {
                UiManager.instance.goMain();
            });
        }


        public function update(msg:GameRoomInfo):void
        {
            updateTingPai(null);
            roomCheckId.text = "房间号:" + msg.roomCheckId;
            updateLeftGameNums(msg.leftChapterNums);
        }

        protected function updateLeftGameNums(nums:int):void
        {
            leftGameNums.text = "剩余：" + nums + "盘"
        }

        public function updateUserInfoScore(designIndex:int, score:Number):void
        {
            this["gold" + designIndex].text = score.toString();
        }

        public function updateUserInfo(designIndex:int, userInfo:GameUserInfo):void
        {
            this["userName" + designIndex].text = userInfo.userName;

            var image:Image = this["userHeadIcon" + designIndex];
            var maskImage:Image = this["userHeadMask" + designIndex];

            if (userInfo.avatar)
            {
                image.skin = defaultHeadIconSkin;
                Laya.loader.load(userInfo.avatar, Handler.create(this, function ():void
                {
                    var t:Texture = Laya.loader.getRes(userInfo.avatar);
                    if (!t)
                    {
                        return;
                    }
                    image.skin = userInfo.avatar;
                    var radioW:Number = nHeadWidth / t.width;
                    var radioH:Number = nHeadHeight / t.height;
                    var radio:Number = radioW > radioH ? radioW : radioH;

                    image.width = t.width;
                    image.height = t.height;
                    image.scale(radio, radio);

                    maskImage.visible = false;
                    image.pivotX = -(nHeadWidth - (t.width * radio)) / 2 + nHeadIconLeft;
                    image.pivotY = -(nHeadHeight - (t.height * radio)) / 2 + nHeadIconTop;
                    this["userHeadIconOut" + designIndex].mask = maskImage;
                }));
            }
            else
            {
                image.skin = defaultHeadIconSkin;
            }
            setUserHeadVisible(designIndex, true);
            this["userId" + designIndex].text = "ID:" + userInfo.userId;
            this["gold" + designIndex].text = userInfo.score.toString();
            this["userHeadOffline" + designIndex].visible = !userInfo.online;
//            console.log("userHeadOffline:", designIndex, !userInfo.online);
        }


        public function offlineUser(index:int):void
        {
            this["userHeadOffline" + index].visible = true;
        }


        public function delUserInfo(designIndex:int):void
        {
            setUserHeadVisible(designIndex, false);
            this["userHeadOffline" + designIndex].visible = false;
            this["userHeadIcon" + designIndex].skin = defaultHeadIconSkin;
        }


        public function set isWaitOut(value:Boolean):void
        {
            if (_isWaitOut != value)
            {
                _isWaitOut = value;
                handlerWaitOut();
            }
        }


        public function updateTingPai(pais:Array):void
        {
            if (pais && pais.length)
            {
                tingPaiSprite.visible = true;
                var x:Number = tingPaiPai.x;
                var number:Number = Math.min(8, pais.length);
                tingPaiPaiSprite.removeChildren();
                for (var i:int = 0; i < number; i++)
                {
                    var pai:int = pais[i];
                    var paiImage:Image = Image(LayaUtils.clone(tingPaiPai));
                    paiImage.x = x;
                    tingPaiPaiSprite.addChild(paiImage);
                    x += paiImage.width + 4;

                    PaiUtils.showPaiIcon(Image(paiImage.getChildAt(0)), pai);
                }

                tingPaiBg.width = (this.nTingPaiBgWidth + ((tingPaiPai.width * tingPaiPaiSprite.scaleX + 4) * (number))) /tingPaiBg.scaleX;
            }
            else
            {
                tingPaiSprite.visible = false;
            }
        }

        public function handlerWaitOut():void
        {
            for (var i:int = 0; i < shouPai3.numChildren; i++)
            {
                var paiSprite:Sprite = shouPai3.getChildAt(i) as Sprite;
                if (PAI_NAME == paiSprite.name)
                {
//                    (paiSprite.getChildAt(paiSprite.numChildren - 1) as Sprite).visible = !_isWaitOut;
                    (paiSprite.getChildAt(paiSprite.numChildren - 1) as Sprite).visible = false;
                }
            }
        }
    }
}
