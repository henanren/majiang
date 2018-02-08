/**
 * @author zuoge85@gmail.com on 16/10/13.
 */
package mj.ui
{
    import laya.events.Event;
    import laya.utils.Browser;

    public class UiHelps
    {
        public static const DESIGN_WIDTH:Number = 1902;
        public static const DESIGN_HEIGHT:Number = 1080;

        private static var ratio:Number = 0;
        private static var gameRatio:Number = 0;
        private static var gameRatioX:Number = 0;
        private static var gameRatioY:Number = 0;

        private static function onResize():void
        {
            ratio = Math.min(Math.max(Browser.width / DESIGN_WIDTH, 0.3), 10);
            gameRatio = Math.min(Math.max(Browser.width / DESIGN_WIDTH, 0), 10);
            gameRatioX = Math.min(Math.max(Browser.width / DESIGN_WIDTH, 0), 10);
            gameRatioY = Math.min(Math.max(Browser.height / DESIGN_HEIGHT, 0), 10);
        }

        /**
         * 设计和真实宽度的映射比率
         * @return
         */
        public static function getRatio():Number
        {
            if (ratio == 0)
            {
                init();
            }
            return ratio;
        }

        public static function getGameRatio():Number
        {
            if (gameRatio == 0)
            {
                init();
            }
            return gameRatio;
        }

        public static function getGameRatioX():Number
        {
            if (gameRatioX == 0)
            {
                init();
            }
            return gameRatioX;
        }

        public static function getGameRatioY():Number
        {
            if (gameRatioY == 0)
            {
                init();
            }
            return gameRatioY;
        }

        private static function init():void
        {
            Laya.stage.on(Event.RESIZE, null, onResize);
            onResize();
        }

//
//        /**
//         * 设计和真实高度的映射比率
//         * @return
//         */
//        public static function getHeightRatio():Number
//        {
//            return Browser.height / DESIGN_HEIGHT;
//        }
    }
}
