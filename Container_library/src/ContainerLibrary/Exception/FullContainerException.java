package ContainerLibrary.Exception;

/** Eccezione che segnala che il container &egrave pieno e non può ospitare altri elementi
 *
 */

public class FullContainerException extends RuntimeException {

    /** Costruttore vuoto dell'eccezione
     */
    public FullContainerException() {
        super();
    }

    /** Costruttore dell'eccezione con messaggio
     *
     * @param message messaggio da trasportare
     */
    public FullContainerException(String message) {
        super(message);
    }

}
