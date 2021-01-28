package ContainerLibrary.StackAndQueues.ArrayStructures;

import ContainerLibrary.Exception.*;
import ContainerLibrary.Iterators.OurJumpListIterator;
import ContainerLibrary.List.OurList;
import ContainerLibrary.List.ArrayList.OurArrayList;
import ContainerLibrary.Container.Container;
import ContainerLibrary.Iterators.OurIterator;
import ContainerLibrary.StackAndQueues.Queue;
import com.sun.istack.internal.NotNull;

/** Classe che rappresenta una coda. Sfrutta la composizione.
 * Viene lanciata l'OurUnsupportedFunction se la funzionalit&agrave non &egrave supportata.
 * Viene lanciata l'OurNotFoundException se non &egrave possibile restituire un elemento.
 * @param <T> tipo degli oggetti contenuti nella collezione.
 */
public class ArrayQueue1<T> implements Queue<T> {

    /** Lista all'interno della quale sono memorizzati gli elementi della coda.
     */
    OurList<T> struct;

    /** Costruttore della coda.
     */
    public ArrayQueue1 () {
        struct = new OurArrayList<>();
    }

    @Override
    public void add(@NotNull T elem) {
        struct.insertHead(elem);
    }

    public boolean equals (Object var1) {

        if(getClass().getName().equals(var1.getClass().getName())) {
            OurIterator<T> esterno = ((ArrayQueue1<T>) var1).iterator();
            OurIterator<T> interno = iterator();
            while(interno.hasNext() && esterno.hasNext() && interno.next().equals(esterno.next())) {}
            return !interno.hasNext() && !esterno.hasNext();
        } else return false;
    }

    @Override
    public boolean contains(@NotNull T elem) {
        return struct.contains(elem);
    }

    @Override
    public boolean isEmpty() {
        return struct.isEmpty();
    }

    @Override
    public OurIterator<T> iterator() {
        return new OurIterator<T>() {

            private OurJumpListIterator<T> supp;

            {
                supp = struct.JumpListIteratorHeavy();
                /*try {
                    supp.jumpList(size() - 1);
                    supp.next();
                } catch (OurIndexOutOfBoundsException e) {} //quando verrà eseguito hasPrevious oppure Previous vengono lanciate le eccezioni OurNotFoundException.
                */
                supp.jumpList(size() - 1);
                supp.next();
            }

            @Override
            public boolean hasNext() {
                return supp.hasPrevious();
            }

            @Override
            public T next() throws OurNotFoundException {
                return supp.previous();
            }

        };
    }

    @Override
    public T remove() throws OurNotFoundException {
        try {
            return struct.removeLast();
        } catch (OurIndexOutOfBoundsException e) {
            throw new OurNotFoundException("The queue is empty.");
        }
    }

    /** {@inheritDoc}
     * Funzonalit&agrave non supportata.
     */
    @Override
    public void remove(T elem) throws OurUnsupportedFunctionException {
        throw new OurUnsupportedFunctionException();
    }

    @Override
    public void removeAll() {
        while(size() != 0)
            dequeue();
    }

    /** {@inheritDoc}
     * Funzonalit&agrave non supportata.
     */
    @Override
    public void removeAllFrom(Container<T> c) throws OurUnsupportedFunctionException {
        throw new OurUnsupportedFunctionException();
    }

    @Override
    public int size() {
        return struct.size();
    }

    /** Metodo che consente di rappresentare la lista come stringa.
     *
     * @return la stringa che rappresenta la lista.
     */
    public String toString () { //funzia
        StringBuilder b = new StringBuilder();
        OurIterator<T> supp = iterator();
        while(supp.hasNext())
            b.append(String.format("%s ", supp.next()));
        return b.toString();
    }

    @Override
    public T top() throws OurNotFoundException {
            return struct.getLast();

    }
}
