package mj.data.majiang;

/**
 * http://hp.vector.co.jp/authors/VA046927/mjscore/AgariBacktrack.java
 */

import mj.data.FanResult;
import mj.data.Pai;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

class AgariBacktrack {
    static final int MAN = 0;

    static final int MAN1 = 0;
    static final int MAN2 = 1;
    static final int MAN3 = 2;
    static final int MAN4 = 3;
    static final int MAN5 = 4;
    static final int MAN6 = 5;
    static final int MAN7 = 6;
    static final int MAN8 = 7;
    static final int MAN9 = 8;


    static final int PIN = 9;

    static final int PIN1 = 9;
    static final int PIN2 = 10;
    static final int PIN3 = 11;
    static final int PIN4 = 12;
    static final int PIN5 = 13;
    static final int PIN6 = 14;
    static final int PIN7 = 15;
    static final int PIN8 = 16;
    static final int PIN9 = 17;


    static final int SOU = 18;

    static final int SOU1 = 18;
    static final int SOU2 = 19;
    static final int SOU3 = 20;
    static final int SOU4 = 21;
    static final int SOU5 = 22;
    static final int SOU6 = 23;
    static final int SOU7 = 24;
    static final int SOU8 = 25;
    static final int SOU9 = 26;

    static final int TON = 27;
    static final int NAN = 28;
    static final int SHA = 29;
    static final int PEI = 30;

    static final int HAK = 31;
    static final int HAT = 32;
    static final int CHU = 33;

    static final int[] n_zero;

    static {
        n_zero = new int[34];
        Arrays.fill(n_zero, 0);
    }

    // 牌の種類ごとの個数を求める
    static int[] analyse(int[] hai) {
        int[] n = n_zero.clone();

        for (int i : hai) {
            n[i]++;
        }
        return n;
    }

    // バックトラック法で雀頭と面子の組み合わせを求める
    static List<Integer[][]> agari(int[] n) {
        List<Integer[][]> ret = new ArrayList<Integer[][]>();

        for (int i = 0; i < 34; i++) {
            for (int kotsu_first = 0; kotsu_first < 2; kotsu_first++) {
                Integer[] janto = new Integer[1];
                ArrayList<Integer> kotsu = new ArrayList<Integer>();
                ArrayList<Integer> shuntsu = new ArrayList<Integer>();

                int[] t = n.clone();
                if (t[i] >= 2) {
                    // 雀頭をはじめに取り出す
                    t[i] -= 2;
                    janto[0] = i;

                    if (kotsu_first == 0) {
                        // 刻子を先に取り出す
                        for (int j = 0; j < 34; j++) {
                            if (t[j] >= 3) {
                                t[j] -= 3;
                                kotsu.add(j);
                            }
                        }
                        // 順子を後に取り出す
                        for (int a = 0; a < 3; a++) {
                            for (int b = 0; b < 7; ) {
                                if (t[9 * a + b] >= 1 &&
                                        t[9 * a + b + 1] >= 1 &&
                                        t[9 * a + b + 2] >= 1) {
                                    t[9 * a + b]--;
                                    t[9 * a + b + 1]--;
                                    t[9 * a + b + 2]--;
                                    shuntsu.add(9 * a + b);
                                } else {
                                    b++;
                                }
                            }
                        }
                    } else {
                        // 順子を先に取り出す
                        for (int a = 0; a < 3; a++) {
                            for (int b = 0; b < 7; ) {
                                if (t[9 * a + b] >= 1 &&
                                        t[9 * a + b + 1] >= 1 &&
                                        t[9 * a + b + 2] >= 1) {
                                    t[9 * a + b]--;
                                    t[9 * a + b + 1]--;
                                    t[9 * a + b + 2]--;
                                    shuntsu.add(9 * a + b);
                                } else {
                                    b++;
                                }
                            }
                        }
                        // 刻子を後に取り出す
                        for (int j = 0; j < 34; j++) {
                            if (t[j] >= 3) {
                                t[j] -= 3;
                                kotsu.add(j);
                            }
                        }
                    }

                    // 和了の形か調べる
                    if (Arrays.equals(t, n_zero)) {
                        ret.add(new Integer[][]{janto, kotsu.toArray(new Integer[0]), shuntsu.toArray(new Integer[0])});
                    }
                }
            }
        }
        return ret;
    }


    public static void main(String[] args) {
        sss(new int[]{
                MAN1, MAN2, MAN3,
                MAN4, MAN5, MAN6,
                MAN7, MAN8, MAN9,
                TON, TON, TON,
                SHA, SHA
        });

        sss(new int[]{
                MAN2, MAN4, MAN3,
                CHU, CHU
        });

        sss(new int[]{
                TON, TON, TON,
                NAN, NAN, NAN,
                SHA, SHA, SHA,
                MAN6, MAN6
        });

        sss(new int[]{MAN1, MAN1, PIN1, PIN1, MAN3, MAN3, PIN3, PIN3, MAN5, MAN5, PIN5, PIN5, MAN7, MAN7});
    }

    private static void sss(int[] hai) {
        int[] n = analyse(hai);
        List<Integer[][]> ret = agari(n);
        System.out.println(ret.size());
        System.out.print("结果[");
        for (Integer[][] r : ret) {
            System.out.print("雀頭=");
            System.out.print(r[0][0]);
            for (Integer kotsu : r[1]) {
                System.out.print("刻子=");
                System.out.print(kotsu);
            }
            for (Integer shuntsu : r[2]) {
                System.out.print("順子=");
                System.out.print(shuntsu);
            }
            System.out.print(";");
        }
        System.out.println("]");
    }
}