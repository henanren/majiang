package com.isnowfox.io.serialize.tool;


public class ClassTest {
//	private  FastClass fc;
//	private  int index;
//	private  FastCC fcc;
//	private  FastConstructor fccc;
//	private  Constructor<TestAbc> cc;
//	TestAbc abc;
//	public ClassTest() throws InvocationTargetException, NoSuchMethodException, SecurityException{
//		//cg
//		fc = FastClass.create(TestAbc.class);
//		fcc = new FastCC(fc);
//		index =  fc.getIndex(new Class[]{});
//		fc.newInstance();
//		
//		
//		cc = TestAbc.class.getConstructor(null);
//		
//		fccc = fc.getConstructor(new Class[]{});
//	}
//	
////	public void test(){
////		TimeSpan ts = new TimeSpan();
////		TestAbc abc;
////		for (int i = 0; i < 1000000000; i++) {
////			try {
////				abc = cc.newInstance();
////			} catch (Exception e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////		}
////		System.out.println("test" + ts);
////	}
//	
//	public void testSS(){
//		for (long i = 0; i < 100000000l; i++) {
//			abc = new TestAbc();
//		}
//	}
//	
//	public void testCgi1(){
//		for (long i = 0; i < 100000000l; i++) {
//			try {
//				abc = (TestAbc)fcc.newInstance();
//			} catch (InvocationTargetException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	public void testCgi2(){
//		for (long i = 0; i < 100000000l; i++) {
//			try {
//				abc = (TestAbc)fc.newInstance(index, null);
//			} catch (InvocationTargetException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//	public void testCgi3(){
//		
//		for (long i = 0; i < 100000000l; i++) {
//			try {
//				abc = (TestAbc)fccc.newInstance();
//			} catch (InvocationTargetException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	public void testCgi4(){
//		
//		for (long i = 0; i < 1000000000l; i++) {
//			try {
//				abc = (TestAbc)fc.newInstance();
//			} catch (InvocationTargetException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	public final class FastCC{
//		private final FastClass fc;
//		private final int index;
//		public FastCC(FastClass fc) {
//			this.fc = fc;
//			this.index =  fc.getIndex(new Class[]{});
//		}
//		
//		public final Object newInstance() throws InvocationTargetException{
//			return fc.newInstance(index, null);
//		}
//	}
}
