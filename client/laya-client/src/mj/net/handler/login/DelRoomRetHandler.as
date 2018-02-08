package mj.net.handler.login
{
    import com.isnowfox.core.SingletonError;
    import com.isnowfox.core.io.message.Message;
    import com.isnowfox.core.io.message.MessageHandler;

    import mj.manager.UserManager;
    import mj.net.message.login.DelRoomRet;
    import mj.ui.dialog.Wait;

    /**
     * 解散房间结果
     *
     * 消息处理器！
     * <b>生成器生成代码，生成后不会在覆盖!</b>
     * @author isnowfox消息生成器
     */
    public final class DelRoomRetHandler implements MessageHandler
    {
        public static const instance:DelRoomRetHandler = new DelRoomRetHandler();

        public function DelRoomRetHandler()
        {
            if (instance != null)
            {
                throw new SingletonError("ResourceManager 是单例模式");
            }
        }

        public function handler(msg:Message):Boolean
        {
            return inHandler(DelRoomRet(msg));
        }

        /**
         * @return 返回true 表示需要脱离缓冲，不然这个消息的内容可能被覆盖
         */
        private function inHandler(msg:DelRoomRet):Boolean
        {
//            if (msg.result)
//            {
//                UserManager.instance.delRoomRet();
//            }
//            else
//            {
//
//            }
            Wait.close();
            return false;
        }
    }
}