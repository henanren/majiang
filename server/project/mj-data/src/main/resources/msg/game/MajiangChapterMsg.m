//一局麻将的信息
handler=client
message{
    int freeLength;//剩余张数
    int baoliuLength;//保留张数
    int[] huiEr;//会儿牌
    String bianType;//变化类型！！
    int bianSource;//变化类型！！


    UserPlaceMsg[] userPlace;用户的牌信息
    int currentIndex;//当前操作用户
    int chapterNums;//局数, 0开始
    int chapterNumsMax;//局数, 0开始
    int quanIndex;//圈index 0 东 1南 2西 3北 逆时针顺序
    Îint zhuangIndex;//庄index 0 东 1南 2西 3北 逆时针顺序

    OperationCPGH optCpgh;
    OperationFaPai optFaPai;
    OperationOut optOut;
    SyncOptTime syncOptTime;
    GameChapterEnd gameChapterEnd;

    TingPai tingPai;
}