package ContainerLibrary.Iterators;
import ContainerLibrary.Exception.OurNotFoundException;

/** Interfaccia che definisce un iteratore "pesante", in grado di rimuovere l'elemento che precede il cursore, se esiste (in altri termini, quello appena restituito tramite il metodo next()).
 *  Viene lanciata la OurNotFoundException nel caso in cui non ci siano più elementi da rimuovere.
 *
 * @param <T> tipo degli elementi su cui iterare.
 */


public interface IteratorHeavy<T> extends OurIterator<T> {

	/** Metodo che consente di rimuovere l'elemento che precede il cursore, se esiste (in altri termini, quello appena restituito tramite il metodo next()).
	 *
	 * @throws OurNotFoundException se non ci sono più elementi da rimuovere.
	 */
    void remove() throws OurNotFoundException;

}
