package ContainerLibrary.Iterators;
import ContainerLibrary.Exception.OurNotFoundException;

/** Interfaccia che definisce le funzionalit&egrave che deve offire un iteratore che permette di scorrere una collezione a partire da due lati diversi (ad esempio dequeue).
 *
 * @param <T> tipo degli elementi della collezione.
 */
public interface TwoSidesIterator<T> extends OurIterator<T>{

    /** Metodo che consente di recepire se &egrave possibile visitare un nuovo elemento nel verso non convenzionale (dall'ultimo al primo).
     *
     * @return true se si pu&ograve visitare un altro elemento, false altrimenti.
     */
    boolean hasNextSecondSide();

    /** Metodo che consente di ricevere il prossimo elemento nella sequenza di visita dall'ultimo al primo elemento della collezione.
     *
     * @return prossimo elemento nella sequenza dal primo all'ultimo elemento della collezione.
     * @throws OurNotFoundException se non c'&egrave alcun elemento da visitare
     */
    T nextSecondSide() throws OurNotFoundException;
}
