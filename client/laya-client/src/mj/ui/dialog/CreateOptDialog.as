/**
 * @author zuoge85@gmail.com on 16/10/12.
 */
package mj.ui.dialog
{
    import laya.display.Sprite;
    import laya.utils.Handler;

    import mj.net.message.login.OptionEntry;

    import ui.test.CreateOptUI;

    public final class CreateOptDialog extends CreateOptUI implements DialogContent
    {
        public static function showDialog(confirmHandler:Handler):Dialog
        {
            var createOptDialog:CreateOptDialog = new CreateOptDialog(confirmHandler);
            var createDialog:Dialog = Dialog.showDialog(
                    DialogSize.LARGE, createOptDialog, "创建房间", null,
                    Handler.create(createOptDialog, createOptDialog.onConfirmHandler), "创建房间"
            );
            return createDialog;
        }

        private static const CHAPTER_MAX_VALUES:Array = ["8", "16"];
        private static const MAI_MA_VALUES:Array = ["0", "-1", "2", "4", "6"];
        private static const TYPE_VALUES:Array = ["tuiDaoHu", "hongZhongBian", "baiBanBian", "danGui", "shuangGui"];

        private var nWidth:Number;
        private var nHeight:Number;
        private var confirmHandler:Handler;

        public function CreateOptDialog(confirmHandler:Handler)
        {
            this.confirmHandler = confirmHandler;
            this.nWidth = this.width;
            this.nHeight = this.height;
        }


        public function layout():void
        {
        }


        private function onConfirmHandler():void
        {
            chapterMaxRadio.selectedIndex;

            var options:Array = [
                OptionEntry.create("chapterMax", CHAPTER_MAX_VALUES[chapterMaxRadio.selectedIndex]),
                OptionEntry.create("maiMa", MAI_MA_VALUES[maiMaRadio.selectedIndex]),
                OptionEntry.create("bianType", TYPE_VALUES[typeRadio.selectedIndex]),
            ];
            confirmHandler.runWith([options]);
        }

        public function getDisplayObject():Sprite
        {
            return this;
        }

        public function getContentWidth():Number
        {
            return content.width * this.scaleX;
        }

        public function getContentHeight():Number
        {
            return content.height * this.scaleY;
        }
    }
}