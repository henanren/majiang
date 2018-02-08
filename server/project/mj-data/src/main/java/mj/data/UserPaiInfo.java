package mj.data;

import mj.data.majiang.AgariUtils;
import mj.net.message.game.GameFanResult;
import org.apache.commons.lang.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zuoge85@gmail.com on 2016/11/4.
 */
public class UserPaiInfo {
    private ArrayList<Pai> shouPai = new ArrayList<>();
    /**
     * 暗杠
     * 当前手数大于杠牌手数4，显示
     * 手数 -> 牌
     */
    private ArrayList<Pai> anGang = new ArrayList<>();

    /**
     * 小明杠(加杠)
     * 自己以前碰了人家一张牌形成三张然后摸到相同的那一张牌后形成的杠称加杠。加杠也可以叫做小明杠。
     */
    private ArrayList<Pai> xiaoMingGang = new ArrayList<>();

    /**
     * 别人打出一张牌时自己手中有三张相同的牌开的杠称大明杠
     */
    private ArrayList<Pai> daMingGang = new ArrayList<>();

    /**
     * 碰
     */
    private ArrayList<Pai> peng = new ArrayList<>();

    /**
     * 吃
     */
    private ArrayList<Pai[]> chi = new ArrayList<>();

    private ArrayList<Pai> out = new ArrayList<>();
    private int locationIndex;


    private FanResult[] fanResults;
    private FanResult maxFanResult;
    private boolean isHuPai;
    private boolean isZhuang;
    private boolean isZiMo;

    private int userId;
    private String userName;

    private int score;
    private int fan;
    private int guaFengXiaYu;

    public UserPaiInfo() {
    }

    @SuppressWarnings("unchecked")
    public UserPaiInfo(ArrayList<Pai> allPai, Pai[] huiErPai, UserPlace userPlace, boolean isHuPai, boolean isZhuang, boolean isZiMo) {
        shouPai = (ArrayList<Pai>) userPlace.getShouPai().clone();

        anGang = userPlace.getAnGang().stream().map(Map.Entry::getValue)
                .collect(Collectors.toCollection(ArrayList::new));

        xiaoMingGang = (ArrayList<Pai>) userPlace.getXiaoMingGang().clone();
        daMingGang = (ArrayList<Pai>) userPlace.getDaMingGang().clone();
        peng = (ArrayList<Pai>) userPlace.getPeng().clone();
        chi = (ArrayList<Pai[]>) userPlace.getChi().clone();
        out = (ArrayList<Pai>) userPlace.getOut().clone();
        locationIndex = userPlace.getLocationIndex();

        this.isHuPai = isHuPai;
        this.isZhuang = isZhuang;
        this.isZiMo = isZiMo;

        if (isHuPai) {
            fanResults = AgariUtils.getHuPaiResultByHuiErr(allPai, shouPai, huiErPai);
            /*
             * 特殊牌形
             */
            if (ArrayUtils.isEmpty(fanResults)) {
                fanResults = new FanResult[]{
                        new FanResult()
                };
            }
        }

        this.userName = userPlace.getUserName();
        this.userId = userPlace.getUserId();
//        shouPai.
    }


    public GameFanResult toMessage() {
        GameFanResult m = new GameFanResult();
        m.setShouPai(MajiangUtils.toIndex(shouPai));
        m.setChi(MajiangUtils.toIndexByDyadicArray(chi));
        m.setDaMingGang(MajiangUtils.toIndex(daMingGang));
        m.setPeng(MajiangUtils.toIndex(peng));
        m.setXiaoMingGang(MajiangUtils.toIndex(xiaoMingGang));
        m.setAnGang(MajiangUtils.toIndex(anGang));
        if (isHuPai) {

            if (maxFanResult != null) {
                m.setBaseFanType(maxFanResult.getBaseFanType().name);

                m.setFanString(maxFanResult.getFanString());
                m.setHuiErBian(maxFanResult.getHuiErBian());

                if (maxFanResult.getQueTou() != null) {
                    m.setQueTou(maxFanResult.getQueTou().getIndex());
                    m.setKeZi(MajiangUtils.toIndex(maxFanResult.getKeZi()));
                    m.setShunZi(MajiangUtils.toIndex(maxFanResult.getShunZi()));
                } else {
                    m.setQueTou(Pai.NOT_PAI_INDEX);
                }
            }
        } else {
            m.setQueTou(Pai.NOT_PAI_INDEX);
        }
        m.setScore(score);
        m.setFan(fan);
        m.setGuaFengXiaYu(guaFengXiaYu);

        m.setUserName(userName);
        return m;
    }

    public int getFanNums() {
        return maxFanResult == null ? 0 : maxFanResult.getFan();
    }

    public FanResult getMaxFanResult() {
        return maxFanResult;
    }

    public boolean hasChi() {
        return !chi.isEmpty();
    }

    public boolean hasPeng() {
        return !peng.isEmpty();
    }

    public boolean hasGang() {
        return !anGang.isEmpty() || !daMingGang.isEmpty() || !xiaoMingGang.isEmpty();
    }

//    public void compute(ChapterEndResult chapteResult, UserPlace userPlace) {
//        if (fanResults != null) {
//            for (FanResult result : fanResults) {
//                result.compute(chapteResult, userPlace, this);
//            }
//            Optional<FanResult> max = Arrays.stream(fanResults).max(Comparator.comparingInt(FanResult::getFan));
//            maxFanResult = max.orElseGet(() -> null);
//        }
//    }

    public ArrayList<Pai> getShouPai() {
        return shouPai;
    }

    public ArrayList<Pai> getAnGang() {
        return anGang;
    }

    public ArrayList<Pai> getXiaoMingGang() {
        return xiaoMingGang;
    }

    public ArrayList<Pai> getDaMingGang() {
        return daMingGang;
    }

    public ArrayList<Pai> getPeng() {
        return peng;
    }

    public ArrayList<Pai[]> getChi() {
        return chi;
    }

    public ArrayList<Pai> getOut() {
        return out;
    }

    public int getLocationIndex() {
        return locationIndex;
    }


    public void setMaxFanResult(FanResult maxFanResult) {
        this.maxFanResult = maxFanResult;
    }

    public FanResult[] getFanResults() {
        return fanResults;
    }

    public boolean isHuPai() {
        return isHuPai;
    }

    public boolean isZhuang() {
        return isZhuang;
    }

    public boolean isZiMo() {
        return isZiMo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFan() {
        return fan;
    }

    public void setFan(int fan) {
        this.fan = fan;
    }

    public int getGuaFengXiaYu() {
        return guaFengXiaYu;
    }

    public void setGuaFengXiaYu(int guaFengXiaYu) {
        this.guaFengXiaYu = guaFengXiaYu;
    }

    @Override
    public String toString() {
        return "UserPaiInfo{" +
                "shouPai=" + shouPai +
                ", anGang=" + anGang +
                ", xiaoMingGang=" + xiaoMingGang +
                ", daMingGang=" + daMingGang +
                ", peng=" + peng +
                ", chi=" + chi +
                ", out=" + out +
                ", locationIndex=" + locationIndex +
                ", fanResults=" + Arrays.toString(fanResults) +
                ", maxFanResult=" + maxFanResult +
                ", isHuPai=" + isHuPai +
                ", isZhuang=" + isZhuang +
                ", isZiMo=" + isZiMo +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", score=" + score +
                ", fan=" + fan +
                ", guaFengXiaYu=" + guaFengXiaYu +
                '}';
    }

}
