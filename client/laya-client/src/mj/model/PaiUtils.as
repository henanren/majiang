package mj.model
{
    import laya.ui.Image;

    import mj.manager.GameManager;
    import mj.net.message.game.GameFanResult;
    import mj.net.message.game.UserPlaceMsg;

    /**
     * @author zuoge85@gmail.com on 2016/11/1.
     */
    public class PaiUtils
    {

        /**
         * 有牌，但是不知道是啥
         */
        public static const HAS_PAI_INDEX:int = -2;
        public static const NOT_PAI_INDEX:int = -1;

        public function PaiUtils()
        {
        }


        public static function toString(pai:int):String
        {
            if (pai < 9)
            {
                return (pai + 1) + "筒"
            }
            else if (pai < 18)
            {
                return (pai - 8) + "条"
            }
            else if (pai < 27)
            {
                return (pai - 17) + "万"
            }
            else
            {
                switch (pai)
                {
                    case 27:
                        return "東";
                    case 28:
                        return "南";
                    case 29:
                        return "西";
                    case 30:
                        return "北";
                    case 31:
                        return "中";
                    case 32:
                        return "發";
                    case 33:
                        return "白";
                }
            }
            return null;
        }

        public static function checkIsHuiEr(pai:int):Boolean
        {
            var huiEr:Array = GameManager.instance.huiEr;
            if (huiEr == null || huiEr.length == 0)
            {
                return false;
            }
            for (var i:int = 0; i < huiEr.length; i++)
            {
                var huierItem:int = huiEr[i];
                if (huierItem == pai)
                {
                    return true;
                }
            }
            return false;
        }

        public static function showPaiIcon(paiImage:Image, pai:int):void
        {
            if (checkIsHuiEr(pai))
            {
//                ui/majiang/zheng_19.png
                var huiErImage:Image = new Image();
                huiErImage.skin = paiImage.skin.replace(/[0-9]+\.png/gi, "hun.png");
                paiImage.addChild(huiErImage);
            }
            paiImage.skin = paiImage.skin.replace(/[0-9]+\.png/gi, pai.toString() + ".png");
        }

        public static function toUserPlace(result:GameFanResult):UserPlaceMsg
        {
            var m:UserPlaceMsg = new UserPlaceMsg();

            if (result.queTou != PaiUtils.NOT_PAI_INDEX)
            {
                var shouPai:Array = [];
                var i:int;
                var pai:int;

                for (i = 0; i < result.shunZi.length; i++)
                {
                    pai = result.shunZi[i];
                    shouPai.push(pai, pai + 1, pai + 2);
                }

                for (i = 0; i < result.keZi.length; i++)
                {
                    pai = result.keZi[i];
                    shouPai.push(pai, pai, pai);
                }

                shouPai.push(result.queTou, result.queTou);

                m.shouPai = shouPai;
            }
            else
            {
                m.shouPai = result.shouPai;
            }

            m.anGang = result.anGang;
            m.daMingGang = result.daMingGang;
            m.xiaoMingGang = result.xiaoMingGang;
            m.chi = result.chi;
            m.peng = result.peng;
            return m;
        }
    }
}
