package ContainerLibrary.StackAndQueues;

import ContainerLibrary.Exception.OurNotFoundException;
import ContainerLibrary.Exception.FullContainerException;

/** Interfaccia che definisce le funzionalità che deve offrire uno stack, ADT che prevede l'inserimento
 *  e la rimozione degli elementi dalla stessa estremit&agrave della collezione, secondo una politica FIFO.
 *
 * @param <T> tipo degli elementi contenuti nello stack
 */
public interface Stack<T> extends OurStackAndQueue<T> {


    /** Metodo che consente l'inserimento di un elemento nello stack.
     *
     * @param elem l'elemento da inserire.
     * @throws FullContainerException se lo stack &egrave pieno.
     */
    default void push(T elem) throws FullContainerException {
        add(elem);
    }

    /** Metodo che consente la rimozione dell'elemento dallo stack.
     *
     * @return l'elemento rimosso.
     * @throws OurNotFoundException se lo stack &egrave vuoto.
     */
    default T pop() throws OurNotFoundException {
        return remove();
    };
}
