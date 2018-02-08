package mj.utils
{
    /**
     * @author zuoge85@gmail.com on 2016/11/1.
     */
    public class ArrayUtils
    {
        public function ArrayUtils()
        {
        }

        public static function isEmpty(array:Array):Boolean
        {
            return array === null ? true : array.length === 0;
        }
    }
}
