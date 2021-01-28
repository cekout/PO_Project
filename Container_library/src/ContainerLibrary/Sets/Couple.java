package ContainerLibrary.Sets;

/** Questa classe rappresenta una coppia di elementi.
 *
 * @param <T> tipo del primo elemento della coppia.
 * @param <E> tipo del secondo elemento della coppia.
 */

public class Couple <T,E> {
	
	/** Primo elemento della coppia.
	 */
    private T first;
	
	/** sScondo elemento della coppia.
	 */
    private E second;

	/** Costruttore della coppia.
	 * 
	 * @param first primo elemento della coppia.
	 * @param second secondo elemento della coppia.
	 */
    public Couple(T first, E second) {
        this.first = first;
        this.second = second;
    }

	/** Metodo che restituisce il primo elemento della coppia.
	 *
	 * @return primo elemento della coppia.
	 */
    public T getFirst() {
        return first;
    }

	/** Metodo che restituisce il secondo elemento della coppia.
	 *
	 * @return secondo elemento della coppia.
	 */
    public E getSecond() {
        return second;
    }

	/** Metodo che consente di impostare il primo elemento della coppia.
	 * @param first primo elemento della coppia.
	 */
    public void setFirst(T first) {
        this.first = first;
    }

	/** Metodo che consente di impostare il secondo elemento della coppia.
	 * @param second secondo elemento della coppia.
	 */
    public void setSecond(E second) {
        this.second = second;
    }

    public String toString () {
    	StringBuilder b = new StringBuilder();
    	b.append("Primo elemento: " + first + ", ");
    	b.append("Secondo elemento " + second);
		return b.toString();
	}
}
