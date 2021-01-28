package ContainerLibrary.Sets;

import ContainerLibrary.Container.Container;
import ContainerLibrary.Exception.OurNotFoundException;
import ContainerLibrary.Exception.InvalidIndexGenerated;
import ContainerLibrary.Exception.FullContainerException;

/** Interfaccia che definisce le funzionalit&agrave che deve offrire una mappa, ADT che mappa elementi di un tipo con elementi di altro tipo.
 * 
 * @param <K> tipo dei valori da mappare.
 * @param <V> tipo dei valori mappati.
 */

public interface Map<K,V> {

	/** Metodo che consente di verificare se una determinata chiave ha un corrispondente nella mappa, cioè &egrave contenuta in essa.
	 * 
	 * @param key valore della chiave.
	 * @return true se la chiave &egrave contenuta, altrimenti false.
	 */
    boolean containsKey(K key);
	
	/** Metodo che consente di verificare se la mappa contiene un determinato valore value che &egrave stato mappato.
	 * 
	 * @param value valore di cui verificare la presenza.
	 */
    boolean containsValue(V value);

	/** Metodo che restituisce la coppia chiave-valore corrispondente a una determinata chiave.
	 * 
	 * @param key valore della chiave di cui cercare la coppia.
	 * @return coppia chiave-valore.
	 * @throws InvalidIndexGenerated se l'indice generato dalla funzione di hashing non &egrave un
	 * indice valido.
	 * @throws OurNotFoundException se nella mappa non &egrave presente un elemento associato alla
	 * chiave data.
	 */
    Couple<K,V> getElem(K key)throws InvalidIndexGenerated, OurNotFoundException;

	/** Metodo che restituisce il valore associato ad una chiave.
	 * 
	 * @param key chiave di cui cercare il valore associato.
	 * @return valore associato.
	 * @throws InvalidIndexGenerated se l'indice generato dalla funzione di hashing non &egrave un
	 * indice valido.
	 * @throws OurNotFoundException se nella mappa non &egrave presente un elemento associato alla
	 * chiave data.
	 */
    V getValue(K key) throws InvalidIndexGenerated, OurNotFoundException;

	/** Metodo che inserisce nella mappa una coppia chiave-valore.
	 * 
	 * @param key chiave.
	 * @param value valore.
	 * @throws InvalidIndexGenerated se l'indice generato dalla funzione di hashing non &egrave un
	 * indice valido.
	 * @throws FullContainerException se non &egrave possibile inserire elementi nella mappa.
	 */
    void put(K key, V value)throws InvalidIndexGenerated, FullContainerException;

	/** Metodo che consente di aggiungere dentro la mappa tutti i valori contenuti in una collezione che contenga coppie chiave-valore dentro la mappa.
	 * 
	 * @param cont container contenente gli elementi da inserire.
	 * @throws InvalidIndexGenerated se l'indice generato dalla funzione di hashing non &egrave un
	 * indice valido.
	 * @throws FullContainerException se non è possibile inserire elementi nella mappa.
	 */
    void putAll(Container<Couple<K, V>> cont) throws InvalidIndexGenerated, FullContainerException;

	/** Metodo che consente di verificare se la mappa sia piena o vuota.
	 */
    boolean isEmpty();

	/** Metodo che consente di rimuovere dalla mappe una coppia chiave-valore associata ad una chiave.
	 *
	 * @param key chiave.
	 * @throws InvalidIndexGenerated se l'indice generato dalla funzione di hashing non &egrave un
	 * indice valido.
	 * @throws OurNotFoundException se nella mappa non &egrave presente un elemento associato alla
	 * chiave data.
	 */
    V remove (K key) throws InvalidIndexGenerated, OurNotFoundException;
	
	/** Metodo che consente la rimozione della totalit&agrave degli elementi della mappa.
	 */
    void removeAll();
	
	/** Metodo che consente di rimuovere dalla mappa tutti gli elementi contenuti in una collezione di coppie chiave-valore.
	 *	Tutte le coppie devono essere presenti in Map.
	 *
	 * @param cont collezione di coppie chiave-valore.
	 * @throws InvalidIndexGenerated se l'indice generato dalla funzione di hashing non &egrave un
	 * indice valido.
	 */
    void removeAllFrom(Container<Couple<K, V>> cont) throws InvalidIndexGenerated;

	/** Metodo che consente di ottenere il numero di coppie memorizzate nella mappa.
	 *
	 * @return numero di elementi contenuti.
	 */
    int size();

	/** Metodo che consente di sostituire in una coppia chiave-valore il campo valore associato alla chiave.
	 *
	 * @param key chiave.
	 * @param newvalue nuovo valore.
	 * @return vecchio valore della coppia.
	 * @throws InvalidIndexGenerated se l'indice generato dalla funzione di hashing non &egrave un
	 * indice valido.
	 * @throws OurNotFoundException se nella mappa non è presente un elemento associato alla
	 * chiave data.
	 */
    V replace (K key, V newvalue) throws InvalidIndexGenerated, OurNotFoundException;
}
