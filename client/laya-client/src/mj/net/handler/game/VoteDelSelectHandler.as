package mj.net.handler.game
{
    import com.isnowfox.core.SingletonError;
    import com.isnowfox.core.io.message.Message;
    import com.isnowfox.core.io.message.MessageHandler;

    import laya.utils.Handler;

    import mj.net.Net;

    import mj.net.message.game.VoteDelSelect;
    import mj.net.message.game.VoteDelSelectRet;
    import mj.ui.dialog.Dialog;

    public final class VoteDelSelectHandler implements MessageHandler
    {
        public static const instance:VoteDelSelectHandler = new VoteDelSelectHandler();

        public function VoteDelSelectHandler()
        {
            if (instance != null)
            {
                throw new SingletonError("ResourceManager 是单例模式");
            }
        }

        public function handler(msg:Message):Boolean
        {
            return inHandler(VoteDelSelect(msg));
        }

        /**
         * @return 返回true 表示需要脱离缓冲，不然这个消息的内容可能被覆盖
         */
        private function inHandler(msg:VoteDelSelect):Boolean
        {
            Dialog.confirm(
                "玩家:" + msg.userName + "发起解散房间投票",
                Handler.create(null, function ():void
                {
                    Net.instance.write(VoteDelSelectRet.create(true, msg.userId));
                }),
                Handler.create(null, function ():void
                {
                    Net.instance.write(VoteDelSelectRet.create(false, msg.userId));
                })
            );
            return false;
        }
    }
}