/**
 * @author zuoge85@gmail.com on 16/10/12.
 */
package mj.ui.dialog
{
    import laya.display.Node;
    import laya.display.Sprite;
    import laya.events.Event;
    import laya.utils.Handler;

    import mj.net.Net;
    import mj.net.message.login.SendAuthCode;

    import ui.test.PhoneDialogUI;

    public final class PhoneDialog extends PhoneDialogUI implements DialogContent
    {
        private static var dialog:Dialog;
        public static var phoneDialog:PhoneDialog;

        public static function showDialog(confirmHandler:Handler):Dialog
        {
            if (dialog == null)
            {
                phoneDialog = new PhoneDialog(confirmHandler);

                dialog = Dialog.showDialog(
                        DialogSize.AUTO, phoneDialog, "", null, null, null
                );
                phoneDialog.setDialog(dialog)
            }
            else
            {
                if (dialog.confirmHandler)
                {
                    dialog.confirmHandler.recover();
                }
                dialog.confirmHandler = confirmHandler;
                dialog.popup(true);
            }
            phoneDialog.gotoOne();
            return dialog;
        }

        public static function close():void
        {
            if (dialog)
            {
                dialog.close();
            }
        }

        private var codeBtnText:String;
        private var _dialog:Dialog;
        private var confirmHandler:Handler;

        public function PhoneDialog(confirmHandler:Handler)
        {
            super();
            this.confirmHandler = confirmHandler;

            phoneInput.on(Event.INPUT, this, function ():void
            {
                codeBtn.disabled = phoneInput.text.length === 0;
                stopCodeBtn();
            });
            codeBtn.disabled = true;
            phoneInput.changeText("");

            codeInput.changeText("");

            codeBtnText = codeBtn.text.text;

            codeBtn.on(Event.CLICK, this, function ():void
            {
                handlerCode();
            });

            for (var i:int = 0; i < inputSprite.numChildren; i++)
            {
                var child:Node = inputSprite.getChildAt(i);
                child.on(Event.CLICK, this, onInputClick, [i]);
            }

            super.nextBtn.on(Event.CLICK, this, onNext);
            super.resumeBtn.on(Event.CLICK, this, onResume);
        }

        private function setDialog(_dialog):void
        {
            this._dialog = _dialog;
        }

        private var phone:String;

        private function onNext():void
        {
            if (oneStep.visible)
            {
                handlerCode();
                phone = input;
            }
            else
            {
                confirmHandler.runWith([phone, input]);
            }
        }


        public function sendRet(success:Boolean):void
        {
            Wait.close();
            if (!success)
            {
                stopCodeBtn();
            }
            else
            {
                gotoTwo();
            }
        }

        private function handlerCode():void
        {
            Net.instance.write(SendAuthCode.create(phoneInput.text));
            Wait.show("发送中...");
            codeBtn.disabled = true;
            startCodeBtn();
        }

        private function onResume():void
        {
            clearInput();
        }

        private static const INPUT_MAX:int = 7;

        private function onInputClick(index:Number):void
        {
            var input:String = this.input;
            if (input.length < inputMax)
            {
                this.input += index;
            }
            onInputChange();
        }

        public function backInput():void
        {
            if (input.length > 0)
            {
                input = input.substring(0, input.length - 1);
            }
            onInputChange();
        }

        private function onInputChange():void
        {

        }

        private function get input():String
        {
            return oneStep.visible ? phoneInput.text : codeInput.text;
        }

        private function set input(v:String):void
        {
            return oneStep.visible ? phoneInput.changeText(v) : codeInput.changeText(v);
        }

        private function get inputMax():Number
        {
            return oneStep.visible ? 13 : 6;
        }

        public function clearInput():void
        {
            input = "";
        }

        private var leftTime:Number = 60;

        private function startCodeBtn():void
        {
            Laya.timer.loop(1000, this, codeBtnChange);
        }

        private function stopCodeBtn():void
        {
            codeBtn.text.changeText(codeBtnText);
            codeBtn.disabled = false;
            Laya.timer.clear(this, codeBtnChange);
        }

        private function codeBtnChange():void
        {
            codeBtn.text.changeText("(" + leftTime + ")");
            if (leftTime == 0)
            {
                stopCodeBtn();
                return;
            }
            leftTime--;
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

        public function getPhone():String
        {
            return phoneInput.text;
        }

        public function getCode():String
        {
            return codeInput.text;
        }

        override public function destroy(destroyChild:Boolean = true):void
        {
            stopCodeBtn();
            super.destroy(destroyChild);
        }

        public function gotoOne():void
        {
            oneStep.visible = true;
            twoStep.visible = false;
            _dialog.setTitle("第一步  输入手机号！");
            input = "";
        }

        public function gotoTwo():void
        {
            oneStep.visible = false;
            twoStep.visible = true;
            _dialog.setTitle("第二步  输入验证码！");
            input = "";
        }
    }
}