package ContainerLibrary.StackAndQueues;

import ContainerLibrary.Container.Container;
import com.sun.istack.internal.NotNull;
import ContainerLibrary.Exception.OurUnsupportedFunctionException;
import ContainerLibrary.Exception.OurNotFoundException;

/** Interfaccia che descrive le funzionalit&agrave che dovrebbero possedere al minimo Stack e Code.
 *
 * @param <T> tipo degli elementi memorizzati nella collezione.
 */
public interface OurStackAndQueue<T> extends Container<T> {

    /** Metodo che consente la rimozione di un elemento.
     *
     * @return l'elemento rimosso.
     * @throws OurNotFoundException se la collezione &egrave vuota.
     */
    T remove() throws OurNotFoundException;



    /** Metodo che restituisce l'elemento in cima alla collezione.
     *
     * @return oggetto in cima.
     * @throws OurNotFoundException se la collezione &egrave vuota.
     */
    T top() throws OurNotFoundException;    //ritorna l'elemento in testa/coda senza toglierlo


    /**
     * Operazione non supportata
     * @param elem
     * @throws OurUnsupportedFunctionException sempre
     */
    @Override
    default void remove(@NotNull T elem)  {
        throw new OurUnsupportedFunctionException();
    }

    /**
     * Operazione non supportata
     *
     * @param c
     * @throws OurUnsupportedFunctionException sempre
     */
    @Override
    default void removeAllFrom(Container<T> c) {
        throw new OurUnsupportedFunctionException();
    }
}
