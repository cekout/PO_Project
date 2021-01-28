package ContainerLibrary.List.NodeList;

/** Interfaccia che specifica i metodi che deve implementare un nodo della lista.
 *
 * @param <T> tipo dell'informazione contenuta nel nodo.
 */
interface OurNode<T> {

    /** Metodo che ritorna l'informazione contenuta nel nodo.
     *
     * @return informazione contenuta nel nodo.
     */
    T getInfo();

    /** Metodo che comunica se un nodo &egrave allacciato ad un nodo successivo
     *
     * @return true se il nodo &egrave allacciato ad un nodo successivo, false altrimenti.
     */

    boolean hasNext();

    /** Metodo che restituisce il riferimento al nodo successivo.
     *
     * @return riferimento al nodo successivo.
     */
    OurNode<T> getNext();

    /** Metodo che permette di inserire l'informazione contenuta nel nodo.
     *
     * @param elem informazione da inserire.
     */

    void setInfo(T elem);

    /** Metodo che permette di inserire il riferimento al nodo successivo.
     *
     * @param node riferimento al nodo successivo.
     */

    void setNext(OurNode<T> node);

}
