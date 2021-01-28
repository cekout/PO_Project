package ContainerLibrary.List;

import ContainerLibrary.Container.Container;
import ContainerLibrary.Iterators.OurListIterator;
import ContainerLibrary.Iterators.OurJumpListIteratorHeavy;
import ContainerLibrary.Exception.OurNotFoundException;
import ContainerLibrary.Exception.OurIndexOutOfBoundsException;
import ContainerLibrary.Exception.OurUnsupportedFunctionException;
import ContainerLibrary.Exception.FullContainerException;

/** Interfaccia che definisce le funzionalità di una lista, intesa come collezione di elementi al cui interno esiste l'inidicizzazione.
 *
 *
 *  I metodi che accettano come parametro un indice possono lanciare una eccezione di
 *  tipo OurIndexOutOfBoundsException se l'indice è negativo o troppo grande.
 *
 *  I metodi che effettuano una ricerca di un determinato elemento all'interno della lista
 *  possono lanciare una eccezione di tipo OurNotFoundException se l'elemento non è presente.
 *
 *
 * @param <T> tipo degli elementi da memorizzare.
 */
public interface OurList<T> extends Container<T> {

    /** Metodo che consente di ottenere l'elemento in una certa posizione della lista.
     *
     * @param index indice dell'elemento da restituire.
     * @return elemento in posizione index.
     * @throws OurIndexOutOfBoundsException se l'indice inserito non &egrave valido (negativo o troppo grande).
     */
    T getAt(int index) throws OurIndexOutOfBoundsException;

    /** Metodo che resituisce l'elemento in testa alla lista.
     *
     * @return l'elemento in testa alla lista.
     * @throws OurNotFoundException se la lista &egrave vuota.
     */
    default T getHead() throws OurNotFoundException{
        try{
            return getAt(0);
        } catch(OurIndexOutOfBoundsException c){
            throw new OurNotFoundException("The collection is empty.");
        }
    }

    /** Metodo che resituisce l'elemento alla fine alla lista.
     *
     * @return l'elemento alla fine della lista.
     * @throws OurNotFoundException se la lista &egrave vuota.
     */
    default T getLast() throws OurNotFoundException {
        try {
            return getAt(size() - 1);
        } catch(OurIndexOutOfBoundsException c){
            throw new OurNotFoundException("The collection is empty.");
        }
    }

    /** Metodo che resituisce l'indice di un prefissato elemento della lista, se presente.
     *
     * @return l'indice di elem se presente.
     * @throws OurNotFoundException se l'elemento non &egrave presente.
     */
    int indexOf(T elem) throws OurNotFoundException;

    /** Metodo che consente l'inserimento di un elemento alla lista in una determinata posizione.
     *
     * @param index posizione in cui inserire l'elemento.
     * @param elem l'elemento da inserire.
     * @throws OurIndexOutOfBoundsException se l'indice inserito non &egrave valido (negativo o troppo grande).
     * @throws FullContainerException se la lista non pu&ograve pi&ugrave aggiungere elementi
     */
    void insertAt(int index, T elem) throws OurIndexOutOfBoundsException, FullContainerException;

    /** Metodo che consente l'inserimento in testa alla lista.
     *
     * @param elem elemento da inserire.
     * @throws FullContainerException se la lista non pu&ograve pi&ugrave aggiungere elementi.
     */
    default void insertHead(T elem) throws FullContainerException {
        insertAt(0, elem);
    }

    /** Metodo che consente l'inserimento alla fine della lista.
     *
     * @param elem elemento da inserire.
     * @throws FullContainerException se la lista non pu&ograve pi&ugrave aggiungere elementi.
     */
    default void insertTail(T elem) throws FullContainerException {
        insertAt(size(), elem);
    }

    /** Metodo che restituisce l'ultima posizione (a partire dalla testa) in cui un elemento presenzia all'interno della lista.
     *
     * @param elem elemento di cui cercare la posizione.
     * @return l'indice.
     * @throws OurNotFoundException se l'elemento non &egrave presente
     */
    int lastIndexOf(T elem) throws OurNotFoundException;

    /** Metodo che restituisce l'iteratore della lista.
     *
     * @return un iteratore.
     */
    OurListIterator<T> listIterator ();

    /** Metodo che consente l'eliminazione di un elemento in una determinata posizione all'interno della lista.
     *
     * @param index posizione in cui rimuovere.
     * @return l'elemento rimosso.
     * @throws OurIndexOutOfBoundsException se l'indice inserito non &egrave valido (negativo o troppo grande)

     */
    T removeAt(int index) throws IndexOutOfBoundsException;

    /** Metodo che consente di impostare l'oggetto memorizzato in una data posizione all'interno della lista.
     *
     * @param index posizione in cui memorizzare.
     * @param elem elemento da memorizzare.
     * @throws OurIndexOutOfBoundsException se l'indice inserito non &egrave valido (negativo o troppo grande)

     */
    void setAt(int index, T elem) throws IndexOutOfBoundsException;

    /** Metodo che consente di rimuovere l'ultimo elemento della lista.
     *
     * @return l'elemento rimosso.
     * @throws OurNotFoundException se la lista &egrave vuota.
     */
    default T removeLast() throws OurNotFoundException{
        try {
            return removeAt(size() - 1);
        } catch(OurIndexOutOfBoundsException c){
            throw new OurNotFoundException("The collection is empty.");
        }
    }

    /** Metodo che consente di rimuovere la testa della lista.
     *
     * @return l'elemento rimosso.
     * @throws OurNotFoundException se la lista &egrave vuota.
     */
    default T removeHead() throws OurNotFoundException{
        try {
            return removeAt(0);
        } catch(OurIndexOutOfBoundsException c){
            throw new OurNotFoundException("The collection is empty.");
        }
    }

    /** Metodo che restituisce l'iteratore di tipo OurJumpListIteratorHeavy della lista.
     *
     * @return un iteratore di tipo OurJumpListIteratorHeavy.
     * @throws OurUnsupportedFunctionException se la lista non implementa questo metodo.
     */
    OurJumpListIteratorHeavy<T> JumpListIteratorHeavy()throws OurUnsupportedFunctionException;
    

}
