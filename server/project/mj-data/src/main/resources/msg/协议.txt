isnowfox msg


协议使用的工具生成java 和 as代码
isnowfox-serialize

协议支持基本类型如下
boolean int long double bytes

bytes[]对应java 的bytes[]

boolean[] int[] long[] double[] 这些数组对应 java的 boolean[] int[] long[] double[]

对于对象的支持，支持使用消息对象，如果不是一个包的那么需要写全部限定名
对象也可以有数组如 Abc[],对应java 的ArrayLiat<Abc>而不是Abc[]


生成消息字段默认全部是null 和 0 false 这些初始值

类型对照
isnowfox msg					java						as

boolean							boolean				Boolean
int									int						int
long									long						Number
double								double					Number
bytes[]								byte[]					BateArray
String								String					String


int[]									int[]						Vector.<int>
boolean[]							boolean[]				Vector.<Boolean>
long[]								long[]					Vector.<Number>
double[]							double[]				Vector.<Number>
String[]								String[]					Vector.<String>

对于对象的支持，支持使用消息对象，如果不是一个包的那么需要写全部限定名
对象也可以有数组如 Abc[],对应java 的ArrayLiat<Abc>而不是Abc[]


声明包！如果不声明使用文件夹名称
javaPackage = copy
asPackage = copy