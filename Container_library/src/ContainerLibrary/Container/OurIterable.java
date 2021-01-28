package ContainerLibrary.Container;

import ContainerLibrary.Iterators.OurIterator;

/** Radice della nostra gerarchia di classi: tutti i container sono iterabili.
 *  Devono fornire un metodo che restituisca un iteratore, un oggetto in grado di poter iterare in modo sicuro e controllato sugli elementi della collezione.
 *
 *  @param <E> tipo degli elementi di ci&ograve che può essere iterato.
 */
public interface OurIterable<E> {
    /**
     * Restituisce un iteratore che consenta di esplorare la collezione su cui viene invocato.
     * @return Un iteratore
     */
    OurIterator<E> iterator();
}
