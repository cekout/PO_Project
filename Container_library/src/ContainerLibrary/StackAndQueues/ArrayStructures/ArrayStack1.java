package ContainerLibrary.StackAndQueues.ArrayStructures;


import ContainerLibrary.Exception.*;
import ContainerLibrary.List.ArrayList.OurArrayList;
import ContainerLibrary.List.OurList;
import ContainerLibrary.Container.Container;
import ContainerLibrary.Iterators.OurIterator;
import ContainerLibrary.StackAndQueues.Stack;
import com.sun.istack.internal.NotNull;

/** Classe che rappresenta lo stack. Sfrutta la composizione.
 * Viene lanciata l'OurUnsupportedFunction se la funzionalit&agrave non &egrave supportata.
 * Viene lanciata l'OurNotFoundException se non &egrave possibile restituire un elemento.
 * @param <T> tipo degli oggetti contenuti nella collezione.
 */

public class ArrayStack1<T> implements Stack<T> {

    /** Lista all'interno della quale sono memorizzati gli elementi della coda.
     */
    OurList<T> structure;

    /** Costruttore della coda.
     */
    public ArrayStack1 () {
        structure = new OurArrayList<>();
    }

    /*int position (T elem) {
        return structure.indexOf(elem);
    }*/

    @Override
    public void add(@NotNull T elem) {
        structure.insertHead(elem);
    }

    @Override
    public boolean contains(@NotNull T elem) {
        return structure.contains(elem);
    }

    public boolean equals (Object var1) {

        if(getClass().getName().equals(var1.getClass().getName())) {
            OurIterator<T> esterno = ((ArrayStack1<T>) var1).iterator();
            OurIterator<T> interno = iterator();
            while(interno.hasNext() && esterno.hasNext() && interno.next().equals(esterno.next())) {}
            return !interno.hasNext() && !esterno.hasNext();
        } else return false;
    }

    @Override
    public boolean isEmpty() {
        return structure.isEmpty();
    }

    @Override
    public OurIterator<T> iterator() {
        return structure.iterator();
    }

    @Override
    public T remove() throws OurNotFoundException {
        try {
            return structure.removeHead();
        } catch (OurIndexOutOfBoundsException e) {
            throw new OurNotFoundException("The stack is empty.");
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
            pop();
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
        return structure.size();
    }

    /** Metodo che consente di rappresentare la lista come stringa.
     *
     * @return la stringa che rappresenta la lista.
     */
    public String toString () { //funzia
        StringBuilder b = new StringBuilder();
        OurIterator<T> supp = iterator();
        while(supp.hasNext())
            b.append(String.format("%s ", supp.next()) + "; ");
        return b.toString();
    }

    @Override
    public T top() throws OurNotFoundException {
        return structure.getHead();
    }
}
