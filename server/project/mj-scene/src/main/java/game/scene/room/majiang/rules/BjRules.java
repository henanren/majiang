package game.scene.room.majiang.rules;

import game.scene.room.majiang.PaiPool;
import mj.data.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author zuoge85@gmail.com on 2016/10/24.
 */
class BjRules extends Rules {
    private static final ArrayList<Pai> ALL_PAI_LIST = createAllList();

    private static ArrayList<Pai> createAllList() {
        ArrayList<Pai> list = new ArrayList<>();
        for (Pai pai : Pai.values()) {
            list.add(pai);
        }
        return list;
    }

    @Override
    public boolean rest() {
        return false;
    }

    BjRules(Config config) {
        super(config);
    }

    @Override
    public ArrayList<Pai> getAllPai() {
        return createAllList();
    }

    @Override
    public Pai[] getHuiEr(PaiPool paiPool) {
        if (config.getBoolean(Config.IS_HUIER)) {
            Pai hunPeiEr = paiPool.get(getBaoliuLength());
            return new Pai[]{hunPeiEr.next()};
        }
        return null;
    }

    @Override
    public boolean isChi() {
        return true;
    }

    @Override
    public boolean isFangPao() {
        return true;
    }

    @Override
    public boolean isZaMa() {
        return false;
    }

    @Override
    public int getZaMa() {
        return 0;
    }

    @Override
    public Map<JiaFanType, FanInfo> getJiaFanMap() {
        return JiaFanType.jiaFanMap;
    }

    @Override
    public Map<BaseFanType, FanInfo> getBaseFanMap() {
        return BaseFanType.baseFanMap;
    }

    @Override
    public boolean isHuiGang() {
        return false;
    }
}
