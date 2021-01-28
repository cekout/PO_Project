package ContainerLibrary;

import ContainerLibrary.Container.*;
import ContainerLibrary.Iterators.*;
import ContainerLibrary.List.*;
import ContainerLibrary.List.NodeList.*;
import ContainerLibrary.List.ArrayList.*;
import ContainerLibrary.Sets.*;
import ContainerLibrary.StackAndQueues.*;
import ContainerLibrary.StackAndQueues.NodeStructures.*;
import ContainerLibrary.StackAndQueues.ArrayStructures.*;
import ContainerLibrary.Exception.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class CompleteMain {

    public static void main (String args[]) {

        Scanner in = new Scanner(System.in);
        boolean continuation = true;
        while (continuation) {
            System.out.println("Questo è il programma di test della nostra libreria di container.");
            System.out.println("Premere 1 per testare le liste.");
            System.out.println("Premere 2 per testare le hashmap.");
            System.out.println("Premere 3 per testare gli stack");
            System.out.println("Premere 4 per testare le code.");
            System.out.println("Premere 5 per testare le dequeue.");
            System.out.println("Premere 6 per testare il set.");
            System.out.println("Premere 0 per uscire.");
            MyInput<String> request = new MyInput<>(in.next(), a -> {

                try {
                    int input = Integer.parseInt(a);
                    return input >= 0 && input <= 6;
                } catch (NumberFormatException e) {
                    return false;
                }
            });
            if (request.getValidation()) {
                try {
                    switch (request.transform(a -> Integer.parseInt(a))) {
                        case 1:
                            testList();
                            break;

                        case 2:
                            testHashMap();
                            break;

                        case 3:
                            testStack();
                            break;

                        case 4:
                            testQueue();
                            break;

                        case 5:
                            testDequeue();
                            break;

                        case 6:
                            testHashSet();
                            break;

                        default:
                            System.out.println("Terminazione programma.\n");
                            continuation = false;
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Errore nell'appertura del file per la popolazione della struttura dati.\n");
                }
            } else {
                System.out.println("Input non valido!\n");
            }
        }
    }

    private static void testList () throws FileNotFoundException {
        OurList<Persona> list1, list2;
        System.out.println("Premere 1 per testare le liste realizzate tramite array.");
        System.out.println("Premere 2 per testare le liste singolarmente concatenate con nodi.");
        System.out.println("Premere 3 per testare le liste doppiamente concatenate con nodi.");
        Scanner in = new Scanner(System.in);
        MyInput<String> request = new MyInput<>(in.next(), (String a) -> {

            try {
                int input = Integer.parseInt(a);
                return input >= 1 && input <= 3;
            } catch (NumberFormatException e) {
                return false;
            }

        });
        if(request.getValidation()) {

            switch (request.transform(a -> Integer.parseInt(a))) {
                case 1:
                    list1 = new OurArrayListSortable<>();
                    list2 = new OurArrayListSortable<>();
                    break;
                case 2:
                    list1 = new OurSortableNodeList<>();
                    list2 = new OurSortableNodeList<>();
                    break;
                default:
                    list1 = new OurSortableLinkedList<>();
                    list2 = new OurSortableLinkedList<>();
                    break;
            }

            System.out.println("Inizialmente la lista list1 è vuota, infatti la sua dimensione è " + list1.size() + ".\n");
            System.out.println("La lista è vuota? Il metodo isEmpty afferma " + list1.isEmpty() + ".\n");

            System.out.println("Provo a farmi restituire un elemento in una posizione non valida.\n");
            try {
                list1.getHead();
            } catch (OurNotFoundException c) {
                System.out.println("Eccezione 1 lanciata correttamente: " + c.getMessage() + "\n");
            }

            System.out.println("Provo a inserire un elemento in una posizione non valida, posizione 2.\n");
            try {
                list1.insertAt(2, new Persona());
            } catch (OurIndexOutOfBoundsException c) {
                System.out.println("Eccezione 2 lanciata correttamente: " + c.getMessage() + "\n");
            }

            System.out.println("Popolo list1 con gli elementi contenuti nel file di testo input.txt .\n");

            populateContainer(list1, "/home/lorenzo/Scrivania/unive/PO/PO_unive_project-official/progettino_po/src/input.txt");

            System.out.println("Stampo la lista:\n" + list1 + "\n");

            //inserisco un elemento in testa e uno in coda e controllo

            Persona elemInTesta = new Persona("Testa", "Testina (come Davide Cecchini)", 0, 0);
            System.out.println("Inserisco un elemento in testa che è " + elemInTesta + "\n");
            list1.insertHead(elemInTesta);

            Persona elemInCoda = new Persona("Coda", "Codino", 0, 0);
            System.out.println("Inserisco un elemento in coda che è: " + elemInCoda + "\n");
            list1.insertTail(elemInCoda);

            System.out.println("La lista list1 aggiornata è: \n" + list1);

            Persona elemInMezzo = new Persona("In", "Mezzo", 100, 10);
            System.out.printf("Inserisco un elemento in mezzo (posizione 3, dimensione lista %d).\n\n", list1.size());
            list1.insertAt(3, elemInMezzo);

            System.out.println("Stampiamo la lista con il nuovo elemento: \n" + list1 + "\n");

            System.out.println("Provo la funzione contains con l'elemento messo precedentemente in mezzo.\n");

            if (list1.contains(elemInMezzo)) {
                System.out.println("La lista contiene l'elemento: " + elemInMezzo + ".\n");
            } else {
                System.out.println("La lista non contiene l'elemento: 1" + elemInMezzo + "\n");
            }

            Persona elemNonContenuto = new Persona("AAAAA", "BBBBBB", 0, 0);

            if (list1.contains(elemNonContenuto))
                System.out.println("La lista contiene " + elemNonContenuto + ".\n");
            else
                System.out.println("La lista non contiene " + elemNonContenuto + "\n");

            System.out.println("Proviamo la funzione getAt.\n");

            System.out.println("Elemento in posizione 2: " + list1.getAt(2) + "\n");

            System.out.println("Proviamo a richiedere un elemento fuori range, in posizione 30.\n");

            try {
                list1.getAt(30);
            } catch (OurIndexOutOfBoundsException c) {
                System.out.println("Eccezione 3 lanciata correttamente: " + c.getMessage() + "\nInfatti non c'è alcun elemento in posizione 30, dato che la size è " + list1.size() + ".\n");
            }

            System.out.println("Proviamo indexOf e lastIndexOf!\n");

            System.out.println("Non vi è alcun elemento " + elemNonContenuto + "\n");

            try {
                list1.indexOf(elemNonContenuto);
            } catch (OurNotFoundException c) {
                System.out.println("Eccezione 4 lanciata correttamente: " + c.getMessage() + "\nInfatti questo elemento non è contenuto.\n");
            }

            System.out.printf("Inserisco un elemento già presente " + elemInMezzo + " in posizione %d.\n", list1.size() / 2);
            list1.insertAt(list1.size() / 2, elemInMezzo);

            System.out.println("Il contenuto attuale di list1 è: \n" + list1 + "\n");

            System.out.println("Ultima posizione dell'elemento " + elemInMezzo + " : " + list1.lastIndexOf(elemInMezzo) + "\n");

            System.out.println("Prima posizione dell'elemento " + elemInMezzo + " : " + list1.indexOf(elemInMezzo) + "\n");

            System.out.println("Controlliamo getLast, getHead, removeLast e removeHead.\n");

            System.out.println("Contenuto attuale della lista: \n" + list1);

            System.out.println("Sto per rimuovere l'elemento in coda: " + list1.getLast() + ".\n");
            System.out.println("Ho rimosso l'elemento in coda: " + list1.removeLast() + ".\n");
            System.out.println("Adesso il contenuto è: \n" + list1 + "\n");


            System.out.println("Sto per rimuovere l'elemento in testa: " + list1.getHead() + ".\n");
            System.out.println("Ho rimosso l'elemento in testa" + list1.removeHead() + ".\n");
            System.out.println("Adesso il contenuto è \n" + list1 + "\n");

            System.out.println("Controllo getLast, removeLast, getHead, removeHead con una lista list2 che è vuota.\n");

            Persona nuovoElem = new Persona("Nuovo", "Elemento", 150, 10);
            System.out.println("Inserisco un elemento in una nuova lista list2, tale elemento è " + nuovoElem + ".\n");
            list2.add(nuovoElem);
            System.out.println("Il contenuto di questa nuova lista è: \n" + list2 + "\n");

            System.out.println("Sto per rimuovere l'elemento in testa a list2: " + list2.getHead() + ".\n");
            System.out.println("Ho rimosso l'elemento in testa" + list2.removeHead() + ".\n");
            System.out.println("La nuova lista ora contiene: " + list2 + "\n");

            System.out.println("Proviamo a vedere se c'è un elemento in coda e a rimuoverlo.\n");
            try {
                System.out.println("Sto per rimuovere l'elemento in coda: " + list2.getLast() + ".\n");
                System.out.println("Ho rimosso l'elemento in coda" + list2.removeLast() + ".\n");
                System.out.println(list2);
            } catch (OurNotFoundException c) {
                System.out.println("Eccezione 5 catturata con successo " + c.getMessage() + "\n");
            }

            System.out.println("Proviamo a vedere se c'è un elemento in testa e a rimuoverlo.\n");
            try {
                System.out.println("Sto per rimuovere l'elemento in testa: " + list2.getHead() + ".\n");
                System.out.println("Ho rimosso l'elemento in testa" + list2.removeHead() + ".\n");
                System.out.println(list2);
            } catch (OurNotFoundException c) {
                System.out.println("Eccezione 6 catturata con successo " + c.getMessage() + "\n");
            }

            System.out.println("Ora popolo questa nuova lista con gli stessi elementi di list1.\n");
            System.out.println("La size di list2 è inizialmente" + list2.size() + ".\n");
            list2.addAll(list1);
            System.out.println("La size di list2 dopo il popolamento è" + list2.size() + ".\n");

            System.out.println("Ora verifico che le due liste siano uguali.\n");
            System.out.println("Lista 1: \n" + list1 + "\n");
            System.out.println("Lista 2: \n" + list2 + "\n");
            System.out.println("Sono uguali? " + list1.equals(list2));

            System.out.println("Adesso rimuovo qualche elemento.\n");
            System.out.println("Ho rimosso l'elemento " + list2.removeAt(1) + " da list2.\n");
            System.out.println("Ho rimosso l'elemento " + list2.removeAt(list2.size() - 1) + " da list2.\n");
            System.out.println("Ho rimosso l'elemento " + list2.removeAt(list2.size() / 2) + " da list2.\n");

            System.out.println("Ora riverifico che le due liste siano uguali.\n");
            System.out.println("Lista 1: \n" + list1 + "\n");
            System.out.println("Lista 2: \n" + list2 + "\n");
            System.out.println("Sono uguali? " + list1.equals(list2) + "\n");

            System.out.println("Verifichiamo ora che la seconda lista sia contenuta nella prima.\n");
            System.out.println("Lista2 contenuta nella prima? " + list1.containsAll(list2) + ".\n");

            System.out.println("Ora rimuoviamo dalla prima tutti gli elementi contenuti nella seconda.\n");

            list1.removeAllFrom(list2);
            System.out.println("Ora la prima lista è: \n" + list1 + "\n");
            System.out.println("Ora la seconda lista è: \n" + list2 + "\n");

            System.out.println("Ora proviamo a scorrere la list1 tramite l'iteratore.\n");

            OurListIterator<Persona> it = list1.listIterator();

            int i = -1;
            while (it.hasNext()) {
                i++;
                System.out.println("Elemento in pos " + i + ": " + it.next() + "\n");
            }

            System.out.println("Ora al contrario.\n");

            while (it.hasPrevious()) {
                System.out.println("Elemento in pos " + i + ": " + it.previous() + "\n");
                i--;
            }

            System.out.println("Proviamo ora a sfruttare il nostro super iteratore!\n");

            System.out.println("Inseriamo dentro la lista altri 5 elementi.\n");

            list1.add(new Persona("Ciao", "Come", 40, 200));

            list1.add(new Persona("Promosso", "Bocciato", 40, 270));

            list1.add(new Persona("Ambara", "ba", 4, 120));

            list1.add(new Persona("Cicci", "Coco", 4, 125));

            list1.add(new Persona("Tre", "Civette", 4, 130));

            list1.add(new Persona("Sul", "Como", 4, 135));

            System.out.println("Ora la lista contiene: \n" + list1);

            OurJumpListIteratorHeavy<Persona> it1 = list1.JumpListIteratorHeavy();

            System.out.println("Effettuiamo qualche salto!\n");

            System.out.println("Salto all'elemento in posizione 3.\n");

            it1.jumpList(3);

            System.out.println("L'elemento in posizione 3 è " + it1.next() + ".\n");

            System.out.println("Saltiamo 3 posizioni in avanti.\n");

            it1.jump(3);

            System.out.println("L'elemento in posizione 7 (notare che dopo la next precedente il puntatore sarà in pos. 4) è " + it1.next() + ".\n");

            System.out.println("Proviamo ora ad eliminare il sesto elemento tramite l'iteratore e quello in posizione 2.\n");

            it1.remove();
            it1.removeIndex(2);

            System.out.println("Ora la lista contiene: \n" + list1 + "\n");

            System.out.println("Stampiamo ora la lista ordinata.\n");

            ((Sortable<Persona>) list1).sort();

            System.out.println("La lista contiene: \n" + list1 + "\n");
        } else
            System.out.println("Input non valido!\n");
    }

    private static void testHashMap() {

        HashMap<Integer, String> prova = new HashMap<>();

        System.out.println("Istanziamo una nuova hashmap.\n");

        System.out.println("La nostra hashmap è vuota? La funzione isEmpty afferma : " + prova.isEmpty() + ".\n");
        System.out.println("La dimensione dell'hashmap è: " + prova.size() + ".\n");


        System.out.println("Stampo la mappa vuota: \n" + prova);

        System.out.println("Proviamo le funzioni put, getValue, containsKey, containsValue.\n");

        System.out.println("Inserisco la stringa \"ciao\" e chiave 1 .\n");
        prova.put(1, "ciao");
        System.out.println("Inserisco la stringa \"come\" e chiave 2.\n");
        prova.put(2, "come");
        System.out.println("Inserisco la stringa \"stai\" e chiave 3 .\n");
        prova.put(3, "stai");
        System.out.println("Inserisco la stringa \"ciao2\" e chiave 11.\n");
        prova.put(11, "ciao2");

        System.out.println("Contiene la chiave 2?  " + prova.containsKey(2) + "\n");
        System.out.println("Contiene la chiave 14? " + prova.containsKey(14) + "\n");

        System.out.println("Contiene il valore \"ciao\"? " + prova.containsValue("ciao") + "\n");
        System.out.println("contiene il valore \"ciao3\"? " + prova.containsValue("ciao3") + "\n");
        System.out.println("contiene il valore \"ciao2\"? " + prova.containsValue("ciao2") + "\n");

        System.out.println("Valore con chiave 3: " + prova.getValue(3) + "\n");

        System.out.println("Valore con chiave 1: " + prova.getValue(1) + "\n");
        System.out.println("Valore con chiave 11: " + prova.getValue(11) + "\n");

        System.out.println("L'hashmap ha dimensione " + prova.size() + "\n");
        System.out.println("Stampo la map con un po' di elementi: \n" + prova + "\n");



        OurNodeList<Couple<Integer, String>> l = new OurNodeList<>();

        System.out.println("Istanziamo una nuova lista.\n");
        System.out.println("Inserisco la stringa \"ciao4\" e chiave 21 .\n");
        l.add(new Couple<>(21, "ciao4"));
        System.out.println("Inserisco la stringa \"elemento7\" e chiave 7 .\n");
        l.add(new Couple<>(7, "elemento7"));
        System.out.println("Inserisco la stringa \"stai2\" e chiave 43 .\n");
        l.add(new Couple<>(43, "stai2"));
        System.out.println("Inserisco la stringa \"ciao4\" e chiave 58 .\n");
        l.add(new Couple<>(58, "ciao4"));

        System.out.println("Inserisco all'interno della prima hashmap tutti gli elementi contenuti nella lista.\n");
        prova.putAll(l);

        System.out.println("La prima hashmap ha ora dimensione " + prova.size() + "\n");
        System.out.println("Stampo la map con un po' di elementi: \n" + prova + "\n");

        System.out.println("Inserisco la stringa \"ciao\" e chiave 4 in entrambe le collezioni.\n");
        prova.put(4, "ciao");
        l.add(new Couple<>(4, "ciao"));

        System.out.println("Rimuovo dalla prima hashmap tutti gli elementi contenuti nella seconda.\n");

        System.out.println("Stampiamo entrambe le collezioni prima di rimuovere dalla prima gli elementi della seconda.\n");
        System.out.println("Stampo la mappa: \n" + prova + "\n");
        System.out.println("Stampo la lista: \n" + l + "\n");

        prova.removeAllFrom(l);

        System.out.println("Stampo la  mappa dopo aver rimosso: \n" + prova + "\n");

        System.out.println("La mappa è vuota? " + prova.isEmpty() + ".\n");

        System.out.println("Rimuoviamo tutti gli elementi dalla hashmap.\n");
        prova.removeAll();
        System.out.println("Mappa svuotata. Infatti il suo contenuto è: \n" + prova + "\n");

        System.out.println("La mappa è vuota? La funzione isEmpty afferma: " + prova.isEmpty() + ", size = " + prova.size() + "\n");

        System.out.println("Ripopoliamo la mappa con gli elementi della lista.\n");
        prova.putAll(l);

        System.out.println("Adesso la dimensione della mappa è: " + prova.size() + ".\n");
        System.out.println("Stampo la mappa: \n" + prova + "\n");


        System.out.println("Elemento con chiave 7: " + prova.getElem(7) + "\n");
        System.out.println("Ora proviamo a cercare un elemento non incluso.\n");
        try{
            System.out.println("elemento con chiave 9: " + prova.getElem(9));
        } catch (OurNotFoundException c){
            System.out.println("Eccezione 1 lanciata con successo: " + c.getMessage() + "\n");
        }

        System.out.println("Metto \"stringa\" nella chiave 7 che aveva: " + prova.replace(7,"stringa") + ".\n");

        System.out.println("La mappa dopo il rimpiazzo è: \n" + prova + "\n");

        System.out.println("Adesso tolgo l'elemento con chiave 7, il cui valore è " + prova.remove(7) + ".\n");

        System.out.println("La mappa dopo l'eliminazione è: \n" + prova + "\n");
        System.out.println("La dimensione dell prima mappa è " + prova.size() + ".\n");

        System.out.println("Adesso creiamo una nuova hashmap con una funzione di hashing (key+1)%tabSize e tabSize = 15, cioè diversa da quella di default (13).\n");
        HashMap<Integer, String> prova2 = new HashMap<>(15 ,(Integer key) -> { return (key+1)%15; });

        System.out.println("Inserisco all'interno della nuova tabella tutti gli elementi contenuti nella lista.\n");
        prova2.putAll(l);

        System.out.println("La dimensione della nuova mappa è: " + prova.size() + ".\n");
        System.out.println("Stampo la nuova mappa: \n " + prova2 + "\n");

        System.out.println("Rimuoviamo l'elemento con chiave 7.\n");
        prova2.remove(7);

        System.out.println("Adesso la dimensione della nuova mappa è: " + prova.size() + ".\n");
        System.out.println("Stampo la nuova mappa: \n " + prova2 + "\n");

        System.out.println("Stampo la mappa iniziale: \ns" + prova + "\n");

        System.out.println("Le due mappe contengono gli stessi elementi? " + prova.hasSameCouples(prova2) + ".\n");
        System.out.println("Quindi contengono gli stessi elementi anche se hanno funzioni hash e dimensioni diverse.\n");

        System.out.println("Aggiungo un elemento alla seconda mappa con chiave 227 e valore \"cia\".\n");
        prova2.put(227, "cia");

        System.out.println("Stampo la prima mappa :" + prova + "\n");
        System.out.println("Stampo la seconda mappa :" + prova2 + "\n");

        System.out.println("Le due mappe contengono gli stessi elementi? " + prova.hasSameCouples(prova2) + ".\n");

        /*System.out.println("Ora istanziamo un ulteriore hashmap che verrà confrontata con quella ")
        HashMap<Integer, String> prova3 = new HashMap<>();
        prova3.put(421, "ciao4");

        System.out.println("prova3: " + prova.size());
        System.out.println(prova3.toString());

        System.out.println("prova e prova3 sono equals: " + prova.equals(prova3));
        System.out.println("prova3 e prova sono equals: " + prova3.equals(prova));

        prova3.removeAll();
        prova3.putAll(l);

        System.out.println("prova3: " + prova.size());
        System.out.println(prova3.toString());

        System.out.println("prova e prova3 sono equals: " + prova.equals(prova3));
        System.out.println("prova3 e prova sono equals: " + prova3.equals(prova));


        System.out.println("tolgo da prova3 l'elemento con chiave 7: " + prova3.remove(7));


        System.out.println("prova3: " + prova.size());
        System.out.println(prova3.toString());

        System.out.println("prova e prova3 sono equals: " + prova.equals(prova3));
        System.out.println("prova3 e prova sono equals: " + prova3.equals(prova));

        System.out.println("prova è equals con se stessa: " + prova.equals(prova));*/


        System.out.println("Facciamo ora le ultime prove creando 3 nuove mappe mappa1, mappa2 e mappa3, con mappa3 che anzichè avere dimensione di default(10) avrà dimensione 15.\n");

        HashMap<Integer, String> prova4 = new HashMap<>();

        HashMap<Integer, String> prova5 = new HashMap<>();

        HashMap<Integer, String> prova6 = new HashMap<Integer, String>(15 ,(Integer key) -> { return (key+1)%15; });

        System.out.println("Svuotiamo anche la mappa iniziale.\n");
        prova.removeAll();

        System.out.println("Confrontiamo due mappe appena create con la medesima dimensione della tabella.\n");
        System.out.println("Due mappe vuote appena create contengono i medesimi elementi. Confrontiamo mappa1 e mappa2. Contengono i medesimi elementi?: " + prova4.equals(prova5) + ".\n");

        System.out.println("Due mappe vuote con dimensioni diverse appena create contengono i medesimi elementi. Confrontiamo mappa1 e mappa3. Contengono i medesimi elementi? " + prova4.equals(prova6) + ".\n");

        System.out.println("Una mappa appena creata e una svuotata contengono i medesimi elementi. Confrontiamo la prima mappa svuotata con mappa3, contengono i medesimi elementi? " + prova6.equals(prova) + ".\n");
    }

    private static void testStack() throws FileNotFoundException {

        Stack<Persona> stack;
        Stack<Persona> stack2;
        Scanner in = new Scanner(System.in);
        System.out.println("Premere 1 per testare lo stack realizzato tramite composizione.");
        System.out.println("Premere 2 per testare lo stack realizzato tramite ereditarietà.");
        System.out.println("Premere 3 per testare lo stack realizzato tramite nodi.");

        MyInput<String> request = new MyInput<>(in.next(), (String a) -> {

            try {
                int input = Integer.parseInt(a);
                return input >= 1 && input <= 3;
            } catch (NumberFormatException e) {
                return false;
            }

        });

        if(request.getValidation()) {

            switch (request.transform(a -> Integer.parseInt(a))) {
                case 1:
                    stack = new ArrayStack1<>();
                    stack2 = new ArrayStack1<>();
                    break;
                case 2:
                    stack = new ArrayStack2<>();
                    stack2 = new ArrayStack2<>();
                    break;
                default:
                    stack = new NodeStack<>();
                    stack2 = new NodeStack<>();
                    break;
            }

            System.out.println("Proviamo ora ad eseguire una pop dal nostro stack vuoto.\n");
            try {
                stack.pop();
            } catch (OurNotFoundException c) {
                System.out.println("Eccezione 1 lanciata correttamente " + c.getMessage() + "\n");
            }

            System.out.println("Lo stack contiene: \n" + stack + "\n");

            System.out.println("La dimensione del nostro stack vuoto è : " + stack.size() + ".\n");

            System.out.println("Il nostro stack è vuoto? La funzione constains afferma: " + stack.isEmpty() + ".\n");

            System.out.println("Popoliamo il nostro container.\n");

            populateContainer(stack, "/home/lorenzo/Scrivania/unive/PO/PO_unive_project-official/progettino_po/src/input.txt");

            System.out.println("Lo stack contiene: \n" + stack + "\n");

            System.out.println("La dimensione dello stack è: " + stack.size() + ".\n");

            System.out.println("Estraggo il primo elemento: " + stack.pop() + ".\n");
            System.out.println("Estraggo un altro elemento: " + stack.pop() + "\n");

            System.out.println("Lo stack contiene: \n" + stack + "\n");

            System.out.println("Il nostro stack è vuoto? La funzione constains afferma: " + stack.isEmpty() + ".\n");

            System.out.println("La dimensione dello stack è: " + stack.size() + ".\n");

            System.out.println("Svuotiamo completamente il nostro stack tramite la funzione removeAll.\n");

            stack.removeAll();

            System.out.println("Lo stack contiene: \n" + stack + "\n");

            System.out.println("La dimensione dello stack è: " + stack.size() + ".\n");

            System.out.println("Il nostro stack è vuoto? La funzione constains afferma: " + stack.isEmpty() + "\n");

            System.out.println("Ripopolo nuovamente lo stack.\n");

            populateContainer(stack, "/home/lorenzo/Scrivania/unive/PO/PO_unive_project-official/progettino_po/src/input.txt");

            System.out.println("Utilizzando due pop consecutive, inseriamo due elementi.\n");

            Persona elemInTesta = new Persona("Testa", "Testina (come Davide Cecchini)", 0, 0);

            stack.push(elemInTesta);

            Persona elemInMezzo = new Persona("In", "Mezzo", 100, 10);

            stack.push(elemInMezzo);

            System.out.println("Lo stack contiene: \n" + stack + "\n");

            System.out.println("Popoliamo il secondo stack con gli stessi elementi del primo a meno dei due ultimi inseriti.\n");

            populateContainer(stack2, "/home/lorenzo/Scrivania/unive/PO/PO_unive_project-official/progettino_po/src/input.txt");

            System.out.println("Il secondo stack contiene: \n" + stack2 + "\n");

            System.out.println("I due stack sono uguali? La funzione equals afferma: " + stack.equals(stack2) + "\n");

            System.out.println("Proviamo ora inserendo i due elementi mancanti.\n");

            stack2.push(elemInTesta);

            stack2.push(elemInMezzo);

            System.out.println("Il secondo stack contiene: \n" + stack + "\n");

            System.out.println("I due stack sono uguali? La funzione equals afferma: " + stack.equals(stack2) + "\n");

            System.out.println("Ora visitiamo gli elementi del primo stack tramite iteratore.\n");

            OurIterator it = stack.iterator();

            int i = -1;
            while (it.hasNext()) {
                i++;
                System.out.println("Elemento in pos " + i + ": " + it.next() + "\n");
            }

            System.out.println("Aggiungiamo tutti gli elementi contenuti nel secondo stack al primo stack tramite l'addAll.\n");

            stack.addAll(stack2);

            System.out.println("Il primo stack contiene: \n" + stack + "\n");
        } else
            System.out.println("Input non valido!");
    }

    private static void testQueue() throws FileNotFoundException {
        Queue<Persona> queue, queue2;
        Scanner in = new Scanner(System.in);
        System.out.println("Premere 1 per testare la coda realizzata tramite composizione.");
        System.out.println("Premere 2 per testare la coda realizzata tramite ereditarietà.");
        System.out.println("Premere 3 per testare la coda realizzata tramite nodi.");

        MyInput<String> request = new MyInput<>(in.next(), (String a) -> {

            try {
                int input = Integer.parseInt(a);
                return input >= 1 && input <= 3;
            } catch (NumberFormatException e) {
                return false;
            }

        });
        if(request.getValidation()) {

            switch (request.transform(a -> Integer.parseInt(a))) {
                case 1:
                    queue = new ArrayQueue1<>();
                    queue2 = new ArrayQueue1<>();
                    break;
                case 2:
                    queue = new ArrayQueue2<>();
                    queue2 = new ArrayQueue2<>();
                    break;
                default:
                    queue = new NodeQueue<>();
                    queue2 = new NodeQueue<>();
                    break;
            }

            System.out.println("Proviamo ora ad eseguire una dequeue dalla nostra coda vuota.\n");
            try {
                queue.dequeue();
            } catch (OurNotFoundException c) {
                System.out.println("Eccezione 1 lanciata correttamente " + c.getMessage() + "\n");
            }

            System.out.println("La coda contiene: \n" + queue + "\n");

            System.out.println("La dimensione della nostra coda vuota è : " + queue.size() + ".\n");

            System.out.println("La nostra coda è vuota? La funzione constains afferma: " + queue.isEmpty() + ".\n");

            System.out.println("Popoliamo il nostro container.\n");

            populateContainer(queue, "/home/lorenzo/Scrivania/unive/PO/PO_unive_project-official/progettino_po/src/input.txt");

            System.out.println("La coda contiene: \n" + queue + "\n");

            System.out.println("La dimensione della coda è: " + queue.size() + ".\n");

            System.out.println("Estraggo il primo elemento: " + queue.dequeue() + ".\n");
            System.out.println("Estraggo un altro elemento: " + queue.dequeue() + "\n");

            System.out.println("La coda contiene: \n" + queue + "\n");

            System.out.println("La nostra coda è vuota? La funzione constains afferma: " + queue.isEmpty() + ".\n");

            System.out.println("La dimensione della coda è: " + queue.size() + ".\n");

            System.out.println("Svuotiamo completamente la nostra coda tramite la funzione removeAll.\n");

            queue.removeAll();

            System.out.println("La coda contiene: \n" + queue + "\n");

            System.out.println("La dimensione della coda è: " + queue.size() + ".\n");

            System.out.println("La nostra coda è vuota? La funzione constains afferma: " + queue.isEmpty() + "\n");

            System.out.println("Ripopolo nuovamente la coda.\n");

            populateContainer(queue, "/home/lorenzo/Scrivania/unive/PO/PO_unive_project-official/progettino_po/src/input.txt");

            System.out.println("Utilizzando due enqueue consecutive, inseriamo due elementi.\n");

            Persona elemInTesta = new Persona("Testa", "Testina (come Davide Cecchini)", 0, 0);

            queue.enqueue(elemInTesta);

            Persona elemInMezzo = new Persona("In", "Mezzo", 100, 10);

            queue.enqueue(elemInMezzo);

            System.out.println("La coda contiene: \n" + queue + "\n");

            System.out.println("Popoliamo la seconda coda con gli stessi elementi del primo a meno dei due ultimi inseriti.\n");

            populateContainer(queue2, "/home/lorenzo/Scrivania/unive/PO/PO_unive_project-official/progettino_po/src/input.txt");

            System.out.println("La seconda coda contiene: \n" + queue2 + "\n");

            System.out.println("Le due code sono uguali? La funzione equals afferma: " + queue.equals(queue2) + "\n");

            System.out.println("Proviamo ora inserendo i due elementi mancanti.\n");

            queue2.enqueue(elemInTesta);

            queue2.enqueue(elemInMezzo);

            System.out.println("La seconda coda contiene: \n" + queue + "\n");

            System.out.println("Le due code sono uguali? La funzione equals afferma: " + queue.equals(queue2) + "\n");

            System.out.println("Ora visitiamo gli elementi della prima coda tramite iteratore.\n");

            OurIterator it = queue.iterator();

            int i = -1;
            while (it.hasNext()) {
                i++;
                System.out.println("Elemento in pos " + i + ": " + it.next() + "\n");
            }

            System.out.println("Aggiungiamo tutti gli elementi contenuti nella seconda coda alla prima coda tramite l'addAll.\n");

            queue.addAll(queue2);

            System.out.println("La prima coda contiene: \n" + queue + "\n");
        } else
            System.out.println("Input non valido!");
    }

    private static void testDequeue() throws FileNotFoundException {
        Dequeue<Persona> dequeue, dequeue2;
        Scanner in = new Scanner(System.in);
        System.out.println("Premere 1 per testare la dequeue realizzata tramite composizione.");
        System.out.println("Premere 2 per testare la dequeue realizzata tramite ereditarietà.");
        System.out.println("Premere 3 per testare la dequeue realizzata tramite nodi.");
        MyInput<String> request;
        request = new MyInput<>(in.next(), (String a) -> {

            try {
                int input = Integer.parseInt(a);
                return input >= 1 && input <= 3;
            } catch (NumberFormatException e) {
                return false;
            }

        });
        if(request.getValidation()) {
            switch (request.transform(a -> Integer.parseInt(a))) {
                case 1:
                    dequeue = new ArrayDequeue1<>();
                    dequeue2 = new ArrayDequeue1<>();
                    break;
                case 2:
                    dequeue = new ArrayDequeue2<>();
                    dequeue2 = new ArrayDequeue2<>();
                    break;
                default:
                    dequeue = new NodeDequeue<>();
                    dequeue2 = new NodeDequeue<>();
                    break;
            }

            System.out.println("Proviamo ora ad eseguire una removeFirst dalla nostra coda vuota.\n");
            try {
                dequeue.removeFirst();
            } catch (OurNotFoundException c) {
                System.out.println("Eccezione 1 lanciata correttamente: " + c.getMessage() + "\n");
            }

            System.out.println("Proviamo ora ad eseguire una removeLast dalla nostra coda vuota.\n");
            try {
                dequeue.removeLast();
            } catch (OurNotFoundException c) {
                System.out.println("Eccezione 2 lanciata correttamente: " + c.getMessage() + "\n");
            }

            System.out.println("La dequeue contiene: \n" + dequeue + "\n");

            System.out.println("La dimensione della nostra dequeue vuota è : " + dequeue.size() + ".\n");

            System.out.println("La nostra dequeue è vuota? La funzione constains afferma: " + dequeue.isEmpty() + ".\n");

            System.out.println("Popoliamo il nostro container.\n");

            populateContainer(dequeue, "/home/lorenzo/Scrivania/unive/PO/PO_unive_project-official/progettino_po/src/input.txt");

            System.out.println("La dequeue contiene: \n" + dequeue + "\n");

            System.out.println("La dimensione della dequeue è: " + dequeue.size() + ".\n");

            System.out.println("Estraggo il primo elemento: " + dequeue.removeFirst() + ".\n");
            System.out.println("Estraggo l'elemento in fondo: " + dequeue.removeLast() + "\n");

            System.out.println("Ora il primo elemento della dequeue è: " + dequeue.first() + "\n");
            System.out.println("Ora l'ultimo elemento della dequeue è: " + dequeue.last() + "\n");

            System.out.println("La dequeue contiene: \n" + dequeue + "\n");

            System.out.println("La nostra dequeue è vuota? La funzione constains afferma: " + dequeue.isEmpty() + ".\n");

            System.out.println("La dimensione della dequeue è: " + dequeue.size() + ".\n");

            System.out.println("Svuotiamo completamente la nostra dequeue tramite la funzione removeAll.\n");

            dequeue.removeAll();

            System.out.println("La dequeue contiene: \n" + dequeue + "\n");

            System.out.println("La dimensione della dequeue è: " + dequeue.size() + ".\n");

            System.out.println("La nostra dequeue è vuota? La funzione constains afferma: " + dequeue.isEmpty() + "\n");

            System.out.println("Ripopolo nuovamente la dequeue.\n");

            populateContainer(dequeue, "/home/lorenzo/Scrivania/unive/PO/PO_unive_project-official/progettino_po/src/input.txt");

            System.out.println("Utilizzando addFirst e addLast inseriamo due elementi.\n");

            Persona elemInTesta = new Persona("Testa", "Testina (come Davide Cecchini)", 0, 0);

            dequeue.addFirst(elemInTesta);

            Persona elemInCoda = new Persona("Coda", "Codino", 0, 0);

            dequeue.addLast(elemInCoda);

            System.out.println("La dequeue contiene: \n" + dequeue + "\n");

            System.out.println("Popoliamo la seconda dequeue con gli stessi elementi del primo a meno dei due ultimi inseriti.\n");

            populateContainer(dequeue2, "/home/lorenzo/Scrivania/unive/PO/PO_unive_project-official/progettino_po/src/input.txt");

            System.out.println("La seconda dequeue contiene: \n" + dequeue2 + "\n");

            System.out.println("Le due dequeue sono uguali? La funzione equals afferma: " + dequeue.equals(dequeue2) + "\n");

            System.out.println("Proviamo ora inserendo i due elementi mancanti.\n");

            dequeue2.addFirst(elemInTesta);

            dequeue2.addLast(elemInCoda);

            System.out.println("La seconda dequeue contiene: \n" + dequeue2 + "\n");

            System.out.println("Le due dequeue sono uguali? La funzione equals afferma: " + dequeue.equals(dequeue2) + "\n");

            System.out.println("Ora visitiamo gli elementi della prima dequeue tramite iteratore.\n");

            OurIterator it = dequeue.iterator();

            int i = -1;
            while (it.hasNext()) {
                i++;
                System.out.println("Elemento in pos " + i + ": " + it.next() + "\n");
            }

            System.out.println("Aggiungiamo tutti gli elementi contenuti nella seconda dequeue alla prima coda tramite l'addAll.\n");

            dequeue.addAll(dequeue2);

            System.out.println("La prima dequeue contiene: \n" + dequeue + "\n");
        } else
            System.out.println("Input non valido!");
    }

    private static void testHashSet() throws FileNotFoundException {

        OurHashSet<String> prova = new OurHashSet<>();

        System.out.println("Istanziamo un nuovo insieme (insieme1) e verifichiamo che sia vuoto.\n");
        System.out.println("L'insieme1 è vuoto? " + prova.isEmpty() + ".\n");
        System.out.println("La dimensione è: " + prova.size() + "\n");


        System.out.println("Stampo l'insieme1 vuoto: " + prova + "\n");;

        System.out.println("Aggiungiamo la stringa \"ciao\".\n");
        prova.add("ciao");
        System.out.println("Aggiungiamo la stringa \"come\".\n");
        prova.add("come");
        System.out.println("Aggiungiamo la stringa \"stai\".\n");
        prova.add("stai");
        System.out.println("Aggiungiamo la stringa \"tutto\".\n");
        prova.add("tutto");
        System.out.println("Aggiungiamo la stringa \"bene\".\n");
        prova.add("bene");

        System.out.println("L'insieme1 è vuoto? " + prova.isEmpty() + "\n");
        System.out.println("La dimensione è: " + prova.size() + "\n");


        System.out.println("Stampo l'insieme1 dopo aver aggiunto un po' di elementi: \n" + prova + "\n");

        System.out.println("Ora istanzio una lista e la riempio con due elementi.\n");
        OurList<String> l = new OurNodeList<>("ciao2");
        System.out.println("Aggiungiamo alla lista la stringa \"come2\".\n");
        l.add("come2");
        System.out.println("Aggiungiamo alla lista la stringa \"stai2\".\n");
        l.add("stai2");

        System.out.println("Contenuto lista da aggiungere all'insieme: \n" + l + "\n");

        System.out.println("Aggiungiamo tutte le stringhe della lista alla mappa.\n");
        prova.addAll(l);

        System.out.println("Insieme1 dopo aver aggiunto gli elementi dalla lista: \n " + prova + "\n");

        System.out.println("Ora proviamo a rimuovere dall'insieme gli elementi contenuti nella lista.\n");
        System.out.println("Rimuovo due elementi dalla lista e ne aggiungo uno.\n");
        System.out.println("Il primo elemento rimosso è " + l.removeHead() + ".\n");
        System.out.println("Il secondo elemento rimosso è " + l.removeHead() + ".\n");

        System.out.println("Aggiungo la stringa \"wow\".\n");
        l.add("wow");

        System.out.println("Contenuto lista da togliere: \n" + l + "\n");

        prova.removeAllFrom(l);
        System.out.println("Insieme1 dopo aver tolto gli elementi dalla lista " + prova + "\n");

        System.out.println("Ora riempiamo una lista e verifichiamo che il nostro Insieme contenga gli elementi della lista.\n");
        OurList<String> l2 = new OurNodeList<String>();
        System.out.println("Contenuto lista da controllare " + l2 + "\n");
        System.out.println("L'insieme1 contiene gli elementi della lista? " + prova.containsAll(l2) + ".\n");

        System.out.println("Aggiungiamo alla lista la stringa \"cia\"\n");
        l2.add("cia");
        System.out.println("Contenuto lista da controllare " + l2 + "\n");
        System.out.println("L'insieme1 contiene gli elementi della lista: " + prova.containsAll(l2) + ".\n");


        System.out.println("Svuotiamo la lista e verifichiamo se l'insieme contiene tutti gli elementi della lista.\n");
        l2.removeAll();

        System.out.println("L'insieme1 contiene gli elementi della lista? " + prova.containsAll(l2) + ".\n");

        System.out.println("\n\nInsieme1 prima dello svuotamento: " + prova + "\n");

        prova.removeAll();

        System.out.println("\n\nInsieme1 dopo lo svuotamento: " + prova + "\n");
        System.out.println("L'insieme1 è vuoto? " + prova.isEmpty() + ".\n");

        System.out.println("Ora ripopoliamo l'insieme1.\n");
        System.out.println("Aggiungiamo la stringa \"ciai\".\n");
        prova.add("ciai");
        System.out.println("Aggiungiamo la stringa \"ciao\".\n");
        prova.add("ciao");
        System.out.println("Aggiungiamo la stringa \"comi\".\n");
        prova.add("comi");
        System.out.println("Aggiungiamo la stringa \"como\".\n");
        prova.add("como");
        System.out.println("Aggiungiamo la stringa \"ciai2\".\n");
        prova.add("ciai2");
        System.out.println("Aggiungiamo la stringa \"ciao2\".\n");
        prova.add("ciao2");
        System.out.println("Aggiungiamo la stringa \"ciacomo\".\n");
        prova.add("ciacomo");

        System.out.println("\n\nInsieme1 ri-riempito: " + prova + ".\n");

        System.out.println("L'insieme1 è vuoto? " + prova.isEmpty() + ".\n");

        System.out.println("Dimensione: " + prova.size() + "\n");

        System.out.println("Verifichiamo se l'insieme1 contiene certi elementi.\n");
        System.out.println("L'insieme1 contiene \"ciao\"? " + prova.contains("ciao") + ".\n");
        System.out.println("l'insieme1 contiene \"ueueue\"? " + prova.contains("ueueue") + ".\n");

        System.out.println("Istanziamo l'insieme2.\n");
        OurHashSet<String> prova2 = new OurHashSet<>(22);

        System.out.println("Aggiungiamo la stringa \"ciacomo\".\n");
        prova2.add("ciacomo");
        System.out.println("Aggiungiamo la stringa \"comi\".\n");
        prova2.add("comi");
        System.out.println("Aggiungiamo la stringa \"ciacomo\".\n");
        prova2.add("ciacomo");
        System.out.println("Notare che, essendo un insieme, non ci devono essere duplicati.\n");

        System.out.println("Verifichiamo ora qualche relazione fra i due insiemi.\n");

        System.out.println("Contenuto di insieme2: " + prova2 + "\n");

        System.out.println("\nL'Insieme1 iniziale contiene: " + prova + "\n");

        System.out.println("Insieme1 e insieme2 sono uguali? " + prova.equals(prova2) + "\n");

        System.out.println("Insieme2 è sottinsieme di insieme1? " + prova2.isSubset(prova) + "\n");

        System.out.println("Istanziamo ora insieme3 e inizializziamolo come complementare dell'insieme2 rispetto all'insieme1 come insieme universo.\n");
        OurHashSet<String> prova3 = prova2.complementar(prova);
        System.out.println("Contenuto di insieme3: " + prova3 + "\n");
        System.out.println("Per chiarezza ecco il contenuto di insieme1 e insieme2.\n");
        System.out.println("Insieme1: " + prova + ".\n");
        System.out.println("Insieme2: " + prova2 + ".\n");
        System.out.println("Insieme3 è complementare di Insieme2 rispetto a insieme1? " + prova3.isComplementar(prova2, prova) + ".\n");
        System.out.println("Insieme2 è complementare di Insieme3 rispetto a insieme1? " + prova2.isComplementar(prova3, prova) + ".\n");
        System.out.println("Insieme è complementare di insieme3 rispetto a prova2? " + prova.isComplementar(prova3, prova2) + ".\n");


        System.out.println("Istanziamo ora l'insieme4 che sarà inizialmente vuoto.\n");
        OurHashSet<String> prova4 = new OurHashSet<>(43);
        System.out.println("Il contenuto di insieme4 è: " + prova4 + ".\n");

        System.out.println("Istanziamo ora l'insieme5 ed inizializziamo come il complementare di insieme4 rispetto l'insieme universo insieme1.\n");
        OurHashSet<String> prova5 = prova4.complementar(prova);

        System.out.println("Insieme4: " + prova4 + "\n");
        System.out.println(prova4);

        System.out.println("Insieme5: " + prova5 + "\n");
        System.out.println(prova5);



        OurSet<String> prova6;
        try {
            System.out.println("Mostriamo ora il contenuto di insieme1 e insieme2. Insieme1: " + prova + "\nInsieme2: " + prova2 +"\nVerifichiamo ora se esiste e qual è il complementare di insieme1 rispetto insieme2 come insieme universo.\n");
            prova6 = prova.complementar(prova2);
            System.out.println("Complementare di insieme rispetto a insieme2: " + prova6);
            System.out.println(prova6.toString());
        } catch(IllegalArgumentException c){
            System.out.println("Eccezione 1 catturata con successo:" + c.getMessage());
            System.out.println("Infatti notare che l'insieme2 ha cardinalità inferiore rispetto insieme1.\n");
        }

        System.out.println("Istanziamo ora l'insieme6 che è la differenza di insieme1 e insieme3\n");
        prova6 = prova.difference(prova3);
        System.out.println("Differenza tra insieme e insieme3: " + prova6 + "\n");

        System.out.println("Rimuoviamo tutti gli elementi da insieme4 che diventa vuoto.\n");
        prova4.removeAll();
        System.out.println("Ora consideriamo la differenza tra insieme e insieme4.\n" );
        prova6 = prova.difference(prova4);
        System.out.println("Risultato: " + prova6 + "\n");

        System.out.println("L'insieme1 contiene: " + prova + "\n");
        System.out.println("\nL'insieme2 contiene: " + prova2 + "\n");

        prova6 = prova2.difference(prova);
        System.out.println("Differenza tra insieme2 e insieme1: " + prova6 + "\n");

        System.out.println("Aggiungiamo all'insieme2 due elementi.\n");

        System.out.println("Aggiungiamo la stringa \"aela\".\n");
        prova2.add("aela");
        System.out.println("Aggiungiamo la stringa \"aela2\".\n");
        prova2.add("aela2");

        System.out.println("\n\nOra l'insieme2 contiene: " + prova2 + "\n");


        prova6 = prova2.difference(prova);
        System.out.println("Differenza tra prova2 e prova: " + prova6 + "\n");

        prova4.removeAll();
        prova6 = prova4.difference(prova);
        System.out.println("Differenza tra insieme vuoto e insieme1: " + prova6 + "\n");


        //intersection
        System.out.println("Prima di testare l'intersezione tra insiemi, facciamo il punto della situazione sul contenuto degli insiemi.\n");

        System.out.println("Rimuoviamo da insieme2 due elementi.\n");
        System.out.println("Primo elemento rimosso: aela\n");
        prova2.remove("aela");
        System.out.println("Primo elemento rimosso: aela2\n");
        prova2.remove("aela2");

        System.out.println("\n\nInsieme: " + prova + "\n");

        System.out.println("\n\nInsieme2: " + prova + "\n");

        System.out.println("\n\nInsieme3: " + prova3 + "\n");

        System.out.println("\n\nInsieme4: " + prova4 + "\n");

        System.out.println("\n\nInsieme5: " + prova5 + "\n");

        prova6 = prova.intersection(prova3);
        System.out.println("Intersezione tra insieme1 e insieme3: " + prova6 + "\n");

        prova4.removeAll();
        prova6 = prova.intersection(prova4);
        System.out.println("Intersezione tra insieme1 e vuoto: " + prova6 + "\n");


        System.out.println("\n\nInsieme2: " + prova2 + "\n");


        prova6 = prova2.intersection(prova);
        System.out.println("Intersezione tra insieme2 e insieme: " + prova6 + "\n");

        System.out.println("Rimuoviamo da insieme2 due stringhe.\n");
        System.out.println("Aggiungiamo la stringa \"aela\".\n");
        prova2.add("aela");
        System.out.println("Aggiungiamo la stringa \"aela2\".\n");
        prova2.add("aela2");

        System.out.println("\n\nInsieme2: " + prova2 + "\n");

        prova6 = prova2.intersection(prova);
        System.out.println("Intersezione tra insieme2 e insieme1: " + prova6 + "\n");

        prova4.removeAll();
        prova6 = prova4.intersection(prova);
        System.out.println("Intersezione tra insieme vuoto e insieme1: " + prova6 + "\n");

        System.out.println("\n\n\n\n\n");

        System.out.println("Facciamo nuovamente un recap sul contenuto degli insiemi.\n");
        System.out.println("\n\nInsieme: " + prova + "\n");

        System.out.println("\n\nInsieme2: " + prova2 + "\n");

        System.out.println("\n\nInsieme3: " + prova3 + "\n");

        System.out.println("\n\nInsieme4: " + prova4 + "\n");

        System.out.println("\n\nInsieme5: " + prova5 + "\n");

        System.out.println("\n\n");

        System.out.println("Insieme4 è sottoinsieme di insieme1? " + prova4.isSubset(prova) + ".\n");
        System.out.println("Viceversa? " + prova.isSubset(prova4) + "\n");

        System.out.println("\n\n");

        System.out.println("Insieme2 è sottoinsieme di insieme? " + prova2.isSubset(prova) + ".\n");
        System.out.println("Viceversa? " + prova.isSubset(prova2) + ".\n");

        System.out.println("Insieme2 è sottoinsieme di insieme vuoto? " + prova2.isSubset(prova4) + ".\n");
        System.out.println("Viceversa?" + prova4.isSubset(prova2) + ".\n");

        OurHashSet<String> prova7 = new OurHashSet<>(27);

        System.out.println("Insieme vuoto è sottinsiemen di insieme vuoto? " + prova7.isSubset(prova4) + ".\n");
        System.out.println("Viceversa " + prova4.isSubset(prova7));

        System.out.println("Istanziamo un nuovo insieme (insieme7) e riempiamolo per gli ultimi test.\n");
        System.out.println("Aggiungiamo la stringa \"ciao2\".\n");
        prova7.add("ciao2");
        System.out.println("Aggiungiamo la stringa \"como\".\n");
        prova7.add("como");
        System.out.println("Aggiungiamo la stringa \"ciao\".\n");
        prova7.add("ciao");
        System.out.println("Aggiungiamo la stringa \"ciai2\".\n");
        prova7.add("ciai2");

        System.out.println("Insieme7: " + prova7 + "\n");
        System.out.println("Insieme3 " + prova3 + "\n");

        System.out.println("Insieme7 è sottinsieme di prova3? " + prova7.isSubset(prova3) + ".\n");
        System.out.println("Viceversa?" + prova3.isSubset(prova7) + ".\n");


        System.out.println("Ora invece vediamo l'unione fra insiemi.\n");
        prova7 = prova.union(prova4);

        System.out.println("Unione tra insieme1 e insieme vuoto: " + prova7.size() + "\n");
        System.out.println(prova7);

        System.out.println("Insieme1 è uguale a insieme7? " + prova.equals(prova7) + ".\n");


        prova7 = prova2.union(prova);

        System.out.println("Consideriamo l'insieme7 come unione tra insieme2 e insieme1: " + prova7 + "\n");

        System.out.println("Insieme1 è uguale a insieme7? " + prova.equals(prova7) + ".\n");


        prova7 = prova3.union(prova);
        System.out.println("Insieme7 come unione tra insieme3 e insieme1: " + prova7 + "\n");

        System.out.println("Insieme è uguale a insieme7? " + prova.equals(prova7) + ".\n");


        prova7.removeAll();
        prova7 = prova4.union(prova7);
        System.out.println("Insieme7 come unione tra due insiemi vuoti: " + prova7 + "\n");

        System.out.println("Insieme7 è vuoto? " + prova7.isEmpty() + ".\n");


        System.out.println("Ora proviamo a scorrere tutti gli elementi di insieme1 tramite l'iteratore.\n");
        OurIterator<String> it = prova.iterator();

        while (it.hasNext()){
            System.out.print(it.next() + "\t");
        }

        System.out.println("\n");
    }

    private static void populateContainer (Container<Persona> c, String fileName) throws FileNotFoundException {
        File inputFile = new File(fileName);
        Scanner in = new Scanner(inputFile);
        String input;
        while(in.hasNextLine()) {
            input = in.nextLine();
            input.replace("\n", "");
            String[] splitStr = input.split("\\s+");
            switch(splitStr[0]) {
                case "Persona":
                    Persona temp = new Persona();
                    temp.setName(splitStr[1]);
                    temp.setSurname(splitStr[2]);
                    temp.setAge(Integer.parseInt(splitStr[3]));
                    temp.setHeight(Double.parseDouble(splitStr[4]));
                    c.add(temp);
            }
        }
        in.close();
    }

    private static class MyInput<T> {
        private T value;
        private Predicate<T> validation;

        public MyInput (T value) {
            this.value = value;
        }

        public MyInput (T value, Predicate<T> validation) {
            this.value = value;
            this.validation = validation;
        }

        public boolean getValidation() {
            return validation.test(value);
        }

        public T getValue() {
            return value;
        }

        public <R> R transform (Function<T, R> function) {
            return function.apply(value);
        }
    }

    /*private static boolean myVerify(String a, int leftExt, int rightExt) {
        MyInput<String> request;
        return (request = new MyInput<>(a, (String a) -> {

            try {
                int input = Integer.parseInt(a);
                return input >= 1 && input <= 3;
            } catch (NumberFormatException e) {
                return false;
            }

        }));
    }*/
}