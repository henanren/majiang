package mj.manager
{
    import com.isnowfox.core.SingletonError;
    import com.isnowfox.core.test.AssertUtils;

    import laya.utils.Handler;

    import mj.GameObserver;
    import mj.model.PaiUtils;
    import mj.net.Net;
    import mj.net.message.game.GameChapterEnd;
    import mj.net.message.game.GameRoomInfo;
    import mj.net.message.game.GameUserInfo;
    import mj.net.message.game.MajiangChapterMsg;
    import mj.net.message.game.OperationCPGH;
    import mj.net.message.game.OperationCPGHRet;
    import mj.net.message.game.OperationFaPai;
    import mj.net.message.game.OperationFaPaiRet;
    import mj.net.message.game.OperationOut;
    import mj.net.message.game.OperationOutRet;
    import mj.net.message.game.SyncOpt;
    import mj.net.message.game.SyncOptTime;
    import mj.net.message.game.UserPlaceMsg;
    import mj.scene.GameTableScene;
    import mj.ui.dialog.ChapterResultDialog;
    import mj.ui.dialog.OptDialog;
    import mj.utils.ArrayUtils;

    /**
     * @author zuoge85@gmail.com on 16/10/3.
     */
    public class GameManager
    {
        public static const OPT_OUT:String = "OUT";
        public static const OPT_OUT_OK:String = "OUT_OK";
        public static const OPT_CHI:String = "CHI";
        public static const OPT_PENG:String = "PENG";
        public static const OPT_AN_GANG:String = "AN_GANG";
        public static const OPT_XIAO_MING_GANG:String = "XIAO_MING_GANG";
        public static const OPT_DA_MING_GANG:String = "DA_MING_GANG";
        public static const OPT_HU:String = "HU";
        public static const OPT_FA:String = "FA";
        public static const OPT_GUO:String = "GUO";

        public static const instance:GameManager = new GameManager();


        private var _gameRoomInfo:GameRoomInfo;
        private var _gameTableScene:GameTableScene;
        private var _isMyCreate:Boolean;
        private var _myLocationIndex:Number = -1;

        /**
         * index to DesignIndex;
         */
        private var _indexMap:Array = [];

        /**
         * DesignIndex to index;
         */
        private var _designIndexMap:Array = [];

        private var _huiEr:Array = null;

        private var observer:GameObserver;

        public function GameManager()
        {
            if (instance != null)
            {
                throw new SingletonError("GameManager 是单例模式");
            }
            observer = new GameObserver();
        }

        private static const MY_INDEX:int = 3;


        public function isMyCreate():Boolean
        {
            if(_gameRoomInfo == null){
                return false;
            }
            return _gameRoomInfo.createUserId == UserManager.instance.user.id;
        }

        public function goGame(gameRoomInfo:GameRoomInfo):void
        {
            var chapter:MajiangChapterMsg = gameRoomInfo.chapter;
            this.chapter = chapter;
            AudioManager.instance.playGame();
            _gameRoomInfo = gameRoomInfo;
            UserManager.instance.joinRoomEnd();
            _isMyCreate = isMyCreate();
            _gameTableScene = new GameTableScene(_isMyCreate, observer);
            UiManager.instance.goScene(_gameTableScene);
            _gameTableScene.update(gameRoomInfo);


            var roomCheckId:String = GameManager.instance.roomInfo().roomCheckId;
            __JS__("if(window.initInviteUser) window.initInviteUser(roomCheckId);");

            var i:int, j:int;
            var userInfo:GameUserInfo;
            for (i = 0; i < gameRoomInfo.sceneUser.length; i++)
            {
                userInfo = gameRoomInfo.sceneUser[i];
                if (userInfo.userId == UserManager.instance.user.id)
                {
                    _myLocationIndex = userInfo.locationIndex;
                }
            }

            initIndexMap();

            for (i = 0; i < gameRoomInfo.sceneUser.length; i++)
            {
                handlerUserInfo(gameRoomInfo.sceneUser[i]);
            }


            if (chapter)
            {
                updateChapter(chapter);
                _gameTableScene.initZhuang(chapter);

                if (chapter.optCpgh)
                {
                    optCPGH(chapter.optCpgh);
                }

                if (chapter.optFaPai)
                {
                    optFaPai(chapter.optFaPai);
                }

                if (chapter.optOut)
                {
                    optOut(chapter.optOut);
                }
                if (chapter.syncOptTime)
                {
                    syncOptTime(chapter.syncOptTime)
                }

                if(chapter.gameChapterEnd)
                {
                    this.endChapter(chapter.gameChapterEnd);
                }

                if(chapter.tingPai)
                {
                    this.tingPai(chapter.tingPai.pais);
                }
            }
        }

        public function roomInfo():GameRoomInfo
        {
            return _gameRoomInfo;
        }

        private var chapter:MajiangChapterMsg;


        public function optCPGH(cpgh:OperationCPGH):void
        {
            var pai:int = cpgh.pai;
            var gang:int = cpgh.isGang ? pai : PaiUtils.NOT_PAI_INDEX;
            var peng:int = cpgh.isPeng ? pai : PaiUtils.NOT_PAI_INDEX;
            OptDialog.showCPGH(cpgh.isHu, gang, peng, cpgh.chi,
                    Handler.create(this, function (opt:String, value:Object):void
                    {
                        console.log(opt, value);
                        OptDialog.close();

                        var ret:OperationCPGHRet = new OperationCPGHRet();
                        if (value is Array)
                        {
                            ret.chi = value as Array;
                        }
                        ret.opt = opt;
                        Net.instance.write(ret);
                    }, null, true)
            );
        }

        public function optFaPai(faPai:OperationFaPai):void
        {
            if (faPai.hu || !ArrayUtils.isEmpty(faPai.anGang) || !ArrayUtils.isEmpty(faPai.mingGang))
            {
                OptDialog.showFaPai(faPai.hu, faPai.anGang, faPai.mingGang,
                        Handler.create(this, function (opt:String, value:Object):void
                        {
                            observer.onButtonClick();
                            console.log(opt, value);
                            OptDialog.close();

                            if (OPT_GUO != opt)
                            {
                                var ret:OperationFaPaiRet = new OperationFaPaiRet();
                                ret.pai = value as int;
                                ret.opt = opt;
                                Net.instance.write(ret);
                            }
                            else
                            {
                                startOut(false);
                            }
                        }, null, true)
                );
            }
            else
            {
                startOut(false);
            }
        }

        public function optOut(out:OperationOut):void
        {
            startOut(true);
        }

        private var isOut:Boolean = false;
        private var isOptOut:Boolean = false;

        private function startOut(isOptOut:Boolean):void
        {
            this.isOptOut = isOptOut;
            this.isOut = true;
            _gameTableScene.isWaitOut = true;
        }

        private function endOut():void
        {
            this.isOut = false;
            _gameTableScene.isWaitOut = false;
        }

        public function putPai(pai:int):Boolean
        {
            if (isOut)
            {
                if (isOptOut)
                {
                    Net.instance.write(OperationOutRet.create(pai));
                }
                else
                {
                    Net.instance.write(OperationFaPaiRet.create(GameManager.OPT_OUT, pai));
                }
                endOut();
                return true;
            }
            else
            {
                return false;
            }
        }

        public function startChapter(chapter:MajiangChapterMsg):void
        {
            _huiEr = chapter.huiEr;
            this.chapter = chapter;
            _gameTableScene.startChapter(chapter);
        }

        public function endChapter(msg:GameChapterEnd):void
        {
            _gameTableScene.stopFaPai(chapter);
            _gameRoomInfo.leftChapterNums = (chapter.chapterNumsMax - chapter.chapterNums) - 1;
//            chapter = null;

            _gameRoomInfo.chapter = null;

            ChapterResultDialog.showDialog(msg, _gameRoomInfo, Handler.create(this, function ():void
            {
                ChapterResultDialog.close();
//                goGame(_gameRoomInfo);
            }));
            _gameTableScene.updateEndResult(msg, _gameRoomInfo);
        }

        public function updateChapter(chapter:MajiangChapterMsg):void
        {
            _huiEr = chapter.huiEr;
            this.chapter = chapter;
            _gameTableScene.updateChapter(chapter);
            _gameTableScene.initZhuang(chapter);
        }

        public function syncOpt(msg:SyncOpt):void
        {
            //FA:发牌，OUT:打牌,OUT_OK:打牌成功，没人用这个哎,CHI:吃,PENG:碰,
            //AN_GANG:暗杠牌,XIAO_MING_GANG:暗杠牌,DA_MING_GANG:暗杠牌,HU:胡牌

            var index:int = msg.index;
            var userPlace:UserPlaceMsg = chapter.userPlace[index];
            var sceneUser:GameUserInfo = _gameRoomInfo.sceneUser[index];
            var sex:int = sceneUser == null ? 1 : sceneUser.sex;
            var pai:int = msg.pai;
            endOut();
            switch (msg.opt)
            {
                case OPT_OUT:
                {
                    _gameTableScene.hideOutMovie();
                    _gameTableScene.outMovie(transformToDesignIndex(index), pai);

                    removeShouPai(userPlace, pai);
                    observer.onPaiOut(sex, pai);
                    break;
                }
                case OPT_OUT_OK:
                {
                    userPlace.outPai.push(pai);

                    break;
                }
                case OPT_CHI:
                {
                    _gameTableScene.hideOutMovie();
                    _gameTableScene.optMovie(transformToDesignIndex(index), "chi");

                    userPlace.chi.push.apply(userPlace.chi, msg.chi);
                    for (var i:int = 0; i < msg.chi.length; i++)
                    {
                        var curPai:int = msg.chi[i];
                        if (curPai != pai)
                        {
                            removeShouPai(userPlace, curPai);
                        }
                    }
                    observer.onChi(sex);
                    break;
                }
                case OPT_PENG:
                {
                    _gameTableScene.hideOutMovie();
                    _gameTableScene.optMovie(transformToDesignIndex(index), "peng");

                    userPlace.peng.push(pai);
                    removeShouPai(userPlace, pai);
                    removeShouPai(userPlace, pai);
                    observer.onPeng(sex);
                    break;
                }
                case OPT_AN_GANG:
                {
                    _gameTableScene.hideOutMovie();
                    _gameTableScene.optMovie(transformToDesignIndex(index), "gang");

                    userPlace.anGang.push(pai);
                    removeShouPai(userPlace, pai);
                    removeShouPai(userPlace, pai);
                    removeShouPai(userPlace, pai);
                    removeShouPai(userPlace, pai);
                    observer.onGang(sex);
                    break;
                }
                case OPT_XIAO_MING_GANG:
                {
                    _gameTableScene.hideOutMovie();
                    _gameTableScene.optMovie(transformToDesignIndex(index), "gang");
                    userPlace.xiaoMingGang.push(pai);
                    removePeng(userPlace, pai);
                    observer.onGang(sex);
                    break;
                }
                case OPT_DA_MING_GANG:
                {
                    _gameTableScene.hideOutMovie();
                    _gameTableScene.optMovie(transformToDesignIndex(index), "gang");
                    userPlace.daMingGang.push(pai);
                    removeShouPai(userPlace, pai);
                    removeShouPai(userPlace, pai);
                    removeShouPai(userPlace, pai);
                    observer.onGang(sex);
                    break;
                }
                case OPT_HU:
                {
                    _gameTableScene.hideOutMovie();
                    _gameTableScene.optMovie(transformToDesignIndex(index), "hu");
                    break;
                }
                case OPT_FA:
                {
                    _gameTableScene.hideOutMovie();
                    addShouPai(userPlace, pai);
                    chapter.freeLength -= 1;
                    observer.onDealCard();
                    break;
                }
                default:
                {
                    throw new Error("未知操作");
                }
            }
            if (!_gameTableScene.isWaitStart())
            {
                updateChapter(chapter);
            }

        }

        private static function removePeng(userPlace:UserPlaceMsg, pai:int):void
        {
            var peng:Array = userPlace.peng;
            if (peng && peng.length > 0)
            {
                var index:int = peng.indexOf(pai);
                peng.splice(index, 1);
            }
        }

        private static function addShouPai(userPlace:UserPlaceMsg, pai:int):void
        {
            var shouPai:Array = userPlace.shouPai;
            if (shouPai && shouPai.length > 0)
            {
                shouPai.push(pai);
            }
            else
            {
                userPlace.shouPaiLen++;
            }
        }

        private static function removeShouPai(userPlace:UserPlaceMsg, pai:int):void
        {
            var shouPai:Array = userPlace.shouPai;
            if (shouPai && shouPai.length > 0)
            {
                var index:int = shouPai.indexOf(pai);
                shouPai.splice(index, 1);
            }
            else
            {
                userPlace.shouPaiLen--;
            }
        }

        public function updateUser(userInfo:GameUserInfo):void
        {
            _gameRoomInfo.sceneUser[userInfo.locationIndex] = userInfo;
            handlerUserInfo(userInfo);
        }


        public function otherDelRoomRet(isEnd:Boolean, isStart:Boolean):void
        {
//            if (!_isMyCreate)
//            {
//                UiManager.instance.goMain();
//            }
            if (_gameTableScene && isStart && isEnd)
            {
                _gameTableScene.gameEnd(isEnd);
            }
            else
            {
                UiManager.instance.goMain();
            }
        }

        public function exitUser(userId:int):void
        {
            for (var i:int = 0; i < _gameRoomInfo.sceneUser.length; i++)
            {
                var userInfo:GameUserInfo = _gameRoomInfo.sceneUser[i];
                if (userInfo.userId == userId)
                {
                    userInfo[i] = null;
                    _gameTableScene.delUserInfo(_indexMap[userInfo.locationIndex]);
                    checkFullUser();
                    return;
                }
            }
        }

        private function handlerUserInfo(userInfo:GameUserInfo):void
        {
            _gameTableScene.updateUserInfo(_indexMap[userInfo.locationIndex], userInfo);
            checkFullUser();
        }

        /**
         * 检查是不是 人到齐了
         */
        private function checkFullUser():void
        {
            if (_gameRoomInfo == null || _gameRoomInfo.sceneUser == null || _gameRoomInfo.sceneUser.length < 4)
            {
                _gameTableScene.updateFullUser(false, _isMyCreate);
                return;
            }
            for (var i:int = 0; i < _gameRoomInfo.sceneUser.length; i++)
            {
                var userInfo:GameUserInfo = _gameRoomInfo.sceneUser[i];
                if (!userInfo || !userInfo.online)
                {
                    _gameTableScene.updateFullUser(false, _isMyCreate);
                    return;
                }
            }
            _gameTableScene.updateFullUser(true, _isMyCreate);
        }

        public function transformToDesignIndex(index:int):int
        {
            return _indexMap[index];
        }

        public function transformToIndex(designIndex:int):int
        {
            return _designIndexMap[designIndex];
        }

        private function initIndexMap():Object
        {
            if (_myLocationIndex == -1)
            {
                throw new Error("错误,第一次同步用户信息,却没有自己");
            }
            //0东 1南 2西 3北 逆时针顺序
            // index to DesignIndex;
            //
            var i:int, j:int;
            for (i = _myLocationIndex, j = 3; j > -1; i++, j--)
            {
                if (i > 3)
                {
                    i = 0;
                }
                _indexMap[i] = j;
                _designIndexMap[j] = i;
            }
            //如果自己的index 是 0
            if (_myLocationIndex == 0)
            {
                AssertUtils.assertTrue(_indexMap[0] == 3);
                AssertUtils.assertTrue(_indexMap[1] == 2);
                AssertUtils.assertTrue(_indexMap[2] == 1);
                AssertUtils.assertTrue(_indexMap[3] == 0);
            }
            //如果自己的index 是 3
            if (_myLocationIndex == 3)
            {
                AssertUtils.assertTrue(_indexMap[3] == 3);
                AssertUtils.assertTrue(_indexMap[0] == 2);
                AssertUtils.assertTrue(_indexMap[1] == 1);
                AssertUtils.assertTrue(_indexMap[2] == 0);
            }


            //    上西1
            //左北0     右南2
            //    下东3


            //    上北
            //左西     右东
            //    下南
        }


        public function get huiEr():Array
        {
            return _huiEr;
        }

        public function get myLocationIndex():Number
        {
            return _myLocationIndex;
        }

        public function syncOptTime(msg:SyncOptTime):void
        {
//            var designIndex:int = transformToDesignIndex(msg.index);
//            Console.log("syncOptTime[designIndex:{0}, index:{1}]", designIndex, msg.index);
            _gameTableScene.syncOptTime(msg.index, msg.leftTime);
        }

        public function offlineUser(index:int):void
        {
            if (index > -1 && index < _gameRoomInfo.sceneUser.length)
            {
                var userInfo:GameUserInfo = _gameRoomInfo.sceneUser[index];
                userInfo.online = false;
                _gameTableScene.offlineUser(transformToDesignIndex(index));
            }
        }

        public function isStart():Boolean
        {
            return chapter != null;
        }

        public function tingPai(pais:Array):void
        {
            if(_gameTableScene)
            {
                _gameTableScene.updateTingPai(pais);
            }
        }
    }
}
