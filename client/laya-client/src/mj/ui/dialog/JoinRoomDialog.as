/**
 * @author zuoge85@gmail.com on 16/10/12.
 */
package mj.ui.dialog
{
    import laya.display.Node;
    import laya.display.Sprite;
    import laya.events.Event;
    import laya.ui.Image;
    import laya.utils.Handler;

    import ui.test.JoinRoomUI;

    public final class JoinRoomDialog extends JoinRoomUI implements DialogContent
    {
        private static const INPUT_MAX:int = 6;

        public static function showDialog(confirmCallback:Function):Dialog
        {
            var joinRoomDialog:JoinRoomDialog = new JoinRoomDialog(confirmCallback);
            var createDialog:Dialog = Dialog.showDialog(
                    DialogSize.AUTO, joinRoomDialog,
                    "进入房间", null,
                    Handler.create(joinRoomDialog, joinRoomDialog.clearInput), "重输",
                    Handler.create(joinRoomDialog, joinRoomDialog.backInput), "删除"
            );
            return createDialog;
        }

        private var _input:String = "";
        private var confirmCallback:Function;

        public function JoinRoomDialog(confirmCallback:Function)
        {
            super();
            this.confirmCallback = confirmCallback;
            for (var i:int = 0; i < inputSprite.numChildren; i++)
            {
                var child:Node = inputSprite.getChildAt(i);
                child.on(Event.CLICK, this, onInputClick, [i]);
            }
            onInputChange();
        }


        private function onInputClick(index:Number):void
        {
            if (_input.length < INPUT_MAX)
            {
                _input += index;
                if (_input.length == INPUT_MAX)
                {
                    confirmCallback(_input);
                }
            }
            onInputChange();
        }

        public function clearInput():void
        {
            _input = "";
            onInputChange();
        }

        public function backInput():void
        {
            if (_input.length > 0)
            {
                _input = _input.substring(0, _input.length - 1);
            }
            onInputChange();
        }

        private function onInputChange():void
        {
            for (var i:int = 0; i < INPUT_MAX; i++)
            {
                var inputChar:String = i < _input.length ? _input.charAt(i) : null;
                var node:Image = Image(inputShow.getChildAt(i));

                var numberImage:Image = Image(node.getChildAt(0));
                if (inputChar == null)
                {
                    numberImage.visible = false;
                }
                else
                {
                    numberImage.visible = true;
                    numberImage.skin = "base/number/win/" + inputChar + ".png";
                }
            }
        }


        public function get input():String
        {
            return _input;
        }

        public function layout():void
        {
        }


        public function getDisplayObject():Sprite
        {
            return this;
        }

        public function getContentWidth():Number
        {
            return mainSprite.width;
        }

        public function getContentHeight():Number
        {
            return mainSprite.height;
        }


    }
}