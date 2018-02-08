package mj.data;

import gnu.trove.list.array.TIntArrayList;
import mj.data.Pai;

import java.util.Collection;
import java.util.List;

/**
 * @author zuoge85@gmail.com on 16/10/17.
 */
public class MajiangUtils {

    public static int[] toIndexByDyadicArray(List<Pai[]> dyadicPais) {
        TIntArrayList list = new TIntArrayList();
        for (int i = 0; i < dyadicPais.size(); i++) {
            Pai[] pais = dyadicPais.get(i);
            for (int j = 0; j < pais.length; j++) {
                list.add(pais[j].getIndex());
            }
        }
        return list.toArray();
    }

    public static int[] toIndex(Pai[] pais) {
        int[] arr = new int[pais.length];
        int i = 0;
        for (Pai pai : pais) {
            arr[i] = pai.getIndex();
            i++;
        }
        return arr;
    }

    public static int[] toIndex(Collection<Pai> pais) {
        int[] arr = new int[pais.size()];
        int i = 0;
        for (Pai pai : pais) {
            arr[i] = pai.getIndex();
            i++;
        }
        return arr;
    }
}
