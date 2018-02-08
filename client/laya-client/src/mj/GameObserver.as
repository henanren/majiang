package mj
{
    import mj.manager.AudioManager;

    /**
     * @author zuoge85@gmail.com on 2016/11/24.
     */
    public class GameObserver
    {
        public function GameObserver()
        {
        }

        public function onPaiOut(sex:int, pai:int):void
        {
            AudioManager.paiOut(sex, pai);
        }

        public function onPaiClick(pai:int):void
        {
            AudioManager.paiClick();
        }

        public function onGang(sex:int):void
        {
            AudioManager.gang(sex);
        }

        public function onPeng(sex:int):void
        {
            AudioManager.peng(sex);
        }

        public function onChi(sex:int):void
        {
            AudioManager.chi(sex);
        }

        public function onDealCard():void
        {
            AudioManager.dealCard();
        }

        public function onButtonClick():void
        {
            AudioManager.buttonClick();
        }

        public function onTimeupAlarm():void
        {
            AudioManager.timeupAlarm();
        }

        public function onStopTimeupAlarm():void
        {
            AudioManager.stopTimeupAlarm();
        }

        public function onEnter():void
        {
            AudioManager.enter();
        }
    }
}
