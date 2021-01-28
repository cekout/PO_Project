package ContainerLibrary.StackAndQueues.ArrayStructures;


import ContainerLibrary.Exception.*;
import ContainerLibrary.StackAndQueues.AbstractArrayContainer;
import ContainerLibrary.Container.Container;
import ContainerLibrary.Iterators.*;
import ContainerLibrary.StackAndQueues.Stack;


/** Classe che rappresenta lo stack. L'implementazione sfrutta l'ereditariet&agrave.
 * Viene lanciata l'OurUnsupportedFunction se la funzionalit&agrave non &egrave supportata.
 * Viene lanciata l'OurNotFoundException se non &egrave possibile restituire un elemento.
 * @param <T> tipo degli elementi contenuti nella collezione.
 */

public class ArrayStack2<T> extends AbstractArrayContainer<T> implements Stack<T> {

    @Override
    public void add(T elem) {
        insertInAPosition(0, elem);
    }

    public boolean equals (Object var1) {
        if(getClass().getName().equals(var1.getClass().getName())) {
            OurIterator<T> esterno = ((ArrayStack2<T>) var1).iterator();
            OurIterator<T> interno = iterator();
            while(interno.hasNext() && esterno.hasNext() && interno.next().equals(esterno.next())) {}
            return !interno.hasNext() && !esterno.hasNext();
        } else return false;
    }


    @Override
    public OurIterator<T> iterator() {
        return new OurIterator<T>() {

            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < size();
            }

            @Override
            public T next() throws OurNotFoundException {
                try {
                    return getFromAPosition(pos++);
                } catch (OurIndexOutOfBoundsException e) {
                    throw new OurNotFoundException("Out of the border of the stack.");
                }
            }
        };
    }

    @Override
    public T remove() throws OurNotFoundException {
        try {
            return removeAtAPosition(0);
        } catch (OurIndexOutOfBoundsException e) {
            throw new OurNotFoundException("The stack is empty.");
        }
    }

    /** {@inheritDoc}
     * Funzionalit&agrave non supportata.
     */
    @Override
    public void remove(T elem) throws OurUnsupportedFunctionException {
        throw new OurUnsupportedFunctionException();
    }

    @Override
    public void removeAll() {
        while(size() != 0)
            remove();
    }

    /** {@inheritDoc}
     * Funzionalit&agrave non supportata.
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
            return getFromAPosition(0);
        } catch (OurIndexOutOfBoundsException e) {
            throw new OurNotFoundException("The stack is empty.");
        }
    }
}
