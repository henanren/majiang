package com.isnowfox.core.io
{
    import com.isnowfox.core.utils.AbstractMethodError;

    import laya.utils.Byte;

    /**
     * 创建这个对象之后修改 endian会出现bug
     */
    public class MarkCompressInput extends AbstractInput
    {
        public static function create(in0:Byte):Input
        {
            return new MarkCompressInput(in0, MarkCompressProtocol.DEFAULT_CHARSET);
        }

        public static function createByCharset(in0:Byte, charset:String):Input
        {
            return new MarkCompressInput(in0, charset);
        }

//
//        /**读取double的缓冲*/
        private const mDoubleBuffer:Byte = new Byte();

        public function MarkCompressInput(in0:Byte, charset:String)
        {
            mDoubleBuffer.endian = in0.endian;
            super(in0, charset);
        }

        override public function readBoolean():Boolean
        {
            var b:int = mIn.getUint8();
            if (b == MarkCompressProtocol.TYPE_TRUE)
            {
                return true;
            }
            else if (b == MarkCompressProtocol.TYPE_NULL_OR_ZERO_OR_FALSE)
            {
                return false;
            }
            else
            {
                throw ProtocolException.newTypeException(b);
            }
        }

        override public function readInt():int
        {
            var b:int = mIn.getUint8();

            switch (b)
            {
                case MarkCompressProtocol.TYPE_TRUE:
                    return 1;
                case MarkCompressProtocol.TYPE_NULL_OR_ZERO_OR_FALSE:
                    return 0;
                case MarkCompressProtocol.TYPE_INT_1BYTE:
                    return mIn.getUint8();
                case MarkCompressProtocol.TYPE_INT_2BYTE:
                    return mIn.getUint16();
                case MarkCompressProtocol.TYPE_INT_3BYTE:
                    return Utils.getThree(mIn);
                case MarkCompressProtocol.TYPE_INT_4BYTE:
                    return mIn.getInt32();
                default:
                    if (b > MarkCompressProtocol.TYPE_MIN && b < MarkCompressProtocol.TYPE_MAX)
                    {
                        return b - MarkCompressProtocol.TYPE_MINUS;
                    }
                    else
                    {
                        throw  ProtocolException.newTypeException(b);
                    }
            }
        }

        override public function readLong():Number
        {
            var b:int = mIn.getUint8();
            switch (b)
            {
                case MarkCompressProtocol.TYPE_TRUE:
                    return 1;
                case MarkCompressProtocol.TYPE_NULL_OR_ZERO_OR_FALSE:
                    return 0;
                case MarkCompressProtocol.TYPE_INT_1BYTE:
                    return mIn.getUint8();
                case MarkCompressProtocol.TYPE_INT_2BYTE:
                    return mIn.getUint16();
                case MarkCompressProtocol.TYPE_INT_3BYTE:
                    return Utils.getThree(mIn);
                case MarkCompressProtocol.TYPE_INT_4BYTE:
                    return mIn.getUint32();
                case MarkCompressProtocol.TYPE_INT_5BYTE:
                    return getInt64Five();
                case MarkCompressProtocol.TYPE_INT_6BYTE:
                    return getInt64Six();
                case MarkCompressProtocol.TYPE_INT_7BYTE:
                    return getInt64Seven();
                case MarkCompressProtocol.TYPE_INT_8BYTE:
                    return getInt64Long();
                default:
                    if (b > MarkCompressProtocol.TYPE_MIN && b < MarkCompressProtocol.TYPE_MAX)
                    {
                        return b - MarkCompressProtocol.TYPE_MINUS;
                    }
                    else
                    {
                        throw  ProtocolException.newTypeException(b);
                    }
            }
        }

        override public function readFloat():Number
        {
            var b:int = mIn.getUint8();
            switch (b)
            {
                case MarkCompressProtocol.TYPE_NULL_OR_ZERO_OR_FALSE:
                    return 0;
                case MarkCompressProtocol.TYPE_INT_1BYTE:
                    return readFloatByNums(1);
                case MarkCompressProtocol.TYPE_INT_2BYTE:
                    return readFloatByNums(2);
                case MarkCompressProtocol.TYPE_INT_3BYTE:
                    return readFloatByNums(3);
                case MarkCompressProtocol.TYPE_INT_4BYTE:
                    return mIn.getFloat32();
                default:
                    throw ProtocolException.newTypeException(b);
            }
        }

        override public function readDouble():Number
        {
            var b:int = mIn.getUint8();
            switch (b)
            {
                case MarkCompressProtocol.TYPE_NULL_OR_ZERO_OR_FALSE:
                    return 0;
                case MarkCompressProtocol.TYPE_INT_1BYTE:
                    return readDoubleByNums(1);
                case MarkCompressProtocol.TYPE_INT_2BYTE:
                    return readDoubleByNums(2);
                case MarkCompressProtocol.TYPE_INT_3BYTE:
                    return readDoubleByNums(3);
                case MarkCompressProtocol.TYPE_INT_4BYTE:
                    return readDoubleByNums(4);
                case MarkCompressProtocol.TYPE_INT_5BYTE:
                    return readDoubleByNums(5);
                case MarkCompressProtocol.TYPE_INT_6BYTE:
                    return readDoubleByNums(6);
                case MarkCompressProtocol.TYPE_INT_7BYTE:
                    return readDoubleByNums(7);
                case MarkCompressProtocol.TYPE_INT_8BYTE:
                    return readDoubleByNums(8);
                default:
                    throw ProtocolException.newTypeException(b);
            }
        }

        override public function readString():String
        {
            var b:int = mIn.getUint8();
            if (b == MarkCompressProtocol.TYPE_STRING)
            {
                var length:int = readInt();
                return mIn.readUTFBytes(length);
//                return mIn.readMultiByte(length, mCharset);
            }
            else if (b == MarkCompressProtocol.TYPE_NULL_OR_ZERO_OR_FALSE)
            {
                return null;
            }
            else
            {
                throw  ProtocolException.newTypeException(b);
            }
        }

        override public function readBytes():Byte
        {
//            var b:int = mIn.getUint8();
//            if (b == MarkCompressProtocol.TYPE_BYTES)
//            {
//                var length:int = readInt();
//                var ba:ByteArray = new ByteArray();
//                mIn.readBytes(ba, 0, length);
//                return ba;
//            }
//            else if (b == MarkCompressProtocol.TYPE_NULL_OR_ZERO_OR_FALSE)
//            {
//                return null;
//            }
//            else
//            {
//                throw  ProtocolException.newTypeException(b);
//            }

            throw new AbstractMethodError();
        }

        private function readFloatByNums(bitNums:int):Number
        {
            mDoubleBuffer.length = 0;
            mDoubleBuffer.pos = 0;
            var i:int;
            var len:int = 4 - bitNums;
            if (mIn.endian == Byte.BIG_ENDIAN)
            {
                for (i = 0; i < len; i++)
                {
                    mDoubleBuffer.writeByte(0);
                }
                mDoubleBuffer.writeArrayBuffer(mIn.buffer, mIn.pos, bitNums);
//                mIn.readBytes(mDoubleBuffer, mDoubleBuffer.position, bitNums);
            }
            else
            {
                mDoubleBuffer.writeArrayBuffer(mIn.buffer, mIn.pos, bitNums);
//                mIn.readBytes(mDoubleBuffer, 0, bitNums);
                mDoubleBuffer.pos = bitNums;
                for (i = 0; i < len; i++)
                {
                    mDoubleBuffer.writeByte(0);
                }
            }
            mDoubleBuffer.pos = 0;
            return mDoubleBuffer.getFloat32();
        }

        private function readDoubleByNums(bitNums:int):Number
        {
//            mDoubleBuffer.length = 0;
//            mDoubleBuffer.pos = 0;
//            var i:int;
//            var len:int = 8 - bitNums;
//            if (mIn.endian == Byte.BIG_ENDIAN)
//            {
//                for (i = 0; i < len; i++)
//                {
//                    mDoubleBuffer.writeByte(0);
//                }
//                mDoubleBuffer.writeArrayBuffer(mIn.buffer, mIn.pos, bitNums);
////                mIn.readBytes(mDoubleBuffer, mDoubleBuffer.position, bitNums);
//            }
//            else
//            {
//                mDoubleBuffer.writeArrayBuffer(mIn.buffer, mIn.pos, bitNums);
////                mIn.readBytes(mDoubleBuffer, 0, bitNums);
//                mDoubleBuffer.pos = bitNums;
//                for (i = 0; i < len; i++)
//                {
//                    mDoubleBuffer.writeByte(0);
//                }
//            }
//            mDoubleBuffer.pos = 0;
//            return mDoubleBuffer.buffer.readDouble();
            throw new AbstractMethodError();
        }


        private function getInt64Five():Number
        {
            var low:Number;
            var high:Number;
            if (mIn.endian == Byte.BIG_ENDIAN)
            {
                high = mIn.getUint8();
                low = mIn.getUint32();
            }
            else
            {
                low = mIn.getUint32();
                high = mIn.getUint8();
            }
            return high * 4294967296.0 + low;
        }

        private function getInt64Six():Number
        {
            var low:Number;
            var high:Number;
            if (mIn.endian == Byte.BIG_ENDIAN)
            {
                high = mIn.getUint16();
                low = mIn.getUint32();
            }
            else
            {
                low = mIn.getUint32();
                high = mIn.getUint16();
            }
            return high * 4294967296.0 + low;
        }

        /**7位*/
        private function getInt64Seven():Number
        {
            var low:Number;
            var high:Number;
            if (mIn.endian == Byte.BIG_ENDIAN)
            {
                high = Utils.getThree(mIn);
                low = mIn.getUint32();
            }
            else
            {
                low = mIn.getUint32();
                high = Utils.getThree(mIn);
            }
            return high * 4294967296.0 + low;
        }

        private function getInt64Long():Number
        {
            var low:Number;
            var high:Number;
            if (mIn.endian == Byte.BIG_ENDIAN)
            {
                high = mIn.getUint32();
                low = mIn.getUint32();
            }
            else
            {
                low = mIn.getUint32();
                high = mIn.getUint32();
            }
            if (high > 0x1fffff)
            {
                throw ProtocolException.newIn64ExceptionByHight(high);
            }
            return high * 4294967296 + low;
        }
    }
}