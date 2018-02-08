//牌局结束
message{
    int queTou;
    int[] shunZi;
    int[] keZi;
    int[] shouPai;

    //会儿变化
    int[] huiErBian;

    int[] anGang;//已经显示的暗杠，如果是自己的则全部显示,不是自己的且如果还不能显示，那么传递-1
    int[] xiaoMingGang;
    int[] daMingGang;
    int[] peng;
    int[] chi;//3个一组一组

    String baseFanType;
    String fanString;
    int fan;
    String userName;
    int score;//改变值
    int guaFengXiaYu;
}