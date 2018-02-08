package com.isnowfox.core.io
{
    public class ProtocolException extends Error
    {
        public function ProtocolException(message:* = "", id:* = 0)
        {
            super(message, id);
        }

        public static function newTypeException(b:int):ProtocolException
        {
            return new ProtocolException("error type :" + b);
        }

        public static function newIn64Exception(v:Number):ProtocolException
        {
            return new ProtocolException("不支持的int64值,不能是小数或者负数 error value :" + v);
        }

        public static function newIn64ExceptionByHight(v:uint):ProtocolException
        {
            return new ProtocolException("不支持的int64值,不能是小数或者负数 hight value :" + v);
        }
    }
}