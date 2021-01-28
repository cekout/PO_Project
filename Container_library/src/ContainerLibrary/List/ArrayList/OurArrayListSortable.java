package ContainerLibrary.List.ArrayList;

import java.util.Random;
import ContainerLibrary.List.Sortable;
/** ArrayList che implementa l'interfaccia Sortable e quindi &egrave ordinabile.
 * @param <T> tipo degli elementi contenuti nella lista, deve estendere Comparable di T.
 */

public class OurArrayListSortable<T extends Comparable<T>> extends OurArrayList<T> implements Sortable<T> {

    @Override
    public void sort() {
        quicksort_supp(0, size()-1);
    }

    private class Pair_int {
        public int first;
        public int second;

        public Pair_int(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    private void scambio (int first, int second) {
        T support;
        support = getAt(first);
        setAt(first, getAt(second));
        setAt(second, support);
    }

    private Pair_int partition (int p, int r) {
        Random generator = new Random();
        scambio(generator.nextInt(r-p) + p, r);
        int min, eq, max;
        min = eq = p;
        max = r;
        while(eq < max) {
            int res = (getAt(eq)).compareTo(getAt(r));
            if(res < 0) {
                scambio(min, eq);
                min++;
                eq++;
            } else if (res == 0) eq++;
            else {
                max--;
                scambio(eq, max);
            }
        }
        scambio(eq, r);
        return new Pair_int(min, max);
    }

    private void quicksort_supp(int p, int r) {
        if(p < r) {
            Pair_int results = partition(p, r);
            quicksort_supp(p, results.first - 1);

            quicksort_supp(results.second + 1, r);
        }
    }
}
