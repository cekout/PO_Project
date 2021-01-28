package ContainerLibrary.Sets;

/** Interfaccia che definisce le funzionalit&agrave che deve offrire una funzione hash.
 *
 * @param <K> tipo della chiave accettato dalla funzione hash.
 */
public interface FunHash<K> {
    //funzione hash per il calcolo dell'indice.
    // nulla mi assicura che la funzione funHash sia effettivamente valida, però nella mappa io mi
	// proteggo dagli indici non validi facendo dei controlli
	/** Metodo che calcola il valore hash a partire dalla chiave di tipo K.
	 * 
	 * @param key chiave su cui calcolare il valore hash.
	 * @return il valore hash.
	 */
    int funHash(K key);
}
