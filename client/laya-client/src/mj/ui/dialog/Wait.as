package mj.ui.dialog
{
    import laya.display.Animation;
    import laya.events.Event;
    import laya.maths.Rectangle;
    import laya.utils.Browser;

    import mj.manager.UiManager;
    import mj.ui.*;

    import ui.test.WaitUI;

    /**
     * @author zuoge85@gmail.com on 16/10/10.
     */
    public class Wait extends WaitUI
    {
        private var _message:String;
        private var _waitAni:Animation;

        public function Wait(message:String)
        {
            super();

            Laya.stage.on(Event.RESIZE, this, layout);
            Laya.stage.on(Event.ADDED, this, layout);
            layout();

            this.message = message;

            _waitAni = new Animation();
            _waitAni.source = "res/atlas/movie/wait.json";
            _waitAni.interval = 60;			// 设置播放间隔（单位：毫秒）
            _waitAni.loop = false;
            _waitAni.scale(2, 2);
            _waitAni.x = main.width / 2;
            _waitAni.y = main.height / 2;
            var bounds:Rectangle = _waitAni.getGraphicBounds();

            _waitAni.pivot(bounds.width / 2, bounds.height * 0.7);

            _waitAni.play(0, true);
            main.addChild(_waitAni);
//            Laya.timer.frameLoop(1, this, iconTurn);
        }


        public function set message(message:String):void
        {
            _message = message;
            text.text = message;
        }

        public function layout():void
        {
            var bWidth:Number = Browser.width;
            var bHeight:Number = Browser.height;
            var ratio:Number = UiHelps.getRatio();

            this.x = (bWidth - this.width * ratio) / 2;
            this.y = (bHeight - this.height * ratio) / 2;


            this.scale(ratio, ratio);
        }

//        private var turnValue:Number = 0;

//        private function iconTurn():void
//        {
//            icon.rotation += Laya.timer.delta / 7;
//        }

        override public function destroy(destroyChild:Boolean = true):void
        {
            super.destroy(destroyChild);
//            Laya.timer.clear(this, iconTurn);
            Laya.stage.off(Event.RESIZE, this, layout);
        }

        private static var wait:Wait;

        public static function show(message:String = "正在加载"):void
        {
            if (wait != null)
            {
                close();
            }
            wait = new Wait(message);
//            wait.popupCenter = false;
//            wait.popup();
            UiManager.instance.showMask();
            UiManager.instance.topLayer.addChild(wait)
        }

        public static function close():void
        {
            if (wait)
            {
                wait.close();
                wait = null;
            }
        }

        public override function close(type:String = null):void
        {
            Laya.timer.once(300, this, function ():void
            {
                UiManager.instance.hideMask();
                superClose(type);
                destroy(true);
            });
        }

        private function superClose(type:String = null):void
        {
            super.close(type);
        }
    }
}
