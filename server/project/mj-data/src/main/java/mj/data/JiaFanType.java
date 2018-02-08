package mj.data;

import org.apache.commons.collections.map.CompositeMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 叠加番	1	庄胡	庄家胡牌
 * 1	自摸	自摸胡牌
 * 2	无“混儿”	牌中无“混儿”
 * 3	一条龙	和牌时手牌中凑齐一种花色的 “1--9”为“一条龙”	例：1到9条，加上任意一坎牌和一对相同的牌
 * 4	本混儿龙	只有一个同花色的混儿，按照无混儿计算
 * 2	大钓（即全球人）	吃碰12张牌，只剩一钓和牌。
 * 2	捉五魁	和牌时只和“五万”一张牌的牌型
 * 4	海底捞月	拿到海底牌最后一张时和牌称为“海底捞月”	注意：海底捞月的这张牌抓到后如果不和，必须打出去，不得换其他牌打出（可以选择放弃抓“海底牌”）。如果这张牌给别人放了铳，也要给别人加1番。
 * 5	杠上开花	听牌时开杠补得的牌为自摸牌称为“杠上开花”
 * 6	清一色	和牌的14张均为一种花色时称为“清一色”	例：1、2、3万+2、3、4万+5、6、7万+6、7、8万+9万x2
 * 2	 混一色	胡牌的14张只有一种花色和字牌。	例：1、2、3万+2、3、4万+北风x3+发财x3+9万x2
 * 2	门清	没有吃碰明杠过的牌型。
 * 40	混杠	摸齐四个“混儿牌”时自动和牌。（40番，不分庄家闲家） 	不分闲家
 * 8	字一色	胡牌中只有字牌。	北风x3+东风x3+南风x3+发财x3+红中x2
 *
 * @author zuoge85@gmail.com on 2016/11/4.
 */
public enum JiaFanType {
    ZHUANG_HU("庄胡", 1) {
        @Override
        public int compute(FanResult fanResult, ChapterEndResult chapterResult, UserPlace userPlace, UserPaiInfo userPaiInfo) {
            return userPaiInfo.isZhuang() ? 1 : 0;
        }
    },
//    ZI_MO("自摸", 1) {
//        @Override
//        public int compute(FanResult fanResult, ChapterEndResult chapterResult, UserPlace userPlace, UserPaiInfo userPaiInfo) {
//            return userPaiInfo.isZiMo() ? 1 : 0;
//        }
//    },
    WU_HUN_ER("无“混儿”", 2) {
        @Override
        public int compute(FanResult fanResult, ChapterEndResult chapterResult, UserPlace userPlace, UserPaiInfo userPaiInfo) {
            return userPlace.existShouPai(chapterResult.getHuiEr()) ? 1 : 0;
        }
    },
    YI_TIAO_LONG("一条龙", 3) {
        @Override
        public int compute(FanResult fanResult, ChapterEndResult chapterResult, UserPlace userPlace, UserPaiInfo userPaiInfo) {
            return userPlace.isYiTiaoLong() != null ? 1 : 0;
        }
    },
    BEN_HUN_ER_LONG("本混儿龙", 4) {
        @Override
        public int compute(FanResult fanResult, ChapterEndResult chapterResult, UserPlace userPlace, UserPaiInfo userPaiInfo) {
            PaiType paiType = userPlace.isYiTiaoLong();
            if(chapterResult.getHuiEr() == null){
                return 0;
            }
            boolean isBenHunErLong = paiType != null && chapterResult.getHuiEr() != null && paiType.equals(chapterResult.getHuiEr()[0].getType());
            return isBenHunErLong ? 1 : 0;
        }
    },
    DA_DIAO("大钓", 2) {
        @Override
        public int compute(FanResult fanResult, ChapterEndResult chapterResult, UserPlace userPlace, UserPaiInfo userPaiInfo) {
            return (userPlace.getShouPai().size() == 2) ? 1 : 0;
        }
    },
    ZUO_WU_KUI("捉五魁", 2) {
        @Override
        public int compute(FanResult fanResult, ChapterEndResult chapterResult, UserPlace userPlace, UserPaiInfo userPaiInfo) {
            ArrayList<Pai> shouPai = userPlace.getShouPai();
            Pai pai = shouPai.get(shouPai.size() - 1);
            boolean isZuoWuKui = Pai.WAN_5.equals(pai) && fanResult.isDanDiao(pai);
            return isZuoWuKui ? 1 : 0;
        }
    },
    HAIDI_LAO_YUE("海底捞月", 4) {
        @Override
        public int compute(FanResult fanResult, ChapterEndResult chapterResult, UserPlace userPlace, UserPaiInfo userPaiInfo) {
            return chapterResult.isLastPai() ? 1 : 0;
        }
    },
    GANG_SHANG_HUA("杠上开花", 5) {
        @Override
        public int compute(FanResult fanResult, ChapterEndResult chapterResult, UserPlace userPlace, UserPaiInfo userPaiInfo) {
            return chapterResult.isGangShangHua() ? 1 : 0;
        }
    },
    QIN_YI_SE("清一色", 6) {
        @Override
        public int compute(FanResult fanResult, ChapterEndResult chapterResult, UserPlace userPlace, UserPaiInfo userPaiInfo) {
            return userPlace.isQingYiSe() ? 1 : 0;
        }
    },
    HUN_YI_SE("混一色", 2) {
        @Override
        public int compute(FanResult fanResult, ChapterEndResult chapterResult, UserPlace userPlace, UserPaiInfo userPaiInfo) {
            return userPlace.isHunYiSe() ? 1 : 0;
        }
    },
    MEN_QING("门清", 2) {
        @Override
        public int compute(FanResult fanResult, ChapterEndResult chapterResult, UserPlace userPlace, UserPaiInfo userPaiInfo) {
            boolean isMengPing = !userPaiInfo.hasChi() && !userPaiInfo.hasPeng() && !userPaiInfo.hasGang();
            return isMengPing ? 1 : 0;
        }
    },
    ZHI_YI_SHE("字一色", 8) {
        @Override
        public int compute(FanResult fanResult, ChapterEndResult chapterResult, UserPlace userPlace, UserPaiInfo userPaiInfo) {
            return userPlace.isZhiYiSe() ? 1 : 0;
        }
    },
    JIA_AN_GANG("加暗杠", 2) {
        @Override
        public int compute(FanResult fanResult, ChapterEndResult chapterResult, UserPlace userPlace, UserPaiInfo userPaiInfo) {
            return userPlace.getAnGang().size();
        }
    },
    JIA_MING_GANG("加明杠", 1) {
        public int compute(FanResult fanResult, ChapterEndResult chapterResult, UserPlace userPlace, UserPaiInfo userPaiInfo) {
            return userPlace.getDaMingGang().size() + userPlace.getXiaoMingGang().size();
        }
    },
    DAI_GENG("带跟", 1) {
        public int compute(FanResult fanResult, ChapterEndResult chapterResult, UserPlace userPlace, UserPaiInfo userPaiInfo) {
            return userPlace.getGengCount();
        }
    };

    public static final Map<JiaFanType, FanInfo> jiaFanMap = Arrays.stream(JiaFanType.values()).collect(
            Collectors.toMap(r-> r,v->new FanInfo(v.name, v.fan))
    );

    public final int fan;
    public final String name;

    JiaFanType(String name, int fan) {
        this.fan = fan;
        this.name = name;
    }

    public int compute(FanResult fanResult, ChapterEndResult chapterResult, UserPlace userPlace, UserPaiInfo userPaiInfo) {
        return 0;
    }
}
