package ContainerLibrary.List.NodeList;

import ContainerLibrary.Iterators.OurListIterator;
import ContainerLibrary.Exception.OurNotFoundException;
import ContainerLibrary.Exception.OurIndexOutOfBoundsException;

public class MainNodeList {
    public static void main(String[] args) {
        //creo la lista vuota e controllo la sua dimensione e che sia vuota
        OurNodeList<Integer> prova = new OurNodeList<Integer>();
        System.out.println(prova.size());
        System.out.println("la lista è vuota: " + prova.isEmpty());

        //provo a farmi restituirre un elemento in una posizione non valida

        try{
            prova.getHead();
        }
        catch(OurNotFoundException c){
            System.out.println("eccezione 1 trovata correttamente: " + c.getMessage());
        }

        //provo a inserire un elemento in una posizione non valida

        try{
            prova.insertAt(2, 2);
        } catch (OurIndexOutOfBoundsException c){
            System.out.println("eccezione 2 trovata correttamente: " + c.getMessage());
        }

        //stampo la lista

        System.out.println(prova.toString());

        //inserisco un elemento in testa e uno in coda e controllo

        prova.insertHead(3);



        prova.insertTail( 4);

        System.out.println(prova.toString());

        //inserisco un elemento in mezzo(posizione 1)

        prova.insertAt(1, 5);

        //inserisco un elemento in testa
        prova.add(9);

        //provo la contains

        if(prova.contains(8)) {
            System.out.println("la lista contiene 8");
        } else {
            System.out.println("la lista non contiene 8");
        }

        if (prova.contains(9))
            System.out.println("la lista contiene 9");
        else
            System.out.println("la lista non contiene 9");

        System.out.println(prova.toString());

        //provo la getAt

        System.out.println("elemento in posizione 2: " + prova.getAt(2));

        try{
            prova.getAt(10);
        } catch (OurIndexOutOfBoundsException c){
            System.out.println("eccezione 3 trovata correttamente: " + c.getMessage());
        }

        //provo indexOf e lastIndexOf

        try{
            prova.indexOf(15);
        } catch (OurNotFoundException c){
            System.out.println("eccezione 4 trovata correttamente: " + c.getMessage());
        }



        prova.add(9);

        System.out.println("ultimo indice dell'elemento 9: " + prova.lastIndexOf(9));

        System.out.println("primo indice dell'elemento 9: " + prova.indexOf(9));

        //controllo se la lista è vuota e la sua dimensione

        System.out.println("la lista è vuota: " + prova.isEmpty());

        System.out.println("dimensione della lista: " + prova.size());

        //controllo getLat, getHead removeLast e removeHead

        System.out.println(prova.toString());

        System.out.println("sto per rimuovere l'elemento in coda: " + prova.getLast());
        System.out.println("Ho rimosso l'elemento in coda" + prova.removeLast());
        System.out.println(prova.toString());


        System.out.println("sto per rimuovere l'elemento in testa: " + prova.getHead());
        System.out.println("Ho rimosso l'elemento in testa" + prova.removeHead());
        System.out.println(prova.toString());

        //controllo getLast, removeLast, getHead, removeHead con una lista che svuoto

        OurNodeList<Integer> prova2 = new OurNodeList<>(32);
        prova2.add(45);

        System.out.println("sto per rimuovere l'elemento in testa: " + prova2.getHead());
        System.out.println("Ho rimosso l'elemento in testa" + prova2.removeHead());
        System.out.println(prova.toString());

        try{

            System.out.println("sto per rimuovere l'elemento in coda: " + prova2.getLast());
            System.out.println("Ho rimosso l'elemento in coda" + prova2.removeLast());
            System.out.println(prova.toString());
        } catch (OurIndexOutOfBoundsException c){
            System.out.println("eccezione 5 rilevata con successo");
        }


        try{
        System.out.println("sto per rimuovere l'elemento in testa: " + prova2.getHead());
        System.out.println("Ho rimosso l'elemento in testa" + prova2.removeHead());
        System.out.println(prova.toString());
        } catch (OurNotFoundException c) {
            System.out.println("eccezione 5 rilevata con successo");
        }


        System.out.println(prova.toString());

        prova.removeAll();
        System.out.println("la lista è vuota: " + prova.isEmpty());

        System.out.println(prova.toString());

        OurNodeList<Integer> prova3 = new OurNodeList<>();

        prova.add(11);
        prova.add(12);
        prova.add(13);
        prova.add(14);
        prova.add(15);
        prova.add(16);

        System.out.println("prova");

        System.out.println(prova.toString());
        prova.setAt(5, 10);

        System.out.println("prova");
        System.out.println(prova.toString());
        prova.removeAt(0);

        System.out.println("prova");
        System.out.println(prova.toString());



        prova2.add(27);
        prova2.add(26);
        prova2.add(25);
        prova2.add(24);
        prova2.add(23);
        prova2.add(22);
        prova2.add(21);

        System.out.println("prova2");
        System.out.println(prova2.toString());

        prova.addAll(prova2);

        System.out.println("prova2");
        System.out.println(prova2.toString());

        System.out.println("prova");
        System.out.println(prova.toString());

        prova2.removeAt(2);
        System.out.println("tolgo");

        System.out.println("prova2");
        System.out.println(prova2.toString());

        System.out.println("prova");
        System.out.println(prova.toString());

        prova.removeAt(10);

        System.out.println("prova2");
        System.out.println(prova2.toString());

        System.out.println("prova");
        System.out.println(prova.toString());

        prova3.add(21);
        prova3.add(10);

        System.out.println("prova contiene 10, 21: " + prova.containsAll(prova3));
        System.out.println("prova2 contiene 10, 21: " + prova2.containsAll(prova3));



        prova.removeAllFrom(prova3);
        System.out.println(prova.toString());

        prova2.removeAllFrom(prova3);
        System.out.println(prova2.toString());

        OurListIterator<Integer> it = prova.listIterator();

        int i = -1;
        while(it.hasNext()){
            i++;
            System.out.println("elemento in pos " + i + ": " + it.next());
        }

        while(it.hasPrevious()){
            i--;
            System.out.println("elemento in pos " + i + ": " + it.previous());
        }

        System.out.println(prova.toString());
        System.out.println(prova2.toString());
        System.out.println(prova3.toString());

        //testo equals

        System.out.println("prova-prova2: " + prova.equals(prova2));

        System.out.println("prova-prova3" + prova3.equals(prova));

        //creo una nuova lista uguale a prova2

        OurNodeList<Integer> prova4 = new OurNodeList<>();
        prova4.add(27);
        prova4.add(26);
        prova4.add(25);
        prova4.add(24);
        prova4.add(22);


        // testo equals
        System.out.println(prova4.toString());
        System.out.println("prova4-prova2: " + prova4.equals(prova2));

        prova4.add(2);
        System.out.println("prova4-prova2: " + prova4.equals(prova2));

        prova2.add(3);
        System.out.println("prova4-prova2: " + prova4.equals(prova2));
        System.out.println("prova2-prova4: " + prova2.equals(prova4));

        prova.removeAll();
        prova2.removeAll();

        System.out.println("prova-prova2: " + prova.equals(prova2));
    }


}
