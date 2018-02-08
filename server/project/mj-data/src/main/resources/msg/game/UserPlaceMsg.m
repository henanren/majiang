//一局麻将的信息
handler=client
message{
    int[] shouPai;
    int shouPaiLen;//别人的信息只显示手牌数量

    int[] anGang;//已经显示的暗杠，如果是自己的则全部显示,不是自己的且如果还不能显示，那么传递-1
    int[] xiaoMingGang;
    int[] daMingGang;
    int[] peng;
    int[] chi;//3个一组一组
    int[] outPai;//打出的牌
}