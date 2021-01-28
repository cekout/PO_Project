package ContainerLibrary.Exception;

/** Eccezione che segnala l'invalidit&agrave di un indice
 */

public class OurIndexOutOfBoundsException extends RuntimeException {
	
	/** Costruttore vuoto dell'eccezione
	*/
    public OurIndexOutOfBoundsException() {
        super();
    }

	/** Costruttore dell'eccezione con messaggio
	 *
	 * @param message messaggio da trasportare.
	 */
    public OurIndexOutOfBoundsException(String message) {
        super(message);
    }
}
