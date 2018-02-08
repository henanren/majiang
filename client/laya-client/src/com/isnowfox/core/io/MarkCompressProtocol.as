package com.isnowfox.core.io
{
    public class MarkCompressProtocol
    {
        public static const DEFAULT_CHARSET:String = "utf-8";
//		public static const DEFAULT_IS_BIG_ENDIAN:Boolean						= true; 


        public static const THREE_MAX:int = 0x7fffff;

        public static const THREE_MIN:int = -8388608;
        /**
         * 第一个字节只有7位数可以用
         */
        public static const FIRST_WIDTH:int = 7;

        /**
         * 其他字节宽度
         */
        public static const OTHER_WIDTH:int = 8;

        //类型只能小于等于127
        public static const TYPE_NULL_OR_ZERO_OR_FALSE:int = 0xFF - 1;
        public static const TYPE_TRUE:int = 0xFF - 2;

        public static const TYPE_INT_1BYTE:int = 0xFF - 3;
        public static const TYPE_INT_2BYTE:int = 0xFF - 4;
        public static const TYPE_INT_3BYTE:int = 0xFF - 5;
        public static const TYPE_INT_4BYTE:int = 0xFF - 6;

        public static const TYPE_INT_5BYTE:int = 0xFF - 7;
        public static const TYPE_INT_6BYTE:int = 0xFF - 8;
        public static const TYPE_INT_7BYTE:int = 0xFF - 9;
        public static const TYPE_INT_8BYTE:int = 0xFF - 10;


        public static const TYPE_STRING:int = 0xFF - 11;
        public static const TYPE_BYTES:int = 0xFF - 12;

        public static const TYPE_MAX:int = 0xFF - 14;
        public static const TYPE_MIN:int = 0;

        public static const TYPE_MINUS:int = 4;

        public static const TYPE_ONE_BYTE_MAX:int = TYPE_MAX - TYPE_MINUS;
        public static const TYPE_ONE_BYTE_MIN:int = 0 - TYPE_MINUS;
    }
}