package com.isnowfox.core.io
{
    import com.isnowfox.core.utils.AbstractMethodError;

    import laya.utils.Byte;

    public class AbstractInput implements Input
    {
        protected var mIn:Byte;
        protected var mCharset:String;

        public function AbstractInput(in0:Byte, charset:String)
        {
            mIn = in0;
            mCharset = charset;
        }

        public function getDataInput():Byte
        {
            return mIn;
        }

        public function readBoolean():Boolean
        {
            throw new AbstractMethodError();
        }

        public function readInt():int
        {
            throw new AbstractMethodError();
        }

        public function readLong():Number
        {
            throw new AbstractMethodError();
        }

        public function readFloat():Number
        {
            throw new AbstractMethodError();
        }

        public function readDouble():Number
        {
            throw new AbstractMethodError();
        }

        public function readString():String
        {
            throw new AbstractMethodError();
        }

        public function readBytes():Byte
        {
            throw new AbstractMethodError();
        }

        public function readByteBuf():Byte
        {
//            var len:int = readInt();
//            if (len >= 0)
//            {
//                var ba:ByteArray = new ByteArray();
//                mIn.getUint8Array();
//                mIn.readBytes(ba, 0, len);
//                return ba;
//            }
//            else
//            {
//                return null;
//            }
            throw new AbstractMethodError();
        }

        public function readBooleanArray():Array
        {
            var len:int = readInt();
            if (len == -1)
            {
                return null;
            }
            var a:Array = [];
            for (var i:int = 0; i < len; i++)
            {
                a[i] = readBoolean();
            }
            return a;
        }

        public function readIntArray():Array
        {
            var len:int = readInt();
            if (len == -1)
            {
                return null;
            }
            var a:Array = [];
            for (var i:int = 0; i < len; i++)
            {
                a[i] = readInt();
            }
            return a;
        }

        public function readLongArray():Array
        {
            var len:int = readInt();
            if (len == -1)
            {
                return null;
            }
            var a:Array = [];
            for (var i:int = 0; i < len; i++)
            {
                a[i] = readLong();
            }
            return a;
        }

        public function readFloatArray():Array
        {
            var len:int = readInt();
            if (len == -1)
            {
                return null;
            }
            var a:Array = [];
            for (var i:int = 0; i < len; i++)
            {
                a[i] = readFloat();
            }
            return a;
        }

        public function readDoubleArray():Array
        {
            var len:int = readInt();
            if (len == -1)
            {
                return null;
            }
            var a:Array = [];
            for (var i:int = 0; i < len; i++)
            {
                a[i] = readDouble();
            }
            return a;
        }

        public function readStringArray():Array
        {
            var len:int = readInt();
            if (len == -1)
            {
                return null;
            }
            var a:Array = [];
            for (var i:int = 0; i < len; i++)
            {
                a[i] = readString();
            }
            return a;
        }
    }
}