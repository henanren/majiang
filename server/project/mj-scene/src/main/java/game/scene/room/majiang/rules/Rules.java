package game.scene.room.majiang.rules;

import game.scene.room.majiang.PaiPool;
import mj.data.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author zuoge85@gmail.com on 2017/1/17.
 */
public abstract class Rules {
    public static Rules createRules(String name, Config config) {
        switch (name) {
            case "zhongyouGD":
                return new ZhongyouGdRules(config);
            default:
                return new ZhongyouGdRules(config);
        }
    }
    public abstract boolean rest();

    protected Config config;
    private int baoliuLength = 12;


    private int shouTimeMillisecond =15000;

    public Rules(Config config) {
        this.config = config;
    }

    public int getBaoliuLength() {
        return baoliuLength;
    }

    public int getShouTimeMillisecond() {
        return shouTimeMillisecond;
    }

    public void setShouTimeMillisecond(int shouTimeMillisecond) {
        this.shouTimeMillisecond = shouTimeMillisecond;
    }

    public abstract ArrayList<Pai> getAllPai();

    public abstract Pai[] getHuiEr(PaiPool paiPool);

    public abstract boolean isChi();

    public abstract boolean isFangPao();

    public abstract boolean isZaMa();
    public abstract int getZaMa();

    public abstract Map<JiaFanType, FanInfo> getJiaFanMap();
    public abstract Map<BaseFanType, FanInfo> getBaseFanMap();

    public abstract boolean isHuiGang();
}
