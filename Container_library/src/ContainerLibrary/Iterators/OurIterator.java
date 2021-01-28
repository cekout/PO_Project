package ContainerLibrary.Iterators;
import ContainerLibrary.Exception.OurNotFoundException;

/** Interfaccia che definisce i metodi che un iteratore su elementi di tipo E deve implementare.
 * Viene lanciata l'OurNotFoundException nel caso in cui non ci siano più elementi da visitare.
 *
 * @param <E> tipo degli elementi da visitare.
 */

public interface OurIterator<E> {

    /** Metodo che consente di verificare se ci siano altri elementi da visitare o meno.
     *
     * @return true se esiste almeno un altro elemento da visitare, altrimenti false.
     */
    boolean hasNext();

    /** Metodo che consente di ottenere un elemento per poi passare al successivo.
     *
     * @return il prossimo elemento di tipo E.
     * @throws OurNotFoundException se non ci sono più elementi da visitare.
	 */
    E next() throws OurNotFoundException;

}
