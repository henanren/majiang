package mj.data;

import com.google.common.collect.ArrayListMultimap;
import mj.data.majiang.AgariUtils;
import mj.net.message.game.UserPlaceMsg;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 用户的牌信息
 *
 * @author zuoge85@gmail.com on 16/10/17.
 */
public class UserPlace {
    /**
     * 显示暗杠的手数
     */
    public static final int SHOW_ANGANG_SHOUNUMS = 4;
    /**
     * 手牌
     */
    private final ArrayListMultimap<PaiType, Pai> shouPai = ArrayListMultimap.create();
    private final ArrayListMultimap<Pai, Pai> shouPaiMap = ArrayListMultimap.create();

    private final ArrayList<Pai> shouPaiList = new ArrayList<>();

    /**
     * 暗杠
     * 当前手数大于杠牌手数4，显示
     * 手数 -> 牌
     */
    private final ArrayList<Map.Entry<Integer, Pai>> anGang = new ArrayList<>();

    /**
     * 小明杠(加杠)
     * 自己以前碰了人家一张牌形成三张然后摸到相同的那一张牌后形成的杠称加杠。加杠也可以叫做小明杠。
     */
    private final ArrayList<Pai> xiaoMingGang = new ArrayList<>();

    /**
     * 别人打出一张牌时自己手中有三张相同的牌开的杠称大明杠
     */
    private final ArrayList<Pai> daMingGang = new ArrayList<>();


    /**
     * 碰
     */
    private final ArrayList<Pai> peng = new ArrayList<>();

    /**
     * 吃
     */
    private final ArrayList<Pai[]> chi = new ArrayList<>();

    private ArrayList<Pai> out = new ArrayList<>();
    private int locationIndex;

    /**
     * 全部打出的牌，不管是不是被人 吃碰杠胡了
     */
    private ArrayList<Pai> allOut = new ArrayList<>();

    private int userId;
    private String userName;

    public UserPlace() {

    }


    public void clear() {
        shouPai.clear();
        shouPaiMap.clear();
        shouPaiList.clear();
        anGang.clear();
        xiaoMingGang.clear();
        daMingGang.clear();
        peng.clear();
        allOut.clear();
        chi.clear();
        out.clear();
    }

    public void addShouPai(Pai pai) {
        if (shouPaiList.size() >= 14) {
            throw new RuntimeException("严重错误");
        }
        shouPai.put(pai.getType(), pai);
        shouPaiMap.put(pai, pai);
        shouPaiList.add(pai);
    }

    public void removeShouPai(Pai pai) {

        if (!shouPai.remove(pai.getType(), pai)) {
            throw new RuntimeException("删除牌失败！" + this + ",PAI:" + pai);
        }
        if (!shouPaiMap.remove(pai, pai)) {
            throw new RuntimeException("删除牌失败！" + this + ",PAI:" + pai);
        }
        if (!shouPaiList.remove(pai)) {
            throw new RuntimeException("删除牌失败！" + this + ",PAI:" + pai);
        }
    }

    public UserPlaceMsg toMessage(boolean isMy, int shouIndex) {
        UserPlaceMsg m = new UserPlaceMsg();
        m.setAnGang(getAnGangIndex(isMy, shouIndex));
        m.setChi(MajiangUtils.toIndexByDyadicArray(chi));
        m.setDaMingGang(MajiangUtils.toIndex(daMingGang));
        m.setPeng(MajiangUtils.toIndex(peng));
        if (isMy) {
            m.setShouPai(MajiangUtils.toIndex(shouPai.values()));
        } else {
            m.setShouPaiLen(shouPai.size());
        }
        m.setXiaoMingGang(MajiangUtils.toIndex(xiaoMingGang));

        m.setOutPai(MajiangUtils.toIndex(out));

        return m;
    }

    private int[] getAnGangIndex(boolean isMy, int shouIndex) {
        int[] arr = new int[anGang.size()];
        for (int i = 0; i < anGang.size(); i++) {
            Map.Entry<Integer, Pai> entry = anGang.get(i);
            if (isMy || shouIndex - entry.getKey() > SHOW_ANGANG_SHOUNUMS) {
                arr[i] = entry.getValue().getIndex();
            } else {
                arr[i] = Pai.HAS_PAI_INDEX;
            }
        }
        return arr;
    }

    public ArrayList<Pai> getShouPai() {
        return shouPaiList;
    }

    public ArrayList<Map.Entry<Integer, Pai>> getAnGang() {
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

    public void changeFa(Pai pai) {
        addShouPai(pai);
    }

    /**
     * 碰了的牌，自己在摸到一张，那么可以杠，叫小明杠
     */
    public void xiaoMingGang(Pai pai) {
        if (!peng.remove(pai)) {
            throw new RuntimeException("删除牌失败！" + this + ",PAI:" + pai);
        }
        removeShouPai(pai);
        xiaoMingGang.add(pai);
    }

    public void anGang(int shou, Pai pai) {
        anGang.add(new AbstractMap.SimpleEntry<Integer, Pai>(shou, pai));
        removeShouPai(pai);
        removeShouPai(pai);
        removeShouPai(pai);
        removeShouPai(pai);
    }

    public int[] checkAnGang(Pai pai) {
        Stream<Collection<Pai>> stream = shouPaiMap.asMap().values().stream().filter(pais -> pais.size() == 4);
        return stream.mapToInt(r -> r.iterator().next().getIndex()).toArray();
    }

    /**
     * 碰了的牌，自己在摸到一张，那么可以杠，叫小明杠
     */
    public int[] checkXiaoMingGang(Pai pai) {
        return peng.stream().filter(shouPaiMap::containsKey).mapToInt(Pai::getIndex).toArray();
    }

    public int getLocationIndex() {
        return locationIndex;
    }

    public void setLocationIndex(int locationIndex) {
        this.locationIndex = locationIndex;
    }

    public void peng(Pai pai) {
        peng.add(pai);
        removeShouPai(pai);
        removeShouPai(pai);
    }

    public boolean isPeng(Pai pai) {
        return getShouPaiCount(pai) > 1;
    }

    public void daMingGang(Pai pai) {
        daMingGang.add(pai);
        removeShouPai(pai);
        removeShouPai(pai);
        removeShouPai(pai);
    }

    /**
     * 手上的牌可以杠
     */
    public boolean isDaMingGang(Pai pai) {
        return getShouPaiCount(pai) > 2;
    }

    public void chi(Pai pai, int[] chi) {
        Pai[] chiPais = Arrays.stream(chi).mapToObj(Pai::fromIndex).toArray(Pai[]::new);
        this.chi.add(chiPais);
        for (Pai chiItem : chiPais) {
            if (!chiItem.equals(pai)) {
                removeShouPai(chiItem);
            }
        }
    }

    public List<Pai[]> isChi(Pai pai) {
//        List<Pai> pais = shouPai.get(pai.getType());
        Pai next0 = pai.nextPaiType();
        Pai next1 = next0 != null ? next0.nextPaiType() : null;
        Pai prev0 = pai.prevPaiType();
        Pai prev1 = prev0 != null ? prev0.prevPaiType() : null;

        next0 = shouPaiMap.containsKey(next0) ? next0 : null;
        next1 = shouPaiMap.containsKey(next1) ? next1 : null;
        prev0 = shouPaiMap.containsKey(prev0) ? prev0 : null;
        prev1 = shouPaiMap.containsKey(prev1) ? prev1 : null;

        List<Pai[]> result = new ArrayList<>();
        if (next0 != null && prev0 != null) {
            result.add(new Pai[]{prev0, pai, next0});
        }
        if (next0 != null && next1 != null) {
            result.add(new Pai[]{pai, next0, next1});
        }
        if (prev0 != null && prev1 != null) {
            result.add(new Pai[]{prev1, prev0, pai});
        }
        return result;
    }

    public boolean isQiDui() {
        if (shouPaiMap.size() == 14) {
            return shouPaiMap.asMap().values().stream().filter(v -> v.size() == 2).count() == 7;
        }
        return false;
    }

    public boolean isQiDui(Pai pai) {
        if (shouPaiMap.size() == 14) {
            Stream<Pai> concat = Stream.concat(shouPai.values().stream(), Stream.of(pai));
            return concat.collect(Collectors.groupingBy(
                    r -> r
            )).values().stream().filter(v -> v.size() == 2).count() == 7;
        }
        return false;
    }


    public boolean isHuPai(boolean isHuihuiGang, ArrayList<Pai> all, Pai huiEr[]) {
        return isHuiErGang(isHuihuiGang, huiEr) || isQiDui() || AgariUtils.isHuiErHuPai(all, shouPai.values(), huiEr);
    }

    public ArrayList<Pai> checkTingPai(boolean isHuihuiGang, ArrayList<Pai> all, Pai huiEr[]) {
        ArrayList<Pai> tingPais = new ArrayList<>();
        if (isHuiErGang(isHuihuiGang, huiEr)) {
            Collections.addAll(tingPais, huiEr);
        }

        ArrayList<Pai> testPais = new ArrayList<>(shouPai.values());
        testPais.add(Pai.FENG_BEI);
        int shuPaiLen = shouPai.values().size();

        for (int i = 0; i < all.size(); i++) {
            Pai pai = all.get(i);
            testPais.set(shuPaiLen, pai);
            if (isQiDui(pai)) {
                tingPais.add(pai);
            } else if (AgariUtils.isHuiErHuPai(all, testPais, huiEr)) {
                tingPais.add(pai);
            }
        }
        return tingPais;
    }

    public boolean isHuiErGang(boolean isHuihuiGang, Pai huiEr[]) {
        if (!isHuihuiGang || huiEr == null || huiEr.length != 1) {
            return false;
        }
        return getShouPaiCount(huiEr[0]) == 4;
    }

    /**
     * 检查是否能胡别人打出的牌
     */
    public boolean isHuPaiBy(Pai pai) {
        return isQiDui(pai) || AgariUtils.isHuPai(shouPai.values(), pai);
    }


    private int getShouPaiCount(Pai pai) {
        return shouPaiMap.get(pai).size();
    }

    public boolean checkShouPai(Pai pai) {
        return shouPaiMap.containsKey(pai);
    }

    public void addOut(Pai pai) {
        out.add(pai);
    }

    public void addAllOut(Pai pai) {
        allOut.add(pai);
    }

    public boolean hasAllOut() {
        return !allOut.isEmpty();
    }

    public boolean existShouPai(Pai[] pai) {
        if (pai == null) {
            return false;
        }
        for (Pai p : pai) {
            if (shouPaiMap.containsKey(p)) {
                return true;
            }
        }
        return false;
    }

    public PaiType isYiTiaoLong() {
        PaiType type = isYiTiaoLong(Pai.TONG_1);
        if (type != null) {
            return type;
        }
        type = isYiTiaoLong(Pai.TIAO_1);
        if (type != null) {
            return type;
        }
        type = isYiTiaoLong(Pai.WAN_1);
        return type;
    }

    private PaiType isYiTiaoLong(Pai first) {
        if (shouPai.containsKey(first.getType())) {
            Pai next = first;
            while (shouPaiMap.containsKey(next)) {
                next = next.nextPaiType();
                if (next == null) {
                    return first.getType();
                }
            }
        }
        return null;
    }

    public boolean isQingYiSe() {
        if (shouPai.keySet().size() == 1) {
            PaiType type = shouPai.keySet().iterator().next();
            return !type.isZhi() && testAllPaiType(type);
        }
        return false;
    }

    public boolean isZhiYiSe() {
        if (shouPai.keySet().size() == 1) {
            PaiType type = shouPai.keySet().iterator().next();
            return type.isZhi() && testAllPaiType(type);
        }
        return false;
    }

    public boolean testAllPaiType(PaiType type) {
        return peng.stream().allMatch(pai -> type.equals(pai.getType())) &&
                anGang.stream().allMatch(e -> type.equals(e.getValue().getType())) &&
                daMingGang.stream().allMatch(pai -> type.equals(pai.getType())) &&
                xiaoMingGang.stream().allMatch(pai -> type.equals(pai.getType())) &&
                chi.stream().allMatch(pais -> type.equals(pais[0].getType()));
    }

    public boolean isHunYiSe() {
        if (shouPai.keySet().size() == 2) {
            Optional<PaiType> optional = shouPai.keySet().stream().filter(pai -> !pai.isZhi()).findAny();

            optional.map(type -> {
                Stream<Pai> paiStream = Stream.concat(
                        Stream.concat(shouPaiList.stream(), peng.stream()),
                        Stream.concat(anGang.stream().map(Map.Entry::getValue),
                                Stream.concat(
                                        daMingGang.stream(),
                                        Stream.concat(xiaoMingGang.stream(), chi.stream().map(pais -> pais[0]))
                                )
                        )
                );
                return paiStream.allMatch(pai -> type.equals(pai.getType()) || pai.getType().isZhi());
            });
        }
        return false;
    }

    public int getGengCount() {
        return (int) shouPai.asMap().values().stream().filter(coll -> coll.size() == 4).count();
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

    @Override
    public String toString() {
        return "UserPlace{" +
                "shouPai=" + shouPai +
                ", shouPaiMap=" + shouPaiMap +
                ", shouPaiList=" + shouPaiList +
                ", anGang=" + anGang +
                ", xiaoMingGang=" + xiaoMingGang +
                ", daMingGang=" + daMingGang +
                ", peng=" + peng +
                ", chi=" + chi +
                ", out=" + out +
                ", locationIndex=" + locationIndex +
                ", allOut=" + allOut +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }

}
