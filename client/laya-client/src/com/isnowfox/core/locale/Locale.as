package com.isnowfox.core.locale
{
    import com.isnowfox.core.utils.StringUtils;

    import laya.utils.Dictionary;

    /**
     * 本地化管理器
     * @auther zuoge85@qq.com
     * @version Created by：16/5/1 21:18
     */
    public class Locale
    {
        private const dict:Dictionary = new Dictionary();
        public var language:String;

        public function Locale(language:String = "zh-CN")
        {
            this.language = language;
        }

        public function init(json:Object):void
        {
            for (var key:String in json)
            {
                dict[key] = json[key];
            }
        }

        public function get(key:String):String
        {
            return dict[key];
        }

        /** Formats a String in .Net-style, with curly braces ("{0}"). Does not support any
         *  number formatting options yet. */
        public function format(key:String, ...args):String
        {
            var value:String = dict[key];
            if (value == null)
            {
                value = key;
                trace("未找到国际化字符串key:", key, "直接使用key 作为value");
            }
            return StringUtils.format(value, args);
        }
    }
}
