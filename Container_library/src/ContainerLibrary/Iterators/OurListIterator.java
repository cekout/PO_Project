package ContainerLibrary.Iterators;
import ContainerLibrary.Exception.OurNotFoundException;

/** Interfaccia che mi definisce in metodi che in aggiunta un iteratore deve definire per iterare su una lista, che consideriamo come raccolta indicizzata di elementi di tipo T.
 * Viene lanciata l'OurNotFoundException se non ci sono pi&ugrave elementi da visitare.
 *
 * @param <T> tipo di elementi su cui iterare.
 */

public interface OurListIterator<T> extends OurIterator<T> {

    /** Metodo che consente di verificare se ci siano altri elementi da iterare che precedono la posizione attuale del cursore.
     *
     * @return true se ci sono elementi precedenti da visitare, false altrimenti.
     */
    boolean hasPrevious();

    /** Metodo che consente di visionare l'elemento che precede immediatamente la posizione attuale del cursore.
     *
     * @return oggetto da visitare che precede il cursore.
     * @throws OurNotFoundException se non ci sono pi&ugrave elemnti da visitare.
	 */
    T previous() throws OurNotFoundException;

}
