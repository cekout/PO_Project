package ContainerLibrary.List.NodeList;


/*** Classe che rappresenta un singolo nodo che viene utilizzato dalle liste costruite con nodi
 *
 * @param <T> tipo dell'informazione contenuta dal nodo
 */

class OurSingleNode<T> implements OurNode<T> {

    /**
     *  Variabile che contiene l'informazione.
     */
    private T key;

    /**
     * Variabile che contiene il riferimento all'elemento successivo.
     */
    private OurNode<T> next;

    public OurSingleNode(){
        key = null;
        next = null;
    }

    /**
     *
     * @param key informazione da inserire nel nodo.
     */

    public OurSingleNode(T key){
        this();
        setInfo(key);
    }

    /**
     *
     * @param next riferimento al futuro nodo successivo.
     */

    public OurSingleNode(OurNode<T> next){
        this();
        setNext(next);
    }

    /**
     *
     * @param key informazione da inserire nel nodo.
     * @param next riferimento al futuro nodo successivo.
     */
    public OurSingleNode(T key ,OurNode<T> next) {
        setInfo(key);
        setNext(next);
    }


    @Override
    public T getInfo() {
        return this.key;
    }

    @Override
    public OurNode<T> getNext() {
        return this.next;
    }

    @Override

    public void setInfo(T key) {
        this.key = key;
    }

    @Override
    public void setNext(OurNode<T> next) {
        this.next = next;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }
}


/*
il campo next è di tipo OurNode (interfaccia), in questo modo è possibile realizzare l'overriding del metodo
setNext(che accetta un OurNode), cosa che non si potrebbe fare con un campo List.NodeList.OurSingleNode.

altra curiosità: con un campo di tipo OurSingleNode avremmo potuto overridare il metodo next dando come tipo di ritorno
List.NodeList.OurSingleNode senza problemi perchè avremmo sfruttato la proprietà della covarianza; ciò non sarebbe stato del
tutto corretto perchè nell'interfaccia si fa intendere che il tipo di ritorno sia un OurNode.
Comunque non ci sarebbero stati grossi problemi perchè List.NodeList.OurSingleNode è un sottotipo di OurNode e, grazie alla sussunzione,
sarebbe stato comunque possibile un assegnamento del tipo: OurNode a = nodo.next();

 */
