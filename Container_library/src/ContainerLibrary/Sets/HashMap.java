package ContainerLibrary.Sets;

import ContainerLibrary.Container.Container;
import ContainerLibrary.List.OurList;
import ContainerLibrary.List.NodeList.OurLinkedList;
import ContainerLibrary.Iterators.OurIterator;
import ContainerLibrary.Exception.OurNotFoundException;
import ContainerLibrary.Exception.FullContainerException;
import ContainerLibrary.Exception.InvalidIndexGenerated;

import com.sun.istack.internal.NotNull;

/** Classe che implementa una tabella hash di coppie (chiave, valore) mediante concatenamento.
 *
 * Ogni cella della tabella contiene la lista di coppie (chiave, valore) che presentano una chiave
 * mappata all'indice della cella.
 *
 *
 * Le chiavi devono essere uniche o comunque avere un HashCode unico, se si cerca di inserire un valore utilizzando
 * una chiave già contenuta nella HashMap o che ha un HashCode uguale ad un'alra chiave contenuta nella HashMap,
 * l'elemento corrispondente alla chiave viene rimpiazzato con il nuovo elemento inserito.
 *
 *  I metodi che inseriscono elementi nella tabella possono lanciare una eccezione di
 *  tipo FullContainerException() se l'inserimento di un elemento causa un incremento del
 *  campo size con conseguente overflow di quest'ultimo.
 *
 *  I metodi che effettuano una ricerca di un determinato elemento all'interno della tabella
 *  possono lanciare una eccezione di tipo OurNotFoundException se l'elemento non &egrave presente.
 *
 *  Se la funzione di Hashing utilizzata per mappare le chiavi sugli indici non &egrave corretta, può generare indici
 *  non validi; se ci&ograve succede viene lanciata una eccezione di tipo InvalidIndexGenerated.
 *
 *
 * @param <K> tipo della chiave di ogni coppia.
 * @param <V> tipo sel valore di ogni coppia.
 */

public class HashMap<K, V> implements Map<K,V> {  // concatenamento
    /** Campo che memorizza un oggetto che incapsula la funzione usata per tradurre le chiavi in indici
     * della tabella.
     */

    private FunHash<K> hashFunction;
    /** Campo che memorizza il numero di elementi inseriti.
     *
     */
    private int size;

    /** Campo che memorizza la dimensione della tabella.
     */
    private  int tabsize;         //non diamo la possibilità di variare il tabsize all'utente

    /** Tabella che memorizza i riferimenti alle liste che contengono le coppie.
     */
    private Object[] table;


     /** Costruttore di default, crea una HashMap con una tabella di 13 elementi e una
      * funzione Hash di default.
     */
    public  HashMap() {
        this(13);

    }


    /** Costruttore che prende come parametro la dimensione della tabella e crea una HashMap
     * che ha una tabella della dimensione indicata e utilizza una funzione di hashing di default.
     *
     * @param tabsize dimensione della tabella.
     * @throws IllegalArgumentException se il parametro tabsize &egrave negativo o nullo.
     */
    public HashMap(int tabsize) throws IllegalArgumentException {
        this(tabsize, (K key) -> {
            int index = key.hashCode();
            if (index < 0)      //hashCode può anche ritornare interi negativi, quindi mi assicuro che
                                //l'indice generato sia positivo
                index = index * (-1);
            return index % tabsize;});
    }

    /** Costruttore che prende come parametro un oggetto di tipo FunHash, il quale contiene
     *  la funzione di hashing e la dimensione della tabella.
     *
     * @param hashFunction oggetto che memorizza la funzione di hashing e la dimensione della
     *                     tabella.
     * @throws IllegalArgumentException se il parametro tabsize &egrave negativo o nullo.
     */
    public HashMap(int tabsize , @NotNull FunHash<K> hashFunction) throws IllegalArgumentException {
        if (tabsize < 1)
            throw new IllegalArgumentException("impossibile istanziare una hashMap con numero di celle negativo o nullo.");

        setTabsize(tabsize);
        this.hashFunction = hashFunction;

        setSize(0);
        table = new Object[getTabsize()];
        //this(hashFunction.getTabsize());
        //this.hashFunction = hashFunction;
    }




    @Override
    public boolean containsKey(@NotNull K key) {
        try {
            getElem(key);//utilizzo la getElem
        }catch (OurNotFoundException c ) {
            return false;
        }
        return true;
    }

    /** Metodo che utilizza la funzione specificata dal campo hasFunction per generare l'indice
     * che corrisponde alla chiave passata come parametro.
     * Il metodo inoltre controlla che l'indice generato sia valido, cio&egrave che sia positivo
     * e che non vada al di fuori della dimensione della tabella.
     *
     * @param key chiave utilizzata per calcolare l'indice.
     * @return indice generato dalla funzione specificata dal campo hashFunction.
     * @throws InvalidIndexGenerated se l'indice generato dalla funzione di hashing non &egrave un
     * indice valido.
     */

    private int validIndex(@NotNull K key) throws InvalidIndexGenerated {      //wrappiamo questa parte di codice così per il controllo non dobbiamo riscriverlo
                                                                //sempre, inoltre se un giorno volessimo cambiare il tipo di eccezione lanciata
        //basterebbe cambiarlo qua e siamo a posto
        int index;

        if ( ( index = hashFunction.funHash(key)) < 0 || (index >= getTabsize()))
            throw  new InvalidIndexGenerated("la funzione di hash ha generato un indice non valido");
        return index;
    }


    /** Metodo che trova la coppia (chiave, valore) all'interno di una lista utilizzando una chiave.
     *
     * @param list lista in cui cercare la coppia.
     * @param key chiave della coppia da cercare.
     * @return coppia che ha come chiave quella passata come parametro.
     * @throws OurNotFoundException se nella lista non &egrave presente una coppia con la chiave data.
     */

    private Couple<K, V> listScanKey(OurList<Couple<K, V>> list, K key) throws OurNotFoundException{
        if (list != null ) {
            OurIterator<Couple<K, V>> it = list.iterator();
            Couple<K, V> res;
            while (it.hasNext()) {
                if ((res = it.next()).getFirst().equals(key))
                    return res;
            }
        }
        throw new OurNotFoundException();
    }


    @Override
    public boolean containsValue(@NotNull V value) {

        OurList<Couple<K, V>> list;
        int i = 0;

        while(i < getTabsize()){
            if (table[i]!= null) {
                OurIterator<Couple<K, V>> it = ((OurList<Couple<K, V>>) table[i]).iterator();
                while (it.hasNext()) {
                    if ((it.next()).getSecond().equals(value))
                        return true;
                }
            }
            i++;
        }
        return false;
    }

    @Override
    public Couple<K, V> getElem(@NotNull K key) throws InvalidIndexGenerated, OurNotFoundException{
        int index = validIndex(key);
        return listScanKey((OurList<Couple<K, V>>) table[index], key);
    }

    @Override
    public V getValue(@NotNull K key)  throws InvalidIndexGenerated, OurNotFoundException {
        return getElem(key).getSecond();
    }

    @Override
    public void put(@NotNull K key,@NotNull V value) throws InvalidIndexGenerated, FullContainerException {
        int index = validIndex(key);

        if (table[index] == null) {
            incrementSize();
            table[index] = new OurLinkedList<Couple<K, V>>(new Couple<K, V>(key, value));
        }
        else {
            try {
                replace(key, value);
            }catch (OurNotFoundException c)
            {
                incrementSize();
                ((OurList) table[index]).add(new Couple<K, V>(key, value));

            }
        }
    }

    @Override
    public void putAll(Container<Couple<K, V>> cont) throws InvalidIndexGenerated, FullContainerException {
        OurIterator< Couple<K,  V> > it = cont.iterator();
        Couple<K, V> newcouple;
        while(it.hasNext()){
            put( (newcouple = it.next() ).getFirst()  , newcouple.getSecond() );
        }
    }


    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public V remove(@NotNull K key) throws InvalidIndexGenerated, OurNotFoundException  {
        int index = validIndex(key);
        Couple<K, V> ret = getElem(key);
        ( (OurList)table[index] ).remove(ret);
        decrementSize();
        return ret.getSecond();
    }

    @Override
    public void removeAll() {
        for (int i = 0; i < getTabsize(); i++){
            table[i] = null;
        }
        setSize(0);
    }

    @Override
    public void removeAllFrom(Container<Couple<K, V>> cont ) throws InvalidIndexGenerated {
        OurIterator<Couple<K,V>> it = cont.iterator();
        while(it.hasNext()){
            Couple<K, V> elem = it.next();
            try {
                remove(elem.getFirst());                                      //supponiamo che le chiavi K key siano uniche per ogni nodo
            } catch (OurNotFoundException c) {}
        }
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public V replace(@NotNull K key, @NotNull V newvalue) throws InvalidIndexGenerated, OurNotFoundException {

        Couple<K,V> rep = getElem(key);

        /*if (rep == null)
            return null;*/
        V oldvalue = rep.getSecond();
        rep.setSecond(newvalue);
        return oldvalue;
    }

    @Override
    public String toString(){
        String ret = "";
        for (int i = 0; i < getTabsize(); i++){
            ret += "[" + i + "]: ";
            if (table[i] != null)
                ret += ((OurList<Couple<K, V>>) table[i]).toString();
            ret += "\n";
        }
        return ret;
    }

    /** Metodo che controlla se l'HashMap in this e quello passato come parametro contengono le stesse
     *  coppie (chiave,valore).
     *
     * @param o HashMap in cui controllare le coppie.
     * @return true se i due HashMap contengono le stesse coppie, false altrimenti.
     */
    public boolean hasSameCouples(HashMap<K, V> o) {
            boolean a = equalsAus(o);
            boolean b = o.equalsAus(this);
            return a && b;
    }

    /** Metodo che restituisce true se per ogni chiave associata a un dato valore in this, esiste
     * una chiave uguale associata allo stesso valore in o.
     *
     * @param o HashMap in cui cercare le coppie corrispondenti.
     * @return true se tutte le chiave in this sono associate agli stessi valori in o.
     */

    private boolean equalsAus(HashMap<K, V> o){
        //ritorno true se ogni chiave in this è associata allo stesso elemento in o
        for (int i = 0; i < getTabsize(); i++){
            //per ogni lista utilizzo l'iteratore per farmi restituire le coppie, poi
            //controllo che esista un elemento in o con la stessa chiave e con lo stesso valore
            if (table[i] != null) {
                OurIterator<Couple<K, V>> it = ((OurList<Couple<K, V>>) table[i]).iterator();

                while (it.hasNext()) {
                    Couple<K, V> s = it.next();

                    try {
                        V tmp = o.getValue(s.getFirst());

                        if (!(tmp.equals(s.getSecond())))
                            return false;
                    } catch (OurNotFoundException c) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /** Metodo che incrementa di 1 la dimensione della lista.
     *  @throws FullContainerException se size va in overflow.
     */

    private void incrementSize() throws FullContainerException{
        if (size == 2147483647)
            throw new FullContainerException();
        size++;
    }

    /** Metodo che decrementa di 1 la dimensione della lista.
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

    /** Metodo che cambia il valore di tabsize.
     *
     * @param a nuovo valore di tabsize.
     */

    private void setTabsize(int a){
        tabsize = a;
    }

    /** Metodo che restituisce il valore di tabsize.
     *
     * @return valore di tabsize.
     */

    public int getTabsize(){
        return tabsize;
    }

    /** Metodo che restituisce un array che contiene tutti i valori delle coppie contenuti nell'Hashmap.
     *
     * @return array dei valori.
     */

    public V[] toArray(){
        Object[] arr = new Object[size()];
        int j = 0;
        for (int i = 0; i < getTabsize(); i++){
            if (table[i] != null) {
                OurIterator<Couple<K, V>> it = ((OurList<Couple<K, V>>) table[i]).iterator();
                while (it.hasNext()){
                    arr[j] = it.next().getSecond();
                    j++;
                }
            }
        }
        return (V[]) arr;
    }
}


/*
    non diamo la possibilità di variare la dimensione dell'hashmap poichè se lo facessimo dovremmo permettere anche la modifica della
    funzione di hash;
    supponiamo di avere un hashmap di 100 celle pieno che vogliamo ingrandire, l'utente sceglia di allargarlo a, per esempio, 173 celle;
    possiamo procedere in due modi:

    1) riallocare una tabella che ha come primi 100 elementi gli elementi della vecchia hashmap; in questo modo dovremmo anche cambiare
    la funzione di hash per riadattarla alla nuova dimensione; in questo modo però se provassimo a cercare uno degli elementi vecchi utilizzando
    la nuova funzione di hash, non troveremmo l'elemento desiderato perchè la funzione è diversa.

    2) allocare una tabella rimappando gli elementi vecchi con la funzione nuova;<- troppo dispendiosa



 */



