package ContainerLibrary.List.NodeList;

/** Classe che implementa i nodi doppiamente concatenati
 *
 * @param <T> tipo dell'informazione contenuta nel nodo
 */
class OurLinkedNode<T> implements OurDoubleLinkedNode<T> {

    /**
     * Informazione contenuta nel nodo.
     */
    private T info;

    /**
     * Riferimento al nodo che precedente.
     */
    private OurDoubleLinkedNode<T> prev = null;


    /**
     * Riferimento al nodo successivo.
     */
    private OurDoubleLinkedNode<T> next = null;


    /** Costruttore che istanzia un nodo che contiene una data informazione.
     *
     * @param info informazione che sar&agrave contenuta dal nodo.
     */



    public OurLinkedNode(T info ){
        this.info = info;
    }

    /** Costruttore che istanzia un nodo che contiene una data informazione e setta i riferimenti
     * al nodo precedente e al nodo successivo.
     *
     * @param info informazione che sarà contenuta dal nodo.
     * @param next riferimento al nodo successivo.
     * @param prev riferimento al nodo precedente.
     */

    public OurLinkedNode(T info, OurDoubleLinkedNode<T> next, OurDoubleLinkedNode<T> prev){
        this.info = info;
        this.next = next;
        this.prev = prev;
    }




    @Override
    public OurDoubleLinkedNode<T> getPrev() {
        return prev;
    }

    @Override
    public OurDoubleLinkedNode<T> getNext() {
        return next;
    }

    @Override
    public void setPrev(OurDoubleLinkedNode<T> prev) {
        this.prev = prev;   // modificato il tipo del parametro, anche nell'interfaccia
    }

    @Override
    public void setNext(OurNode<T> next) {
        this.next = (OurDoubleLinkedNode) next; //questo cast è necessario per via del fatto che setNext viene ereditata da OurNode
    }

    @Override
    public void setInfo(T info) {
        this.info = info;
    }


    @Override
    public boolean hasPrev() {
        return (prev != null);
    }

    @Override
    public T getInfo() {
        return info;
    }

    @Override
    public boolean hasNext() {
        return (next != null);
    }
}
