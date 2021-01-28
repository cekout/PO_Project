package ContainerLibrary.Iterators;
import ContainerLibrary.Exception.OurIndexOutOfBoundsException;

/** Interfaccia che definisce la funzionalit&agrave di un iteratore che consente di saltare ad uno specifico indice della collezione indicizzata su cui &egrave creato l'iteratore.
 *
 * @param <T> tipo degli elementi su cui iterare.
 */

public interface OurJumpListIterator<T> extends OurListIterator<T>, OurJumpIterator<T> {
    
    /** Metodo che consente di spostare il cursore in modo tale che il prossimo next() restituisca l'oggetto in posizione index.
     * 
     * @param index indice dell'elemento su cui saltare.
     * @throws OurIndexOutOfBoundsException se l'indice va al di fuori della dimensione della lista oppure è negativo.
     */
    void jumpList (int index) throws OurIndexOutOfBoundsException;

    /** Metodo che controlla che all'indice dato sia presente un elemento.
     *
     * @param index indice al quale controllare la presenza dell'elemento.
     * @return true se esiste l'elemento alla posizione indicata, false altrimenti.
     */
    boolean hasInPosition(int index);
}
