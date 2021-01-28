package ContainerLibrary.Exception;

/** Eccezione che segnala l'invalidit&agrave di un indice generato nel contesto dell'operazione eseguita dal metodo da cui viene lanciata.
*/

public class InvalidIndexGenerated extends RuntimeException {
	
	/** Costruttore vuoto dell'eccezione.
	*/
    public InvalidIndexGenerated() {
        super();
    }

	/** Costruttore dell'eccezione con messaggio.
	 *
	 * @param message messaggio da trasportare.
	 */
	 
    public InvalidIndexGenerated(String message) {
        super(message);
    }
}
