package ContainerLibrary.Iterators;
import ContainerLibrary.Exception.OurIndexOutOfBoundsException;

/** Interfaccia che definisce un iteratore in grado di restituire non l'elemento successivo rispetto la posizione in cui &egrave locato il cursore,
 *  ma di raggiungere elementi successivi pi&ugrave avanti.
 *  Viene lanciata l'OurIndexOutOfBoundsException se si cerca di saltare al di fuori della collezione.
 *
 * @param <T> tipo degli elementi su cui iterarare.
 */

public interface OurJumpIterator<T> extends OurIterator<T> {

    /** Questo metodo consente di spostare il cursore dell'iteratore di un numero preciso di posizioni in avanti.
     *
     * @param offset numero di elementi da saltare.
     * @throws OurIndexOutOfBoundsException se si cerca di saltare al di fuori della collezione.
     */
    void jump (int offset) throws OurIndexOutOfBoundsException;

    /** Metodo che controlla se alla posizione relativa indicata è presente un elemento.
     *
     * @param offset posizione relativa nella quale controllare la presenza di un elemento.
     * @return true se è presente un elemento, false altrimenti.
     */
    boolean hasAtDistance(int offset);
}
