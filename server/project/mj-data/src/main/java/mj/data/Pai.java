package mj.data;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zuoge85@gmail.com on 16/10/17.
 */
public enum Pai {
    TONG_1(PaiType.TONG, 0, 1, "1筒"),
    TONG_2(PaiType.TONG, 1, 2, "2筒"),
    TONG_3(PaiType.TONG, 2, 3, "3筒"),
    TONG_4(PaiType.TONG, 3, 4, "4筒"),
    TONG_5(PaiType.TONG, 4, 5, "5筒"),
    TONG_6(PaiType.TONG, 5, 6, "6筒"),
    TONG_7(PaiType.TONG, 6, 7, "7筒"),
    TONG_8(PaiType.TONG, 7, 8, "8筒"),
    TONG_9(PaiType.TONG, 8, 9, "9筒"),

    TIAO_1(PaiType.TIAO, 9, 1, "1条"),
    TIAO_2(PaiType.TIAO, 10, 2, "2条"),
    TIAO_3(PaiType.TIAO, 11, 3, "3条"),
    TIAO_4(PaiType.TIAO, 12, 4, "4条"),
    TIAO_5(PaiType.TIAO, 13, 5, "5条"),
    TIAO_6(PaiType.TIAO, 14, 6, "6条"),
    TIAO_7(PaiType.TIAO, 15, 7, "7条"),
    TIAO_8(PaiType.TIAO, 16, 8, "8条"),
    TIAO_9(PaiType.TIAO, 17, 9, "9条"),


    WAN_1(PaiType.WAN, 18, 1, "1万"),
    WAN_2(PaiType.WAN, 19, 2, "2万"),
    WAN_3(PaiType.WAN, 20, 3, "3万"),
    WAN_4(PaiType.WAN, 21, 4, "4万"),
    WAN_5(PaiType.WAN, 22, 5, "5万"),
    WAN_6(PaiType.WAN, 23, 6, "6万"),
    WAN_7(PaiType.WAN, 24, 7, "7万"),
    WAN_8(PaiType.WAN, 25, 8, "8万"),
    WAN_9(PaiType.WAN, 26, 9, "9万"),

    //右 東、上 南、左 西、下北
    FENG_DONG(PaiType.FENG, 27, 10, "東"),
    FENG_NAN(PaiType.FENG, 28, 10, "南"),
    FENG_XI(PaiType.FENG, 29, 10, "西"),
    FENG_BEI(PaiType.FENG, 30, 10, "北"),

    //中、發、白
    SANYUAN_ZHONG(PaiType.SANYUAN, 31, 10, "中"),
    SANYUAN_FA(PaiType.SANYUAN, 32, 10, "發"),
    SANYUAN_BEI(PaiType.SANYUAN, 33, 10, "白");

    /**
     * 有牌，但是不知道是啥
     */
    public static final int HAS_PAI_INDEX = -2;
    public static final int NOT_PAI_INDEX = -1;

    private static final Map<Integer, Pai> indexMap = Arrays.stream(Pai.values()).collect(
            Collectors.toMap(Pai::getIndex, v -> v)
    );

    private final PaiType type;
    private final int index;
    private final String name;
    private final int dian;

    Pai(PaiType type, int index, int dian, String name) {
        this.type = type;
        this.index = index;
        this.name = name;
        this.dian = dian;
    }

    public PaiType getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    private static final BiMap<Pai, Pai> nextMap = ImmutableBiMap.of(
            TONG_9, TONG_1, TIAO_9, TIAO_1, WAN_9, WAN_1, SANYUAN_BEI, FENG_DONG
    );

    private static final BiMap<Pai, Pai> prevMap = nextMap.inverse();

    public Pai next() {
        Pai next = nextMap.get(this);
        if (next == null) {
            int index = this.ordinal() + 1;
            next = Pai.values()[index];
            return next;
        } else {
            return next;
        }
    }

    public Pai prev() {
        Pai next = prevMap.get(this);
        if (next == null) {
            int index = this.ordinal() - 1;
            next = Pai.values()[index];
            return next;
        } else {
            return next;
        }
    }

    /**
     * 同类型的下一张,如果是末尾的，那么返回null
     */
    public Pai nextPaiType() {
        if (getType().isZhi()) {
            return null;
        }
        if (!nextMap.containsKey(this)) {
            int index = this.ordinal() + 1;
            return Pai.values()[index];
        } else {
            return null;
        }
    }

    /**
     * 同类型的上一张,如果是末尾的，那么返回null
     */
    public Pai prevPaiType() {
        if (getType().isZhi()) {
            return null;
        }
        if (!prevMap.containsKey(this)) {
            int index = this.ordinal() - 1;
            return Pai.values()[index];
        } else {
            return null;
        }
    }

    public int getDian() {
        return dian;
    }

    public static Pai fromIndex(int index) {
        return indexMap.get(index);
    }

    @Override
    public String toString() {
        return "Pai{" +
                "name='" + name + '\'' +
                '}';
    }
}
