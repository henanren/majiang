package mj.utils
{
    import laya.utils.Dictionary;
    import laya.utils.Tween;

    /**
     * @author zuoge85@gmail.com on 2016/12/29.
     */
    public class TweenTargetPoll
    {
        private var tweenTargetDict:Dictionary = new Dictionary();

        public function TweenTargetPoll()
        {
        }


        public function add(target:*):void
        {
            tweenTargetDict.set(target, this);
        }

        public function remove(obj:*):void
        {
            tweenTargetDict.remove(obj);
        }

        public function clear():void
        {
            var values:Array = tweenTargetDict.values;
            for (var i:int = 0; i < values.length; i++)
            {
                var object:Object = values[i];
                Tween.clearAll(object);
            }
            tweenTargetDict.clear();
        }
    }
}
