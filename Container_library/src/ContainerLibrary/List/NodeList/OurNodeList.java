package ContainerLibrary.List.NodeList;

import ContainerLibrary.Exception.OurIndexOutOfBoundsException;
import ContainerLibrary.Exception.FullContainerException;
import ContainerLibrary.Exception.OurNotFoundException;
import ContainerLibrary.Iterators.OurIterator;
import ContainerLibrary.Iterators.OurListIterator;
import ContainerLibrary.Iterators.OurJumpListIteratorHeavy;
import ContainerLibrary.List.OurList;
import ContainerLibrary.Sets.Couple;
import com.sun.istack.internal.NotNull;

/** Classe che implementa una lista costruita mediante nodi.
 *
 *  I metodi che inseriscono elementi nella lista possono lanciare una eccezione di
 *  tipo FullContainerException() se l'inserimento di un elemento causa un incremento del
 *  campo size con conseguente overflow di quest'ultimo.
 *
 * @param <T> tipo degli elementi da memorizzare.
 */

public class OurNodeList<T> implements OurList<T>{
    /**
     *  Nodo che rappresenta la testa della lista.
     */
    OurNode<T> head = null;

    /**
     *  Campo che memorizza la dimensione (numero di nodi) della lista.
     *  Questo campo viene modificato solo dalla funzione setSize, incrementSize, decrementSize;
     *  in nessun'altra parte del codice viene modificato direttamente il campo size.
     */

    private int size = 0;

    /** Costruttore di default che istanzia una lista vuota.
     *
     */
    public OurNodeList(){
        head = null;
        setSize(0);
    }

    /** Costruttore che istanzia una lista con un solo elemento.
     *
     * @param head elemento che sar&agrave l'unico elemento contenuto nella lista.
     */
    public OurNodeList (@NotNull T head){
        insertAt(0, head);
        setSize(1);
    }

    /** Costruttore che istanzia una lista che avr&agrave in testa un alista data.
     *
     * @param headlist lista che viene messa in testa alla nuova lista.
     */
    public OurNodeList (@NotNull OurNodeList<T> headlist){

        this.head = headlist.head;
        setSize(headlist.size());
    }


    @Override
    public T getAt(int index) throws OurIndexOutOfBoundsException {
        return findNode(index).getInfo();
    }

    @Override
    public int indexOf(@NotNull T elem) throws OurNotFoundException {

        return findNode(elem).getSecond();
    }



    @Override
    public void insertAt(int index, @NotNull T elem) throws OurIndexOutOfBoundsException, FullContainerException {

        OurNode<T> newNode = new OurSingleNode<T>(elem);

        if (index == 0) {
            incrementSize();
            newNode.setNext(head);
            head = newNode;
            return;
        } else {
                OurNode<T> curs = findNode(index - 1);
                incrementSize();
                newNode.setNext(curs.getNext());
                curs.setNext(newNode);
        }
    }


    @Override
    public int lastIndexOf(@NotNull T elem) throws OurNotFoundException {

        int i = -1, index = 0;
        OurNode<T> now = head;
        while (now != null){
            if (now.getInfo().equals(elem))
                i = index;
            now = now.getNext();
            index ++;
        }
        return i;
    }



    @Override
    public T removeAt(int index) throws OurIndexOutOfBoundsException {
        OurNode<T> ret;
        if (index == 0){
            if (size < 1)
                throw new OurIndexOutOfBoundsException("non c'è nessun elemento da eliminare all'indice indicato");
            else {
                ret = head;
                head = head.getNext();
                decrementSize();
                return ret.getInfo();
            }
        }
        else {
            OurNode<T> prev = findNode(index-1);

            if (!prev.hasNext())
                throw new OurIndexOutOfBoundsException("non c'è nessun elemento da eliminare all'indice indicato");
            ret = prev.getNext();
            prev.setNext( (prev.getNext()).getNext() );
            decrementSize();
            return ret.getInfo();
        }

    }





    @Override
    public void setAt(int index, @NotNull T elem) throws OurIndexOutOfBoundsException {

        findNode(index).setInfo(elem);
    }




    @Override
    public void add(@NotNull T elem) throws FullContainerException {
        incrementSize();
        head = new OurSingleNode<T>(elem, head);
    }



    @Override
    public void remove(@NotNull T elem) throws OurNotFoundException {
        if (head.getInfo().equals(elem)){
            head = head.getNext();
            decrementSize();
        }
        else {
            OurNode<T> del1 = null;
            OurNode<T> del2 = head;
            boolean flag = false;
            while (del2.hasNext() && !flag) {
                del1 = del2;
                del2 = del2.getNext();
                if (del2.getInfo().equals(elem)) {
                    del1.setNext(del2.getNext());
                    decrementSize();
                    flag = true;
                }
            }
            if (!flag)
                throw new OurNotFoundException();
        }
    }



    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void removeAll() {
        setSize(0);
        head = null;
    }

    @Override
    public boolean contains(@NotNull T elem) {
        try{
            Couple<OurNode<T>, Integer> res = findNode(elem);

        } catch (OurNotFoundException c){
            return false;
        }

        return true;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Metodo utilizzato dagli altri metodi per trovare un nodo in base all'indice.
     *
     * @param index indice del nodo da trovare.
     * @return nodo in posizione index.
     * @throws OurIndexOutOfBoundsException se l'indice non è valido (negativo o troppo grande).
     */
    private OurNode<T> findNode(int index) throws OurIndexOutOfBoundsException {
        OurNode<T> now = head;

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
     *
     * @param elem valore dell'informazione del nodo da trovare.
     * @return  Coppia (indice,nodo) che si riferisce al nodo con il valore cercato.
     * @throws OurNotFoundException se l'elemento non è presente.
     */
    private Couple<OurNode<T>, Integer> findNode(@NotNull T elem) throws OurNotFoundException {


        OurNode<T> now = head;
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

    /** Metodo che incrementa di 1 la dimensione della lista.
     *  @throws FullContainerException se size va in overflow.
     */

    private void incrementSize() throws FullContainerException{
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


    @Override
    public boolean equals(Object o) {
        if(getClass().equals(o.getClass())) {

            OurNodeList<T> l = (OurNodeList<T>) o;
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

    /** Classe che rappresenta l'iteratore di tipo OurIterator per la lista.
     *
     */

    public class NodeListIterator implements OurIterator<T>{

        /**
         * Riferimento a un nodo, esso verrà usato come cursore per scorrere i nodi della lista.
         */
        protected OurNode<T> curs = head;

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
    public class NodeListListIterator extends NodeListIterator implements OurListIterator<T>{

        @Override
        public boolean hasPrevious()  {
            return ((index <= size()) && (index > 0));
        }

        @Override
        public T previous() {
            index --;
            curs = findNode(index);
            return curs.getInfo();
        }
    }

    /** Classe che rappresenta l'iteratore di tipo OurJumpListIteratorHeavy per la lista.
     *
     */

    public class NodeListJumpListIteratorHeavy extends NodeListListIterator implements OurJumpListIteratorHeavy<T>{
        @Override
        public void jump(int offset) throws IndexOutOfBoundsException {
            index += offset;
            curs = findNode(index);
        }



        @Override
        public boolean hasAtDistance(int offset) {
            return ( (index+offset >= 0) && (index+offset<= size() - 1) );
        }

        @Override
        public void jumpList(int index) throws IndexOutOfBoundsException {
            this.index = index;
            curs = findNode(index);
        }

        @Override
        public boolean hasInPosition(int index) {
            return ( (index >= 0) && (index<= size() - 1) );
        }

        @Override
        public void remove() {
            try {
                removeAt(index - 1);
            } catch (OurIndexOutOfBoundsException e) {
                throw new OurNotFoundException("The list is empty.");
            }
        }

        @Override
        public T removeIndex (int index) throws IndexOutOfBoundsException {
            return removeAt(index);
        }
    }

    @Override
    public OurIterator<T> iterator() {
        return new NodeListIterator();
    }

    @Override
    public OurListIterator<T> listIterator() {
        return new NodeListListIterator();
    }
    @Override
    public OurJumpListIteratorHeavy<T> JumpListIteratorHeavy() {
        return new NodeListJumpListIteratorHeavy();
    }




}




/*
    È importante notare che tutti i riferimenti ai nodi utilizzati all'interno della classe sono di tipo OurNode (interfaccia),
    gli unici punti in cui viene utilizzato il tipo OurSingleNode(classe) sono nella insertAt e nella add, quando viene creato
    il nodo da inserire.
    In questo modo la manutenzione del codice risulta più comoda e efficiente,se in futuro si volessero utilizzare altri tipi
    di nodi, sarebbe possibile farlo semplicemente modificando il tipo utilizzato alla creazione nella insertAt.

 */

