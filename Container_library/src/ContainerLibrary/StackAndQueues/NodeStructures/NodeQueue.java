package ContainerLibrary.StackAndQueues.NodeStructures;

import ContainerLibrary.Iterators.OurIterator;
import ContainerLibrary.List.NodeList.OurNodeList;
import com.sun.istack.internal.NotNull;
import ContainerLibrary.Exception.OurNotFoundException;
import ContainerLibrary.Exception.FullContainerException;
import ContainerLibrary.StackAndQueues.Queue;



/** Classe che implementa una Queue incapsulando una NodeList.
 *
 *
 *  I metodi che inseriscono elementi nella coda possono lanciare una eccezione di
 *  tipo FullContainerException() se l'inserimento di un elemento causa un incremento del
 *  campo size con conseguente overflow di quest'ultimo.
 *
 *  I metodi che rimuovono o ritornano un elemento possono lanciare una eccezione di tipo OurNotFoundException
 *  se l'elemento non &egrave presente.
 *
 * @param <T> tipo degli elementi contenuti nello Dequeue.
 */
public class NodeQueue<T> implements Queue<T> {
    /**
     * Lista incapsulata, la coda utilizza questa lista per gestire gli elementi.
     *
     * La lista capovolta, cio&grave la fine della lista corrisponde alla testa della coda, e viceversa
     * in questo modo posso utilizzare come iteratore l'iteratore di tipo OurIterator della lista dato che scorre
     * dall'inizio alla fine della lista, quindi dalla coda alla testa della coda.
     *
     */
    private OurNodeList<T> list = new OurNodeList<>();



    @Override
    public T remove() throws OurNotFoundException {
        return list.removeHead();
    }


    @Override
    public T top() throws OurNotFoundException {
        return list.getHead();
    }


    @Override
    public void add(@NotNull T elem) throws FullContainerException  {
        list.insertTail(elem);
    }

    @Override
    public boolean contains(@NotNull T elem) {
        return list.contains(elem);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }


    @Override
    public void removeAll()  {
        list.removeAll();
    }


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
        if (getClass().equals(o.getClass())) {
            NodeQueue<T> l = (NodeQueue<T>) o;
            return list.equals(l.list);
        }
        return false;
    }


    @Override
    public String toString() {
        return list.toString();
    }

}