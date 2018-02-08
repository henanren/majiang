package com.isnowfox.core.io
{
    import laya.utils.Byte;

    public interface Output
    {
        function writeBoolean(v:Boolean):void;

        function writeInt(v:int):void;

        function writeLong(v:Number):void;

        function writeFloat(v:Number):void;

        function writeDouble(v:Number):void;

        function writeString(s:String):void;

        function writeBytes(bs:Byte):void;

        function writeByteBuf(bs:Byte):void;

        function writeBooleanArray(v:Array):void;

        function writeIntArray(v:Array):void;

        function writeLongArray(v:Array):void;

        function writeFloatArray(v:Array):void;

        function writeDoubleArray(v:Array):void;

        function writeStringArray(v:Array):void;
    }
}