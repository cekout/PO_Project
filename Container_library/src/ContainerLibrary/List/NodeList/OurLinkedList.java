package ContainerLibrary.List.NodeList;

import ContainerLibrary.Exception.OurNotFoundException;
import ContainerLibrary.Exception.FullContainerException;
import ContainerLibrary.Exception.OurIndexOutOfBoundsException;
import ContainerLibrary.Iterators.OurIterator;
import ContainerLibrary.Iterators.OurJumpListIteratorHeavy;
import ContainerLibrary.Iterators.OurListIterator;
import ContainerLibrary.List.OurList;
import ContainerLibrary.Sets.Couple;
import com.sun.istack.internal.NotNull;

/** Classe che implementa una lista realizzata mediante nodi doppiamente concatenati.
 *
 *
 *  I metodi che inseriscono elementi nella lista possono lanciare una eccezione di
 *  tipo FullContainerException() se l'inserimento di un elemento causa un incremento del
 *  campo size con conseguente overflow di quest'ultimo.
 *
 * @param <T> tipo degli elementi contenuti nella lista.
 *
 */
public class OurLinkedList<T> implements OurList<T> {

    /** Campo che memorizza il riferimento alla testa della lista.
     *
     */
    OurDoubleLinkedNode<T> first;           //questo campo ha visibilità default perchè la sottoclasse OurSortableLinkedList
                                            //deve poterci accedere

    /** Campo che memorizza il riferimento alla coda della lista.
     *
     */
    private OurDoubleLinkedNode<T> last;


    /** Campo che memorizza il numero di elementi inseriti.
     *
     */
    private int size;


    /** Costruttore di default che istanzia una lista vuota.
     *
     */
    public OurLinkedList(){
        first = null;
        last = null;
        size = 0;
    }

    /** Costruttore che istanzia una lista con un solo elemento.
     *
     * @param elem elemento che sar&agrave l'unico elemento contenuto nella lista.
     */
    public OurLinkedList(@NotNull T elem){
        OurDoubleLinkedNode<T> newNode = new OurLinkedNode<>(elem);
        first = newNode;
        last = newNode;
        setSize(1);
    }


    @Override
    public void add(@NotNull T elem) throws FullContainerException {
        incrementSize();        //incrementSize viene messo prima così se size diventa troppo grande l'eccezione viene lanciata
                                //prima di inserire il nodo
        first = new OurLinkedNode<T>(elem, first, null);
        if (size() == 1)
            last = first;
        else
            first.getNext().setPrev(first);
    }

    @Override
    public boolean contains(@NotNull T elem) {
        try{
            findNode(elem);
        } catch (OurNotFoundException c){
            return false;
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return (first == null);
    }



    @Override
    public void remove(@NotNull T elem) throws OurNotFoundException {
        OurDoubleLinkedNode<T> n = findNode(elem).getFirst();

        if (n == first)
            first = n.getNext();
        else
            n.getPrev().setNext(n.getNext());

        if(n == last)
            last = n.getPrev();
        else
            n.getNext().setPrev(n.getPrev());

        decrementSize();

    }

    @Override
    public void removeAll() {
        first = last = null;
        setSize(0);
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public T getAt(int index) throws OurIndexOutOfBoundsException {
        return findNodeEff(index).getInfo();
    }

    @Override
    public int indexOf(@NotNull T elem) throws OurNotFoundException {
        return findNode(elem).getSecond();
    }

    @Override
    public void insertAt(int index, @NotNull T elem) throws OurIndexOutOfBoundsException, FullContainerException {

        if (index == 0)
            insertHead(elem);
        else if(index == size)
            insertTail(elem);

        else {
            OurDoubleLinkedNode<T> n = findNodeEff(index);
            incrementSize();
            OurDoubleLinkedNode<T> newNode = new OurLinkedNode<>(elem, n, n.getPrev());

            n.getPrev().setNext(newNode);
            n.setPrev(newNode);
        }
    }

    @Override
    public void insertHead(@NotNull T elem) throws FullContainerException{
        add(elem);
    }

    @Override
    public void insertTail(@NotNull T elem) throws FullContainerException {
        if (size() == 0)
            insertHead(elem);
        else{
            incrementSize();
            last = new OurLinkedNode<>(elem, null, last);
            last.getPrev().setNext(last);
        }

    }

    @Override
    public int lastIndexOf(@NotNull T elem) throws OurNotFoundException {
        OurDoubleLinkedNode<T> n = last;
        int index = size()-1;
        while (n != null){
            if (n.getInfo().equals(elem))
                return index;
            else {
                index--;
                n = n.getPrev();
            }
        }
        throw new OurNotFoundException();
    }

    @Override
    public T removeLast() throws OurNotFoundException {
        if (last == first)
            try {
                return removeAt(size()-1);
            }catch(OurIndexOutOfBoundsException c){
                throw new OurNotFoundException();
            }
        else{
            T ret = last.getInfo();
            last.getPrev().setNext(null);
            last = last.getPrev();
            decrementSize();
            return ret;
        }
    }

    @Override
    public T removeAt(int index) throws OurIndexOutOfBoundsException {
        OurDoubleLinkedNode<T> n = findNodeEff(index);


        if (n == first)
            first = n.getNext();
        else
            n.getPrev().setNext(n.getNext());

        if(n == last)
            last = n.getPrev();
        else
            n.getNext().setPrev(n.getPrev());

        decrementSize();
        return n.getInfo();
    }

    @Override
    public void setAt(int index,@NotNull T elem) throws OurIndexOutOfBoundsException {
        findNodeEff(index).setInfo(elem);
    }









    /**
     * Metodo utilizzato dagli altri metodi per trovare un nodo in base all'indice.
     *
     * @param index indice del nodo da trovare.
     * @return nodo in posizione index.
     * @throws OurIndexOutOfBoundsException se l'inidice non è valido.
     */
    private OurDoubleLinkedNode<T> findNode(int index) throws OurIndexOutOfBoundsException {
        OurDoubleLinkedNode<T> now = first;

        if (index < 0)
            throw new OurIndexOutOfBoundsException("l'indice " + index + " è negativo, quindi al di fuori della lista");

        if (index >= size())
            throw new OurIndexOutOfBoundsException("l'indice " + index + " è al di fuori della lista");

        while(index > 0) {
            now = now.getNext();
            index--;
        }

        return now;
    }

    /**
     * Metodo utilizzato dagli altri metodi per trovare un nodo scorrendo la lista, dalla coda verso
     * la testa, in base all'indice.
     *
     * @param index indice del nodo da trovare.
     * @return nodo in posizione index.
     * @throws OurIndexOutOfBoundsException se l'inidice non &egrave valido.
     */
    private OurDoubleLinkedNode<T> findNodeFromTail(int index) throws OurIndexOutOfBoundsException {
        OurDoubleLinkedNode<T> now = last;

        if (index < 0)
            throw new OurIndexOutOfBoundsException("l'indice " + index + " è negativo, quindi al di fuori della lista");

        if (index >= size())
            throw new OurIndexOutOfBoundsException("l'indice " + index + " è al di fuori della lista");

        while(index < size()-1) {
            now = now.getPrev();
            index++;
        }

        return now;
    }

    /** Metodo che in base all'indice inserito utilizza il metodo findNode oppure il metodo findNodeFromTail
     *  per restituire il nodo al suddetto indice.
     *  Questo metodo &egrave stato inserito per aumentare l'efficienza media delle ricerche e sfrutta il doppio
     *  concatenamento dei nodi.
     *
     * @param index indice del nodo cercato.
     * @return nodo all'indice index.
     * @throws OurIndexOutOfBoundsException se l'inidice non &egrave valido.
     */
    private OurDoubleLinkedNode<T> findNodeEff(int index) throws OurIndexOutOfBoundsException {
        if (index > size()/2)
            return findNodeFromTail(index);
        else
            return findNode(index);
    }

    /**
     *
     * @param elem valore dell'informazione del nodo da trovare.
     * @return  Coppia (indice,nodo) che si riferisce al nodo con il valore cercato.
     * @throws OurNotFoundException se l'elemento non &egrave presente.
     */
    private Couple<OurDoubleLinkedNode<T>, Integer> findNode(@NotNull T elem) throws OurNotFoundException {
        OurDoubleLinkedNode<T> now = first;
        int index = 0;

        while(now != null && !(now.getInfo().equals(elem))){
            now = now.getNext();
            index++;
        }

        if (now == null)
            throw new OurNotFoundException("L'elemento cercato non è presente nella lista");
        else
            return new Couple<>(now, index);

    }


    /** Classe che rappresenta l'iteratore di tipo OurIterator per la lista.
     *
     */


    public class DoubleListIterator implements OurIterator<T>{

        /**
         * Riferimento a un nodo, esso verrà usato come cursore per scorrere i nodi della lista.
         */
        protected OurDoubleLinkedNode<T> curs = first;

        /**
         *  Indice che il nodo riferito da curs ha all'interno della lista.
         */
        protected int index = 0;


        @Override
        public boolean hasNext() {
            return (curs != null);
        }

        @Override
        public T next() {
            T ret = curs.getInfo();
            curs = curs.getNext();
            index++;
            return ret;
        }
    }

    /** Classe che rappresenta l'iteratore di tipo OurListIterator per la lista.
     *
     */
    public class DoubleListListIterator extends DoubleListIterator implements OurListIterator<T>{

        @Override
        public boolean hasPrevious()  {
            return ((index <= size()) && (index > 0));
        }

        @Override
        public T previous() {
            if (index == size())
                curs = findNode(size()-1);
            else
                curs = curs.getPrev();


            index--;
            return curs.getInfo();
        }
    }

    /** Classe che rappresenta l'iteratore di tipo OurJumpListIteratorHeavy per la lista.
     *
     */
    public class DoubleListJumpListIteratorHeavy extends DoubleListListIterator implements OurJumpListIteratorHeavy<T>{
        @Override
        public void jump(int offset) {
            while (offset > 0){
                curs = curs.getNext();
                index++;
                offset--;
            }
            while (offset < 0){
                curs = curs.getPrev();
                index--;
                offset++;
            }
        }



        @Override
        public boolean hasAtDistance(int offset) throws OurIndexOutOfBoundsException {
            return ( (index+offset >= 0) && (index+offset<= size() - 1) );
        }

        @Override
        public void jumpList(int index) throws OurIndexOutOfBoundsException  {
            curs = findNodeEff(index);
            this.index = index;
        }

        @Override
        public boolean hasInPosition(int index) {
            return ( (index >= 0) && (index<= size() - 1) );
        }

        @Override
        public void remove()  throws OurNotFoundException {
            try {
                removeAt(index -1);
			} catch (OurIndexOutOfBoundsException e) {
				throw new OurNotFoundException("The element to remove doesn't exist.");
			}
        }

        @Override
        public T removeIndex(int index) throws OurIndexOutOfBoundsException  {
            return removeAt(index);
        }
    }

    @Override
    public OurIterator<T> iterator() {
        return new DoubleListIterator();
    }

    @Override
    public OurListIterator<T> listIterator() {
        return new DoubleListListIterator();
    }
    @Override
    public OurJumpListIteratorHeavy<T> JumpListIteratorHeavy()  {
        return new DoubleListJumpListIteratorHeavy();
    }

    @Override
    public boolean equals(Object o) {
        if(getClass().equals(o.getClass())) {

            OurLinkedList<T> l = (OurLinkedList<T>) o;
            OurIterator<T> it = this.iterator();
            OurIterator<T> it2 = l.iterator();
            while (it.hasNext() && it2.hasNext()) {
                if (it.next() != it2.next())
                    return false;
            }

            if (it.hasNext() || it2.hasNext())
                return false;
            return true;
        }
        return false;
    }

    /** Metodo che permette di rappresentare la lista come una stringa.
     *
     * @return la lista rappresentata come stringa.
     */

    @Override
    public String toString() {
        String ret = "";
        OurIterator<T> it = this.iterator();
        int i = 0;
        while (it.hasNext()){
            ret += "[" + i++ + "]:\t" + it.next() + "\n";
        }
        return ret;
    }






    /** Metodo che incrementa di 1 la dimensione della lista.
     * @throws FullContainerException se con l'incremento size andrebbe in overflow.
     */

    private void incrementSize() throws  FullContainerException {
        if (size == 2147483647)
            throw new FullContainerException();
        size++;
    }

    /** Metodo che decrementa di 1 la dimensione della lista.
     *
     */

    private void decrementSize(){
        size--;
    }

    /** Metodo che cambia il valore di size.
     *
     * @param a nuovo valore di size.
     */
    private void setSize(int a){
        size = a;
    }

}
