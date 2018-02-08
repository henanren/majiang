package mj.data;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 1	推倒胡	即基本平和
 * 2	对对和	和牌时的14张牌有4个“刻子”和一个“对子”。	跟四川麻将一样，4坎牌是碰或者每坎牌都是3张一样的牌，加上一对相同的牌，例：3条x3+4万x3+9筒x3+1条x3+9万x2
 * 2	七对子	14张牌为7个对子的和牌方法叫“七对子”	例：2条x2+5条x2+8条x2+1万x2+3万x2+八筒x2+发财x2
 * 40	天和	庄家在一局还没有出过牌的时候构成可和牌牌型时称“天和”。
 * 40	地和	闲家在一局还没有出过牌的时候构成可和牌牌型时称“地和”
 * @author zuoge85@gmail.com on 2016/11/4.
 */
public enum BaseFanType {
    JI_HU("鸡胡", 1),
    ZI_MO("自摸", 2),
    DUI_DUI_HU("对对胡", 2),
    QI_DUI("七对子", 2),
    TIAN_HU("天胡", 40),
    HUI_ER_GANG("混杠", 40),
    DI_HU("地胡", 40);



    public static final Map<BaseFanType, FanInfo> baseFanMap = Arrays.stream(BaseFanType.values()).collect(
            Collectors.toMap(r-> r, v->new FanInfo(v.name, v.fan))
    );

    public final int fan;
    public final String name;

    BaseFanType(String name, int fan) {
        this.fan = fan;
        this.name = name;
    }
}
