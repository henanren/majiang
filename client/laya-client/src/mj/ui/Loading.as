package mj.ui
{
    import com.isnowfox.core.SingletonError;

    import laya.events.Event;
    import laya.utils.Browser;

    import mj.manager.UiManager;

    import ui.test.LoadingUI;

    /**
     * 加载界面,单例!
     * @author zuoge85@gmail.com on 16/10/11.
     */
    public class Loading extends LoadingUI
    {
        public static const instance:Loading = new Loading();

        private const vector:Array = [];
        private var nWidth:Number;
        private var nHeight:Number;
        private var nProgressBarWidth:Number;
        private var nProgressBarHeigth:Number;

        public function Loading()
        {
            if (instance != null)
            {
                throw new SingletonError("ResourceManager 是单例模式");
            }
            Laya.stage.on(Event.RESIZE, this, layout);

            nWidth = bg.width;
            nHeight = bg.height;
            nProgressBarWidth = progressBar.width;
            nProgressBarHeigth = progressBar.height;
            layout();
        }

        private function layout():void
        {
            var bWidth:Number = Browser.width;
            var bHeight:Number = Browser.height;
            var wRatio:Number = bWidth / nWidth;
            var hRatio:Number = bHeight / nHeight;
            //使用最大比例尽量覆盖背景
            var ratio:Number = wRatio > hRatio ? wRatio : hRatio;
            bg.scale(ratio, ratio);
            var width:Number = bg.width * ratio;
            var height:Number = bg.height * ratio;

            bg.x = (bWidth - width) / 2;
            bg.y = (bHeight - height) / 2;

            var progressWidth:Number = bWidth * 0.7;
            progressWidth = progressWidth > nProgressBarWidth ? nProgressBarWidth : progressWidth;

            progressBar.width = progressWidth;
            progressBar.x = -(progressWidth - bWidth) / 2;
            progressBar.y = bHeight * 0.9 - nProgressBarHeigth;
            progressBarLabel.width = progressWidth;
            progressBarLabel.x = progressBar.x;
            progressBarLabel.y = progressBar.y + 2;
            progress = 0;

            this.size(width, height);
        }

        public function set progress(progress:Number):void
        {
            progressBar.value = progress;
            if (progress == 1.0)
            {
                progressBarLabel.text = "加载完成等待初始化";
            }
            else
            {
                progressBarLabel.text = "加载中 " + Math.ceil(progress * 100) + "%";
            }
        }

        public function show():void
        {
            UiManager.instance.topLayer.addChild(this);
        }

        public function hide():void
        {
            UiManager.instance.topLayer.removeChild(this);
        }

        override public function destroy(destroyChild:Boolean = true):void
        {
            super.destroy(destroyChild);
            Laya.stage.off(Event.RESIZE, this, layout);
        }
    }
}
