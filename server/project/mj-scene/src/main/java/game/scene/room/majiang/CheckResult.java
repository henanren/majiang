package game.scene.room.majiang;

import mj.data.Pai;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author zuoge85@gmail.com on 2016/10/31.
 */
public class CheckResult {
    private List<Pai[]> chi;
    private boolean isPeng;
    private boolean isGang;
    private boolean isHu;
    private int locationIndex;
    private int sequence;

    public boolean hasOperation() {
        return CollectionUtils.isNotEmpty(chi) || isPeng || isGang || isHu;
    }

    public List<Pai[]> getChi() {
        return chi;
    }

    public void setChi(List<Pai[]> chi) {
        this.chi = chi;
    }

    public boolean isPeng() {
        return isPeng;
    }

    public void setPeng(boolean peng) {
        isPeng = peng;
    }

    public boolean isGang() {
        return isGang;
    }

    public void setGang(boolean gang) {
        isGang = gang;
    }

    public boolean isHu() {
        return isHu;
    }

    public void setHu(boolean hu) {
        isHu = hu;
    }

    public int getLocationIndex() {
        return locationIndex;
    }

    public void setLocationIndex(int locationIndex) {
        this.locationIndex = locationIndex;
    }

    public int getPriority() {
        int p = sequence;
        if (isHu) {
            p += 10000;
        }
        if(isGang) {
            p += 1000;
        }
        if(isPeng) {
            p += 100;
        }
        return p;
    }

    @Override
    public String toString() {
        return "CheckResult{" +
                "chi=" + chi +
                ", isPeng=" + isPeng +
                ", isGang=" + isGang +
                ", isHu=" + isHu +
                ", locationIndex=" + locationIndex +
                '}';
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
