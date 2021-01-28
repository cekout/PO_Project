package ContainerLibrary.Exception;

/** Eccezione che segnala l'assenza dell'elemento cercato o a cui ci si vuole riferire.
 */
 
public class OurNotFoundException extends RuntimeException {

	/** Costruttore vuoto dell'eccezione.
	*/
    public OurNotFoundException() {
        super();
    }

	/** Costruttore dell'eccezione con messaggio.
	 *
	 * @param message messaggio da trasportare.
	 */
    public OurNotFoundException(String message) {
        super(message);
    }
}
