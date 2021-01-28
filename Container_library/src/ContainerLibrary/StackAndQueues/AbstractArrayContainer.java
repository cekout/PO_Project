package ContainerLibrary.StackAndQueues;

import ContainerLibrary.Exception.*;
import ContainerLibrary.Iterators.*;
import ContainerLibrary.Container.*;
import ContainerLibrary.List.OurList;
import com.sun.istack.internal.NotNull;

/** Classe astratta che funge da base per la modellazione di un container basata su vettori. In particolare in questa classe viene offerta una modalità di gestione dell'array che contiene gli elementi memorizzati.
 *  La gestione prevede che sia possibile solo aggiungere elementi a numberOfElements+1 e indicizzare elementi compresi tra 0 e numberOfElements.
 * Nel caso di indice fuori range, viene lanciata la OurIndexOutOfBoundsException.
 * Nel caso in cui invece si fornisca un argomento non presente nella lista a funzioni che implementino una ricerca, allora viene lanciata una OurNotFoundException.
 * Se viene fornita una capacit&agrave minore o uguale a 0 alla funzione per stabilire una dimensione minima, allora viene lanciata la IllegalArgumentException.
 * Se la funzionalità non &egrave supportata, viene lanciata l'OurUnsupportedFunctionException.
 * @param <T> tipo degli elementi memorizzati.
 */

public abstract class AbstractArrayContainer<T> implements Container<T> {
    //accorpare incrementVector e incrementSize
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
    public AbstractArrayContainer() { //funzia
        store = new Object[1];
        numberOfElements = 0;
        ensuredSize = 0;
    }

    /*private Object[] getStorage() {
        return store;
    }*/ //può avere senso per nascondere l'implentazione interna?

    /** Metodo che consente di decrementare la dimensione del vettore.
     */
    protected void decrementVector() {
        if (numberOfElements == store.length / 4 && (ensuredSize <= store.length/2)) //funzia
            resizeCapacity(store.length/2, store.length/4);
    }

    /** Metodo che consente di decrementare il numero di elementi contenuti nel vettore.
     */
    protected void decrementSize() {
        numberOfElements--;
    } //funzia

    /** Metodo che restituisce l'elemento contenuto in una certa posizione del vettore, purch&ugrave sia valida.
     *
     * @param position indice.
     * @return elemento contenuto in posizione position.
     * @throws OurIndexOutOfBoundsException se l'indice non è valido, cioè < 0 oppure > numberOfElements.
     */
    protected T getFromAPosition(int position) throws OurIndexOutOfBoundsException { //deve conoscere dettagli dell'implementazione interna //funzia
        if(position < 0 || position >= getSize()) throw new OurIndexOutOfBoundsException("Index out of bounds.");
        else return (T) store[position];
    }

    /** Metodo che restituisce il numero di elementi memorizzati nel vettore.
     *
     * @return numero di elementi memorizzati.
     */
    protected int getSize() {
        return numberOfElements;
    }

    //testing
    /*protected int arraySize() {
        return store.length;
    }*/

    /** Metodo che consente l'inserimento di un elemento in una certa posizione del vettore.
     *
     * @param position posizione in cui memorizzare l'oggetto.
     * @param data elemento da inserire.
     * @throws OurIndexOutOfBoundsException se l'indice non &egrave valido, cioè < 0 oppure > numberOfElements.
     * @throws FullContainerException se il container &egrave pieno.
     */
    protected void insertInAPosition(int position, T data) throws OurIndexOutOfBoundsException, FullContainerException { //devo conoscere l'implementazione interna //funzia
        if (position < 0 || position > size()) throw new OurIndexOutOfBoundsException("Index out of bounds."); // > perchè ci può essere la volontà di inserirlo in coda.
        else {
            incrementVector();
            incrementSize();
            System.arraycopy(store, position, store, position+1, size() - position - 1);
            /*for (int i = size() - 1; i > position; i--) store[i] = store[i - 1];*/
            store[position] = data;
        }
    }

    /** Metodo che consente di incrementare il numero di elementi memorizzati nel vettore.
     */
    protected void incrementSize() throws FullContainerException {
        if(numberOfElements == store.length)
            throw new FullContainerException();
        else numberOfElements++;
    } //funzia

    /** Metodo che consente di incrementare la dimensione del vettore.
     */
    protected void incrementVector() { //funzia
        if(store.length == 0)
            resizeCapacity(1, 0);
        else if (numberOfElements == store.length && store.length * 2 < 2147483647)
            resizeCapacity(store.length * 2, store.length);
    }

    /** Metodo che consente di rimuovere l'elemento memorizzato in una data posizione del vettore.
     *
     * @param position posizione all'interno del vettore.
     * @return elemento rimosso.
     * @throws OurIndexOutOfBoundsException se l'indice fornito &egrave fuori range.
     */
    protected T removeAtAPosition(int position) {
        if(position < 0 || position >= size()) throw new OurIndexOutOfBoundsException("Index out of bounds.");
        else {
            Object elem =  store[position];
            decrementSize();
            System.arraycopy(store, position+1, store, position, getSize() - position);
            store[getSize()] = null;
            decrementVector();
            return (T) elem;
        }
    }

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

    /** Metodo che consente di memorizzare un nuovo elemento in una data posizione del vettore
     *
     * @param position posizione all'interno del vettore.
     * @param data elemento da memorizzare.
     * @throws OurIndexOutOfBoundsException se l'indice fornito &egrave fuori range.
     */
    protected void setInAPosition(int position, T data) throws OurIndexOutOfBoundsException {
        if(position < 0 || position >= size())
            throw new IndexOutOfBoundsException("Index out of bounds.");
        store[position] = data;
    }

    @Override
    public abstract void add(@NotNull T elem); //non servirebbe scriverlo poichè la classe non è istanziabile e il metodo è nell'interfaccia.

    @Override
    public boolean contains(@NotNull T elem) { //qui concettualmente non serve conoscere l'organizzazione interna. Infatti mi basta scorrere tutti gli elementi dal primo all'ultimo.
        for(int i = 0; i < getSize(); i++) { //funzia
            if(elem.equals(getFromAPosition(i))) return true;
        }
        return false;
    }

    /** Metodo che consente di stabilire una dimensione minima che il vettore deve assicurare.
     *
     * @param capacity capacit&agrave, in termini di numero di elementi, da assicurare.
     * @throws IllegalArgumentException se la capacità fornita &egrave negativa o pari a 0.
     */
    public void ensuredSize(int capacity) throws IllegalArgumentException { //devo conoscere per forza l'organizzazione interna
        if(capacity <= 0 || capacity >= 2147483647) throw new IllegalArgumentException("Capacity <= 0 or too big not allowed."); //ne facciamo una nostra?
        ensuredSize = capacity;
        if(ensuredSize > store.length) {
            resizeCapacity(ensuredSize, numberOfElements);
        }
    }

    @Override
    public boolean isEmpty() { //funzia
        return size() == 0;
    }

    @Override
    public abstract OurIterator<T> iterator();

    /** {@inheritDoc}
     * @throws OurUnsupportedFunctionException se la funzionalit&agrave non &egrave supportata.
     */
    @Override
    public abstract void remove(@NotNull T elem) throws OurNotFoundException, OurUnsupportedFunctionException;

    @Override
    public abstract void removeAll();

    /** {@inheritDoc}
     * @throws OurUnsupportedFunctionException se la funzionalit&agrave non &egrave supportata.
     */
    @Override
    public abstract void removeAllFrom(@NotNull Container<T> c) throws OurUnsupportedFunctionException;


    @Override
    public int size() { //funzia
        return getSize();
    }

    /** Metodo che consente di rappresentare la lista come stringa.
     *
     * @return la stringa che rappresenta la lista.
     */
    public String toString () { //funzia
        String s = "";
        for(int i = 0; i < getSize(); i++) {
            s += getFromAPosition(i).toString() + " ";
        }
        s += "\n";
        return s;
    }

}
