package ContainerLibrary.List.NodeList;
import ContainerLibrary.List.Sortable;
import com.sun.istack.internal.NotNull;

/** Classe che implementa le liste doppiamente concatenate ordinabili.
 *
 * @param <T> tipo degli elementi contenuti nella lista, essi dovranno essere comparabili.
 */
public class OurSortableLinkedList<T extends Comparable<T>> extends OurLinkedList<T> implements Sortable<T> {
    /** Metodo che realizza l'ordinamento tramite l'algoritmo BubbleSort.
     *
     */

    /** Costruttore di default che semplicemente richiama il costruttore di default di OurLinkedList.
     *
     */

    public OurSortableLinkedList(){
        super();
    }

    /** Costruttore che prende come parametro un elemento, semplicemente richiama il costruttore analogo di OurLinkedList.
     *
     * @param elem elemento che sara&agrave la testa della lista creata.
     */
    public OurSortableLinkedList(@NotNull T elem){
        super(elem);
    }


    /** Metodo che realizza l'ordinamento tramite l'algoritmo BubbleSort.
     *
     */

    @Override
    public void sort(){
        if (super.size() > 0){

            boolean swaped = true;
            while (swaped){
                swaped = false;
                OurDoubleLinkedNode<T> curs = first;
                while (curs.hasNext())
                    swaped = swapSort(curs, (curs = curs.getNext())) || swaped;
            }
        }
    }

    /** Metodo che scambia il contenuto di due nodi se il quello del primo &egrave maggiore
     * di quello del secondo
     *
     * @param first primo nodo.
     * @param second secondo nodo.
     * @param <E>tipo dell'informazione contenuto nei nodi, deve essere sottotipo di
     *           Comparable.
     */
    private static < E extends Comparable<E>> boolean swapSort( OurDoubleLinkedNode<E> first, OurDoubleLinkedNode<E> second ){
        if (first.getInfo().compareTo(second.getInfo()) > 0 ){
            E tmp = first.getInfo();
            first.setInfo(second.getInfo());
            second.setInfo(tmp);
            return true;
        }
        return false;
    }
}
