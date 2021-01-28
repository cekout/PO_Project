package ContainerLibrary.List.NodeList;
import ContainerLibrary.List.Sortable;
import com.sun.istack.internal.NotNull;

/** Classe che implementa le liste singolarmente concatenate ordinabili
 *
 * @param <T> tipo degli elementi contenuti nella lista, essi dovranno essere comparabili
 */
public class OurSortableNodeList<T extends Comparable<T>> extends OurNodeList<T> implements Sortable<T> {

    /** Costruttore di default che semplicemente richiama il costruttore di default di OurNodeList.
     *
     */

    public OurSortableNodeList(){
        super();
    }

    /** Costruttore che prende come parametro un elemento, semplicemente richiama il costruttore analogo di OurNodeList.
     *
     * @param elem elemento che sara la testa della lista creata.
     */
    public OurSortableNodeList(@NotNull T elem){
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
                OurNode<T> curs = head;
                while (curs.hasNext())
                    swaped =  swapSort(curs, (curs = curs.getNext())) || swaped;
            }
        }
    }

    /** Metodo che scambia il contenuto di due nodi se il quello del primo &egrave maggiore
     * di quello del secondo.
     *
     * @param first primo nodo.
     * @param second secondo nodo.
     * @param <E> tipo dell'informazione contenuta nei nodi.
     */
    private static <E extends Comparable<E>> boolean swapSort( OurNode<E> first, OurNode<E> second ) {
        if (first.getInfo().compareTo(second.getInfo()) > 0) {
            E tmp = first.getInfo();
            first.setInfo(second.getInfo());
            second.setInfo(tmp);
            return true;
        }
        return false;
    }
}
