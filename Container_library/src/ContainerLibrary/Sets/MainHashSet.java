package ContainerLibrary.Sets;

import ContainerLibrary.Iterators.OurIterator;
import ContainerLibrary.List.NodeList.OurNodeList;

import ContainerLibrary.List.OurList;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;


import java.util.List;

public class MainHashSet {
    public static void main(String argv[]){
        OurHashSet<String> prova = new OurHashSet<>();

        System.out.println("è vuoto: " + prova.isEmpty());
        System.out.println("la dimensione è: " + prova.size());


        System.out.println("Stampo l'insieme vuoto: " + prova.size());
        System.out.println(prova.toString());

        prova.add("ciao");
        prova.add("come");
        prova.add("stai");
        prova.add("tutto");
        prova.add("bene");

        System.out.println("è vuoto: " + prova.isEmpty());
        System.out.println("la dimensione è: " + prova.size());


        System.out.println("Stampo l'insieme dopo aver aggiunto un po' di elementi " + prova.size());
        System.out.println(prova.toString());

        OurList<String> l = new OurNodeList<>("ciao2");
        l.add("come2");
        l.add("stai2");

        prova.addAll(l);


        System.out.println("contenuto lista da aggiungere " + l.toString());


        System.out.println("Stampo l'insieme dopo aver aggiunto gli elementi dalla lista " + prova.size());
        System.out.println(prova.toString());

        System.out.println("rimuovo due elementi dalla lista e ne aggiungo uno");
        l.removeHead();
        l.removeHead();

        l.add("wow");

        System.out.println("contenuto lista da togliere " + l.toString());



        prova.removeAllFrom(l);
        System.out.println("Stampo l'insieme dopo aver tolto gli elementi dalla lista " + prova.size());
        System.out.println(prova.toString());


        OurList<String> l2 = new OurNodeList<String>();
        System.out.println("contenuto lista da controllare " + l2.toString());
        System.out.println("L'insieme contiene gli elementi della lista: " + prova.containsAll(l2));

        l2.add("cia");
        System.out.println("contenuto lista da controllare " + l2.toString());
        System.out.println("L'insieme contiene gli elementi della lista: " + prova.containsAll(l2));


        l2.removeAll();

        l2.add("ciao");
        l2.add("come");
        l2.add("stai2");

        System.out.println("contenuto lista da controllare " + l2.toString());
        System.out.println("L'insieme contiene gli elementi della lista: " + prova.containsAll(l2));


        l2.add("cia");

        System.out.println("contenuto lista da controllare " + l2.toString());
        System.out.println("L'insieme contiene gli elementi della lista: " + prova.containsAll(l2));

        System.out.println("\n\n insieme prima dello svuotamento: " + prova.size());
        System.out.println(prova.toString());

        prova.removeAll();

        System.out.println("\n\n insieme dopo lo svuotamento: " + prova.size());
        System.out.println(prova.toString());
        System.out.println("è vuoto: " + prova.isEmpty());

        prova.add("ciai");
        prova.add("ciao");
        prova.add("comi");
        prova.add("como");
        prova.add("ciai2");
        prova.add("ciao2");
        prova.add("ciacomo");

        System.out.println("\n\n insieme ri-riempito: " + prova.size());
        System.out.println(prova.toString());
        System.out.println("è vuoto: " + prova.isEmpty());


        System.out.println("l'insieme contiene ciao: " + prova.contains("ciao"));
        System.out.println("l'insieme contiene ueueue " + prova.contains("ueueue"));

        OurHashSet<String> prova2 = new OurHashSet<>(22);

        prova2.add("ciai");
        prova2.add("comi");
        prova2.add("ciacomo");


        System.out.println("\n\n");

        System.out.println("insieme prova2: " + prova2.size());
        System.out.println(prova2.toString());

        System.out.println("\nl'insieme prova: " + prova.size());
        System.out.println(prova.toString());

        System.out.println("prova e prova2 sono equals: " + prova.equals(prova2));

        System.out.println("prova2 è sottinsieme di prova: " + prova2.isSubset(prova));


        OurHashSet<String> prova3 = prova2.complementar(prova);
        System.out.println("comlementare di prova2 rispetto prova: " + prova3.size());
        System.out.println(prova3.toString());
        System.out.println("prova3 è complementare di prova2 rispetto a prova: " + prova3.isComplementar(prova2, prova));
        System.out.println("prova2 è complementare di prova3 rispetto a prova: " + prova2.isComplementar(prova3, prova));
        System.out.println("prova è complementare di prova3 rispetto a prova2: " + prova.isComplementar(prova3, prova2));


        OurHashSet<String> prova4 = new OurHashSet<>(43);

        OurHashSet<String> prova5 = prova4.complementar(prova);

        System.out.println("insieme prova4: " + prova4.size());
        System.out.println(prova4.toString());

        System.out.println("insieme prova5: " + prova5.size());
        System.out.println(prova5.toString());



        OurSet<String> prova6;
        try {
            prova6 = prova.complementar(prova2);
            System.out.println("comlementare di prova rispetto prova2: " + prova6.size());
            System.out.println(prova6.toString());
        } catch(IllegalArgumentException c){
            System.out.println("Eccezione 1 catturata con successo");
        }

        prova6 = prova.difference(prova3);
        System.out.println("differenza tra prova e prova3: " + prova6.size());
        System.out.println(prova6.toString());



        prova4.removeAll();
        prova6 = prova.difference(prova4);
        System.out.println("differenza tra prova e vuoto: " + prova6.size());
        System.out.println(prova6.toString());



        System.out.println("\nprova2: " + prova2.size());
        System.out.println(prova2.toString());


        prova6 = prova2.difference(prova);
        System.out.println("differenza tra prova2 e prova: " + prova6.size());
        System.out.println(prova6.toString());

        prova2.add("aela");
        prova2.add("aela2");


        System.out.println("\n\nprova2: " + prova2.size());
        System.out.println(prova2.toString());


        prova6 = prova2.difference(prova);
        System.out.println("differenza tra prova2 e prova: " + prova6.size());
        System.out.println(prova6.toString());



        prova4.removeAll();
        prova6 = prova4.difference(prova);
        System.out.println("differenza tra vuoto e prova: " + prova6.size());
        System.out.println(prova6.toString());



        //intersection


        prova2.remove("aela");
        prova2.remove("aela2");

        System.out.println("\n\nprova: " + prova.size());
        System.out.println(prova.toString());


        System.out.println("\n\nprova2: " + prova2.size());
        System.out.println(prova2.toString());

        System.out.println("\n\nprova3: " + prova3.size());
        System.out.println(prova3.toString());

        System.out.println("\n\nprova4: " + prova4.size());
        System.out.println(prova4.toString());

        System.out.println("\n\nprova5: " + prova5.size());
        System.out.println(prova5.toString());

        prova6 = prova.intersection(prova3);
        System.out.println("intersezione tra prova e prova3: " + prova6.size());
        System.out.println(prova6.toString());



        prova4.removeAll();
        prova6 = prova.intersection(prova4);
        System.out.println("intersezione tra prova e vuoto: " + prova6.size());
        System.out.println(prova6.toString());



        System.out.println("\n\nprova2: " + prova2.size());
        System.out.println(prova2.toString());


        prova6 = prova2.intersection(prova);
        System.out.println("intersezione tra prova2 e prova: " + prova6.size());
        System.out.println(prova6.toString());

        prova2.add("aela");
        prova2.add("aela2");


        System.out.println("\n\nprova2: " + prova2.size());
        System.out.println(prova2.toString());


        prova6 = prova2.intersection(prova);
        System.out.println("intersezione tra prova2 e prova: " + prova6.size());
        System.out.println(prova6.toString());



        prova4.removeAll();
        prova6 = prova4.intersection(prova);
        System.out.println("intersezione tra vuoto e prova: " + prova6.size());
        System.out.println(prova6.toString());


        System.out.println("\n\n\n\n\n");

        System.out.println("\n\nprova: " + prova.size());
        System.out.println(prova.toString());


        System.out.println("\n\nprova2: " + prova2.size());
        System.out.println(prova2.toString());

        System.out.println("\n\nprova3: " + prova3.size());
        System.out.println(prova3.toString());

        System.out.println("\n\nprova4: " + prova4.size());
        System.out.println(prova4.toString());

        System.out.println("\n\nprova5: " + prova5.size());
        System.out.println(prova5.toString());

        System.out.println("\n\n");

        System.out.println("prova4 è sottoinsieme di prova, (t): " + prova4.isSubset(prova));
        System.out.println("viceversa(f) : " + prova.isSubset(prova4));

        System.out.println("\n\n");

        System.out.println("prova2 è sottoinsieme di prova, (f): " + prova2.isSubset(prova));
        System.out.println("viceversa(f) : " + prova.isSubset(prova2));

        System.out.println("prova2 è sottoinsieme di vuoto, (f): " + prova2.isSubset(prova4));
        System.out.println("viceversa(t) : " + prova4.isSubset(prova2));

        OurHashSet<String> prova7 = new OurHashSet<>(27);

        System.out.println("vuoto è sottinsiemen di vuoto, (t): " + prova7.isSubset(prova4));
        System.out.println("viceversa, (t): " + prova4.isSubset(prova7));

        prova7.add("ciao2");
        prova7.add("como");
        prova7.add("ciao");
        prova7.add("ciai2");

        System.out.println("prova7: " + prova7.size());
        System.out.println(prova7.toString());


        System.out.println("prova7 è sottinsieme di prova3, (t): " + prova7.isSubset(prova3));
        System.out.println("viceversa, (t): " + prova3.isSubset(prova7));


        prova7 = prova.union(prova4);



        System.out.println("prova7: unione tra prova e insieme vuoto: " + prova7.size());
        System.out.println(prova7);

        System.out.println("prova è equals a prova7: " + prova.equals(prova7));


        prova7 = prova2.union(prova);

        System.out.println("prova7: unione tra prova2 e prova: " + prova7.size());
        System.out.println(prova7);

        System.out.println("prova è equals a prova7: " + prova.equals(prova7));


        prova7 = prova3.union(prova);
        System.out.println("prova7: unione tra prova3 e prova: " + prova7.size());
        System.out.println(prova7);

        System.out.println("prova è equals a prova7: " + prova.equals(prova7));


        prova7.removeAll();
        prova7 = prova4.union(prova7);
        System.out.println("prova7: unione tra vuoto e vuoto: " + prova7.size());
        System.out.println(prova7);

        System.out.println("prova7 è vuoto: " + prova7.isEmpty());


        OurIterator<String> it = prova.iterator();

        System.out.println("prova: " + prova.size());
        System.out.println(prova);

        System.out.println("\n prova con iteratore: ");
        while (it.hasNext()){
            System.out.print(it.next() + "\t");
        }




    }


}
