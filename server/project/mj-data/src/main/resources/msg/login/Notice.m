//通知
handler=client
message{
    //语言文件的key,或者内容字符串
    String key;
    String[] args;
    //0:横屏实时通知,1:悬停错误提示
    int type;
    //是否需要重新启动游戏
    boolean reboot;
}