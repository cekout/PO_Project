package ContainerLibrary.Sets;

import ContainerLibrary.Container.Container;

/** Interfaccia che definisce le funzionalit&agrave che deve definire un insieme.
 *
 * @param <T> tipo degli elementi dell'insieme.
 */

public interface OurSet<T> extends Container<T> {

    //la add deve controllare che l'elemento non sia già presenta
	
	/** Metodo che consente l'intersezione dell'insieme su cui viene invocato il metodo con l'insieme parametro.
	 *
	 * @param ms insieme con cui eseguire l'intersezione.
	 */
	OurSet<T> intersection (OurSet<T> ms);

	/** Metodo che consente l'unione dell'insieme su cui viene invocato il metodo con l'insieme parametro.
	 *
	 * @param ms insieme con cui eseguire l'unione.
	 */
	OurSet<T> union (OurSet<T> ms);


	/** Metodo che consente di eseguire la differenza tra l'insieme su cui viene invocato il metodo e l'insieme parametro.
	 *
	 * @param ms insieme sottraendo.
	 */
	OurSet<T> difference (OurSet<T> ms); //Gli oggetti potrebbero essere sussunti a T e quindi potrebbero avere tipo dinamico diverso da quelli presenti dentro la T esterna.

	/** Metodo che consente di verificare se l'insieme parametro è sottoinsieme dell'insieme su cui il metodo viene invocato.
	 * 
	 * @param ms insieme che potrebbe contenere l'oggetto su cui viene invocato difference.
	 */
    boolean isSubset(OurSet<T> ms);

	/** Metodo che ritorna l'insieme complementare all'insieme chiamante rispetto all'insieme universo.
	 *
	 * @param universe insieme universo.
	 * @return insieme complementare di this rispetto all'insieme universo.
	 */
	OurSet<T> complementar(OurSet<T> universe);


	/** Metodo che stabilisce se l'insieme chiamante è complementare all'insieme dato rispetto all'insieme universo.
	 *
	 * @param ms insieme dato come complementare.
	 * @param universe insieme universo.
	 * @return true se this è il complementare di ms, false altrimenti.
	 */
    boolean isComplementar(OurSet<T> ms, OurSet<T> universe);


}
