package ContainerLibrary.Container;

import ContainerLibrary.Exception.OurNotFoundException;
import ContainerLibrary.Iterators.OurIterator;
import ContainerLibrary.Exception.FullContainerException;

/** Interfaccia che definisce le funzionalit&agrave di un container, una collezione di oggetti.
 *
 *  I metodi che effettuano una ricerca di un determinato elemento all'interno del Container
 *  possono lanciare una eccezione di tipo OurNotFoundException se l'elemento non &egrave presente.
 *
 *
 *  I metodi che inseriscono elementi nel Container possono lanciare una eccezione di
 *  tipo FullContainerException se il Container non può più inserire elementi.
 *
  * @param <T> tipo degli elementi contenuti nella collezione.
 */

public interface Container<T> extends OurIterable<T> { //mettere in discussione da qui in avanti le eccezioni e scegliere se inserirle o meno.

    /** Metodo che consente di aggiungere elementi alla collezione.
     *
     * @param elem oggetto da aggiungere.
     * @throws FullContainerException se il container non può aggiungere altri elementi.
     */
    void add(T elem)  throws FullContainerException; //non è detto che il container si ridimensioni.

    /** Metodo che consente di aggiungere elementi tutti gli elementi di un altro container il cui tipo sia compatibile con quelli gi&agrave presenti.
     *
     * @param c container di elementi di tipo compatibile con quello degli elementi gi&agrave presenti.
     * @throws FullContainerException se il container non può aggiungere altri elementi.
     */
    default void addAll(Container<T> c) throws FullContainerException{
        OurIterator<T> support = c.iterator();
        while(support.hasNext()) {
            add(support.next());
        }
    }

    /** Metodo che consente di verificare la presenza di un determinato oggetto all'interno della collezione.
     *
     * @param elem elemento di cui verificare la presenza.
     * @return true se l'elemento &egrave presente nella collezione, false altrimenti.
     */
    boolean contains(T elem);

    /** Metodo che consente di verificare se gli elementi di una collezione sono presenti nella collezione su cui viene invocato il metodo.
     *
     * @param c collezione che contiene gli elementi di cui verificare la presenza.
     * @return true se sono tutti presenti, false altrimenti.
     */
    default boolean containsAll(Container<T> c) {
        OurIterator<T> supp = c.iterator();
        while(supp.hasNext()) {
            if(!contains(supp.next())) return false;
        }
        return true;
    }

    /** Metodo che consente di verificare se il container sia vuoto o meno.
     *
     * @return true se il container &egrave vuoto, false altrimenti.
     */
    boolean isEmpty();

    /** Metodo che consente di rimuovere un elemento dalla collezione.
     *
     * @param elem oggetto da eliminare.
     * @throws OurNotFoundException se l'elemento non &egrave presente nel container
     */
    void remove(T elem) throws OurNotFoundException;

    /** Metodo che consente di rimuovere tutti gli elementi dalla collezione.
     *
     */
    void removeAll(); //rimuove tutto

    /** Metodo che consente di rimuovere dal container su cui viene invocato tutti gli elementi presenti in un altro container.
     *  Tutti gli elementi del secondo Container devono essere contenuti nel primo Container.
     */
    default void removeAllFrom(Container<T> c) {
        OurIterator<T> it = c.iterator();
        while (it.hasNext()) {
            try {
                remove(it.next());
            } catch (OurNotFoundException e){}

        }
    }

    /** Metodo che consente di ottenere il numero di elemnti contenuti nella collezione.
     *
     */
    int size();


    /*
    COSA INTERESSANTE: non possiamo inserire un metodo di default nell'interfaccia che sovrascriva
    un metodo di object, perchè se lo facessimo, quando istanzieremo una classe che implementa l'interfaccia,
    ci darebbe un conflitto tra l'implementazione del metodo di Object e quella dell'interfaccia;
    dato che la prima regola per risolvere questo tipo di conflitti dà priorità all'implementazione fatta nella
    superclasse, in questo caso Object, la nostra classe adotterebbe l'implementazione di Object ignorando quella
    di default implementata nell'interfaccia.
    */
}
