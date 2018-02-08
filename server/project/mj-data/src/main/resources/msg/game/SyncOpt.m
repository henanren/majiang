//同步操作
//FA:发牌，OUT:打牌,OUT_OK:打牌成功，没人用这个哎,CHI:吃,PENG:碰,AN_GANG:暗杠牌,XIAO_MING_GANG:暗杠牌,DA_MING_GANG:暗杠牌,HU:胡牌
handler=client
message{
    String opt;
    int index;//位置
    int pai;
    int[] chi;
}