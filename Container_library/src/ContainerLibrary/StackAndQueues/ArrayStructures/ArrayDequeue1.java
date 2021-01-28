package ContainerLibrary.StackAndQueues.ArrayStructures;

import ContainerLibrary.Iterators.OurJumpListIterator;
import ContainerLibrary.Iterators.OurListIterator;
import ContainerLibrary.Iterators.TwoSidesIterator;
import ContainerLibrary.List.ArrayList.OurArrayList;
import ContainerLibrary.Exception.*;
import ContainerLibrary.Iterators.OurIterator;
import ContainerLibrary.Container.Container;
import ContainerLibrary.StackAndQueues.Dequeue;
import com.sun.istack.internal.NotNull;

/** Classe che rappresenta una dequeue. In questo caso l'implementazione sfrutta la composizione.
 * Viene lanciata l'OurUnsupportedFunction se la funzionalit&agrave non &egrave supportata.
 * Viene lanciata l'OurNotFoundException se non &egrave possibile restituire un elemento.
 * @param <T> tipo degli oggetti contenuti nella collezione.
 */
public class ArrayDequeue1<T> implements Dequeue<T> {

    /** Lista dove verranno memorizzati gli elementi.
     */
    private OurArrayList<T> struct;

    /** Costruttore della deqeueue.
     */
    public ArrayDequeue1() {
        struct = new OurArrayList<>();
    }

    @Override
    public void add(@NotNull T elem) {
        struct.insertHead(elem);
    }

    @Override
    public void addLast(@NotNull T elem) {
        struct.insertTail(elem);
    }

    @Override
    public boolean contains(@NotNull T elem) {
        return struct.contains(elem);
    }

    public boolean equals (Object var1) {

        if(getClass().getName().equals(var1.getClass().getName())) {
            OurIterator<T> esterno = ((ArrayDequeue1<T>) var1).iterator();
            OurIterator<T> interno = iterator();
            while(interno.hasNext() && esterno.hasNext() && interno.next().equals(esterno.next())) {}
            return !interno.hasNext() && !esterno.hasNext();
        } else return false;

    }

    @Override
    public boolean isEmpty() {
        return struct.isEmpty();
    }

    @Override
    public TwoSidesIterator<T> iterator() {
        return new TwoSidesIterator<T>() {

            private OurListIterator<T> supp1 = struct.listIterator();
            private OurJumpListIterator<T> supp2 = struct.JumpListIteratorHeavy();

            /*{
                    try {
                        supp2.jumpList(size() - 1);
                        supp2.next();
                    } catch (OurIndexOutOfBoundsException e) {} //poi verranno lanciate le opportune eccezioni utilizzando i metodi previsti.

            }*/

            {
                supp2.jumpList(size() - 1);
                supp2.next();
            }


            @Override
            public boolean hasNextSecondSide() {
                return supp2.hasPrevious();
            }

            @Override
            public T nextSecondSide() throws OurNotFoundException {
                return supp2.previous();
            }

            @Override
            public boolean hasNext() {
                return supp1.hasNext();
            }

            @Override
            public T next() throws OurNotFoundException {
                return supp1.next();
            }
        };

    }

    @Override
    public T last() throws OurNotFoundException {
        return struct.getLast();
    }

    @Override
    public T remove() throws OurNotFoundException {
        try {
            return struct.removeHead();
        } catch (OurIndexOutOfBoundsException e) {
            throw new OurNotFoundException("The dequeue is empty.");
        }
    }

    /** {@inheritDoc}
     * Funzionalit&agrave non supportata.
     *
     */
    @Override
    public void remove(@NotNull T elem) throws OurUnsupportedFunctionException {
        throw new OurUnsupportedFunctionException();
    }

    @Override
    public void removeAll() {
        while(size() != 0)
            remove();
    }

    /** {@inheritDoc}
     * Funzionalit&agrave non supportata.
     *
     */
    @Override
    public void removeAllFrom(@NotNull Container<T> c) throws OurUnsupportedFunctionException {
        throw new OurUnsupportedFunctionException();
    }


    @Override
    public T removeLast() throws OurNotFoundException {
        try {
            return struct.removeLast();
        } catch (OurIndexOutOfBoundsException e) {
            throw new OurNotFoundException("The dequeue is empty.");
        }
    }

    @Override
    public int size() {
        return struct.size();
    }

    /** Metodo che consente di rappresentare la lista come stringa.
     *
     * @return la stringa che rappresenta la lista.
     */
    public String toString () {
        StringBuilder b = new StringBuilder();
        OurIterator<T> supp = iterator();
        while(supp.hasNext())
            b.append(String.format("%s ", supp.next()));
        return b.toString();
    }

    @Override
    public T top() throws OurNotFoundException {
        return struct.getHead();
    }

}
