package mj.ui.dialog
{
    import laya.display.Sprite;
    import laya.events.Event;
    import laya.utils.Handler;
    import laya.utils.Stat;

    import mj.manager.AudioManager;
    import mj.net.message.login.RoomHistoryListRet;

    import ui.test.SettingDialogUI;

    /**
     * @author zuoge85@gmail.com on 2016/11/9.
     */
    public class SettingDialog extends SettingDialogUI implements DialogContent
    {

        private static var dialog:Dialog;

        public static function showDialog():Dialog
        {
            if (dialog)
            {
                close();
            }
            var settingDialog:SettingDialog = new SettingDialog();


            dialog = Dialog.showDialog(
                    DialogSize.AUTO, settingDialog, "用户信息", null, Handler.create(
                            null, close
                    ), "确定"
            );

            return dialog;
        }

        public static function showQuitDialog(confirmHandler:Handler, nameTxt:String):Dialog
        {
            if (dialog)
            {
                close();
            }
            var settingDialog:SettingDialog = new SettingDialog();


            dialog = Dialog.showDialog(
                    DialogSize.AUTO, settingDialog, "用户信息", null, confirmHandler, nameTxt
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
        private var msg:RoomHistoryListRet;
        private static var showState:Boolean = false;

        public function SettingDialog()
        {
            super();
            this.nWidth = this.width;
            this.nHeight = this.height;
            this.msg = msg;



            yinxiaoSlider.changeHandler = new Handler(this, function (value:Number):void
            {
                AudioManager.changeYinxiao(value / 100);
                update();
            });


            yinyueSlider.changeHandler = new Handler(this, function (value:Number):void
            {
                AudioManager.changeYinyue(value / 100);
                update();
            });


            yinxiaoCheckbox.clickHandler = new Handler(this, function (value:Number):void
            {
                AudioManager.changeYinxiaoMuted(yinxiaoCheckbox.selected);
                update();
            });


            yinyueCheckbox.clickHandler = new Handler(this, function (value:Number):void
            {
                AudioManager.changeYinyueMuted(yinyueCheckbox.selected);
                update();
            });

            mainSprite.mouseEnabled = true;
            var count:Number = 0;
            mainSprite.on(Event.CLICK, this, function ():void
            {
                count++;
                if (count % 10 == 0)
                {
                    showState = !showState;
                    if (showState)
                    {
                        Stat.show(0, 100);
                    }
                    else
                    {
                        Stat.hide();
                    }
                }
            });

            update();
        }

        public function update()
        {
            yinxiaoSlider.value = AudioManager.getYinxiaoVolume() * 100;
            yinyueSlider.value = AudioManager.getYinyueVolume() * 100;
            yinxiaoCheckbox.selected = AudioManager.getYinxiaoMuted();
            yinyueCheckbox.selected = AudioManager.getYinyueMuted();
        }

        public function layout():void
        {
            init();
        }

        private function init():void
        {

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
            return nHeight * this.scaleY;
        }
    }
}
