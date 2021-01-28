package ContainerLibrary.List;

/** Interfaccia che stabilisce le funzionalit&agrave che deve offrire qualsiasi cosa sia ordinabile.
 *
  * @param <T> tipo degli elementi da ordinare, deve estendere Comparable.
 */
public interface Sortable<T extends Comparable<? super T>> {

    /**
     * Ordina ci&ograve su cui &egrave invocata
     */
    void sort();
}
