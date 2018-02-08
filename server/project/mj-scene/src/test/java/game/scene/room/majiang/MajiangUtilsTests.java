package game.scene.room.majiang;

import com.google.common.collect.ArrayListMultimap;
import mj.data.Pai;
import mj.data.PaiType;
import mj.data.UserPaiInfo;
import org.junit.Test;

/**
 * 最多13张手牌,
 * @author zuoge85@gmail.com on 16/10/17.
 */
public class MajiangUtilsTests {
    @Test
    public void testIsHupai() throws Exception {
        ArrayListMultimap<PaiType, Pai> multimap = newPaiMultimap(
                Pai.TIAO_1, Pai.TIAO_2, Pai.TIAO_3,
                Pai.TONG_3, Pai.TONG_4, Pai.TIAO_5,
                Pai.WAN_1, Pai.WAN_1, Pai.WAN_1,
                Pai.WAN_2, Pai.WAN_2, Pai.WAN_2,
                Pai.TIAO_9
        );
//        MajiangUtils.isHupai(multimap);
    }

    private ArrayListMultimap<PaiType, Pai> newPaiMultimap(
            Pai... pais
    ) {
        ArrayListMultimap<PaiType, Pai> paiMultimap = ArrayListMultimap.create();
        for (Pai pai : pais) {
            paiMultimap.put(pai.getType(), pai);
        }
        return paiMultimap;
    }
}
