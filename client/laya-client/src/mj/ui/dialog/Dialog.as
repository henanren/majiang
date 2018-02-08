package mj.ui.dialog
{
    import laya.utils.Handler;

    import view.DialogView;

    /**
     * @author zuoge85@gmail.com on 16/10/10.
     */
    public class Dialog extends DialogView
    {
        public function Dialog(size:String, w:Number, h:Number, content:DialogContent, title:String,
                               closeHandler:Handler, confirmHandler:Handler, confirmText:String = "确定",
                               cancelHandler:Handler = null, cancelText:String = null)
        {
            super(size, w, h, content, title, closeHandler, confirmHandler, confirmText, cancelHandler, cancelText);
        }

        public static function showDialog(size:String, content:DialogContent, title:String,
                                          closeHandler:Handler, confirmHandler:Handler, confirmText:String = "确定",
                                          cancelHandler:Handler = null, cancelText:String = null):Dialog
        {
            var dialog:Dialog = new Dialog(
                    size, -1, -1, content, title, closeHandler,
                    confirmHandler, confirmText, cancelHandler, cancelText
            );
            dialog.popupCenter = false;
            dialog.popup();
            return dialog;
        }

        public static function showMessage(message:String, closeHandler:Handler = null,size:String = DialogSize.SMALL, title:String = "提示!"):Dialog
        {
            var dialog:Dialog = showDialog(size, null, title, closeHandler, Handler.create(null, function ():void
            {
                dialog.close();
            }));
            dialog.setText(message);
            return dialog;
        }

        public static function confirm(message:String, confirmHandler:Handler = null, closeHandler:Handler = null):Dialog
        {
            var dialog:Dialog = showDialog(
                    DialogSize.SMALL, null, "确定", null,
                    Handler.create(null, function ():void
                    {
                        dialog.close();
                        if (confirmHandler)
                        {
                            confirmHandler.run();
                        }
                    }), "确定",
                    Handler.create(null, function ():void
                    {
                        dialog.close();
                        if (closeHandler)
                        {
                            closeHandler.run();
                        }
                    }), "取消"
            );
            dialog.setText(message);
            return dialog;
        }

        public override function close(type:String = null):void
        {
            super.close(type);
            destroy(true)
        }

        public function sendRet(success:Boolean):void
        {

        }

        public function setTitle(title:String):void
        {
            this.title.changeText(title);
        }
    }
}
