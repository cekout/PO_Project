package ContainerLibrary.StackAndQueues;

import ContainerLibrary.Exception.OurNotFoundException;
import ContainerLibrary.Exception.FullContainerException;

/** Interfaccia che definisce le funzionalit&agrave di una dequeue, struttura dati che prevede la rimozione e
 * l'inserimento di elementi all'estremo inferiore e superiore della collezione (il primo e l'ultimo).
 *
 * @param <T> tipo degli elementi da memorizzare.
 */
public interface Dequeue<T> extends OurStackAndQueue<T> {

    /** Metodo che consente l'inserimento di un elemento in cima alla collezione.
     *
     * @param elem elemento da inserire.
     * @throws FullContainerException se la dequeue &egrave piena.
     */
    default void addFirst(T elem) throws FullContainerException {
        add(elem);
    }

    /** Metodo che consente l'inserimento di un elemento in dequeue alla collezione.
     *
     * @param elem elemento da inserire.
     * @throws FullContainerException se la dequeue &egrave piena.
     */
    void addLast(T elem) throws FullContainerException;

    /** Metodo che consente la rimozione dell'elemento in cima alla collezione.
     *
     * @return l'elemento rimosso.
     * @throws OurNotFoundException se la dequeue &egrave vuota.
     */
    default T removeFirst() throws OurNotFoundException {
        return remove();
    }

    /** Metodo che consente la rimozione dell'elemento in dequeue alla collezione.
     *
     * @return l'elemento rimosso.
     * @throws OurNotFoundException se la dequeue &egrave vuota.
     */
    T removeLast() throws OurNotFoundException;

    /** Metodo che restituisce l'elemento in cima alla collezione.
     *
     * @return l'elemento in cima.
     * @throws OurNotFoundException se la dequeue &egrave vuota.
     */
    default T first() throws OurNotFoundException {
        return top();
    }

    /** Metodo che restituisce l'elemento in dequeue alla collezione.
     *
     * @return l'elemento in dequeue.
     * @throws OurNotFoundException se la dequeue &grave vuota.
     */
    T last() throws OurNotFoundException;
}
