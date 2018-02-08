/**
 * @author zuoge85@gmail.com on 16/10/12.
 */
package mj.ui
{
    import laya.display.Sprite;
    import laya.display.Text;
    import laya.maths.Rectangle;
    import laya.utils.Ease;
    import laya.utils.Handler;
    import laya.utils.Tween;

    public class HorseLamp extends Sprite
    {
        private var _rect:Sprite;
        private var _txt:Text;
        private var _rectangle:Rectangle;

        public function HorseLamp()
        {
            super();

        }

        private var _tween:Tween;
        private var arr:Array = [];

        public function set setInfo(str:String):void
        {
            if (_tween)
            {
                arr.push(str);
                return;
            }
            if (!_rectangle)
            {
                _rectangle = new Rectangle(0, 0, this.width, this.height);
                this.scrollRect = _rectangle;
            }
            if (!_rect)
            {
                _rect = new Sprite();
                _rect.graphics.drawRect(0, 0, this.width, this.height, "#000000");
                _rect.alpha = 0.5;
                this.addChild(_rect);
            }
            if (!_txt)
            {
                _txt = new Text();
                _txt.fontSize = 20;
                _txt.color = "#ffffff";
                this.addChild(_txt);
            }
            _txt.text = str;
            _txt.pos(this.width, 8);

            start();
        }

        private function start():void
        {
            _tween = Tween.to(_txt, {x: -1000}, 10000, Ease.linearNone, Handler.create(this, overHandler));
        }

        private function overHandler():void
        {
            Laya.timer.once(10000, this, timeOverHandler);
        }

        private function timeOverHandler():void
        {
            _tween = null;
            _txt.text = "";
            if (arr.length > 0)
            {
                nextNotice();
                return;
            }
            this.removeSelf();
        }

        private function nextNotice():void
        {
            setInfo = arr[0];
            arr.shift();
        }
    }
}
