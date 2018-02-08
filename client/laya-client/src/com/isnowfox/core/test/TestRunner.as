package com.isnowfox.core.test
{
    import com.isnowfox.core.console.Console;
    import com.isnowfox.core.utils.StringUtils;

    import laya.utils.ClassUtils;

    /**
     * @author zuoge85@gmail.com on 16/9/23.
     */
    public class TestRunner
    {
        private var _classes:Array = [];

        public function TestRunner()
        {

        }

        public function addCase(cls:Class):void
        {
            _classes.push(cls)
        }

        public function run():void
        {
            var len:uint = _classes.length;
            for (var i:int = 0; i < len; i++)
            {
                var testCls:Class = _classes[i];
                var test:TestCase = new testCls();

                test.setUp();
                //执行测试方法
                for (var key:String in test)
                {
                    if (test.hasOwnProperty(key))
                    {
                        if (StringUtils.beginsWith(key, "test"))
                        {
                            Console.log("{0} 开始测试:{1}", typeof(test), key);
                            test[key]();
                        }
                    }
                }

                test.tearDown();
            }
            Console.log("测试结束");
        }
    }
}
