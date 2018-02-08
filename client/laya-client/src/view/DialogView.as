package view
{
    import laya.display.Sprite;
    import laya.display.Text;
    import laya.events.Event;
    import laya.utils.Browser;
    import laya.utils.Handler;

    import mj.ui.UiHelps;
    import mj.ui.dialog.DialogContent;
    import mj.ui.dialog.DialogSize;

    import ui.test.DialogUI;

    public class DialogView extends DialogUI
    {
        private var myWidth:Number;
        private var myHeight:Number;

        private var designWidth:Number;
        private var designHeight:Number;

        private var inWidth:Number;
        private var inHeight:Number;

        private var nTitlePadding:Number;
        private var nCloseLeft:Number;

        private var nContentWidth:Number;
        private var nContentHeight:Number;

        private var nContentWidthDiff:Number;
        private var nContentHeightDiff:Number;

        private var nContentTextHeight:Number;
        private var nContentTextWidth:Number;

        private var nConfirmBtnTop:Number;
        private var nConfirmBtnLeft:Number;

        private var nCancelBtnTop:Number;
        private var nCancelBtnLeft:Number;

        private var nIcon2Left:Number;
        private var dialogSize:String;
        private var content:DialogContent;

        private var cancelHandler:Handler;
        public var confirmHandler:Handler;

        public function DialogView(size:String, w:Number, h:Number,
                                   content:DialogContent, title:String,
                                   closeHandler:Handler, confirmHandler:Handler, confirmText:String,
                                   cancelHandler:Handler, cancelText:String)
        {
            super();
            this.content = content;
            this.dialogSize = size;
            this.title.text = title;


            nContentWidth = contentSprite.width;
            if (cancelText == null && confirmText == null)
            {
                nContentHeight = contentSprite.height + buttonSprite.height;
                buttonSprite.visible = false;
                buttonSprite.height = 0;
            }
            else
            {
                nContentHeight = contentSprite.height;
            }


            nContentTextHeight = contentText.height;
            nContentTextWidth = contentText.width;

            this.confirmHandler = confirmHandler;
            this.closeHandler = closeHandler;
            confirmBtn.label = confirmText;


            designWidth = this.width;
            designHeight = this.height;

            inWidth = w;
            inHeight = h;

            nTitlePadding = this.title.x;

            nCloseLeft = closeBtn.x;


            nContentWidthDiff = designWidth - nContentWidth;
            nContentHeightDiff = designHeight - nContentHeight;

            nConfirmBtnTop = confirmBtn.y;
            nConfirmBtnLeft = confirmBtn.x;

            nCancelBtnTop = cancelBtn.y;
            nCancelBtnLeft = cancelBtn.x;

            nIcon2Left = icon2.x;

            if (content is Sprite)
            {
                contentSprite.removeChildren();
                contentSprite.addChild(content.getDisplayObject());
            }else{
                contentText.overflow = Text.SCROLL;
                contentText.wordWrap = true;
                contentText.on(Event.MOUSE_DOWN, this, startScrollText);
            }

            confirmBtn.on(Event.CLICK, this, onConfirmClick);

            if (cancelText == null)
            {
                cancelBtn.removeSelf();
                cancelBtn.destroy();
                this.cancelBtn = null;
            }
            else
            {
                cancelBtn.label = cancelText;
                cancelBtn.on(Event.CLICK, this, onCancelClick);

                this.cancelHandler = cancelHandler;
            }


            closeBtn.on(Event.CLICK, this, onCloseClick);


            Laya.stage.on(Event.RESIZE, this, layout);
            Laya.stage.on(Event.ADDED, this, layout);
            layout();
        }

        private var prevX:Number = 0;
        private var prevY:Number = 0;

        /* 开始滚动文本 */
        private function startScrollText(e:Event):void
        {
            prevX = contentText.mouseX;
            prevY = contentText.mouseY;

            Laya.stage.on(Event.MOUSE_MOVE, this, scrollText);
            Laya.stage.on(Event.MOUSE_UP, this, finishScrollText);
        }

        /* 停止滚动文本 */
        private function finishScrollText(e:Event):void
        {
            Laya.stage.off(Event.MOUSE_MOVE, this, scrollText);
            Laya.stage.off(Event.MOUSE_UP, this, finishScrollText);
        }

        /* 鼠标滚动文本 */
        private function scrollText(e:Event):void
        {
            var nowX:Number = contentText.mouseX;
            var nowY:Number = contentText.mouseY;

            contentText.scrollX += prevX - nowX;
            contentText.scrollY += prevY - nowY;

            prevX = nowX;
            prevY = nowY;
        }

        private function onCancelClick():void
        {
            if (cancelHandler)
            {
                cancelHandler.once = false;
                cancelHandler.run();
            }
        }

        private function onConfirmClick():void
        {
            if (confirmHandler)
            {
                confirmHandler.once = false;
                confirmHandler.run();
            }
        }

        override public function destroy(destroyChild:Boolean = true):void
        {
            if (cancelHandler)
            {
                cancelHandler.recover();
            }
            if (confirmHandler)
            {
                confirmHandler.recover();
            }


            Laya.stage.off(Event.RESIZE, this, layout);
        }

        private function onCloseClick():void
        {
            super.close();
        }

        public function layout():void
        {
            var bWidth:Number = Browser.width;
            var bHeight:Number = Browser.height;
            var ratio:Number = UiHelps.getRatio();


            if (inWidth == -1 || inHeight == -1)
            {
                switch (dialogSize)
                {
                    case DialogSize.LARGE:
                    {
                        myWidth = bWidth * 0.9;
                        myHeight = bHeight * 0.9;
                        break;
                    }
                    case DialogSize.MEDIUM:
                    {
                        myWidth = bWidth * 0.7;
                        myHeight = bHeight * 0.7;
                        break;
                    }
                    case DialogSize.SMALL:
                    {
                        myWidth = bWidth * 0.5;
                        myHeight = bHeight * 0.5;
                        break;
                    }
                    case DialogSize.AUTO:
                    {
                        if (content != null)
                        {

                            var curWidth:Number = bWidth - bHeight * 0.15;
                            var curHeight:Number = bHeight - bHeight * 0.15;

                            content.layout();
                            myWidth = (content.getContentWidth() + nContentWidthDiff) * ratio;
                            if (myWidth > curWidth)
                            {
                                ratio = curWidth / (content.getContentWidth() + nContentWidthDiff);
                                myWidth = curWidth;
                            }
                            myHeight = (content.getContentHeight() + nContentHeightDiff) * ratio;

                            if (myHeight > curHeight)
                            {
                                ratio = curHeight / (content.getContentHeight() + nContentHeightDiff);

                                myWidth = (content.getContentWidth() + nContentWidthDiff) * ratio;
                                myHeight = curHeight;
                            }
                            //需要减去外围的宽度和高度
                        }
                        else
                        {
                            throw new Error("DialogSize.AUTO  content 不能为空");
                        }
                        break;
                    }
                    default:
                    {
                        throw new Error("错误的大小!");
                    }
                }
            }
            else
            {
                myWidth = inWidth;
                myHeight = inHeight;
            }


            //先调整对话框长宽比例
            var bgWidth:Number = myWidth / ratio;
            var bgHeight:Number = myHeight / ratio;

            //比例变化后的定位更改
            var wChange:Number = bgWidth - designWidth;
            var hChange:Number = bgHeight - designHeight;

            //先调整对话框长宽比例 然后在缩放
            this.x = (bWidth - myWidth) / 2;
            this.y = (bHeight - myHeight) / 2;
            bg.size(bgWidth, bgHeight);


            title.width = (myWidth / ratio - (nTitlePadding * 2));
            closeBtn.x = nCloseLeft + wChange;
            icon2.x = nIcon2Left + wChange;

//            contentBg.size(nContentWidth + wChange, nContentHeight + hChange);
            contentText.size(nContentTextWidth + wChange, nContentTextHeight + hChange);

            if (cancelHandler == null)
            {
                confirmBtn.x = (bgWidth - confirmBtn.width) / 2;
                confirmBtn.y = nConfirmBtnTop + hChange;
            }
            else
            {
                confirmBtn.y = nConfirmBtnTop + hChange;
                confirmBtn.x = nConfirmBtnLeft + wChange;
                cancelBtn.y = nConfirmBtnTop + hChange;
            }


            mainSprite.scale(ratio, ratio);


            this.dragArea = "0,0," + myWidth + "," + 93 * ratio;
            this.size(myWidth, myHeight);
        }

        public function setText(text:String):void
        {
            contentText.text = text;
        }
    }
}