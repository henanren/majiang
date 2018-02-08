/**
 * @author zuoge85@gmail.com on 16/10/12.
 */
package mj.scene
{
    import laya.display.Sprite;

    public interface GameScene
    {
        function destroy(destroyChild:Boolean = true):void;
        function getDisplayObject():Sprite;
        function init():void;
    }
}
