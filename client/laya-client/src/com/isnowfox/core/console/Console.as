package com.isnowfox.core.console
{
    import com.isnowfox.core.utils.StringUtils;

    /**
     * @author zuoge85@gmail.com on 16/9/23.
     */
    public class Console
    {
        /**
         * @see StringUtils.format
         */
        public static function log(format:String, ...args):void
        {
            var argArray:Array = [format];
            argArray.push.apply(argArray, args);
            trace(StringUtils.format.apply(null, argArray))
        }
    }
}
