package ContainerLibrary.Sets;

import ContainerLibrary.Iterators.OurIterator;
import ContainerLibrary.Exception.FullContainerException;
import ContainerLibrary.Exception.OurNotFoundException;
import com.sun.istack.internal.NotNull;


/** Classe che implementa un insieme incapsulando una Hashmap; non possono esistere due elementi con lo stesso
 * valore o con lo stesso Hashcode, se si prova ad inserire un elemento che ha lo stesso valore o lo stesso HashCode di un elemento
 * contenuto all'interno dell'insieme, l'elemento preesistente verr&agrave sostituito da quello nuovo.
 *
 *
 *  I metodi che inseriscono elementi nell'insieme possono lanciare una eccezione di
 *  tipo FullContainerException() se l'inserimento di un elemento causa un incremento del
 *  campo size con conseguente overflow di quest'ultimo.
 *
 *  I metodi che effettuano una ricerca di un determinato elemento all'interno dell'insieme
 *  possono lanciare una eccezione di tipo OurNotFoundException se l'elemento non &egrave presente.
 *
 * @param <T> tipo degli elementi contenuti nell'insieme.
 */
public class OurHashSet<T> implements OurSet<T>{

    /** Tabella utilizzata per mappare gli elementi.
     *
     */
    private HashMap<Integer, T>  map;

    /** Campo che memorizza il numero di elementi inseriti.
     *
     */

    private int size;
    /** Costruttore di default, costruisce una HashMap con tabella di dimensione 13.
     */
    public OurHashSet(){
        map = new HashMap<>();
    }

    /** Costruttore che prende come parametro la dimensione della tabella da utilizzare per implementare l'hashset,
     * &egrave consigliato usare una dimensione dello stesso ordine di grandezza del numero di elementi che verranno
     * inseriti nell'OurHashSet, non &egrave necessario che sia maggiore.
     *
     * @param tabsize dimensione della tabella hash che verr&agrave utilizzata in map.
     */

    public OurHashSet(int tabsize){
        map = new HashMap<>(tabsize);
    }

    @Override
    public OurHashSet<T> intersection(@NotNull OurSet<T> ms) {
        if (isEmpty()){
            return new OurHashSet<>();
        }
        OurHashSet<T> newSet = new OurHashSet<>(size());
        T elem;
        OurIterator<T> it = ms.iterator();
        while (it.hasNext()){
            if (contains(elem =it.next()))
                newSet.add(elem);
        }
        return newSet;
    }

    @Override
    public OurHashSet<T> union(@NotNull OurSet<T> ms) {
        int newSize = size() + ms.size();
        OurHashSet<T> newSet;
        if(newSize == 0)
            newSet = new OurHashSet<>();
        else
            newSet = new OurHashSet<>(newSize);
        newSet.addAll(this);
        newSet.addAll(ms.difference(this));
        return newSet;
    }

    @Override
    public OurHashSet<T> difference(@NotNull OurSet<T> ms) {
        if (isEmpty()){
            return new OurHashSet<>();
        }
        OurHashSet<T> newSet = new OurHashSet<>(size());
        T elem;
        OurIterator<T> it = iterator();
        while (it.hasNext()){
            if (!ms.contains(elem =it.next()))
                newSet.add(elem);
        }
        return newSet;

    }

    @Override
    public boolean isSubset(@NotNull OurSet<T> ms) {
        OurIterator<T> it = iterator();
        while (it.hasNext()){
            if (! ms.contains(it.next()))
                return false;
        }
        return true;
    }

    /**{@inheritDoc}
     * Se universe non contiene this viene lanciata una eccezione di tipo IllegalArgumentException.
     */
    @Override
    public OurHashSet<T> complementar(@NotNull OurSet<T> universe) {
        if (!isSubset(universe))
            throw new IllegalArgumentException("l'insieme deve essere sottoinsieme dell'insieme passato come universo");
        int NewSize = universe.size() - size();
        OurHashSet<T> ret = (NewSize != 0) ? new OurHashSet<>(NewSize) : new OurHashSet<>(); //non si può usare la difference perchè dovremmo invocare la difference su universe che è un OurSet e non HashSet -> cosa ritorna? Dipende da come è implementata!
        OurIterator<T> it = universe.iterator();
        T elem;
        while (it.hasNext()){
            if (!contains(elem = it.next()))
                ret.add(elem);
        }
        return ret;
    }

    @Override
    public boolean isComplementar(@NotNull OurSet<T> ms, OurSet<T> universe) {
        try{
            return equals(ms.complementar(universe));
        } catch(IllegalArgumentException c){
            return false;
        }
    }

    @Override
    public void add(@NotNull T elem) {
        map.put(elem.hashCode(), elem);
        incrementSize();  //lo metto dopo perchè il controllo sull'overflow lo fa hashMap
    }

    @Override
    public boolean contains(@NotNull T elem) {
        try{
            return map.getValue(elem.hashCode()).equals(elem);
        } catch (OurNotFoundException c){
            return false;
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void remove(@NotNull T elem) {
        map.remove(elem.hashCode());
        decrementSize();
    }

    @Override
    public void removeAll() {
        map.removeAll();
        setSize(0);
    }

    @Override
    public int size() {
        return size;
    }

    /**{@inheritDoc}
     * L'iteratore restituito scorrer&agrave solo gli elementi presenti al momento in cui viene chiamata questo metodo,
     * se dopo aver chiamato questo metodo vengono aggiunti elementi all'insieme, essi non saranno accessibili
     * dall'iteratore; saranno invece disponibili all'iteratore resituito da una successiva chiamata di questo metodo.
     */
    @Override
    public OurIterator<T> iterator() {
        return new OurIterator<T>() {
            int index = 0;
            T[] vett = map.toArray();

            @Override
            public boolean hasNext() {
                return (index < vett.length);
            }

            @Override
            public T next() {
                return vett[index++];
            }
        };
    }


    @Override
    public boolean equals(@NotNull Object o){
        if(getClass().getName().equals(o.getClass().getName())) {
            //OurHashSet<T> o2 = (OurHashSet<T>) o;
            //return map.hasSameCouples(o2.map);
            return ( isSubset((OurSet<T>) o) && ((OurSet<T>) o).isSubset(this));
        }
        return false;
    }

    /** Metodo che incrementa di 1 la dimensione della lista.
     *
     */

    private void incrementSize(){
        if (size == 2147483647)
            throw new FullContainerException();
        size++;
    }

    /** Metodo che decrementa di 1 la dimensione della lista.
     *
     */

    private void decrementSize(){
        size--;
    }

    /** Metodo che cambia il valore di size.
     *
     * @param a nuovo valore di size.
     */
    private void setSize(int a){
        size = a;
    }


    @Override
    public String toString(){
        String ret = "";
        OurIterator<T> it = iterator();
        while(it.hasNext()){
            ret += it.next() + "\t";
        }
        return ret;
    }

}
