package test
{
    import com.isnowfox.core.io.Input;
    import com.isnowfox.core.io.MarkCompressInput;
    import com.isnowfox.core.io.MarkCompressOutput;
    import com.isnowfox.core.io.Output;
    import com.isnowfox.core.test.AssertUtils;
    import com.isnowfox.core.test.TestCase;

    import laya.utils.Byte;

    public class IoTest extends TestCase
    {
        public static const MIN_VALUE:int = -2147483648;
        public static const MAX_VALUE:int = 2147483647;

        public function testBoolean():void
        {
            checkBoolean(true);
            checkBoolean(false);
        }

        public function testInt():void
        {
            checkInt(false, 0);
            checkInt(false, 0xFF);
            checkInt(false, -0xFF);

            checkInt(false, 0xFFF);
            checkInt(false, -0xFFF);

            checkInt(false, 0xFFFF);
            checkInt(false, -0xFFFF);

            checkInt(false, 0xFFFFF);
            checkInt(false, -0xFFFFF);

            checkInt(false, 0xFFFFFF);
            checkInt(false, -0xFFFFFF);

            checkInt(false, 0xFFFFFFF);
            checkInt(false, -0xFFFFFFF);

            checkInt(false, 0xFFFFFFFF);
            checkInt(false, -0xFFFFFFFF);
            checkIntByMaxAndMin(false, -1, 0);//非常没必要，boolean最多一个字节~~
            checkIntByMaxAndMin(false, -32768, 32768);
            checkInt(false, MAX_VALUE);
            checkInt(false, MIN_VALUE);
        }

        public function testIntBg():void
        {
            checkIntByMaxAndMin(true, -1, 0);//非常没必要，boolean最多一个字节~~
            checkIntByMaxAndMin(true, -32768, 32768);
            checkInt(true, MAX_VALUE);
            checkInt(true, MIN_VALUE);
            checkInt(true, MAX_VALUE + 1);
            checkInt(true, MIN_VALUE + 1);
            checkInt(true, MAX_VALUE - 1);
            checkInt(true, MIN_VALUE - 1);
        }

        public function testString():void
        {
            checkString("但发发呆书法大赛发达省份大师傅地方苏打粉23！@￥#@￥#@%43523!@$#@%$$#%^%$^%");
            checkString("但发发呆焱书法大赛发达省份大师傅地方苏打粉23！@￥#@￥#@%43523!@$#@%$$#%^%$^%");
            checkString("");
            checkString(null);
        }

//        public function testDouble():void
//        {
//            tsDouble(false);
//        }
//
//        public function testDoubleBe():void
//        {
//            tsDouble(true);
//        }

        public function testDouble():void
        {
            tsFloat32(false);
        }

        public function testDoubleBe():void
        {
            tsFloat32(true);
        }

        public function testLong():void
        {
            tsLong(false);
        }

        public function testLongBe():void
        {
            tsLong(true);
        }

        public function tsFloat32(isBigEndian:Boolean):void
        {
            checkFloat32(isBigEndian, 0);
            checkFloat32(isBigEndian, 1 / 3);
            checkFloat32(isBigEndian, 255);
            checkFloat32(isBigEndian, 65536);
            checkFloat32ByMaxAndMin(isBigEndian, -1, 0);//非常没必要，boolean最多一个字节~~
            checkFloat32ByMaxAndMin(isBigEndian, -32768, 32768);

            checkFloat32(isBigEndian, MAX_VALUE);
            checkFloat32(isBigEndian, MIN_VALUE);

            checkFloat32(isBigEndian, Number.MAX_VALUE);
            checkFloat32(isBigEndian, Number.MIN_VALUE);

            checkFloat32(isBigEndian, Number.NEGATIVE_INFINITY);
            checkFloat32(isBigEndian, Number.POSITIVE_INFINITY);

            checkFloat32(isBigEndian, Number.NaN);
        }

//        public function tsDouble(isBigEndian:Boolean):void
//        {
//            checkDouble(isBigEndian, 0);
//            checkDouble(isBigEndian, 1 / 3);
//            checkDouble(isBigEndian, 255);
//            checkDouble(isBigEndian, 65536);
//            checkDoubleByMaxAndMin(isBigEndian, -1, 0);//非常没必要，boolean最多一个字节~~
//            checkDoubleByMaxAndMin(isBigEndian, -32768, 32768);
//
//            checkDouble(isBigEndian, MAX_VALUE);
//            checkDouble(isBigEndian, MIN_VALUE);
//
//            checkDouble(isBigEndian, Number.MAX_VALUE);
//            checkDouble(isBigEndian, Number.MIN_VALUE);
//
//            checkDouble(isBigEndian, Number.NEGATIVE_INFINITY);
//            checkDouble(isBigEndian, Number.POSITIVE_INFINITY);
//
//            checkDouble(isBigEndian, Number.NaN);
//        }


        public function tsLong(isBigEndian:Boolean):void
        {

            try
            {
                checkLong(isBigEndian, 1 / 3);
                AssertUtils.assertTrue(false);
            }
            catch (error:Error)
            {
                AssertUtils.assertNotNull(error);
            }
            checkLong(isBigEndian, 255);
            checkLong(isBigEndian, 65536);
            checkLongByMaxAndMin(isBigEndian, 0, 32768 + 1);

            checkLong(isBigEndian, MAX_VALUE);
            checkLongAndStr(isBigEndian, 0x1fffffffffffff, "1fffffffffffff");//最大53位整数
            checkLongAndStr(isBigEndian, 0x20000000000000, "20000000000000");
//			checkLongAndStr(isBigEndian, 0x7fffffffffffffff,"7fffffffffffffff");//最大64位整数
//			checkLong(isBigEndian, MIN_VALUE);

//			checkLong(isBigEndian, Number.MAX_VALUE);
//			checkLong(isBigEndian, Number.MIN_VALUE);

//			checkLong(isBigEndian, Number.NEGATIVE_INFINITY);
//			checkLong(isBigEndian, Number.POSITIVE_INFINITY);
//			
//			checkLong(isBigEndian, Number.NaN);
        }


        public function checkBoolean(isBigEndian:Boolean):void
        {
            var t:Boolean = true;
            var f:Boolean = false;

            var out:Byte = new Byte();
            out.endian = isBigEndian ? Byte.BIG_ENDIAN : Byte.LITTLE_ENDIAN;

            var o:Output = MarkCompressOutput.create(out);
            o.writeBoolean(t);
            o.writeBoolean(f);

            out.pos = 0;
            var i:Input = MarkCompressInput.create(out);

            AssertUtils.assertEquals(t, i.readBoolean());
            AssertUtils.assertEquals(f, i.readBoolean());
        }


        public function checkString(str:String):void
        {
            var t:Boolean = true;
            var f:Boolean = false;

            var out:Byte = new Byte();

            var o:Output = MarkCompressOutput.create(out);
            o.writeString(str);

            out.pos = 0;
            var i:Input = MarkCompressInput.create(out);

            AssertUtils.assertEquals(str, i.readString());
        }


        public function checkInt(isBigEndian:Boolean, v:int):void
        {
            v = v & 0xFFFFFFFF;

            var out:Byte = new Byte();
            out.endian = isBigEndian ? Byte.BIG_ENDIAN : Byte.LITTLE_ENDIAN;

            var o:Output = MarkCompressOutput.create(out);
            o.writeInt(v);

            out.pos = 0;
            var i:Input = MarkCompressInput.create(out);

            AssertUtils.assertEquals(v, i.readInt());
        }

        public function checkIntByMaxAndMin(isBigEndian:Boolean, min:int, max:int):void
        {
            min = min & 0xFFFFFFFF;
            max = max & 0xFFFFFFFF;
            var out:Byte = new Byte();
            out.endian = isBigEndian ? Byte.BIG_ENDIAN : Byte.LITTLE_ENDIAN;

            var o:Output = MarkCompressOutput.create(out);
            var i:int = 0;
            for (i = min; i <= max; i++)
            {
                o.writeInt(i);
            }

            out.pos = 0;
            var input:Input = MarkCompressInput.create(out);

            for (i = min; i <= max; i++)
            {
                AssertUtils.assertEquals(i, input.readInt());
            }
        }



        public function checkFloat32(isBigEndian:Boolean, v:Number):void
        {

            v = convertFloat32(v);

            var out:Byte = new Byte();
            out.endian = isBigEndian ? Byte.BIG_ENDIAN : Byte.LITTLE_ENDIAN;

            var o:Output = MarkCompressOutput.create(out);
            o.writeFloat(v);

            out.pos = 0;
            var i:Input = MarkCompressInput.create(out);

            AssertUtils.assertEquals(v, i.readFloat());
        }

        public function checkFloat32ByMaxAndMin(isBigEndian:Boolean, min:Number, max:Number):void
        {
            min = convertFloat32(min);
            max = convertFloat32(max);
            
            var out:Byte = new Byte();
            out.endian = isBigEndian ? Byte.BIG_ENDIAN : Byte.LITTLE_ENDIAN;

            var o:Output = MarkCompressOutput.create(out);
            var i:Number = 0;
            for (i = min; i <= max; i++)
            {
                o.writeFloat(i);
            }

            out.pos = 0;
            var input:Input = MarkCompressInput.create(out);

            for (i = min; i <= max; i++)
            {
                AssertUtils.assertEquals(i, input.readFloat());
            }
        }


//        public function checkDouble(isBigEndian:Boolean, v:Number):void
//        {
//            var out:Byte = new Byte();
//            out.endian = isBigEndian ? Byte.BIG_ENDIAN : Byte.LITTLE_ENDIAN;
//
//            var o:Output = MarkCompressOutput.create(out);
//            o.writeDouble(v);
//
//            out.pos = 0;
//            var i:Input = MarkCompressInput.create(out);
//
//            AssertUtils.assertEquals(v, i.readDouble());
//        }
//
//        public function checkDoubleByMaxAndMin(isBigEndian:Boolean, min:Number, max:Number):void
//        {
//            var out:Byte = new Byte();
//            out.endian = isBigEndian ? Byte.BIG_ENDIAN : Byte.LITTLE_ENDIAN;
//
//            var o:Output = MarkCompressOutput.create(out);
//            var i:Number = 0;
//            for (i = min; i <= max; i++)
//            {
//                o.writeDouble(i);
//            }
//
//            out.pos = 0;
//            var input:Input = MarkCompressInput.create(out);
//
//            for (i = min; i <= max; i++)
//            {
//                AssertUtils.assertEquals(i, input.readDouble());
//            }
//        }

        public function checkLong(isBigEndian:Boolean, v:Number):void
        {
            var out:Byte = new Byte();
            out.endian = isBigEndian ? Byte.BIG_ENDIAN : Byte.LITTLE_ENDIAN;

            var o:Output = MarkCompressOutput.create(out);
            o.writeLong(v);

            out.pos = 0;
            var i:Input = MarkCompressInput.create(out);

            AssertUtils.assertEquals(v, i.readLong());
        }

        public function checkLongAndStr(isBigEndian:Boolean, v:Number, str:String):void
        {
            var out:Byte = new Byte();
            out.endian = isBigEndian ? Byte.BIG_ENDIAN : Byte.LITTLE_ENDIAN;

            var o:Output = MarkCompressOutput.create(out);
            o.writeLong(v);

            out.pos = 0;
            var i:Input = MarkCompressInput.create(out);

            var l:Number = i.readLong();
            AssertUtils.assertEquals(v, l);
            AssertUtils.assertEquals(str, l.toString(16));
        }

        public function checkLongByMaxAndMin(isBigEndian:Boolean, min:Number, max:Number):void
        {
            var out:Byte = new Byte();
            out.endian = isBigEndian ? Byte.BIG_ENDIAN : Byte.LITTLE_ENDIAN;

            var o:Output = MarkCompressOutput.create(out);
            var i:Number = 0;
            for (i = min; i <= max; i++)
            {
                o.writeLong(i);
            }

            out.pos = 0;
            var input:Input = MarkCompressInput.create(out);

            for (i = min; i <= max; i++)
            {
                AssertUtils.assertEquals(i, input.readLong());
            }
        }


        private function convertFloat32(v:Number):Number
        {
            var tempByte:Byte = new Byte();
            tempByte.writeFloat32(v);
            tempByte.pos = 0;
            return tempByte.getFloat32();
        }
    }
}