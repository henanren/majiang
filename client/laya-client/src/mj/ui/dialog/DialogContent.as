/**
 * @author zuoge85@gmail.com on 16/10/12.
 */
package mj.ui.dialog
{
    import laya.display.Sprite;

    public interface DialogContent
    {
        function layout():void;
        function getDisplayObject():Sprite;
        function getContentWidth():Number;
        function getContentHeight():Number;

    }
}
