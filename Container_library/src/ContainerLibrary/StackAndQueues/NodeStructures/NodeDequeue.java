package ContainerLibrary.StackAndQueues.NodeStructures;


import ContainerLibrary.Exception.OurNotFoundException;
import ContainerLibrary.Exception.OurIndexOutOfBoundsException;
import ContainerLibrary.Exception.FullContainerException;
import ContainerLibrary.Iterators.TwoSidesIterator;
import ContainerLibrary.Iterators.OurIterator;
import ContainerLibrary.Iterators.OurListIterator;
import ContainerLibrary.Iterators.OurJumpListIteratorHeavy;
import ContainerLibrary.List.NodeList.OurLinkedList;
import ContainerLibrary.StackAndQueues.Dequeue;
import com.sun.istack.internal.NotNull;

/** Classe che implementa una Dequeue incapsulando una NodeList.
 *
 *
 *  I metodi che inseriscono elementi nella Dequeue possono lanciare una eccezione di
 *  tipo FullContainerException() se l'inserimento di un elemento causa un incremento del
 *  campo size con conseguente overflow di quest'ultimo.
 *
 *  I metodi che rimuovono o ritornano un elemento possono lanciare una eccezione di tipo OurNotFoundException
 *  se l'elemento non &egrave presente.
 *
 * @param <T> tipo degli elementi contenuti nello Dequeue.
 */

public class NodeDequeue<T> implements Dequeue<T> {
    /**
     * Lista incapsulata, la Dequeue utilizza questa lista per gestire gli elementi.
     *
     */
    private OurLinkedList<T> list = new OurLinkedList<>();


    @Override
    public void addLast(@NotNull T elem) throws FullContainerException {
        list.insertTail(elem);
    }


    @Override
    public T removeLast() throws OurNotFoundException {
        return list.removeLast();
    }


    @Override
    public T last() throws OurNotFoundException {
        return list.getLast();
    }


    @Override
    public T remove() throws OurNotFoundException {
        return list.removeHead();
    }


    @Override
    public T top() throws OurNotFoundException {
        return list.getHead();
    }


    @Override
    public void add(@NotNull T elem) throws FullContainerException {
        list.add(elem);
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
    public TwoSidesIterator<T> iterator() {         //TODO javadoc iteratore

        return new TwoSidesIterator<T>() {

            private OurIterator<T> itLeft = list.iterator();
            private OurListIterator<T> itRight;

            {
                OurJumpListIteratorHeavy<T> itJump = list.JumpListIteratorHeavy();
                itJump.jump(list.size() - 1);
                itJump.next();
                itRight = itJump;
            }

            @Override
            public boolean hasNextSecondSide() { return itRight.hasPrevious(); }

            @Override
            public T nextSecondSide() {
                try {
                    return itRight.previous();
                } catch (OurIndexOutOfBoundsException e) {
                    throw new OurNotFoundException();
                }
            }

            @Override
            public boolean hasNext() { return itLeft.hasNext(); }

            @Override
            public T next() {
                try {
                    return itLeft.next();
                } catch (OurIndexOutOfBoundsException e) {
                    throw new OurNotFoundException();
                }
            }
        };
    }



    @Override
    public boolean equals(@NotNull Object o) {
        if (getClass().equals(o.getClass())) {
            NodeDequeue<T> l = (NodeDequeue<T>) o;
            return list.equals(l.list);
        }
        return false;
    }


    @Override
    public String toString() {
        return list.toString();
    }
}
