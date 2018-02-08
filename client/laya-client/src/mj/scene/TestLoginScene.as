
package mj.scene
{
    import laya.display.Sprite;

    import mj.scene.GameScene;

    import ui.test.TestLoginUI;

    /**
     * @author zuoge85@gmail.com on 16/10/14.
     */
    public class TestLoginScene extends TestLoginUI implements GameScene
    {
        public function TestLoginScene()
        {
            super();
        }

        public function getDisplayObject():Sprite
        {
            return this;
        }

        public function init():void
        {
        }
    }
}
