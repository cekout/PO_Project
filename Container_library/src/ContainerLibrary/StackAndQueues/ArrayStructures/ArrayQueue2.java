package ContainerLibrary.StackAndQueues.ArrayStructures;

import ContainerLibrary.Exception.*;
import ContainerLibrary.StackAndQueues.AbstractArrayContainer;
import ContainerLibrary.Container.Container;
import ContainerLibrary.Iterators.*;
import ContainerLibrary.StackAndQueues.Queue;
import com.sun.istack.internal.NotNull;

/** Classe che rappresenta la coda. L'implementazione sfrutta l'ereditariet&agrave.
 * Viene lanciata l'OurUnsupportedFunction se la funzionalit&agrave non &egrave supportata.
 * Viene lanciata l'OurNotFoundException se non &egrave possibile restituire un elemento.
 * @param <T> tipo degli elementi contenuti nella collezione.
 */
public class ArrayQueue2<T> extends AbstractArrayContainer<T> implements Queue<T> {

    @Override
    public void add(@NotNull T elem) {
        insertInAPosition(0, elem);
    }

    @Override
    public boolean equals(Object var1) {

        if(getClass().getName().equals(var1.getClass().getName())) {
            OurIterator<T> esterno = ((ArrayQueue2<T>) var1).iterator();
            OurIterator<T> interno = iterator();
            while(interno.hasNext() && esterno.hasNext() && interno.next().equals(esterno.next())) {}
            return !interno.hasNext() && !esterno.hasNext();
        } else return false;
    }

    @Override
    public OurIterator<T> iterator() {
        return new OurIterator<T>() {

            int pos = size();

            @Override
            public boolean hasNext() {
                return pos-1 >= 0;
            }

            @Override
            public T next() throws OurNotFoundException {
                try {
                    return getFromAPosition(--pos);
                } catch (OurIndexOutOfBoundsException e) {
                    throw new OurNotFoundException("Out of the border of the queue.");
                }
            }
        };
    }

    @Override
    public T remove() throws OurNotFoundException {
        try {
            return removeAtAPosition(size() - 1);
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
        while( size() != 0 )
            dequeue();
    }

    /** {@inheritDoc}
     * Funzonalit&agrave non supportata.
     */
    @Override
    public void removeAllFrom(Container<T> c) throws OurUnsupportedFunctionException {
        throw new OurUnsupportedFunctionException();
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
        try {
            return getFromAPosition(size() - 1);
        } catch (OurIndexOutOfBoundsException e) {
            throw new OurNotFoundException("The queue is empty.");
        }
    }
}
