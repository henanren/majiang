package com.isnowfox.core.test
{
    import com.isnowfox.core.console.Console;
    import com.isnowfox.core.utils.StringUtils;

    /**
     * @author zuoge85@gmail.com on 16/9/23.
     */
    public class AssertUtils
    {
        public static function assertNotNull(obj:*):void
        {
            if (obj === null)
            {
                fail("obj isNull");
            }
        }

        public static function assertNull(obj:*):void
        {
            if (obj !== null)
            {
                fail(StringUtils.format("obj:{0} is not null", obj));
            }
        }

        public static function assertEquals(obj0:*, obj1:*):void
        {
            if (obj0 != obj1 && (!(isNaN(obj0) && isNaN(obj1))))
            {
                fail(StringUtils.format("obj0:{0} is not equal to obj:{1}", obj0, obj1));
            }
        }

        public static function assertNotEquals(obj0:*, obj1:*):void
        {
            if (obj0 == obj1)
            {
                fail(StringUtils.format("obj0:{0} is equal to obj:{1}", obj0, obj1));
            }
        }


        public static function assertTrue(obj:*):void
        {
            if (!obj)
            {
                fail(StringUtils.format("obj0:{0} is not equal false", obj));
            }
        }

        public static function assertFalse(obj:*):void
        {
            if (obj)
            {
                fail(StringUtils.format("obj0:{0} is equal true", obj));
            }
        }

        private static function fail(message:String):void
        {
            Console.log(message);
            throw new AssertError(message);
        }
    }
}
