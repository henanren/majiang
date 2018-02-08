package mj.manager
{
    import com.isnowfox.core.SingletonError;

    import laya.media.SoundManager;
    import laya.ui.Dialog;

    import mj.scene.GameScene;
    import mj.scene.GameTableScene;

    /**
     * @author zuoge85@gmail.com on 2017/1/31.
     */
    public class CordovaManager
    {
        public static const instance:CordovaManager = new CordovaManager();

        public function CordovaManager()
        {
            if (instance != null)
            {
                throw new SingletonError("GameManager 是单例模式");
            }
        }

        public function init():void
        {
            if (__JS__('navigator&&navigator.splashscreen'))
            {
                if (__JS__('StatusBar'))
                {
                    __JS__('StatusBar.hide()');
                }
                initSplash();
                initCordova();
            }

        }

        private function initSplash():void
        {
            Laya.timer.frameOnce(30, this, onLoadingDisplay);
            function onLoadingDisplay():void
            {
                __JS__("navigator.splashscreen.hide();");
            }
        }

        private function initCordova():void
        {
            __JS__('document.addEventListener("pause", this.onPause.bind(this), false)');
            __JS__('document.addEventListener("resume", this.onResume.bind(this), false)');
            __JS__('document.addEventListener("backbutton", this.onBackKeyDown.bind(this), false)');
        }


        private function onBackKeyDown(e:*):void
        {
            var currentScene:GameScene = UiManager.instance.currentScene;
            if (Dialog.manager.modalLayer.numChildren)
            {
                Dialog.closeAll();
            }
            else if (currentScene instanceof GameTableScene)
            {
                GameTableScene(currentScene).onBack();
            }
            else
            {
                GameMain.current.quit();
            }
            e.preventDefault();
        }

        private var isAudioMuted:Boolean = false;

        private function onPause():void
        {
            isAudioMuted = SoundManager.muted;
            if (!isAudioMuted)
            {
                SoundManager.muted = true;
            }
        }

        private function onResume():void
        {
            SoundManager.muted = isAudioMuted;
            SoundManager.musicMuted = !!SoundManager.musicMuted;
            SoundManager.soundMuted = !!SoundManager.soundMuted;
        }
    }
}