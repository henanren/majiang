package com.isnowfox.core.io
{
    import laya.utils.Byte;

    public interface Input
    {
        function readBoolean():Boolean ;

        function readInt():int;

        function readLong():Number;

        function readFloat():Number;

        function readDouble():Number;

        function readString():String;

        function readBytes():Byte;

        function readByteBuf():Byte;

        function readBooleanArray():Array;

        function readIntArray():Array;

        function readLongArray():Array;

        function readFloatArray():Array;

        function readDoubleArray():Array;

        function readStringArray():Array;

        function getDataInput():Byte;
    }
}