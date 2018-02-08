//同步游戏信息
//0东 1南 2西 3北 逆时针顺序
handler=client
message{
     GameUserInfo[] sceneUser;
     boolean start;
     String roomCheckId;
     int leftChapterNums;
     int createUserId;
     MajiangChapterMsg chapter;
}