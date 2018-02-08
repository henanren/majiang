package com.isnowfox.core.io
{
    /**
     * 只在协议内部使用，所以肯定不强大，
     */
    public class Int64
    {
        private var mLow:uint;
        private var mHigh:uint;

        public function Int64(low:uint, high:uint)
        {
            mLow = low;
            mHigh = high;
        }

        public static function fromNumber(n:Number):Int64
        {
            return new Int64(n, Math.floor(n / 4294967296.0));
        }

        public final function toNumber():Number
        {
            return mHigh * 4294967296.0 + mLow;
        }
    }
}