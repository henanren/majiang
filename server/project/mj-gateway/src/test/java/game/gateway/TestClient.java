package game.gateway;

import com.isnowfox.core.junit.BaseTest;

public class TestClient extends BaseTest {
//	public void test() throws Exception{
//		Socket socket =new Socket("127.0.0.1", 8001);
//		OutputStream out = socket.getOutputStream();
//		final InputStream in = socket.getInputStream();
//		TestMessage msg = new TestMessage();
//		msg.setLog(23123);
////		client.sendMessage(ta);
////		Thread.sleep(100);
//		(new Thread(){
//			@Override
//			public void run() {
//				try {
//					for (;;) {
//						System.out.println(in.read());
//					}
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}).start();
//		for (int i = 0; i < 10000; i++) {
//			
//			//DataOutputStream dout = new DataOutputStream(out);
//        	ByteArrayOutputStream bo = new ByteArrayOutputStream();
//        	Output o = MarkCompressOutput.create(bo);
//        	
//        	o.writeInt(msg.getMessageType());
//        	o.writeInt(msg.getMessageId());
//        	msg.encode(o);
//        	
//        	byte[] arr = bo.toByteArray();
//        	int len  = arr.length;
//        	out.write((len >>> 16));
//	        out.write((len >>>  8));
//	        out.write((len >>>  0));
//	        out.write(0);
//	        System.out.println("发送一个包,len" + len);
//	        out.write(arr);
//	        out.flush();
//			Thread.sleep(100);
//		}
//		
//		socket.close();
//	}

    public void test() throws Exception {
//		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
//			@Override
//			public void uncaughtException(Thread t, Throwable e) {
//				log.error("error", e);
//			}
//		});
//		
//		SocketClient client = SocketClient.createMessageClient(MessageFactoryImpi.getInstance(), "127.0.0.1", 8001, new MessageHandler<Object>(){
//			@Override
//			public void onConnect(Session<Object> session) throws Exception {
//				
//			}
//
//			@Override
//			public void onDisconnect(Session<Object> session) throws Exception {
//				
//			}
//
//			@Override
//			public void onException(Session<Object> sessionl, Throwable cause)
//					throws Exception {
//				
//			}
//
//			@Override
//			public Session<Object> createSession(ChannelHandlerContext ctx)
//					throws Exception {
//				return null;
//			}
//
//			@Override
//			public void onMessage(Message msg) throws Exception {
//				System.out.println(msg);
//			}
//		});
//		client.connect(false);
//		for (int i = 0; i < 1; i++) {
//			TestMessage ta = new TestMessage();
//			ta.setLog(i+100000);
//			client.writeAndFlush(ta);
//			Thread.sleep(100);
//		}
//		
//		client.close();
    }
}
