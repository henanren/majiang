package com.isnowfox.core.utils
{
    /**
     * @author zuoge85@gmail.com on 16/9/23.
     */
    public class StringUtils
    {
        /** Formats a String in .Net-style, with curly braces ("{0}"). Does not support any
         *  number formatting options yet. */
        public static function format(format:String, ...args):String
        {
            // TODO: add number formatting options

            for (var i:int=0; i<args.length; ++i)
                format = format.replace(new RegExp("\\{"+i+"\\}", "g"), args[i]);

            return format;
        }

        
        public static function beginsWith(input:String, prefix:String):Boolean
        {
            return (prefix == input.substring(0, prefix.length));
        }
    }
}
