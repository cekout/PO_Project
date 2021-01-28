package ContainerLibrary.StackAndQueues.ArrayStructures;

import ContainerLibrary.Iterators.TwoSidesIterator;
import ContainerLibrary.StackAndQueues.AbstractArrayContainer;

import ContainerLibrary.Exception.*;
import ContainerLibrary.Iterators.OurIterator;
import ContainerLibrary.Container.Container;
import ContainerLibrary.StackAndQueues.Dequeue;
import com.sun.istack.internal.NotNull;

/** Classe che rappresenta una dequeue. Sfrutta l'ederitarit&agrave.
 * Viene lanciata l'OurUnsupportedFunction se la funzionalit&agrave non &egrave supportata.
 * Viene lanciata l'OurNotFoundException se non &egrave possibile restituire un elemento.
 * @param <T> tipo degli oggetti memorizzati nella collezione.
 */
public class ArrayDequeue2<T> extends AbstractArrayContainer<T> implements Dequeue<T> {

    /** Costruttore della dequeue.
     */
    public ArrayDequeue2() {
        super();
    }

    @Override
    public void add(@NotNull T elem) {
        insertInAPosition(0, elem);
    }

    @Override
    public void addLast(@NotNull T elem) {
        insertInAPosition(size(), elem);
    }

    @Override
    public boolean equals(Object var1) {

        if(getClass().getName().equals(var1.getClass().getName())) {
            OurIterator<T> esterno = ((ArrayDequeue2<T>) var1).iterator();
            OurIterator<T> interno = iterator();
            while(interno.hasNext() && esterno.hasNext() && interno.next().equals(esterno.next())) {}
            return !interno.hasNext() && !esterno.hasNext();
        } else return false;
    }

    @Override
    public TwoSidesIterator<T> iterator() {
        return new TwoSidesIterator<T>() {

            private int posLeft = 0;
            private int posRight = size();

            @Override
            public boolean hasNextSecondSide() {
                return posRight-1 >= 0;
            }

            @Override
            public T nextSecondSide() {
                try {
                    return getFromAPosition(--posRight);
                } catch (IndexOutOfBoundsException e) {
                    throw new OurNotFoundException("Out of the borders of the list.");
                }
            }

            @Override
            public boolean hasNext() {
                return posLeft < size();
            }

            @Override
            public T next() {
                try {
                    return getFromAPosition(posLeft++);
                } catch (IndexOutOfBoundsException e) {
                    throw new OurNotFoundException("Out of the borders of the list.");
                }
            }
        };
    }

    @Override
    public T last() {
        return getFromAPosition(size() - 1);
    }

    @Override
    public T remove() throws OurNotFoundException {
        try {
            return removeAtAPosition(0);
        } catch (OurIndexOutOfBoundsException e) {
            throw new OurNotFoundException("The dequeue is empty.");
        }
    }

    /** {@inheritDoc}
     * Funzonalit&agrave non supportata.
     */
    @Override
    public void remove(T elem) throws OurUnsupportedFunctionException  {
        throw new OurUnsupportedFunctionException();
    }

    @Override
    public void removeAll() {
        while(size() != 0)
            remove();
    }

    /** {@inheritDoc}
     * Funzonalit&agrave non supportata.
     */
    @Override
    public void removeAllFrom(Container<T> c) throws OurUnsupportedFunctionException {
        throw new OurUnsupportedFunctionException();
    }

    @Override
    public T removeLast() throws OurNotFoundException{
        try {
            return removeAtAPosition(size() - 1);
        } catch (OurIndexOutOfBoundsException e) {
            throw new OurNotFoundException("The dequeue is empty.");
        }
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
    public T top() throws OurNotFoundException{
        try {
            return getFromAPosition(0);
        } catch (OurIndexOutOfBoundsException e) {
            throw new OurNotFoundException("The dequeue is empty.");
        }
    }

}
