package ContainerLibrary.StackAndQueues.NodeStructures;

import ContainerLibrary.Iterators.OurIterator;
import ContainerLibrary.List.NodeList.OurNodeList;
import ContainerLibrary.List.OurList;
import ContainerLibrary.Exception.*;

public class MainNodeStack {
    public static void main(String[] args){
        NodeStack<Integer> prova = new NodeStack<>();
        try{
            prova.pop();
        } catch (OurNotFoundException c){
            System.out.println("eccezione 1 catturata correttamente");
        }

        System.out.println(prova.toString());

        System.out.println("dimensione: " + prova.size());

        System.out.println("è vuoto: " + prova.isEmpty());
        prova.push(6);
        prova.push(5);
        prova.push(4);
        prova.push(3);
        prova.push(2);
        prova.push(1);
        prova.push(0);

        System.out.println(prova.toString());

        System.out.println("elimino l'elemento: " + prova.pop());
        System.out.println("elimino l'elemento: " + prova.pop());

        System.out.println(prova.toString());
        System.out.println("è vuoto: " + prova.isEmpty());

        System.out.println("elimino l'elemento: " + prova.pop());

        System.out.println(prova.toString());

        System.out.println("dimensione: " + prova.size());

        prova.removeAll();

        System.out.println(prova.toString());

        System.out.println("dimensione: " + prova.size());

        System.out.println("è vuoto: " + prova.isEmpty());

        System.out.println(prova.toString());

        //testo la equals

        prova.add(5);
        prova.add(4);
        prova.add(3);
        prova.add(2);
        prova.add(1);
        prova.add(0);

        System.out.println(prova.toString());

        NodeStack<Integer> prova2 = new NodeStack<>();



        System.out.println("prova-prova2: " + prova.equals(prova2));

        prova2.add(5);
        prova2.add(4);
        prova2.add(3);
        prova2.add(2);
        prova2.add(1);
        prova2.add(0);

        System.out.println("prova-prova2: " + prova.equals(prova2));

        prova.pop();

        System.out.println("prova-prova2: " + prova.equals(prova2));

        prova.push(9);

        System.out.println("prova-prova2: " + prova.equals(prova2));

        prova.removeAll();

        prova2.removeAll();

        System.out.println("prova-prova2: " + prova.equals(prova2));

        System.out.println(prova.toString());
        System.out.println(prova2.toString());

        //testo l'iteratore

        prova2.add(5);
        prova2.add(4);
        prova2.add(3);
        prova2.add(2);
        prova2.add(1);
        prova2.add(0);

        OurIterator it = prova2.iterator();


        System.out.println("stampo con l'iteratore");
        while(it.hasNext()){
            System.out.println(it.next() + "\t");
        }
        System.out.println("");



        try {
            prova.remove(2);
        } catch (OurUnsupportedFunctionException c){
            System.out.println("eccezione 2 catturata con successo");
        }

        try {
            prova.removeAllFrom(new OurNodeList<>(2));
        } catch (OurUnsupportedFunctionException c){
            System.out.println("eccezione 3 catturata con successo");
        }


        System.out.println("prova contiene 5: " + prova2.contains(5));
        System.out.println("prova contiene 27: " + prova2.contains(27));



        System.out.println("elemento in testa di prova2: " + prova2.top());

        try {
            System.out.println("elemento in testa di prova: " + prova.top());
        } catch (OurNotFoundException c){
            System.out.println("eccezione 3 catturata con successo");
        }

        OurList<Integer> l = new OurNodeList<>(2);
        l.add(9);
        l.add(23);




        prova.addAll(l);
        prova2.addAll(l);

        System.out.println("lista da aggiungere: " + l.size());
        System.out.println(l.toString());

        System.out.println("prova2 dopo l'aggiunta: " + prova2.size());
        System.out.println(prova2.toString());


        prova.removeAll();

        prova.addAll(l);
        System.out.println("stack vuoto dopo l'aggiunta: " + prova.size());
        System.out.println(prova.toString());


        System.out.println("prova contiene gli elementi della lista: " + prova.containsAll(l));

        System.out.println("prova2 contiene gli elementi della lista: " + prova2.containsAll(l));

        prova2.remove();



        System.out.println("prova2 dopo un'eliminazione: " + prova2.size());
        System.out.println(prova2.toString());

        System.out.println("prova2 contiene gli elementi della lista: " + prova2.containsAll(l));


        prova2.remove();



        System.out.println("prova2 dopo due eliminazioniliminazione: " + prova2.size());
        System.out.println(prova2.toString());

        System.out.println("prova2 contiene gli elementi della lista: " + prova2.containsAll(l));

        System.out.println("svuoro prova");

        prova.removeAll();

        System.out.println("stack vuoto contiene gli elementi della lista: " + prova.containsAll(l));

        System.out.println("prova2 contiene gli elementi di una lista vuota: " + prova2.containsAll(new OurNodeList<>()));

    }
}
