package mj.ui.dialog
{
    import laya.display.Node;
    import laya.display.Sprite;
    import laya.ui.Image;
    import laya.ui.Label;
    import laya.utils.Handler;

    import mj.manager.AudioManager;
    import mj.manager.GameManager;
    import mj.model.PaiUtils;
    import mj.net.message.game.GameChapterEnd;
    import mj.net.message.game.GameFanResult;
    import mj.net.message.game.GameRoomInfo;
    import mj.net.message.game.GameUserInfo;
    import mj.net.message.game.UserPlaceMsg;
    import mj.utils.LayaUtils;

    import ui.test.ChapterResultUI;

    /**
     * @author zuoge85@gmail.com on 2016/11/9.
     */
    public class ChapterResultDialog extends ChapterResultUI implements DialogContent
    {
        protected static const H_PAI_SPACE:int = 4;
        protected static const V_PAI_SPACE:int = 0;

        private static var dialog:Dialog;

        public static function showDialog(msg:GameChapterEnd, gameRoomInfo:GameRoomInfo, confirmHandler:Handler):Dialog
        {
            if (dialog)
            {
                close();
            }
            var chapterResultDialog:ChapterResultDialog = new ChapterResultDialog(msg, gameRoomInfo);


            dialog = Dialog.showDialog(
                    DialogSize.AUTO, chapterResultDialog, "", null, confirmHandler, "确定"
            );


            return dialog;
        }

        public static function close():void
        {
            if (dialog)
            {
                dialog.close();
                dialog = null;
            }
        }


        private var nWidth:Number;
        private var nHeight:Number;
        private var _gameChapterEnd:GameChapterEnd;
        private var _gameRoomInfo:GameRoomInfo

        public function ChapterResultDialog(msg:GameChapterEnd, gameRoomInfo:GameRoomInfo)
        {
            _gameChapterEnd = msg;
            _gameRoomInfo = gameRoomInfo;
            super();
            nWidth = this.width;
            nHeight = this.height;
        }

        private var paiSpace:Array = [0, 0, 0, 0];

        public function layout():void
        {
            title.scaleX = 0.85;
            title.scaleY = 0.85;
            title.x = (nWidth - title.width * 0.8) / 2;
            title.y = -title.height * 0.85;


            chi3.removeSelf();
            anGang3.removeSelf();
            mingGang3.removeSelf();
            anGangHide3.removeSelf();
            shou3.removeSelf();
            fa3.removeSelf();


            init();
            initZaMa();
        }

        private function initZaMa():void
        {
            var zaMaType:Number = _gameChapterEnd.zaMaType;
            if (zaMaType == 0)
            {

                this.height -= zaMaSprite.height;
                otherScore.y -= zaMaSprite.height;
                zaMaSprite.removeSelf();
            }
            else
            {
                var zaMaFan:int = _gameChapterEnd.zaMaFan;
                yiMaScore.text = "+" + zaMaFan;

                if (zaMaType == -1)
                {
                    yiMaType.text = "一马全中"

                }
                else
                {
                    yiMaType.text = "买" + zaMaType + "马"
                }
                if (zaMaFan > 0)
                {
                    yiMaWu.removeSelf();
                }

                maPai.removeSelf();

                var x:Number = 0;
                for (var i:int = 0; i < _gameChapterEnd.zaMaPai.length; i++)
                {
                    var pai:int = _gameChapterEnd.zaMaPai[i];

                    var paiImage:Image = Image(LayaUtils.clone(maPai));

                    showPaiIcon(Image(paiImage.getChildAt(0)), pai);
                    paiImage.x = x + 2;
                    maPaiSprite.addChild(paiImage);
                    x += maPai.width;
                }
                maPaiSprite.x += ((((maPai.width + 2) * 8) - x) / 2);
            }
        }

        private function init():void
        {
//
            var gameManager:GameManager = GameManager.instance;
            clearPai(3);
            paiSpace = [0, 0, 0, 0];


            var fanResults:Array = _gameChapterEnd.fanResults;
            if (_gameChapterEnd.huPaiIndex > -1)
            {
                liuju.visible = false;
                var winResult:GameFanResult = fanResults[_gameChapterEnd.huPaiIndex];

                showPai(3, PaiUtils.toUserPlace(winResult), true);
                winName.text = winResult.userName;
                winFanString.text = winResult.fanString;

                if (_gameChapterEnd.zaMaFan)
                {
                    winFan.text = "(" + (winResult.fan - _gameChapterEnd.zaMaFan )
                                  + "番 + 中码" + _gameChapterEnd.zaMaFan + "番)";
                }
                else
                {
                    winFan.text = "(" + winResult.fan + "番)";
                }
                if(winResult.guaFengXiaYu != 0){
                    winFan.text += "(杠:" + winResult.guaFengXiaYu +")";
                }

                winScore.text = "+" + winResult.score;

                if (_gameChapterEnd.huPaiIndex != gameManager.myLocationIndex)
                {
                    if (_gameChapterEnd.fangPaoIndex == -1 || _gameChapterEnd.fangPaoIndex == gameManager.myLocationIndex)
                    {
                        //肯定输了
                        titleIcon.skin = "ui/game/result_title_lost.png";
                        AudioManager.lose();
                    }
                    else
                    {
                        titleIcon.skin = "ui/game/result_title_nor.png";
                        AudioManager.liuJu();
                    }
                }
                else
                {
                    if (_gameChapterEnd.huPaiIndex == -1)
                    {
                        titleIcon.skin = "ui/game/result_title_nor.png";
                        AudioManager.liuJu();
                    }
                    else
                    {
                        var sceneUser:GameUserInfo = _gameRoomInfo.sceneUser[_gameChapterEnd.huPaiIndex];

                        AudioManager.win(sceneUser.sex, _gameChapterEnd.fangPaoIndex, winResult.fan);
                    }
                }

                for (var i:int = 0, j:int = 0; i < fanResults.length; i++)
                {


                    if (i != _gameChapterEnd.huPaiIndex)
                    {

                        var result:GameFanResult = fanResults[i];

                        this["name" + j].text = result.userName;
                        var score:Label = this["score" + j];

                        if (_gameChapterEnd.fangPaoIndex < 0)
                        {
                            score.text = "" + result.score;
                        }
                        else
                        {
                            if (_gameChapterEnd.fangPaoIndex == i)
                            {
                                score.text = "" + result.score;
                            }
                            else
                            {
                                score.text = "0";
                            }
                        }
                        if(result.guaFengXiaYu != 0){
                            score.text += "(杠:" + result.guaFengXiaYu +")";
                        }
                        j++;
                    }
                }
            }
            else
            {
                //慌局
                titleIcon.skin = "ui/game/result_title_lost.png";
                AudioManager.lose();
                shouPai3.visible = false;
                otherScore.visible = false;
                winSprite.visible = false;
                this.height = 100;
            }
        }

        /**
         * 清除牌
         */
        private function clearPai(index:int):void
        {
            var i:int;

            var shouPai:Sprite = this["shouPai" + index];
            while (shouPai.numChildren > 0)
            {
                shouPai.getChildAt(0).removeSelf().destroy(true);
            }
        }

        public function getDisplayObject():Sprite
        {
            return this;
        }

        public function getContentWidth():Number
        {
            return nWidth * this.scaleX;
        }

        public function getContentHeight():Number
        {

            var zaMaType:Number = _gameChapterEnd.zaMaType;
            if (zaMaType == 0)
            {
                return (nHeight - zaMaSprite.height) * this.scaleY;
            }
            else
            {
                return nHeight * this.scaleY;
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
                }
            }
        }

        public static function checkIsFaPai(len:int):Boolean
        {
            //3*n+2;
            return (len - 2) % 3 == 0;
        }


        private function showShouPaiGroup(index:int, pais:Array, source:Sprite, isHorizontal:Boolean):void
        {
            for (var i:int = 0; i < pais.length; i++)
            {
                var paiSprite:Sprite = showPaiGroup(index, pais[i], source, isHorizontal, isHorizontal ? 1 : -80);
            }
        }

        private var prevPaiSprite:Sprite;
        private var dragStartSprite:Sprite;
        private var dragStartX:Number = -1;


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

    }
}
