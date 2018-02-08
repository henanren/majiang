package com.isnowfox.core.test
{
    /**
     * @author zuoge85@gmail.com on 16/9/23.
     */
    public class AssertError extends Error
    {
        public function AssertError(message:* = "", id:* = 0)
        {
            super(message, id);
        }
    }
}
