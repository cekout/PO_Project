package ContainerLibrary.StackAndQueues;

import ContainerLibrary.Exception.OurNotFoundException;
import ContainerLibrary.Exception.FullContainerException;

/** Interfaccia che definisce le funzionalità che devono essere offerte da una coda, ADT che prevede l'inserimento
 *  degli elementi ad una estremit&agrave e la rimozione dall'altra, seguendo la politica LIFO.
 *
 * @param <T> tipo degli elementi contenuti nella coda.
 */
public interface Queue<T> extends OurStackAndQueue<T> {

    /** Metodo che consente l'inserimento di un elemento all'interno della coda.
     *
     * @param elem elemento da inserire.
     * @throws FullContainerException se la coda non può pi&ugrave inserire elementi
     */
    default void enqueue(T elem) throws FullContainerException {
        add(elem);
    }

    /** Metodo che consente la rimozione di un elemento dalla coda.
     *
     * @return elemento rimosso.
     * @throws OurNotFoundException se la coda &egrave vuota
     */
    default T dequeue() throws OurNotFoundException {
        return remove();
    }
}
