package com.isnowfox.core.io
{
    import com.isnowfox.core.utils.AbstractMethodError;

    import laya.utils.Byte;

    /**
     * 创建这个对象之后修改 endian会出现bug
     */
    public class MarkCompressOutput extends AbstractOutput
    {
        /**写入字符串的缓冲*/
        private const mWriteBuffer:Byte = new Byte();

        public static function create(out:Byte):Output
        {
            return new MarkCompressOutput(out, MarkCompressProtocol.DEFAULT_CHARSET);
        }

        public static function createByCharset(out:Byte, charset:String):Output
        {
            return new MarkCompressOutput(out, charset);
        }

        public function MarkCompressOutput(out:Byte, charset:String)
        {
            mWriteBuffer.endian = out.endian;
            super(out, charset);
        }

        override public function writeBoolean(v:Boolean):void
        {
            mOut.writeByte(v ? MarkCompressProtocol.TYPE_TRUE : MarkCompressProtocol.TYPE_NULL_OR_ZERO_OR_FALSE);
        }

        override public function writeInt(v:int):void
        {
            if (v > MarkCompressProtocol.TYPE_MIN - MarkCompressProtocol.TYPE_MINUS && v < MarkCompressProtocol.TYPE_MAX - MarkCompressProtocol.TYPE_MINUS)
            {
                mOut.writeByte(v + MarkCompressProtocol.TYPE_MINUS);
            }
            else if (v >>> 8 == 0)
            {
                mOut.writeByte(MarkCompressProtocol.TYPE_INT_1BYTE);
                mOut.writeByte(v);
            }
            else if (v >>> 16 == 0)
            {
                mOut.writeByte(MarkCompressProtocol.TYPE_INT_2BYTE);
                mOut.writeInt16(v);
            }
            else if (v >>> 24 == 0)
            {
                mOut.writeByte(MarkCompressProtocol.TYPE_INT_3BYTE);
                Utils.putThree(mOut, v);
            }
            else
            {
                mOut.writeByte(MarkCompressProtocol.TYPE_INT_4BYTE);
                mOut.writeInt32(v);
            }
        }

        override public function writeLong(v:Number):void
        {
            //return new Int64(n, Math.floor(n / 4294967296.0));
            if (v < 0 || v != Math.floor(v))
            {
                throw  ProtocolException.newIn64Exception(v);
            }
            var low:Number = Number(v);
            var high:Number = Number(v / 4294967296.0);
            if (high == 0 || ( v > MarkCompressProtocol.TYPE_MIN - MarkCompressProtocol.TYPE_MINUS && v < MarkCompressProtocol.TYPE_MAX - MarkCompressProtocol.TYPE_MINUS))
            {
                writeInt(low);
            }
            else
            {
                if (high >>> 8 == 0)
                {
                    mOut.writeByte(MarkCompressProtocol.TYPE_INT_5BYTE);
                    if (mOut.endian == Byte.BIG_ENDIAN)
                    {
                        mOut.writeByte(high);
                        mOut.writeInt32(low);
                    }
                    else
                    {
                        mOut.writeInt32(low);
                        mOut.writeByte(high);
                    }
                }
                else if (high >>> 16 == 0)
                {
                    mOut.writeByte(MarkCompressProtocol.TYPE_INT_6BYTE);
                    if (mOut.endian == Byte.BIG_ENDIAN)
                    {
                        mOut.writeInt16(high);
                        mOut.writeInt32(low);
                    }
                    else
                    {
                        mOut.writeInt32(low);
                        mOut.writeInt16(high);
                    }
                }
                else if (high >>> 24 == 0)
                {
                    mOut.writeByte(MarkCompressProtocol.TYPE_INT_7BYTE);
                    if (mOut.endian == Byte.BIG_ENDIAN)
                    {
                        Utils.putThree(mOut, high);
                        mOut.writeInt32(low);
                    }
                    else
                    {
                        mOut.writeInt32(low);
                        Utils.putThree(mOut, high);
                    }
                }
                else
                {
                    mOut.writeByte(MarkCompressProtocol.TYPE_INT_8BYTE);
                    if (mOut.endian == Byte.BIG_ENDIAN)
                    {
                        mOut.writeInt32(high);
                        mOut.writeInt32(low);
                    }
                    else
                    {
                        mOut.writeInt32(low);
                        mOut.writeInt32(high);
                    }
                }
            }
        }

        override public function writeFloat(v:Number):void
        {
            if (v == 0)
            {
                mOut.writeByte(MarkCompressProtocol.TYPE_NULL_OR_ZERO_OR_FALSE);
                return;
            }
            mWriteBuffer.length = 0;
            mWriteBuffer.pos = 0;
            mWriteBuffer.writeFloat32(v);

            var freeNums:int = 0;
            var i:int;
            if (mOut.endian == Byte.BIG_ENDIAN)
            {
                for (i = 0; i < mWriteBuffer.length; i++)
                {
                    if (mWriteBuffer[i] == 0)
                    {
                        freeNums++;
                    }
                    else
                    {
                        break;
                    }
                }
                mOut.writeByte(MarkCompressProtocol.TYPE_INT_1BYTE - (3 - freeNums));
//                mOut.writeBytes(mWriteBuffer, freeNums);

                mOut.writeArrayBuffer(mWriteBuffer.buffer, 0, freeNums)
            }
            else
            {
                for (i = mWriteBuffer.length - 1; i >= 0; i--)
                {
                    if (mWriteBuffer[i] == 0)
                    {
                        freeNums++;
                    }
                    else
                    {
                        break;
                    }
                }
                mOut.writeByte(MarkCompressProtocol.TYPE_INT_1BYTE - (3 - freeNums));
//                mOut.writeBytes(mWriteBuffer, 0, 4 - freeNums);

                mOut.writeArrayBuffer(mWriteBuffer.buffer, 0, 4 - freeNums)
            }
        }

        override public function writeDouble(v:Number):void
        {
//            if (v == 0)
//            {
//                mOut.writeByte(MarkCompressProtocol.TYPE_NULL_OR_ZERO_OR_FALSE);
//                return;
//            }
//            mWriteBuffer.length = 0;
//            mWriteBuffer.position = 0;
//            mWriteBuffer.writeDouble(v);
//
//            var freeNums:int = 0;
//            var i:int;
//            if (mOut.endian == Endian.BIG_ENDIAN)
//            {
//                for (i = 0; i < mWriteBuffer.length; i++)
//                {
//                    if (mWriteBuffer[i] == 0)
//                    {
//                        freeNums++;
//                    }
//                    else
//                    {
//                        break;
//                    }
//                }
//                mOut.writeByte(MarkCompressProtocol.TYPE_INT_1BYTE - (7 - freeNums));
//                mOut.writeBytes(mWriteBuffer, freeNums);
//            }
//            else
//            {
//                for (i = mWriteBuffer.length - 1; i >= 0; i--)
//                {
//                    if (mWriteBuffer[i] == 0)
//                    {
//                        freeNums++;
//                    }
//                    else
//                    {
//                        break;
//                    }
//                }
//                mOut.writeByte(MarkCompressProtocol.TYPE_INT_1BYTE - (7 - freeNums));
//                mOut.writeBytes(mWriteBuffer, 0, 8 - freeNums);
//            }
            throw new AbstractMethodError();
        }

        override public function writeString(s:String):void
        {
            if (s == null)
            {
                mOut.writeByte(MarkCompressProtocol.TYPE_NULL_OR_ZERO_OR_FALSE);
            }
            else
            {
                mOut.writeByte(MarkCompressProtocol.TYPE_STRING);
                mWriteBuffer.length = 0;
                mWriteBuffer.pos = 0;
//                mWriteBuffer.writeMultiByte(s, mCharset);
                mWriteBuffer.writeUTFBytes(s);
                writeInt(mWriteBuffer.length);

                mOut.writeArrayBuffer(mWriteBuffer.buffer, 0, mWriteBuffer.length);
//                mOut.writeBytes(mWriteBuffer, 0, mWriteBuffer.length);
//				mOut.w
            }
        }

        override public function writeBytes(bs:Byte):void
        {
//            if (bs == null)
//            {
//                mOut.writeByte(MarkCompressProtocol.TYPE_NULL_OR_ZERO_OR_FALSE);
//            }
//            else
//            {
//                mOut.writeByte(MarkCompressProtocol.TYPE_BYTES);
//                writeInt(bs.length);
//                mOut.writeBytes(bs, 0, bs.length);
//            }
////			if(bs == null){
////				out.write(TYPE_NULL_OR_ZERO_OR_FALSE);
////				return;
////			}
////			out.write(TYPE_STRING);
////			writeInt(bs.length);
////			out.write(bs);
            throw new AbstractMethodError();
        }

    }
}