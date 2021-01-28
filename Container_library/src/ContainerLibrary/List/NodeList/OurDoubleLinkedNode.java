package ContainerLibrary.List.NodeList;

/** Interfaccia che specifica i metodi che deve implementare un nodo della lista
 * doppiamente concatenata.
 *
 * @param <T> tipo dell'informazione contenuta nel nodo.
 */

interface OurDoubleLinkedNode<T> extends OurNode<T> {

    /** Metodo che comunica se un nodo &egrave allacciato ad un nodo precedente.
     *
     * @return true se il nodo &egrave allacciato ad un nodo successivo, false altrimenti.
     */
    boolean hasPrev();

    @Override
    OurDoubleLinkedNode<T> getNext();          //utilizziamo la proprietà della covarianza per ritornare un OurLinkedNode
                                            // e comunque poter comunque eseguire l'Override del metodo next()

    /** Metodo che restituisce il riferimento al nodo precedente.
     *
     * @return riferimento al nodo successivo.
     */
    OurDoubleLinkedNode<T> getPrev();

    /** Metodo che permette di inserire il riferimento al nodo precedente.
     *
     * @param node riferimento al nodo successivo.
     */

    void setPrev(OurDoubleLinkedNode<T> node);
}
