package mj.ui.dialog
{
    import laya.events.Event;
    import laya.filters.GlowFilter;
    import laya.ui.Dialog;
    import laya.ui.Image;
    import laya.utils.Browser;
    import laya.utils.Handler;

    import mj.manager.GameManager;
    import mj.model.PaiUtils;
    import mj.ui.*;
    import mj.utils.LayaUtils;

    import ui.test.OptDialogUI;

    /**
     * @author zuoge85@gmail.com on 16/10/10.
     */
    public class OptDialog extends OptDialogUI
    {
        private static const btnGlowFilters:Array = [new GlowFilter("#ebb531", 8, 4, 4)];
        private static const downBtnGlowFilters:Array = [new GlowFilter("#ebb531", 14, 4, 4)];

//        private var isShowHu:Boolean;
//        private var isShowPeng:Boolean;
//        private var isShowGang:Boolean;
//        private var chiValues:Array;
        private var optHandler:Handler;
        private var opts:Array = [];

        public function OptDialog()
        {
            super();

        }

        public function initCPGH(isShowHu:Boolean, gangValue:int,
                                 pengValue:int, chiValues:Array, optHandler:Handler):void
        {
            this.optHandler = optHandler;
            initButton(isShowHu, this.hu, GameManager.OPT_HU);
            initButton(gangValue != PaiUtils.NOT_PAI_INDEX, this.gang, GameManager.OPT_DA_MING_GANG, gangValue);
            initButton(pengValue != PaiUtils.NOT_PAI_INDEX, this.peng, GameManager.OPT_PENG, pengValue);


            this.chi.removeSelf();
            if (chiValues)
            {
                for (var i:int = 0; i < chiValues.length; i += 3)
                {
                    var curButton:Image = LayaUtils.clone(this.chi) as Image;
                    this.addChild(curButton);
                    initButton(true, curButton, GameManager.OPT_CHI, chiValues.slice(i, i + 3));
                }
            }

            initButton(true, this.guo, GameManager.OPT_GUO);


            init();
        }

        public function initFaPai(isShowHu:Boolean, anGang:Array, mingGang:Array, optHandler:Handler):void
        {
            this.optHandler = optHandler;

            initButton(isShowHu, this.hu, GameManager.OPT_HU);

            this.gang.removeSelf();
            var i:int;
            var curButton:Image;
            if (anGang)
            {
                for (i = 0; i < anGang.length; i++)
                {
                    curButton = LayaUtils.clone(this.gang) as Image;
                    this.addChild(curButton);
                    initButton(true, curButton, GameManager.OPT_AN_GANG, anGang[i]);
                }
            }

            if (mingGang)
            {
                for (i = 0; i < mingGang.length; i++)
                {
                    curButton = LayaUtils.clone(this.gang) as Image;
                    this.addChild(curButton);
                    initButton(true, curButton, GameManager.OPT_XIAO_MING_GANG, mingGang[i]);
                }
            }

            initButton(true, this.guo, GameManager.OPT_GUO);

            this.peng.removeSelf();
            this.chi.removeSelf();
            init();
        }

        private function init():void
        {
            Laya.stage.on(Event.RESIZE, this, layout);
            Laya.stage.on(Event.ADDED, this, layout);
            layout();
        }

        private function initButton(isShow:Boolean, button:Image, optName:String, value:* = null):void
        {
            if (isShow)
            {
                opts.push(button);

                if (value != null)
                {
                    if (value is Array)
                    {
                        PaiUtils.showPaiIcon(button.getChildAt(0).getChildAt(0) as Image, value[0]);
                        PaiUtils.showPaiIcon(button.getChildAt(1).getChildAt(0) as Image, value[1]);
                        PaiUtils.showPaiIcon(button.getChildAt(2).getChildAt(0) as Image, value[2]);
                    }
                    else
                    {
                        PaiUtils.showPaiIcon(button.getChildAt(0).getChildAt(0) as Image, value);
                    }
                }
            }
            else
            {
                button.removeSelf();
                button.destroy();
            }

            button.on(Event.MOUSE_OVER, this, function ():void
            {
                button.filters = btnGlowFilters;
            });

            button.on(Event.MOUSE_OUT, this, function ():void
            {
                button.filters = null;
            });

            button.on(Event.MOUSE_DOWN, this, function ():void
            {
                button.filters = downBtnGlowFilters;
            });

            button.on(Event.CLICK, this, function ():void
            {
                if (optHandler)
                {
                    optHandler.runWith([optName, value]);
                }
            });
        }

        public function layout():void
        {
            var bWidth:Number = Browser.width;
            var bHeight:Number = Browser.height;
            var ratio:Number = UiHelps.getGameRatio();

            var x:Number = 0;
            var height:Number = 0;
            for (var i:int = 0; i < opts.length; i++)
            {
                var btn:Image = opts[i];
                btn.x = x;
                x += btn.width;

                height = Math.max(height, btn.height);
            }
            if (x > bWidth)
            {
                ratio = bWidth / x * 0.9;
            }

            this.x = (bWidth - x * ratio) / 2;
            this.y = (bHeight - (172 + height - 20) * ratio );
            this.scale(ratio, ratio);
        }


        override public function destroy(destroyChild:Boolean = true):void
        {
            super.destroy(destroyChild);
            Laya.stage.off(Event.RESIZE, this, layout);
            if (optHandler)
            {
                optHandler.recover();
            }
        }

        private static var optDialog:OptDialog;

        public static function showCPGH(hu:Boolean, gang:int, peng:int,
                                        chi:Array, optHandler:Handler):void
        {
            if (optDialog != null)
            {
                close();
            }
            optDialog = new OptDialog();
            optDialog.initCPGH(hu, gang, peng, chi, optHandler);
            optDialog.popupCenter = false;
            laya.ui.Dialog.manager.maskLayer.alpha = 0;
            optDialog.popup();
        }

        public static function showFaPai(hu:Boolean, anGang:Array, mingGang:Array, optHandler:Handler):void
        {
            if (optDialog != null)
            {
                close();
            }
            optDialog = new OptDialog();
            optDialog.initFaPai(hu, anGang, mingGang, optHandler);
            optDialog.popupCenter = false;

            laya.ui.Dialog.manager.maskLayer.alpha = 0;
            optDialog.popup();
        }

        public static function close():void
        {
            if (optDialog)
            {
                optDialog.close();
                optDialog = null;
            }
        }

        public override function close(type:String = null):void
        {
            super.close(type);
            destroy(true);
            laya.ui.Dialog.manager.maskLayer.alpha =  UIConfig.popupBgAlpha;
        }

    }
}