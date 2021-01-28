package ContainerLibrary.List.ArrayList;

import ContainerLibrary.Container.Container;
import ContainerLibrary.Iterators.*;
import ContainerLibrary.Exception.*;
import ContainerLibrary.List.OurList;
import com.sun.istack.internal.NotNull;


/** Classe che rappresenta una lista implementata tramite array. Per lista intendiamo una collezione di elementi indicizzabile, in cui esiste il concetto di posizioni contigue.
 * Viene lanciata la OurIndexOutOfBoundsException se l'indice &egrave fuori range (< 0 e >= size(), tranne per la insert per cui vale anche == size()).
 * Viene lanciata la OurNotFoundException dalle funzioni dell'iteratore e dai metodi di ricerca, se l'elemento in input non &egrave presente nella lista.
 * Se viene fornita una capacit&agrave minore o uguale a 0 alla funzione per stabilire una dimensione minima, allora viene lanciata la IllegalArgumentException.
 * Viene lanciata la OurUnsupportedFunction se la funzione non &egrave supportata.
 * @param <T> tipo degli elementi da memorizzare.
 */
public class OurArrayList<T> implements OurList<T> {

    //accorpare incrementSize e increment Vector

    /** Array che contiene gli oggetti memorizzati.
     */
    private Object[] store;

    /** Numero di elementi effettivamente memorizzati.
     */
    private int numberOfElements;

    /** Capacit&agrave minima assicurata in termini di numero di elementi da memorizzare.
     */
    private int ensuredSize;
    //nuova variabile ensureCapacity per la capacità. Funzione ensureCapacity nello stesso stile di quella dell'arrayList. Modificare remove.

    /** Costruttore della lista.
     */
    public OurArrayList() { //funzia
        store = new Object[1];
        numberOfElements = 0;
        ensuredSize = 0;
    }

    /*private Object[] getStorage() {
        return store;
    }*/ //può avere senso per nascondere l'implentazione interna?

    /** Metodo che consente di incrementare la dimensione del vettore.
     */
    private void incrementVector() { //funzia
        if(store.length == 0)
            resizeCapacity(1, 0);
        else if (numberOfElements == store.length && store.length * 2 < 2147483647)
            resizeCapacity(store.length * 2, store.length);
    }

    /** Metodo che consente di decrementare la dimensione del vettore.
     */
    private void decrementVector() {
        if (numberOfElements == store.length / 4 && (ensuredSize == 0 || ensuredSize <= store.length/2)) //funzia
            resizeCapacity(store.length/2, store.length/4);
    }

    /** Metodo che consente di incrementare il numero di elementi memorizzati nel vettore.
     */
    private void incrementSize() {
        if (numberOfElements == store.length)
            throw new FullContainerException();
        else numberOfElements++;
    } //funzia

    /** Metodo che consente di decrementare il numero di elementi contenuti nel vettore.
     */
    private void decrementSize() {
        numberOfElements--;
    } //funzia

    /** Metodo che consente di ridimensionare il vettore che memorizza gli elementi.
     * L'implementazione fa uso di System.arraycopy.
     * @param newCapacity nuova dimensione del vettore.
     * @param numberOfElemToCopy numero di elementi da copiare nel nuovo vettore.
     */
    private void resizeCapacity(int newCapacity, int numberOfElemToCopy) { //funzia
        Object [] store1 = new Object[newCapacity];
        System.arraycopy(store, 0, store1, 0, numberOfElemToCopy);
        /*for (int i = 0; i < numberOfElements; i++) store1[i] = store[i];*/
        store = store1;
    }

    //testing
    /*
    public int arraySize() {
        return store.length;
    }
    */

    @Override
    public void add(@NotNull T elem) { //devo conoscere per forza l'organizzazione interna //funzia
        incrementVector();
        incrementSize();
        store[size()-1] = elem; //semanticamente size() è il numero di elem della lista e io voglio mettere l'elem come l'ultimo.
    }

    @Override
    public boolean contains(@NotNull T elem) { //qui concettualmente non serve conoscere l'organizzazione interna. Infatti mi basta scorrere tutti gli elementi dal primo all'ultimo.
        for(int i = 0; i < size(); i++) { //funzia
            if(elem.equals(getAt(i))) return true;
        }
        return false;
    }

    /** Metodo che consente di stabilire una dimensione minima che il vettore deve assicurare.
     *
     * @param capacity capacit&agrave, in termini di numero di elementi, da assicurare.
     * @throws IllegalArgumentException se la capacità in input &egrave <= 0.
     */
    public void ensuredSize(int capacity) throws IllegalArgumentException{ //devo conoscere per forza l'organizzazione interna
        if(capacity <= 0 || capacity > 2147483647) throw new IllegalArgumentException("Capacity <= 0 not allowed."); //ne facciamo una nostra?
        ensuredSize = capacity;
        if(ensuredSize > store.length) {
            resizeCapacity(ensuredSize, numberOfElements);
        }
    }

    /** Metodo che consente di verificare se due liste siano uguali o meno.
     *
     * @param var1 lista di cui verificare l'ugualianza di quella su cui viene invocato il metodo.
     * @return true se le due liste sono uguali, false altrimenti.
     */
    public boolean equals (Object var1) {

        if(var1 != null && getClass() == var1.getClass()) {
            OurIterator<T> esterno = ((OurList<T>) var1).iterator();
            OurIterator<T> interno = iterator();
            while(interno.hasNext() && esterno.hasNext() && interno.next().equals(esterno.next())) {}
            return !interno.hasNext() && !esterno.hasNext();
        } else return false;
    }

    @Override
    public T getAt(int position) throws OurIndexOutOfBoundsException { //deve conoscere dettagli dell'implementazione interna //funzia
        if(position < 0 || position >= size()) throw new OurIndexOutOfBoundsException("Index out of bounds.");
        else return (T) store[position];
    }

    private class JumpArrayListIterator extends ArrayListIterator implements OurJumpListIteratorHeavy<T> {

        @Override
        public T removeIndex(int index) throws OurIndexOutOfBoundsException {
            return removeAt(index);
        }

        @Override
        public void remove() throws OurNotFoundException {
            try {
                removeIndex(pos - 1);
            } catch (OurIndexOutOfBoundsException e) {
                throw new OurNotFoundException("Out of the border of the list.");
            }
        }

        @Override
        public void jumpList(int index) throws OurIndexOutOfBoundsException {
            if(hasInPosition(index))
                pos = index;
            else throw new OurIndexOutOfBoundsException("Index out of bounds.");
        }

        @Override
        public boolean hasInPosition(int index) {
            return index >= 0 && index < size();
        }

        @Override
        public void jump(int offset) throws OurIndexOutOfBoundsException {
            if(hasAtDistance(offset))
                pos += offset;
            else throw new OurIndexOutOfBoundsException("Index out of bounds.");
        }

        @Override
        public boolean hasAtDistance(int offset) {

            return pos + offset >= 0 && pos + offset < size();
        }
    }

    @Override
    public OurJumpListIteratorHeavy<T> JumpListIteratorHeavy() throws OurUnsupportedFunctionException {
        return new JumpArrayListIterator();
    }

    @Override
    public int indexOf(@NotNull T elem) { //posso usare la getAt e mi serve il numero di elementi presenti. //funzia
        int i = 0, length = size();
        while(i != length && !elem.equals(getAt(i))) i++;
        if(i == length) throw new OurNotFoundException("The element " + elem + " isn't in the list.");
        else return i;
    }

    @Override
    public void insertAt(int position, @NotNull T data) throws OurIndexOutOfBoundsException { //devo conoscere l'implementazione interna //funzia
        if (position < 0 || position > size()) throw new OurIndexOutOfBoundsException("Index out of bounds."); // > perchè ci può essere la volontà di inserirlo in coda.
        else {
            incrementVector();
            incrementSize();
            System.arraycopy(store, position, store, position+1, size() - position - 1);
            /*for (int i = size() - 1; i > position; i--) store[i] = store[i - 1];*/
            store[position] = data;
        }
    }

    @Override
    public boolean isEmpty() { //funzia
        return size() == 0;
    }


    private class ArrayIterator implements OurIterator<T> {

        int pos = 0; //default visibile solo a livello di pacchetto

        private ArrayIterator() {}

        @Override
        public boolean hasNext() {
            return pos < size();
        }

        @Override
        public T next() {
            try {
                return getAt(pos++);
            } catch (OurIndexOutOfBoundsException e) {
                throw new OurNotFoundException("Out of the border of the list.");
            }
        }
    }

    @Override
    public OurIterator<T> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public int lastIndexOf(@NotNull T elem) throws OurNotFoundException { //funzia
        int i = size() - 1;
        while(i != -1 && !getAt(i).equals(elem)) i--; //equals o confronto per indirizzo?
        if(i == -1) throw new OurNotFoundException("The element " + elem + " isn't in the list.");
        else return i;
    }


    private class ArrayListIterator extends ArrayIterator implements OurListIterator<T>  {

        private ArrayListIterator() {}

        @Override
        public boolean hasNext() {
            return pos < size() && pos >= 0;
        }

        @Override
        public boolean hasPrevious() {
            return pos-1 >= 0 && pos-1 < size();
        }

        @Override
        public T previous() throws OurNotFoundException {
            try {
                return getAt(--pos);
            } catch (OurIndexOutOfBoundsException e) {
                throw new OurNotFoundException();
            }
        }
    }

    @Override
    public OurListIterator<T> listIterator() {
        return new ArrayListIterator();
    }

    @Override
    public void remove(@NotNull T elem) throws OurNotFoundException { //funzia
        int position = indexOf(elem);
        removeAt(position);
    }

    @Override
    public void removeAll() { //funzia
        while(size() != 0) {
            //try{
                removeAt(0);
            /*} catch (OurIndexOutOfBoundsException e) { //dato che una volta che ha trovato un elemento non ci dovrebbe essere alcuna eccezione lanciata all'esterno.
                System.out.print("There is an internal problem with remove.");
            }*/
        }
    }

    /*@Override
    public void removeAllFrom(@NotNull Container<T> c) throws OurUnsupportedFunctionException { //lanciare eccezione se danno elementi non presenti da togliere?
        OurIterator <T> supp = c.iterator();
        while(supp.hasNext()) {
            remove(supp.next());
        }
    }*/

    @Override
    public T removeAt(int position) throws OurIndexOutOfBoundsException { //deve conoscere l'implementazione interna //funzia
        if(position < 0 || position >= size()) throw new OurIndexOutOfBoundsException("Index out of bounds.");
        else {
            Object elem =  store[position];
            decrementSize();
            System.arraycopy(store, position+1, store, position, size() - position); //NOTARE BENE
            store[size()] = null;
            decrementVector();
            return (T) elem;
        }
    }

    @Override
    public void setAt(int index, @NotNull T elem) throws OurIndexOutOfBoundsException { //deve conoscere l'implementazione interna. //funzia
        if(index < 0 || index >= size())
            throw new OurIndexOutOfBoundsException("Index out of bounds.");
        store[index] = elem;
    }

    @Override
    public int size() { //funzia
        return numberOfElements;
    }

    /** Metodo che consente di rappresentare la lista come stringa.
     *
     * @return la stringa che rappresenta la lista.
     */
    public String toString () { //funzia
        StringBuilder b = new StringBuilder();
        OurIterator<T> supp = iterator();
        int i = 0;
        while(supp.hasNext()) {
            b.append(String.format("Elemento in posizione %d : %s \n", i, supp.next()));
            i++;
        }
        return b.toString();
    }
}
