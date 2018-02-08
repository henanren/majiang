package mj.utils
{
    import laya.display.Node;
    import laya.display.Sprite;
    import laya.events.Event;
    import laya.ui.Button;
    import laya.ui.Image;

    /**
     * @author zuoge85@gmail.com on 2016/10/28.
     */
    public class LayaUtils
    {
        public function LayaUtils()
        {
        }

        public static function clone(node:Node):Node
        {
            var i:int;
            if (node is Image)
            {
                var image:Image = Image(node);
                var distImage:Image = new Image();
                copyAttri(distImage, image);

                distImage.skin = image.skin;
                distImage.sizeGrid = image.sizeGrid;

                for (i = 0; i < image.numChildren; i++)
                {
                    distImage.addChild(clone(image.getChildAt(i)));
                }
                return distImage;
            }
            else if (node is Sprite)
            {
                var sprite:Sprite = Sprite(node);
                var distSprite:Sprite = new Sprite();
                copyAttri(distSprite, sprite);

                for (i = 0; i < node.numChildren; i++)
                {
                    distSprite.addChild(clone(node.getChildAt(i)));
                }
                return distSprite;
            }
            else
            {
                throw new Error("不支持的类型:" + typeof(node));
            }
        }

        private static function copyAttri(dist:Sprite, sprite:Sprite):void
        {
            dist.pos(sprite.x, sprite.y);
            dist.size(sprite.width, sprite.height);
            dist.scale(sprite.scaleX, sprite.scaleY);
            dist.pivot(sprite.pivotX, sprite.pivotY);
            dist.skew(sprite.skewX, sprite.skewY);
        }

        public static function handlerButton(btn:Button):void
        {
            btn.pivot(btn.width / 2, btn.height / 2);
            btn.pos(btn.x + btn.width / 2, btn.y + btn.height / 2);

            btn.on(Event.MOUSE_DOWN, btn, function ():void
            {
                btn.scale(0.85, 0.85);
            });
            btn.on(Event.MOUSE_UP, btn, function ():void
            {
                btn.scale(1, 1);
            });
            btn.on(Event.MOUSE_OUT, btn, function ():void
            {
                btn.scale(1, 1);
            });
        }

        public static function random(max:int):int
        {
            return Math.floor(Math.random() * max);
        }
    }
}
