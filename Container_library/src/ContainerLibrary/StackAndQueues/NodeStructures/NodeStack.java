package ContainerLibrary.StackAndQueues.NodeStructures;

import ContainerLibrary.Iterators.OurIterator;
import ContainerLibrary.List.NodeList.OurNodeList;
import com.sun.istack.internal.NotNull;
import ContainerLibrary.Exception.OurNotFoundException;
import ContainerLibrary.Exception.FullContainerException;
import ContainerLibrary.StackAndQueues.Stack;

/** Classe che implementa uno stack incapsulando una NodeList.
 *
 *
 *  I metodi che inseriscono elementi nello stack possono lanciare una eccezione di
 *  tipo FullContainerException() se l'inserimento di un elemento causa un incremento del
 *  campo size con conseguente overflow di quest'ultimo.
 *
 *  I metodi che rimuovono o ritornano un elemento possono lanciare una eccezione di tipo OurNotFoundException
 *  se l'elemento non &egrave presente.
 *
 * @param <T> tipo degli elementi contenuti nello Dequeue.
 */

public class NodeStack<T> implements Stack<T> {
    /**
     * Lista incapsulata, lo Stack utilizza questa lista per gestire gli elementi.
     */
    private OurNodeList<T> list = new OurNodeList<>();

    /**
     *  Rimuove l'elemento in testa.
     * @return il valore dell'elemento rimosso.
     */

    @Override
    public T remove() throws OurNotFoundException {
        return list.removeHead();
    }

    /**
     *  Restituisce l'elemento in testa.
     * @return il valore dell'elemento in testa.
     */
    @Override
    public T top()throws OurNotFoundException  {
        return list.getHead();
    }

    /**
     *  Aggiunge un elemento in testa.
     * @param elem elemento da aggiungere.
     */
    @Override
    public void add(@NotNull T elem) throws FullContainerException {
        list.insertHead(elem);
    }

    @Override
    public boolean contains(@NotNull T elem) {
        return list.contains(elem);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Svuota lo Stack.
     */

    @Override
    public void removeAll() {
        list.removeAll();
    }


    /**
     * Restituisce il numero di elementi contenuti nello Stack.
     * @return numero elementi conenuti nello Stack.
     */
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public OurIterator<T> iterator() {
        return list.iterator();
    }
    @Override
    public boolean equals(@NotNull Object o) {
        if(getClass().getName().equals(o.getClass().getName())) {
            NodeStack<T> l = (NodeStack<T>) o;
            return list.equals(l.list);
        }
        return false;
    }

    /** Metodo che consente di rappresentare lo Stack come una stringa.
     *
     * @return lo Stack rappresentato come stringa.
     */
    @Override
    public String toString() {
        return list.toString();
    }

}

