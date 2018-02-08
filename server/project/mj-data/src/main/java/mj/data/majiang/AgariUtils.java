package mj.data.majiang;

import mj.data.FanResult;
import mj.data.Pai;
import org.apache.commons.lang.ArrayUtils;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author zuoge85@gmail.com on 2016/12/29.
 */
public class AgariUtils {


    /**
     * 如果不存在结果，那么返回null
     */
    public static FanResult[] getHuPaiResult(ArrayList<Pai> shouPai) {
        int[] paiIndexs = shouPai.stream().mapToInt(Pai::getIndex).toArray();
        int[] n = AgariIndex.analyse(paiIndexs);
        int[] pos = new int[14];
        int[] ret = AgariIndex.agari(n, pos);
        if (ArrayUtils.isEmpty(ret)) {
            return null;
        }
        return getFanResults(pos, ret);
    }


    public static FanResult[] getHuPaiResultByHuiErr(ArrayList<Pai> allPai, ArrayList<Pai> shouPai, Pai huiErPai[]) {
        if (huiErPai == null || !checkContains(shouPai, huiErPai)) {
            return getHuPaiResult(shouPai);
        } else {
            int[] pos = new int[14];
            Map.Entry<int[], int[]> entry = checkHuiEr(pos, allPai, shouPai, huiErPai);
            if (entry == null || ArrayUtils.isEmpty(entry.getKey())) {
                return null;
            }
            FanResult[] fanResults = getFanResults(pos, entry.getKey());
            for (FanResult fr : fanResults) {
                fr.setHuiErBian(entry.getValue());
            }
            return fanResults;
        }
    }

    private static boolean checkContains(Collection<Pai> shouPai, Pai[] huiErPai) {
        for (int i = 0; i < huiErPai.length; i++) {
            Pai pai = huiErPai[i];
            if (shouPai.contains(pai)) {
                return true;
            }
        }
        return false;
    }

    private static FanResult[] getFanResults(int[] pos, int[] ret) {
        return Arrays.stream(ret).mapToObj(r -> {
            FanResult fanResult = new FanResult();
            fanResult.setQueTou(Pai.fromIndex(pos[(r >> 6) & 0xF]));
            int num_kotsu = r & 0x7;
            int num_shuntsu = (r >> 3) & 0x7;

            Pai[] kezis = new Pai[num_kotsu];
            for (int i = 0; i < num_kotsu; i++) {
                int kezi = pos[(r >> (10 + i * 4)) & 0xF];
                kezis[i] = Pai.fromIndex(kezi);
            }
            fanResult.setKeZi(kezis);
            Pai[] sunzi = new Pai[num_shuntsu];

            for (int i = 0; i < num_shuntsu; i++) {
                sunzi[i] = Pai.fromIndex(pos[(r >> (10 + num_kotsu * 4 + i * 4)) & 0xF]);
            }
            fanResult.setShunZi(sunzi);
            return fanResult;
        }).toArray(FanResult[]::new);
    }

    /**
     * 是否胡牌会儿
     */
    public static boolean isHuiErHuPai(ArrayList<Pai> allPai, Collection<Pai> shouPai, Pai huiErPai[]) {
        if (huiErPai == null || !checkContains(shouPai, huiErPai)) {
            return isHuPai(shouPai);
        } else {
            //会儿需要当做任何牌，现在只能遍历
            int[] pos = new int[14];
            return checkHuiEr(pos, allPai, shouPai, huiErPai) != null;
        }
    }

    private static Map.Entry<int[], int[]> checkHuiEr(
            int[] pos, ArrayList<Pai> allPai, Collection<Pai> shouPai, Pai[] huiErPai
    ) {
        ArrayList<Pai> curShouPai = new ArrayList<>(shouPai);
        int[] curPaiIndexs = new int[shouPai.size()];

        boolean has1 = checkRemove(huiErPai, curShouPai);
        boolean has2 = checkRemove(huiErPai, curShouPai);
        boolean has3 = checkRemove(huiErPai, curShouPai);
        boolean has4 = checkRemove(huiErPai, curShouPai);
        boolean has5 = checkRemove(huiErPai, curShouPai);
        boolean has6 = checkRemove(huiErPai, curShouPai);
        boolean has7 = checkRemove(huiErPai, curShouPai);
        boolean has8 = checkRemove(huiErPai, curShouPai);
        for (int i = 0; i < allPai.size(); i++) {
            if (has2) {
                for (int j = 0; j < allPai.size(); j++) {
                    if (has3) {
                        for (int k = 0; k < allPai.size(); k++) {
                            if (has4) {
                                for (int k1 = 0; k1 < allPai.size(); k1++) {
                                    if (has5) {
                                        for (int k2 = 0; k2 < allPai.size(); k2++) {
                                            if (has6) {
                                                for (int k3 = 0; k3 < allPai.size(); k3++) {
                                                    if (has7) {
                                                        for (int k4 = 0; k4 < allPai.size(); k4++) {
                                                            int[] ints = checkHuiRrHuPai(
                                                                    pos,
                                                                    curShouPai, curPaiIndexs,
                                                                    allPai.get(i).getIndex(),
                                                                    allPai.get(j).getIndex(),
                                                                    allPai.get(k).getIndex(),
                                                                    allPai.get(k1).getIndex(),
                                                                    allPai.get(k2).getIndex(),
                                                                    allPai.get(k3).getIndex(),
                                                                    allPai.get(k4).getIndex()
                                                            );
                                                            if (ints != null) {
                                                                return new AbstractMap.SimpleImmutableEntry<>(ints, new int[]{i, j, k, k1, k2, k3, k4});
                                                            }
                                                        }
                                                    } else {
                                                        int[] ints = checkHuiRrHuPai(
                                                                pos,
                                                                curShouPai, curPaiIndexs,
                                                                allPai.get(i).getIndex(),
                                                                allPai.get(j).getIndex(),
                                                                allPai.get(k).getIndex(),
                                                                allPai.get(k1).getIndex(),
                                                                allPai.get(k2).getIndex(),
                                                                allPai.get(k3).getIndex()
                                                        );
                                                        if (ints != null) {
                                                            return new AbstractMap.SimpleImmutableEntry<>(ints, new int[]{i, j, k, k1, k2, k3});
                                                        }
                                                    }
                                                }
                                            } else {
                                                int[] ints = checkHuiRrHuPai(
                                                        pos,
                                                        curShouPai, curPaiIndexs,
                                                        allPai.get(i).getIndex(),
                                                        allPai.get(j).getIndex(),
                                                        allPai.get(k).getIndex(),
                                                        allPai.get(k1).getIndex(),
                                                        allPai.get(k2).getIndex()
                                                );
                                                if (ints != null) {
                                                    return new AbstractMap.SimpleImmutableEntry<>(ints, new int[]{i, j, k, k1, k2});
                                                }
                                            }
                                        }
                                    } else {
                                        int[] ints = checkHuiRrHuPai(
                                                pos,
                                                curShouPai, curPaiIndexs,
                                                allPai.get(i).getIndex(),
                                                allPai.get(j).getIndex(),
                                                allPai.get(k).getIndex(),
                                                allPai.get(k1).getIndex()
                                        );
                                        if (ints != null) {
                                            return new AbstractMap.SimpleImmutableEntry<>(ints, new int[]{i, j, k, k1});
                                        }
                                    }
                                }
                            } else {
                                int[] ints = checkHuiRrHuPai(
                                        pos,
                                        curShouPai, curPaiIndexs,
                                        allPai.get(i).getIndex(),
                                        allPai.get(j).getIndex(),
                                        allPai.get(k).getIndex()
                                );
                                if (ints != null) {
                                    return new AbstractMap.SimpleImmutableEntry<>(ints, new int[]{i, j, k});
                                }
                            }
                        }
                    } else {
                        int[] ints = checkHuiRrHuPai(
                                pos,
                                curShouPai, curPaiIndexs,
                                allPai.get(i).getIndex(),
                                allPai.get(j).getIndex()
                        );
                        if (ints != null) {
                            return new AbstractMap.SimpleImmutableEntry<>(ints, new int[]{i, j});
                        }
                    }
                }
            } else {
                int[] ints = checkHuiRrHuPai(
                        pos, curShouPai, curPaiIndexs,
                        allPai.get(i).getIndex()
                );
                if (ints != null) {
                    return new AbstractMap.SimpleImmutableEntry<>(ints, new int[]{i});
                }
            }
        }
        return null;
    }

    private static boolean checkRemove(Pai[] huiErPai, ArrayList<Pai> curShouPai) {
        for (int i = 0; i < huiErPai.length; i++) {
            Pai pai = huiErPai[i];
            if (curShouPai.remove(pai)) {
                return true;
            }
        }
        return false;
    }

    private static int[] checkHuiRrHuPai(int[] pos, ArrayList<Pai> curShouPai, int[] curPaiIndexs, int... pais) {
        int l = 0;
        for (; l < curShouPai.size(); l++) {
            curPaiIndexs[l] = curShouPai.get(l).getIndex();
        }

        for (int i = 0; i < pais.length; i++) {
            curPaiIndexs[l] = pais[i];
            l++;
        }
        int[] n = AgariIndex.analyse(curPaiIndexs);

        Arrays.fill(pos, 0);

        int[] ret = AgariIndex.agari(n, pos);
        if (!ArrayUtils.isEmpty(ret)) {
            return ret;
        }
        return null;
    }

    public static boolean isHuPai(Collection<Pai> shouPai) {
        int[] paiIndexs = shouPai.stream().mapToInt(Pai::getIndex).toArray();
        int[] n = AgariIndex.analyse(paiIndexs);
        int[] ret = AgariIndex.agari(n);
        return !ArrayUtils.isEmpty(ret);
    }

    public static boolean isHuPai(Collection<Pai> shouPai, Pai pai) {
        int[] paiIndexs = IntStream.concat(
                shouPai.stream().mapToInt(Pai::getIndex),
                IntStream.of(pai.getIndex())
        ).toArray();

        int[] n = AgariIndex.analyse(paiIndexs);
        int[] ret = AgariIndex.agari(n);
        return !ArrayUtils.isEmpty(ret);
    }
}
