package ContainerLibrary.Exception;

/** Eccezione che segnala l'assenza della funzionalità richiesta ad un particolare metodo.
 */
 
public class OurUnsupportedFunctionException extends RuntimeException {

	/** Costruttore vuoto dell'eccezione.
	*/
    public OurUnsupportedFunctionException() {
        super();
    }
	
	/** Costruttore dell'eccezione con messaggio.
	 *
	 * @param message messaggio da trasportare.
	 */
    public OurUnsupportedFunctionException(String message) {
        super(message);
    }
}
