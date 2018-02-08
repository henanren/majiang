/**
 * @author zuoge85@gmail.com on 16/10/14.
 */
package mj.scene
{
    import com.isnowfox.core.console.Console;

    import laya.display.Animation;
    import laya.display.Node;
    import laya.display.Sprite;
    import laya.events.Event;
    import laya.filters.GlowFilter;
    import laya.maths.Point;
    import laya.maths.Rectangle;
    import laya.ui.Image;
    import laya.utils.Browser;
    import laya.utils.Handler;
    import laya.utils.Tween;

    import mj.GameObserver;
    import mj.manager.GameManager;
    import mj.manager.UiManager;
    import mj.manager.UserManager;
    import mj.model.PaiUtils;
    import mj.net.message.game.GameChapterEnd;
    import mj.net.message.game.GameFanResult;
    import mj.net.message.game.GameRoomInfo;
    import mj.net.message.game.GameUserInfo;
    import mj.net.message.game.MajiangChapterMsg;
    import mj.net.message.game.UserPlaceMsg;
    import mj.ui.UiHelps;
    import mj.ui.dialog.Dialog;
    import mj.ui.dialog.DialogSize;
    import mj.ui.dialog.SettingDialog;
    import mj.ui.dialog.UserDialog;
    import mj.utils.LayaUtils;
    import mj.utils.TweenTargetPoll;

    import view.GameTableView;

    public class GameTableScene extends GameTableView implements GameScene
    {
        private var observer:GameObserver;
        private var _targetPoll:TweenTargetPoll = new TweenTargetPoll();

        public function GameTableScene(isMyCreate:Boolean, observer:GameObserver)
        {
            super(isMyCreate);
            this.observer = observer;


            undateDelButton();
            for (var i:int = 0; i < 4; i++)
            {
                _targetPoll.add(this["outPaiMovie" + i])
            }
            sbSourceBian0.visible = false;
            sbSourceBian1.visible = false;
            isAutoStart.visible = false;
            userHead0.hitTestPrior = true;
            userHead1.hitTestPrior = true;
            userHead2.hitTestPrior = true;
            userHead3.hitTestPrior = true;
            userHead0.on(Event.CLICK, this, getOnHeadClick(0));
            userHead1.on(Event.CLICK, this, getOnHeadClick(1));
            userHead2.on(Event.CLICK, this, getOnHeadClick(2));
            userHead3.on(Event.CLICK, this, getOnHeadClick(3));
            bianMove.visible = false;

            LayaUtils.handlerButton(settingBtn);
            LayaUtils.handlerButton(gameInfoBtn);
            settingBtn.on(Event.CLICK, this, function ():void
            {
                if (isMyCreate)
                {
                    SettingDialog.showQuitDialog(Handler.create(this, function ():void
                    {
                        SettingDialog.close();
                        voteDelRoomClick();
                    }), "解散房间");
                }
                else
                {
                    SettingDialog.showQuitDialog(Handler.create(this, function ():void
                    {
                        SettingDialog.close();
                        voteDelRoomClick();
                    }), "解散房间");
                }
            });

            gameInfoBtn.on(Event.CLICK, this, function ():void
            {
                Dialog.showMessage("规则\n" +
                                   "共一百四十四张：筒、索、万、东、南、西、北风、中、发、白， 有 梅、兰、竹、菊、春、夏、秋、冬这八个皆为花。\n" +
                                   "打法\n" +
                                   "与网上打法有许多不同，玩家开始时，庄家可得到十四张牌，其余的人十三张。庄家从牌中选出一张牌丢出。其他家只能“碰”或者“杠”，当玩家定胡时，可以吃任何一家出道你要的牌，同时有多家吃胡时，只有胡大牌的一家才算胡，而剩下的一家也就被称为截胡。胡法分（由小到大）：鸡胡、杂色、清一色、大哥、十三幺。开台时玩家都会说明玩的大小，常见的有1 2 3 、3个5、10 20 30等。第一个数是指花多少，第二个数是共杠或者暗杠是多少，第三个数是指最小的鸡胡是多少。其他胡法就是按倍数计算。玩家摸到花时，到牌堆尾摸牌进行补牌。有时候有些玩家还有买马这个玩法，买马是玩家买哪家赢，而买到哪家是看你拿到的麻将和上盘的赢家也就是这盘的庄家。庄家为1，逆时针算起，马的牌数是多少，进行计算，算到哪家那哪家就是你买到的人，买到的那家赢了，买马者就可以拿与赢家一样的数，相反你买的那家输了，他要出多少你就出多少。\n" +
                                   "摸牌\n" +
                                   "游戏在一开始，只有庄家可得到十四张牌，其余的人十三张。庄家从牌中选出一张牌丢出。此时，其它三家都有权力要那张丢出的牌。庄家的下家（右手边的玩者），有权力吃或碰那张牌，其它两家则只可碰或杠那张牌。 “ 碰 ” 比 “ 吃 ” 优先。\n" +
                                   "庄家\n" +
                                   "1、如果庄家胡牌或和牌（流局）则下盘依然由该庄家做庄。\n" +
                                   "2、如果非庄家胡牌则下一盘由庄家的下家玩家做庄。\n" +
                                   "3、第一盘庄家由系统随机分配风位，东风位玩家为庄家。",
                        null, DialogSize.LARGE, "规则"
                );
            });
        }

        public function undateDelButton():void
        {
            if (_isMyCreate || GameManager.instance.isStart())
            {
                delRoomBtn.label = "解散房间";
                delRoomBtn.offAll(Event.CLICK);
                delRoomBtn.on(Event.CLICK, this, onDelRoomClick);
            }
            else
            {
                delRoomBtn.label = "退出房间";
                delRoomBtn.offAll(Event.CLICK);
                delRoomBtn.on(Event.CLICK, this, onQuitRoomClick);
            }
        }

        private function getOnHeadClick(index:int):Function
        {
            return function ():void
            {
                var gameRoomInfo:GameRoomInfo = GameManager.instance.roomInfo();
                if (gameRoomInfo)
                {
                    var user:GameUserInfo = gameRoomInfo.sceneUser[GameManager.instance.transformToIndex(index)];
                    if (user)
                    {
                        UserDialog.showDialog(
                                user.userName, user.avatar, user.ip, user.userId,
                                user, gameRoomInfo.sceneUser
                        );
                    }
                }
            };
        }

        private static function onDelRoomClick(e:Event):void
        {
            if (GameManager.instance.isStart())
            {
                UserManager.instance.delVoteRoom();
            }
            else
            {
                UserManager.instance.delRoom();
            }
        }

        public function onBack():void
        {
            if (_isMyCreate || GameManager.instance.isStart())
            {
                voteDelRoomClick();
            }
            else
            {
                onQuitRoomClick();
            }
        }

        private static function voteDelRoomClick():void
        {
            UserManager.instance.delVoteRoom();
        }

        private static function onQuitRoomClick():void
        {
            UserManager.instance.quitRoom();
        }


        public function getDisplayObject():Sprite
        {
            return this;
        }

        override public function destroy(destroyChild:Boolean = true):void
        {
            super.destroy(destroyChild);

            Laya.timer.clearAll(this);
            Laya.stage.off(Event.RESIZE, this, layout);
            if (shaziAni)
            {
                this.shaziAni.removeSelf();
                shaziAni.destroy(true);
            }

            if (currentTurntable)
            {
                Tween.clearAll(currentTurntable)
            }


            _targetPoll.clear();
        }


        public function updateFullUser(isFull:Boolean, isMyCreate:Boolean):void
        {
            inviteBtn.offAll(Event.CLICK);
            if (isFull && isMyCreate)
            {
                inviteBtn.label = "开始游戏";
                inviteBtn.on(Event.CLICK, this, onStartChapterClick);

                if (isAutoStart.selected && !GameManager.instance.isStart())
                {
                    onStartChapterClick(null);
                }
            }
            else
            {
                inviteBtn.label = "邀请好友";
                inviteBtn.on(Event.CLICK, this, onInviteBtnClick);
            }
        }

        private function onStartChapterClick(e:Event):void
        {
            UserManager.instance.startChapter();
        }

        private function onInviteBtnClick(e:Event):void
        {
            var roomCheckId:String = GameManager.instance.roomInfo().roomCheckId;
            __JS__("if(window.shareInviteUser) window.shareInviteUser(roomCheckId);");
//            var chapterMsg:MajiangChapterMsg = new MajiangChapterMsg();
//            var userPlace:UserPlaceMsg = new UserPlaceMsg();
//            userPlace.shouPai = [0, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7];
//            userPlace.fa = 5;
//            userPlace.out = [0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23];
//
//            var ohterUserPlace:UserPlaceMsg = new UserPlaceMsg();
//            ohterUserPlace.shouPaiLen = 13;
//            ohterUserPlace.fa = PaiUtils.HAS_PAI_INDEX;
//
//            ohterUserPlace.out = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23];
//
//            chapterMsg.huiEr = 0;
//
////            chapterMsg.userPlace = [ohterUserPlace, ohterUserPlace, ohterUserPlace, userPlace];
//            chapterMsg.userPlace = [];
//
//            var gameManager:GameManager = GameManager.instance;
//            chapterMsg.userPlace[gameManager.transformToIndex(3)] = userPlace;
//            chapterMsg.userPlace[gameManager.transformToIndex(2)] = ohterUserPlace;
//            chapterMsg.userPlace[gameManager.transformToIndex(1)] = ohterUserPlace;
//            chapterMsg.userPlace[gameManager.transformToIndex(0)] = ohterUserPlace;
//
//            startChapter(chapterMsg);
        }

//        private function onInviteBtnClick(e:Event):void
//        {
//            var chapterMsg:MajiangChapterMsg = new MajiangChapterMsg();
//            var userPlace:UserPlaceMsg = new UserPlaceMsg();
//            userPlace.chi = [11, 12, 13];
//            userPlace.peng = [14];
//            userPlace.anGang = [14];
//            userPlace.xiaoMingGang = [16];
//            userPlace.shouPai = [19];
//            userPlace.fa = 5;
//            userPlace.out = [0, 1, 2, 3, 4, 5, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19];
//
//            var ohterUserPlace:UserPlaceMsg = new UserPlaceMsg();
//            ohterUserPlace.chi = [11, 12, 13];
//            ohterUserPlace.peng = [14];
//            ohterUserPlace.anGang = [PaiUtils.HAS_PAI_INDEX];
//            ohterUserPlace.daMingGang = [15];
//            ohterUserPlace.shouPaiLen = 1;
//            ohterUserPlace.fa = PaiUtils.HAS_PAI_INDEX;
//
//            ohterUserPlace.out = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23];
//
//
//            chapterMsg.userPlace = [ohterUserPlace, ohterUserPlace, ohterUserPlace, userPlace];
//            startMovie(chapterMsg);
//        }


        private var shaziAni:Animation;
        private var startFaPai:Boolean = false;
        private var chapterMsg:MajiangChapterMsg;

        public function startChapter(chapterMsg:MajiangChapterMsg):void
        {
            this.chapterMsg = chapterMsg;
            updateTingPai(null);
            undateDelButton();
            sbSourceBian0.visible = false;
            sbSourceBian1.visible = false;

            waitSprite.visible = false;

            var ratio:Number = UiHelps.getGameRatio();

            if (shaziAni)
            {
                shaziAni.removeSelf();
                shaziAni.destroy();
                shaziAni = null;
            }
            shaziAni = new Animation();
            shaziAni.source = "res/atlas/movie/shazi.json";
            shaziAni.interval = 60;			// 设置播放间隔（单位：毫秒）
            shaziAni.loop = false;


            var bounds:Rectangle = shaziAni.getGraphicBounds();

            var bWidth:Number = Browser.width;
            var bHeight:Number = Browser.height;

            shaziAni.pos(
                    (bWidth - (bounds.width + bounds.x) * ratio) / 2 - bounds.x,
                    (bHeight - (bounds.height * ratio) ) / 2 - bounds.y
            );
            UiManager.instance.dialogLayer.addChild(shaziAni);

            shaziAni.play(0, false);
            shaziAni.scale(ratio, ratio);
            //开始
            //准备 麻将的各种区域
            updateLeftGameNums((chapterMsg.chapterNumsMax - chapterMsg.chapterNums));

            startFaPai = true;


            binaGuiMoveMovie();

        }

        private static const indexName:Array = ["东", "南", "西", "北"];//0东 1南 2西 3北

        private var turntableSecond:int = 0;
        private var turntableIndex:int = -1;
        private var currentTurntable:Sprite = null;
        private var turntableStartTime:Number;

        public function startTime(turntableSecond:int):void
        {
            this.turntableSecond = turntableSecond;
            this.turntableStartTime = Laya.timer.currTimer;
        }


        public function isWaitStart():Boolean
        {
            return startFaPai;
        }

        public function stopFaPai(chapter:MajiangChapterMsg):void
        {
            if (startFaPai)
            {
                startFaPai = false;
                updateChapter(chapterMsg);
            }
        }

        private function frame():void
        {
            if (startFaPai)
            {
                if (shaziAni != null && !shaziAni.isPlaying)
                {
                    shaziAni.removeSelf();
                    shaziAni.destroy();
                    shaziAni = null;
                    faPaiIndex = 0;
                }
                else if (shaziAni == null)
                {
                    chapterFaPai();
                }
            }
            if (turntableSecond > -1)
            {
                var leftTime:Number = Laya.timer.currTimer - turntableStartTime;

                if (turntableSecond == 2)
                {
                    observer.onTimeupAlarm();
                }
                if ((leftTime) > 1000)
                {
                    updateTurntableTime(turntableSecond);
                    turntableStartTime = Laya.timer.currTimer - (leftTime - 1000);
                    turntableSecond--;
                }
            }
        }

        private function startFrame()
        {
            Laya.timer.frameLoop(1, this, frame);
        }

        private function stopOptTime()
        {
            observer.onStopTimeupAlarm();
            if (currentTurntable)
            {
                currentTurntable.visible = false;
            }
            updateTurntableTime(0);
            turntableSecond = -1;
        }

        public function syncOptTime(index:int, leftTime:int):void
        {
            observer.onStopTimeupAlarm();
//            Console.log("syncOptTime[index:{0}, time:{1}]", index, Math.ceil(leftTime / 1000));
            startTime(Math.ceil(leftTime / 1000));
            turntableIndex = index;
            for (var i:int = 0; i < 4; i++)
            {
                this["turntable" + i].visible = false;
            }
            if (currentTurntable)
            {
                Tween.clearAll(currentTurntable)
            }
            currentTurntable = this["turntable" + index];
            currentTurntable.visible = true;
            currentTurntable.alpha = 1.0;

            to0();

            function to0():void
            {
                Tween.to(currentTurntable, {
                    alpha: 0.5
                }, 500, null, Handler.create(this, to1));
            }

            function to1():void
            {
                Tween.to(currentTurntable, {
                    alpha: 1
                }, 500, null, Handler.create(this, to0));
            }
        }

        private var faPaiIndex:int = 0;
        private var faPaiStart:Number;

        private function chapterFaPai():void
        {
            this.chapterMsg = chapterMsg;
            if (faPaiIndex == 0)
            {
                faPaiStart = Laya.timer.currTimer;
                initChapter();
                turntable.visible = false;
            }


            if ((Laya.timer.currTimer - faPaiStart) >= faPaiIndex * 500)
            {
                var gameManager:GameManager = GameManager.instance;
                chapterFaPaiItem(gameManager.transformToDesignIndex(3), chapterMsg.userPlace[3]);
                chapterFaPaiItem(gameManager.transformToDesignIndex(2), chapterMsg.userPlace[2]);
                chapterFaPaiItem(gameManager.transformToDesignIndex(1), chapterMsg.userPlace[1]);
                chapterFaPaiItem(gameManager.transformToDesignIndex(0), chapterMsg.userPlace[0]);


                if (faPaiIndex > 4)
                {
                    updateChapter(chapterMsg);
                    startFaPai = false;
                    observer.onEnter();
                }
                else
                {
                    observer.onDealCard();
                }
                faPaiIndex++;
            }
        }

        private function chapterFaPaiItem(index:int, userPlace:UserPlaceMsg):void
        {
            var isHorizontal:Boolean = index % 2 == 1;
            var startIndex:int = faPaiIndex * 4;
            var shouPai:Array = userPlace.shouPai;
            var shouPaiLen:int = userPlace.shouPaiLen;
            if (shouPai)
            {
                if (shouPai.length > startIndex)
                {
                    var pais:Array = shouPai.slice(startIndex, Math.min((faPaiIndex + 1) * 4, shouPai.length));
                    showShouPaiGroup(index, pais, this["shou" + index], isHorizontal);
                }
            }
            else if (shouPaiLen)
            {
                if (shouPaiLen > startIndex)
                {
                    var array:Array = [];
                    array.length = Math.min(shouPaiLen - startIndex, 4);
                    showShouPaiGroup(index, array, this["shou" + index], isHorizontal);
                }
            }
        }

        /**
         * 更新牌桌信息
         * @param chapterMsg
         */
        public function updateChapter(chapterMsg:MajiangChapterMsg):void
        {
            this.chapterMsg = chapterMsg;

            initChapter();


            const gameManager:GameManager = GameManager.instance;
            showPai(gameManager.transformToDesignIndex(3), chapterMsg.userPlace[3]);
            showPai(gameManager.transformToDesignIndex(2), chapterMsg.userPlace[2]);
            showPai(gameManager.transformToDesignIndex(1), chapterMsg.userPlace[1]);
            showPai(gameManager.transformToDesignIndex(0), chapterMsg.userPlace[0]);
            updateLeftGameNums((chapterMsg.chapterNumsMax - chapterMsg.chapterNums));
        }


        public function initZhuang(chapterMsg:MajiangChapterMsg)
        {
            const gameManager:GameManager = GameManager.instance;
            var zhuangIndex:int = gameManager.transformToDesignIndex(chapterMsg.zhuangIndex);

            for (var i:int = 0; i < 4; i++)
            {
                this["zhuang" + zhuangIndex].visible = zhuangIndex == i;
            }
        }

        private function initChapter():void
        {
            if (shaziAni)
            {
                shaziAni.removeSelf();
                shaziAni.destroy();
                shaziAni = null;
            }
            clearPai(3);
            clearPai(2);
            clearPai(1);
            clearPai(0);
            waitSprite.visible = false;
            turntable.visible = true;
            gameSprite.visible = true;
            paiSpace = [0, 0, 0, 0];
            updateTurntable();
            var hw:Number = turntableCenter.width / 2;
            var hh:Number = turntableCenter.height / 2;
            turntableCenter.pivot(hw, hh);
            turntableCenter.pos(hw, hh);
            var gameManager:GameManager = GameManager.instance;

            /**
             * 东家的index
             */
            var dongIndex:int = gameManager.transformToDesignIndex(0);
            var myIndex:int = gameManager.transformToDesignIndex(gameManager.myLocationIndex);

            turntableCenter.rotation = -90 * (myIndex - dongIndex);
//            turntableCenter.r
        }

        private function updateTurntable():void
        {
            freePayLength.text = chapterMsg.freeLength.toString();
            juName.text = indexName[chapterMsg.quanIndex] + "风" + indexName[chapterMsg.zhuangIndex] + "局";

            if (chapterMsg.bianType == "danGui")
            {
                bianType.text = "单鬼变：" + PaiUtils.toString(chapterMsg.huiEr[0]);
                sbSourceBian0.visible = true;
                showPaiIcon(Image(sbSourceBian0.getChildAt(0)), chapterMsg.huiEr[0]);
            }
            else if (chapterMsg.bianType == "shuangGui")
            {
                bianType.text = "双鬼变：" + PaiUtils.toString(chapterMsg.huiEr[0]) + " " + PaiUtils.toString(chapterMsg.huiEr[1]);
                sbSourceBian0.visible = true;
                sbSourceBian1.visible = true;
                showPaiIcon(Image(sbSourceBian0.getChildAt(0)), chapterMsg.huiEr[0]);
                showPaiIcon(Image(sbSourceBian1.getChildAt(0)), chapterMsg.huiEr[1]);
            }
            else
            {
                bianType.text = "";
                sbSourceBian0.visible = false;
                sbSourceBian1.visible = false;
            }
        }


        private function showPai(index:int, userPlace:UserPlaceMsg, isShowEndResult:Boolean = false):void
        {
            //暗杠
            var isHorizontal:Boolean = index % 2 == 1;

            if (userPlace.anGang)
            {
                showAnGang(index, userPlace.anGang, isHorizontal);
            }

            if (userPlace.daMingGang)
            {
                showMingGangOrPeng(index, userPlace.daMingGang, this["mingGang" + index], isHorizontal);
            }

            if (userPlace.xiaoMingGang)
            {
                showMingGangOrPeng(index, userPlace.xiaoMingGang, this["mingGang" + index], isHorizontal);
            }

            if (userPlace.peng)
            {
                showMingGangOrPeng(index, userPlace.peng, this["chi" + index], isHorizontal);
            }

            if (userPlace.chi)
            {
                showChi(index, userPlace.chi, this["chi" + index], isHorizontal);
            }

            if (this["shouPai" + index].numChildren > 0)
            {
                paiSpace[index] += 16;
            }


            if (isShowEndResult)
            {

                var chi:Sprite = this["chi" + index];
                var source:Sprite = Sprite(LayaUtils.clone(chi));
                source.removeChildAt(1);
                source.removeChildAt(1);
                source.width = Image(source.getChildAt(0)).width;
                source.height = Image(source.getChildAt(0)).height;
                for (var j:int = 0; j < userPlace.shouPai.length; j++)
                {
                    showPaiGroup(
                            index, userPlace.shouPai[j], source, isHorizontal,
                            isHorizontal ? 1 : -30
                    );
                }
                source.destroy(true);
            }
            else
            {
                showShouPai(userPlace, index, isHorizontal);
            }


            if (userPlace.outPai)
            {
                for (var i:int = 0; i < userPlace.outPai.length; i++)
                {
                    var pai:int = userPlace.outPai[i];
                    showOut(index, pai, isHorizontal, i);
                }
            }
        }

        private function showShouPai(userPlace:UserPlaceMsg, index:int, isHorizontal:Boolean):void
        {
            var len:uint;
            var isFaPai:Boolean;
            if (userPlace.shouPai)
            {
                len = userPlace.shouPai.length;
                isFaPai = checkIsFaPai(len);
                var shouPai:Array = isFaPai ? userPlace.shouPai.slice(0, len - 1) : userPlace.shouPai.slice();

                shouPai.sort(function (a:int, b:int):int
                {
                    return a - b;
                });
                showShouPaiGroup(index, shouPai, this["shou" + index], isHorizontal);

                if (isFaPai)
                {
                    var fa:int = userPlace.shouPai[len - 1];
                    paiSpace[index] += (isHorizontal ? 26 : 106);
                    var sprite:Sprite = showPaiGroup(index, fa, this["fa" + index], isHorizontal, isHorizontal ? 4 : V_PAI_SPACE);
                    initPaiButton(sprite, fa);
                }
            }
            else if (userPlace.shouPaiLen)
            {
                len = userPlace.shouPaiLen;
                isFaPai = checkIsFaPai(len);
                var array:Array = [];
                array.length = isFaPai ? len - 1 : len;
                showShouPaiGroup(index, array, this["shou" + index], isHorizontal);

                paiSpace[index] += (isHorizontal ? 26 : 106);

                if (isFaPai)
                {
                    showPaiGroup(index, PaiUtils.HAS_PAI_INDEX, this["fa" + index], isHorizontal, isHorizontal ? 4 : V_PAI_SPACE);
                }
            }
        }

        public static function checkIsFaPai(len:int):Boolean
        {
            //3*n+2;
            return (len - 2) % 3 == 0;
        }

        private var paiSpace:Array = [0, 0, 0, 0];

        private function showShouPaiGroup(index:int, pais:Array, source:Sprite, isHorizontal:Boolean):void
        {
            for (var i:int = 0; i < pais.length; i++)
            {
                var paiSprite:Sprite = showPaiGroup(index, pais[i], source, isHorizontal, isHorizontal ? 1 : -80);
                if (index === MY_INDEX)
                {
                    initPaiButton(paiSprite, pais[i]);
                }
            }
        }

        private var prevPaiSprite:Sprite;
        private var dragStartSprite:Sprite;
        private var dragStartX:Number = -1;

        private function initPaiButton(paiSprite:Sprite, pai:int):void
        {
//            trace("initPaiButton");
            var changeHeight:Number = (shouPaiHeight / 2);
            var time:Number = 0;
            initDragArea();
            var sourceX:Number = paiSprite.x;
            var sourceY:Number = paiSprite.y;

//            paiSprite.name = PAI_NAME;
//            var topLayer:Sprite = new Sprite();
//            topLayer.graphics.clear();
//            topLayer.graphics.drawRect(0, 0, paiSprite.width, paiSprite.height, UIConfig.popupBgColor);
//            topLayer.alpha = 0.3;
//            var topMask:Image = new Image();
//            topMask.skin = Image(paiSprite.getChildAt(0)).skin;
//            topLayer.mask = topMask;
//            topLayer.visible = !_isWaitOut;
//            paiSprite.addChild(topLayer);

            var isDrag:Boolean = false;
            var _lastX:Number, _lastY:Number, _yChange:Number = 0;
            paiSprite.on(Event.MOUSE_DOWN, this, function (e:Event):void
            {
                //trace("paiSprite.MOUSE_DOWN", (Laya.timer.currTimer - time));


                startDrag();
            });

            paiSprite.on(Event.MOUSE_OVER, this, function (e:Event):void
            {
                //trace("paiSprite.MOUSE_OVER");
            });

            paiSprite.on(Event.DRAG_MOVE, this, function (e:Event):void
            {
                trace("paiSprite.MOUSE_OVER");
            });

            paiSprite.on(Event.MOUSE_OUT, this, function (e:Event):void
            {
                trace("paiSprite.MOUSE_OUT");
                stopDrag();
            });
            paiSprite.on(Event.MOUSE_UP, this, function (e:Event):void
            {
                trace("paiSprite.MOUSE_UP");
                stopDrag();
                click(e);
                if (prevPaiSprite && prevPaiSprite == paiSprite && (Laya.timer.currTimer - time) > 100)
                {
                    outPai();
                }
            });

            paiSprite.on(Event.MOUSE_MOVE, this, function (e:Event):void
            {
                if (isDrag)
                {
                    var point:Point = paiSprite.getMousePoint();
                    var mouseX:Number = point.x;
                    var mouseY:Number = point.y;
                    var offsetX:Number = mouseX - _lastX;


                    var offsetY:Number = mouseY - _lastY;

                    if (prevPaiSprite)
                    {
                        prevPaiSprite.y = sourceY;
                    }
                    prevPaiSprite = null;
                    if ((sourceY - (paiSprite.y + _yChange)) < changeHeight)
                    {
//                        paiSprite.y += offsetY;
                        paiSprite.y += offsetY;
                        paiSprite.x = sourceX;
                    }
                    else
                    {
                        paiSprite.y += offsetY;
                        paiSprite.x += offsetX;
                    }
                    _yChange += offsetY;
                }
            });

            paiSprite.on(Event.CLICK, this, function (e:Event):void
            {
                //trace("paiSprite.CLICK");
                click(e);
            });

            paiSprite.on(Event.DOUBLE_CLICK, this, function (e:Event):void
            {
                //trace("paiSprite.DOUBLE_CLICK");
                outPai();
            });


            paiSprite.on(Event.MOUSE_OVER, this, function (e:Event):void
            {
                trace("paiSprite.MOUSE_DOWN");

            });

            function startDrag():void
            {
                isDrag = true;
                _lastX = paiSprite.mouseX;
                _lastY = paiSprite.mouseY;
                _yChange = 0;
            }

            function stopDrag():void
            {
                isDrag = false;

                console.log(paiSprite.y - sourceY, shouPaiHeight);
                if ((sourceY - paiSprite.y) > shouPaiHeight)
                {
                    outPai();
                }

                paiSprite.x = sourceX;
                paiSprite.y = sourceY;
            }

            function click(e:Event):void
            {
                if (prevPaiSprite != paiSprite)
                {
                    if (paiSprite.y < 0)
                    {
                        paiSprite.y = 0;
                        prevPaiSprite = null;
                    }
                    else
                    {
                        paiSprite.height = shouPaiHeight + changeHeight;
                        paiSprite.y = -changeHeight;
                        initDragArea();
                        observer.onPaiClick(pai);

                        if (prevPaiSprite && prevPaiSprite != paiSprite)
                        {
                            prevPaiSprite.y = sourceY;
                        }
                        prevPaiSprite = paiSprite;
                        time = Laya.timer.currTimer;
                    }
                }
            }

            function outPai():void
            {

                prevPaiSprite = null;
                Console.log("outPai");
                GameManager.instance.putPai(pai)
            }

            function initDragArea():void
            {
                paiSprite.hitArea = new Rectangle(0, 0, paiSprite.width, paiSprite.height);
                paiSprite["_$dragArea"] = new Rectangle(0, 0, paiSprite.width, shouPaiHeight);
            }
        }


        private function showChi(index:int, pais:Array, source:Sprite, isHorizontal:Boolean):void
        {
            for (var i:int = 0; i < pais.length; i += 3)
            {
                showPaiGroup(index, [pais[i], pais[i + 1], pais[i + 2]], source, isHorizontal, isHorizontal ? H_PAI_SPACE : V_PAI_SPACE);
            }
        }

        private function showMingGangOrPeng(index:int, pais:Array, source:Sprite, isHorizontal:Boolean):void
        {
            for (var i:int = 0; i < pais.length; i++)
            {
                showPaiGroup(index, pais[i], source, isHorizontal, isHorizontal ? H_PAI_SPACE : V_PAI_SPACE);
            }
        }

        private function showAnGang(index:int, pais:Array, isHorizontal:Boolean):void
        {
            for (var i:int = 0; i < pais.length; i++)
            {
                var pai:int = pais[i];
                if (pai == PaiUtils.HAS_PAI_INDEX)
                {
                    showPaiGroup(index, pai, this["anGangHide" + index], isHorizontal, isHorizontal ? H_PAI_SPACE : V_PAI_SPACE);
                }
                else
                {
                    showPaiGroup(index, pai, this["anGang" + index], isHorizontal, isHorizontal ? H_PAI_SPACE : V_PAI_SPACE);
                }
            }
        }

        private function showOut(index:int, pai:int,
                                 isHorizontal:Boolean, outIndex:int):void
        {
            var childSprite:Sprite = Sprite(this["out" + index].getChildAt(outIndex > 11 ? 0 : 1));
            var outPai:Sprite = this["outPai" + index];
            var dist:Sprite = Sprite(LayaUtils.clone(outPai));
            outIndex = outIndex % 12;
            if (isHorizontal)
            {
                dist.x = outIndex * (outPai.width + 2);
            }
            else
            {
                dist.y = outIndex * (outPai.height - 35);
            }
            var icon:Image = Image(dist.getChildAt(0));
            showPaiIcon(icon, pai);
            childSprite.addChild(dist);
        }

        private function showPaiGroup(index:int, pai:*, source:Sprite, isHorizontal:Boolean, space:int):Sprite
        {
            var dist:Sprite = Sprite(LayaUtils.clone(source));

            if (isHorizontal)
            {
                dist.x = paiSpace[index];
            }
            else
            {
                dist.y = paiSpace[index];
            }
            this["shouPai" + index].addChild(dist);

            if (isHorizontal)
            {
                paiSpace[index] += (space + source.width);
            }
            else
            {
                paiSpace[index] += (space + source.height);
            }


            for (var i:int = 0, j:int = 0; i < dist.numChildren; i++)
            {
                var childAt:Node = dist.getChildAt(i);
                if (childAt is Image)
                {
                    var paiImage:Image = Image(childAt.getChildAt(0));
                    if (paiImage)
                    {
//                        ui/majiang/zheng_19.png
                        if (pai is Array)
                        {
                            showPaiIcon(paiImage, pai[j]);
                            j++;
                        }
                        else
                        {
                            if (pai > PaiUtils.NOT_PAI_INDEX)
                            {
                                showPaiIcon(paiImage, pai);
                            }
                        }
                    }
                }
            }
            return dist;
        }

        private function showPaiIcon(paiImage:Image, pai:int):void
        {
            PaiUtils.showPaiIcon(paiImage, pai);
//            if (pai == chapterMsg.huiEr)
//            {
////                ui/majiang/zheng_19.png
//                var huiEr:Image = new Image();
//                huiEr.skin = paiImage.skin.replace(/[0-9]+\.png/gi, "hun.png");
//                paiImage.addChild(huiEr);
//            }
//            paiImage.skin = paiImage.skin.replace(/[0-9]+\.png/gi, pai.toString() + ".png");
        }

        /**
         * 清除牌
         */
        private function clearPai(index:int, isClearOut:Boolean = true):void
        {
            var i:int;

            var shouPai:Sprite = this["shouPai" + index];
            while (shouPai.numChildren > 0)
            {
                shouPai.getChildAt(0).removeSelf().destroy(true);
            }

            if (!isClearOut)
            {
                return;
            }
            var outSprite:Sprite = this["out" + index];
            for (i = 0; i < outSprite.numChildren; i++)
            {
                var child:Sprite = Sprite(outSprite.getChildAt(i));
                while (child.numChildren > 0)
                {
                    child.getChildAt(0).removeSelf().destroy(true);
                }
            }
        }

        private function updateTurntableTime(turntableSecond:int):void
        {
            var n0:String = (turntableSecond % 10).toString();
            var n1:String = Math.floor(turntableSecond / 10).toString();
            turntableNum0.skin = "ui/game/" + n0 + ".png";
            turntableNum1.skin = "ui/game/" + n1 + ".png";
        }

        public function updateEndResult(msg:GameChapterEnd, gameRoomInfo:GameRoomInfo):void
        {


            waitSprite.visible = true;
            var gameManager:GameManager = GameManager.instance;

            updateLeftGameNums(gameRoomInfo.leftChapterNums);

            clearPai(3, false);
            clearPai(2, false);
            clearPai(1, false);
            clearPai(0, false);
            paiSpace = [0, 0, 0, 0];
            showPai(gameManager.transformToDesignIndex(3), PaiUtils.toUserPlace(msg.fanResults[3]), true);
            showPai(gameManager.transformToDesignIndex(2), PaiUtils.toUserPlace(msg.fanResults[2]), true);
            showPai(gameManager.transformToDesignIndex(1), PaiUtils.toUserPlace(msg.fanResults[1]), true);
            showPai(gameManager.transformToDesignIndex(0), PaiUtils.toUserPlace(msg.fanResults[0]), true);
            stopOptTime();
            for (var i:int = 3; i > -1; i--)
            {
                var fanResult:GameFanResult = msg.fanResults[i];
                var designIndex:int = gameManager.transformToDesignIndex(i);
                var sceneUser:GameUserInfo = gameRoomInfo.sceneUser[i];
                if (sceneUser)
                {
                    sceneUser.score += fanResult.score;
                    updateUserInfoScore(designIndex, sceneUser.score);
                }
            }

        }


        public function init():void
        {
            startFrame();
        }

        private var startPoint:Point = new Point();
        private var startRB:Point = new Point();


        private static const glowFilter:GlowFilter = new GlowFilter("#F0C048", 12, 0, 0);


        public function binaGuiMoveMovie():void
        {
            if (chapterMsg.bianType == "danGui")
            {
                goBinaGuiMoveMovie("danGui");
            }
            else if (chapterMsg.bianType == "shuangGui")
            {
                goBinaGuiMoveMovie("shuangGui");
            }
        }

        public function goBinaGuiMoveMovie(type:String):void
        {
            Laya.timer.once(1000, this, function ():void
            {
                var bWidth:Number = Browser.width;
                var bHeight:Number = Browser.height;
                var ratio:Number = UiHelps.getGameRatio();
                //layout bg

                //先调整对话框长宽比例
                var bgWidth:Number = bWidth / ratio;
                var bgHeight:Number = bHeight / ratio;

                bianMove.visible = true;

                guiOptMovie(3, type + "QueDing", close);
                bianMove.pivot(bianMove.width / 2, bianMove.height / 2);
                bianMove.pos(bgWidth / 2, bgHeight / 2 - bianMove.height * 0.5);


                showPaiIcon(Image(sourcePai.getChildAt(0)), chapterMsg.bianSource);
                showPaiIcon(Image(sourceBian0.getChildAt(0)), chapterMsg.huiEr[0]);
                if ("shuangGui" == type)
                {
                    sourceBian1.visible = true;
                    showPaiIcon(Image(sourceBian1.getChildAt(0)), chapterMsg.huiEr[1]);
                }
                else
                {
                    sourceBian1.visible = false;
                }
                function close():void
                {
                    bianMove.visible = false;
                }
            });
        }

        public function guiOptMovie(designIndex:int, opt:String, encCallback:Function = null):Image
        {
            var outPaiMovie:Sprite = this["outPaiMovie" + designIndex];
            var targetX:Number = this["nOutPaiMovie" + designIndex + "X"];
            var targetY:Number = this["nOutPaiMovie" + designIndex + "Y"];

            var skin:String = "ui/game/" + opt + ".png";


            var image:Image = new Image();
            image.skin = skin;
            image.pivot(image.width / 2, image.height / 2);

            image.x = targetX + outPaiMovie.width / 2;
            image.y = targetY + outPaiMovie.height / 2;

            var centerImage:Image = new Image();
            centerImage.skin = skin;
            centerImage.pivot(image.pivotX, image.pivotY);

            centerImage.x = image.x;
            centerImage.y = image.y;

            centerImage.filters = [glowFilter];
            image.filters = [glowFilter];


//            movieSprite.addChild(image);
            movieSprite.addChildAt(centerImage, 0);

            image.alpha = 0.8;

            Tween.to(centerImage, {
                scaleX: 1.5,
                scaleY: 1.5
            }, 1000, null, Handler.create(this, function ():void
            {
                Tween.to(centerImage, {
                    scaleX: 1,
                    scaleY: 1
                }, 1000, null, Handler.create(this, function ():void
                {
                    Laya.timer.once(3000, this, function ():void
                    {
                        image.skin = null;
                        image.removeSelf();
                        image.dispose();
                        centerImage.skin = null;
                        centerImage.removeSelf();
                        centerImage.dispose();

                        _targetPoll.remove(centerImage);

                        if (encCallback)
                        {
                            encCallback();
                        }
                    })
                }))
            }));
            return centerImage;
        }

        public function optMovie(designIndex:int, opt:String, encCallback:Function = null):Image
        {
            var outPaiMovie:Sprite = this["outPaiMovie" + designIndex];
            var targetX:Number = this["nOutPaiMovie" + designIndex + "X"];
            var targetY:Number = this["nOutPaiMovie" + designIndex + "Y"];

            var skin:String = "ui/game/" + opt + ".png";


            var image:Image = new Image();
            image.skin = skin;
            image.pivot(image.width / 2, image.height / 2);

            image.x = targetX + outPaiMovie.width / 2;
            image.y = targetY + outPaiMovie.height / 2;

            var centerImage:Image = new Image();
            centerImage.skin = skin;
            centerImage.pivot(image.pivotX, image.pivotY);

            centerImage.x = image.x;
            centerImage.y = image.y;

            centerImage.filters = [glowFilter];
            image.filters = [glowFilter];


            movieSprite.addChild(image);
            movieSprite.addChild(centerImage);

            image.alpha = 0.8;
            _targetPoll.add(image);

            Tween.to(image, {
                scaleX: 3,
                scaleY: 3,
                alpha: 0.4
            }, 1000, null, Handler.create(this, function ():void
            {
                Tween.to(image, {
                    alpha: 0
                }, 500, null, Handler.create(this, function ():void
                {
                    _targetPoll.remove(image);
                }))
            }));

            Tween.to(centerImage, {
                scaleX: 1.5,
                scaleY: 1.5
            }, 1000, null, Handler.create(this, function ():void
            {
                Tween.to(centerImage, {
                    scaleX: 1,
                    scaleY: 1
                }, 500, null, Handler.create(this, function ():void
                {
                    Laya.timer.once(500, this, function ():void
                    {
                        image.skin = null;
                        image.removeSelf();
                        image.dispose();
                        centerImage.skin = null;
                        centerImage.removeSelf();
                        centerImage.dispose();

                        _targetPoll.remove(centerImage);

                        if (encCallback)
                        {
                            encCallback();
                        }
                    })
                }))
            }));
            return centerImage;
        }

        private var outPaiMoviePrev:Sprite;


        public function outMovie(designIndex:int, pai:int):void
        {
//            Console.log("outMovie:{0}", designIndex);


            var shouPai:Sprite = this["shouPai" + designIndex];
            var outPaiMovie:Sprite = this["outPaiMovie" + designIndex];
            if (outPaiMoviePrev != null)
            {
                outPaiMoviePrev.visible = false;
            }
            outPaiMoviePrev = outPaiMovie;
            var fa:Sprite = shouPai.getChildAt(shouPai.numChildren - 1) as Sprite;
            if (!fa)
            {
                Console.log("错误？：{0}", fa);
                return;
            }

            var targetX:Number = this["nOutPaiMovie" + designIndex + "X"];
            var targetY:Number = this["nOutPaiMovie" + designIndex + "Y"];

            outPaiMovie.visible = true;


            startPoint.setTo(0, 0);
            startRB.setTo(fa.width, fa.height);

//            Console.log("outMovie1:{0},{1}", startPoint, startRB);

            fa.localToGlobal(startPoint);
            fa.localToGlobal(startRB);

//            Console.log("outMovie2:{0},{1}", startPoint, startRB);
            movieSprite.globalToLocal(startPoint);
            movieSprite.globalToLocal(startRB);

//            Console.log("outMovie3:{0},{1}", startPoint, startRB);

            startRB.setTo(startRB.x - startPoint.x, startRB.y - startPoint.y);
//            Console.log("outMovie:{0}", startRB);

            var scale:Number = Math.abs(startRB.x / outPaiMovie.width);
            outPaiMovie.scale(scale, scale);
            outPaiMovie.pos(startPoint.x, startPoint.y);

            showPaiIcon(outPaiMovie.getChildAt(0).getChildAt(0) as Image, pai);

            Tween.to(outPaiMovie, {
                x: targetX,
                y: targetY,
                scaleX: nOutPaiMovieRatio,
                scaleY: nOutPaiMovieRatio
            }, 0, null, Handler.create(this, function ():void
            {
//                Laya.timer.once(1000, this, function ():void
//                {
////                    outPaiMovie.visible = false;
//                })
            }));
        }

        public function hideOutMovie():void
        {
            if (outPaiMoviePrev)
            {
                var close:Sprite = outPaiMoviePrev;
                outPaiMoviePrev = null;
                Laya.timer.once(1000, this, function ():void
                {
                    close.visible = false;
                });
            }

        }
    }
}
