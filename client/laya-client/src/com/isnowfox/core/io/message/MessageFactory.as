package com.isnowfox.core.io.message
{
    public interface MessageFactory
    {
        function getMessage(type:int, id:int):Message;
    }
}