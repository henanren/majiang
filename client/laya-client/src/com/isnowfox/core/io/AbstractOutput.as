package com.isnowfox.core.io
{

    import com.isnowfox.core.utils.AbstractMethodError;

    import laya.utils.Byte;

    public class AbstractOutput implements Output
    {
        protected var mOut:Byte;
        protected var mCharset:String;

        public function AbstractOutput(out:Byte, charset:String)
        {
            mOut = out;
            mCharset = charset;
        }

        public function writeBoolean(v:Boolean):void
        {
            throw new AbstractMethodError();
        }

        public function writeInt(v:int):void
        {
            throw new AbstractMethodError();
        }

        public function writeLong(v:Number):void
        {
            throw new AbstractMethodError();
        }

        public function writeFloat(v:Number):void
        {
            throw new AbstractMethodError();
        }

        public function writeDouble(v:Number):void
        {
            throw new AbstractMethodError();
        }

        public function writeString(s:String):void
        {
            throw new AbstractMethodError();
        }

        public function writeBytes(bs:Byte):void
        {
            throw new AbstractMethodError();
        }


        public function writeByteBuf(bs:Byte):void
        {
            if (bs === null)
            {
                writeInt(-1);
            }
            else
            {
                mOut.writeArrayBuffer(bs.buffer, 0, bs.length);
            }
        }

        public function writeBooleanArray(v:Vector.<Boolean>):void
        {
            if (v == null)
            {
                writeInt(-1);
            }
            else
            {
                writeInt(v.length);
                var len:int = v.length;
                for (var i:int = 0; i < len; i++)
                {
                    writeBoolean(v[i]);
                }
            }
        }

        public function writeIntArray(v:Vector.<int>):void
        {
            if (v == null)
            {
                writeInt(-1);
            }
            else
            {
                writeInt(v.length);
                var len:int = v.length;
                for (var i:int = 0; i < len; i++)
                {
                    writeInt(v[i]);
                }
            }
        }

        public function writeLongArray(v:Vector.<Number>):void
        {
            if (v == null)
            {
                writeInt(-1);
            }
            else
            {
                writeInt(v.length);
                var len:int = v.length;
                for (var i:int = 0; i < len; i++)
                {
                    writeLong(v[i]);
                }
            }
        }

        public function writeFloatArray(v:Vector.<Number>):void
        {
            if (v == null)
            {
                writeInt(-1);
            }
            else
            {
                writeInt(v.length);
                var len:int = v.length;
                for (var i:int = 0; i < len; i++)
                {
                    writeFloat(v[i]);
                }
            }
        }

        public function writeDoubleArray(v:Vector.<Number>):void
        {
            if (v == null)
            {
                writeInt(-1);
            }
            else
            {
                writeInt(v.length);
                var len:int = v.length;
                for (var i:int = 0; i < len; i++)
                {
                    writeDouble(v[i]);
                }
            }
        }

        public function writeStringArray(v:Vector.<String>):void
        {
            if (v == null)
            {
                writeInt(-1);
            }
            else
            {
                writeInt(v.length);
                var len:int = v.length;
                for (var i:int = 0; i < len; i++)
                {
                    writeBoolean(v[i]);
                }
            }
        }
    }
}