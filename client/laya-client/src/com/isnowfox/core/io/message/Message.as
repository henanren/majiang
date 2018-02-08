package com.isnowfox.core.io.message
{
    import com.isnowfox.core.io.Input;
    import com.isnowfox.core.io.Output;

    public interface Message
    {
        function decode(in0:Input):void;

        function encode(out:Output):void;

        function getMessageType():int;

        function getMessageId():int;
    }
}