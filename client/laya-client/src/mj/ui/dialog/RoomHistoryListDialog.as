package mj.ui.dialog
{
    import laya.display.Sprite;
    import laya.ui.Box;
    import laya.ui.Label;
    import laya.utils.Handler;

    import mj.net.message.login.RoomHistory;
    import mj.net.message.login.RoomHistoryListRet;

    import ui.test.RoomHistoryListUI;

    /**
     * @author zuoge85@gmail.com on 2016/11/9.
     */
    public class RoomHistoryListDialog extends RoomHistoryListUI implements DialogContent
    {
        protected static const H_PAI_SPACE:int = 4;
        protected static const V_PAI_SPACE:int = 0;

        private static var dialog:Dialog;

        public static function showDialog(msg:RoomHistoryListRet):Dialog
        {
            if (dialog)
            {
                close();
            }
            var roomHistoryListDialog:RoomHistoryListDialog = new RoomHistoryListDialog(msg);


            dialog = Dialog.showDialog(
                    DialogSize.AUTO, roomHistoryListDialog, "战   绩", null, Handler.create(
                            null, close
                    ), "确定"
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

        public function RoomHistoryListDialog(msg:RoomHistoryListRet)
        {
            super();
            this.nWidth = this.width;
            this.nHeight = this.height;
            this.msg = msg;

            this.row.removeSelf();
        }

        public function layout():void
        {
            init();
        }

        private function init():void
        {
            list.itemRender = RoomHistoryListUI.uiView.child[0].child[1];

            list.repeatX = 1;
            list.repeatY = msg.list.length;
            list.vScrollBarSkin = "";

            list.renderHandler = new Handler(this, updateItem);

//            for (var i:int = 0; i < msg.list.length; i++)
//            {
//                var roomHistory:RoomHistory = msg.list[i];
//
//            }
            list.array = msg.list;
        }

        private function updateItem(cell:Box, index:int):void
        {
            if (!cell.dataSource)
            {
                return;
            }
            var item:RoomHistory = cell.dataSource;
            var childByName:Label = Label(cell.getChildByName("roomId"));
            childByName.text = "房间号:" + item.roomCheckId;

            var chapterNums:Label = Label(cell.getChildByName("chapterNums"));
            chapterNums.text = "(" + item.chapterNums + "盘)";

            var date:Label = Label(cell.getChildByName("date"));
            date.text = item.startDate;


            for (var i:int = 0; i < item.userNames.length; i++)
            {
                var name:* = item.userNames[i];
                Label(cell.getChildByName("names").getChildByName("user" + i)).text = name ? name : "";
                var score:* = item.scores[i];
                Label(cell.getChildByName("scores").getChildByName("score" + i)).text = score ? score : "0";
            }


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
