package com.isnowfox.core.io.message
{
    public interface MessageHandler
    {
        /**
         * 是否不回收，且从缓冲池删除！
         * 暂时不实现，等后面优化，然后这个对象就脱离了缓存管理！
         * 返回true 表示需要脱离缓冲，不然这个消息的内容可能被覆盖
         */
        function handler(msg:Message):Boolean;
    }
}