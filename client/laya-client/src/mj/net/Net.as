package mj.net
{
    import com.isnowfox.core.SingletonError;
    import com.isnowfox.core.console.Console;
    import com.isnowfox.core.io.Input;
    import com.isnowfox.core.io.MarkCompressInput;
    import com.isnowfox.core.io.MarkCompressOutput;
    import com.isnowfox.core.io.Output;
    import com.isnowfox.core.io.ProtocolException;
    import com.isnowfox.core.io.message.Message;
    import com.isnowfox.core.io.message.MessageHandler;
    import com.isnowfox.core.io.message.MessageProtocol;

    import laya.events.Event;
    import laya.net.Socket;
    import laya.utils.Byte;
    import laya.utils.Handler;

    import mj.net.handler.MessageHandlerFactory;
    import mj.net.message.MessageFactoryImpi;
    import mj.net.message.login.Ping;
    import mj.ui.dialog.Dialog;

    /**
     *
     * @author zuoge85
     */
    public final class Net
    {
        /**
         *
         * @default
         */
        public static const instance:Net = new Net();

        private var socket:Socket;//=new Socket();
        private var connectCallback:Function;
        private var repeatLogin:Boolean;
//		private var errorCallback:Function;

        private const buffer:Byte = new Byte();
//        private const readBuffer:Byte = new Byte();
//        private var messageLen:int = -1;
//        private var messageType:int = 0;

        private const msgFactory:MessageFactoryImpi = MessageFactoryImpi.instance;
        private const msgHandler:MessageHandlerFactory = MessageHandlerFactory.instance;

//        public static var decode:NetCode;

//        private var output:Byte;
        /**
         *
         * @throws SingletonError
         */
        public function Net()
        {
            if (instance != null)
            {
                throw new SingletonError("ResourceManager 是单例模式");
            }
        }

        public function init(url:String, connectCallback:Function):void
        {
//            Net.decode = new BaseNetCode();
            Console.log("准备连接服务器！{0}", url)
            if (socket)
            {
                socket.offAll();
                socket.close();
            }
            socket = new Socket();
            socket.connectByUrl(url);

//            output = socket.output;

            socket.on(Event.OPEN, this, connectHandler);
            socket.on(Event.CLOSE, this, closeHandler);
            socket.on(Event.MESSAGE, this, socketDataHandler);
            socket.on(Event.ERROR, this, ioErrorHandler);
            this.connectCallback = connectCallback;
        }

//		/**
//		 *
//		 * @param host
//		 * @param port
//		 * @param connectCallback
//		 * @param errorCallback
//		 */
//		public function connect(host:String, port:uint, connectCallback:Function, errorCallback:Function):void
//		{
//			this.connectCallback=connectCallback;
//			this.errorCallback=errorCallback;
////            Security.allowDomain("*");
////            Security.loadPolicyFile("http://" + host +"/crossdomain.xml");
//			socket.connect(host, port);
//		}

        private function closeHandler(event:Event):void
        {
            Console.log("closeHandler:", event);
            var msg:String = repeatLogin ? "你在其他地方登录？被挤掉线！\n如果不是本人请联系客服！" : "服务器断开连接！";
            Dialog.showMessage("服务器断开连接！",
                    Handler.create(this, function ():void
                    {
                        GameMain.current.restart()
                    })
            );
            Laya.timer.clearAll(this);
//            PromptMessageManager.instance.showPromptMessage("服务器断开连接，如果不刷新肯定不正常！\n");
        }

        private function connectHandler(event:Event):void
        {
            Console.log("connectHandler:", event);
            repeatLogin = false;
            connectCallback();
            Laya.timer.loop(10 * 1000, this, function ():void
            {

                this.write(Ping.create(Laya.timer.currTimer.toString()))
            });
        }

        private function ioErrorHandler(event:Event):void
        {
            Console.log("ioErrorHandler:", event);
            Dialog.showMessage("连接服务器,网络错误！\n" + event.text,
                    Handler.create(this, function ():void
                    {
                        GameMain.current.restart()
                    })
            );
//            PromptMessageManager.instance.showPromptMessage("连接服务器,网络错误！\n" + event.text);
//			errorCallback();
        }

        private function socketDataHandler(message:* = null):void
        {
            if (message is ArrayBuffer)
            {
                const readBuffer:Byte = new Byte(message);
                readBuffer.endian = Byte.BIG_ENDIAN;
//                Console.log("收到消息debug:{0}", debug(buffer));
                readBuffer.pos = 0;
                var in0:Input = MarkCompressInput.create(readBuffer);
                var type:int = in0.readInt();
                var id:int = in0.readInt();
                var msg:Message = msgFactory.getMessage(type, id);
                msg.decode(in0);

                Console.log(
                        "收到消息[type:{0},id:{1},msg:{2}]",
                        msg.getMessageType(), msg.getMessageId(),
                        msg
                );
                var handler:MessageHandler = msgHandler.getHandler(type, id);
                handler.handler(msg);
            }
            else
            {
                throw new Error("错误的类型,message" + message);
            }
        }

        /**
         *
         * @param msg
         * @throws ProtocolException
         */
        public function write(msg:Message):void
        {
            var cls:Class = MessageFactoryImpi.instance.getMessageClass(msg.getMessageType(), msg.getMessageId());

            if (cls == null)
            {
                throw Error("未知消息！");
            }

            if (!(msg is cls))
            {
                throw Error("错误的类型！" + cls);
            }
//            Console.log(msg,msg.getMessageId(), msg.getMessageType());
            const buffer:Byte = this.buffer;
            buffer.endian = Byte.BIG_ENDIAN;
            buffer.length = 0;
            buffer.pos = 0;

            var out:Output = MarkCompressOutput.create(buffer);
            out.writeInt(msg.getMessageType());
            out.writeInt(msg.getMessageId());
            msg.encode(out);
            if (buffer.length > MessageProtocol.MESSAGE_MAX)
            {
                throw new ProtocolException("严重错误，消息长度超过最大值:" + MessageProtocol.MESSAGE_MAX);
            }

            buffer.pos = 0;
            socket.send(buffer.buffer);
            Console.log(
                    "发送消息[type:{0},id:{1},len:{2}] msg:{3}",
                    msg.getMessageType(), msg.getMessageId(),
                    buffer.length, msg
            );
//            Console.log("发送消息debug:{0}", debug(buffer));
        }

        private static const HEX_CHARS:Array = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"];

        private static function debug(buffer:Byte):String
        {
            var string:String = "";
            buffer.pos = 0;
            for (var i:int = 0; i < buffer.length; i++)
            {
                var b:int = buffer.getByte();
                string += HEX_CHARS[b >> 8 & 0x0F];
                string += HEX_CHARS[b & 0x0F];
            }
            return string;
        }

        public function get connected():Boolean
        {
            return socket != null && socket.connected;
        }

        public function setRepeatLogin(b:Boolean):void
        {
            repeatLogin = b;
        }
    }
}

