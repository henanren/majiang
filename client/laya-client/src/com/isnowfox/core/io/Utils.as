package com.isnowfox.core.io
{
    import laya.utils.Byte;

    public final class Utils
    {
        

        public static function putThree(out:Byte, v:int):void
        {
            if (out.endian == Byte.BIG_ENDIAN)
            {
                out.writeByte(v >>> 16);
                out.writeByte((v >>> 8));
                out.writeByte((v >>> 0));
            }
            else
            {
                out.writeByte(v >>> 0);
                out.writeByte((v >>> 8));
                out.writeByte((v >>> 16));
            }
        }

        public static function getThree(in0:Byte):int
        {
            var ch1:int;
            var ch2:int;
            var ch3:int;

            if (in0.endian == Byte.BIG_ENDIAN)
            {
                ch1 = in0.getUint8();
                ch2 = in0.getUint8();
                ch3 = in0.getUint8();
                return ((ch1 << 16) + (ch2 << 8) + (ch3 << 0));
            }
            else
            {
                ch1 = in0.getUint8();
                ch2 = in0.getUint8();
                ch3 = in0.getUint8();
                return ((ch1 << 0) + (ch2 << 8) + (ch3 << 16));
            }
        }
    }
}