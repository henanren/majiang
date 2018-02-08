package com.isnowfox.core.io.message
{
    import com.isnowfox.core.io.MarkCompressProtocol;

    public class MessageProtocol
    {
        public static const MESSAGE_MAX:int = 0xFFFFFF;

        /**
         * 注意，修改这个必须修改decoder和encoder
         */
        public static const LENGTH_BYTE_NUMS:int = 3;
        public static const HEAD_LENGTH:int = 3;

        public static const TYPE_NORMAL:int = 0;
        public static const TYPE_GZIP:int = 1;

        public static const TYPE_OR_ID_MAX:int = MarkCompressProtocol.TYPE_ONE_BYTE_MAX;

        /**
         * 自定义消息id
         */
        public static const EXPAND_MESSAGE_TYPE:int = 0;
    }
}