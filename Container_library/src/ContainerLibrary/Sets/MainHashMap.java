package ContainerLibrary.Sets;



import ContainerLibrary.List.NodeList.OurNodeList;
import ContainerLibrary.Exception.*;

public class MainHashMap {
    public static void main(String arg[]) {


        HashMap<Integer, String> prova = new HashMap<>();

        System.out.println("è vuoto: " + prova.isEmpty());
        System.out.println("la dimensione è: " + prova.size());


        System.out.println("Stampo la mappa vuota: " + prova.size());
        System.out.println(prova.toString());

        //provo put, get value, containssKey, containsvalue
        prova.put(1, "ciao");
        prova.put(2, "come");
        prova.put(3, "stai");
        prova.put(11, "ciao2");


        System.out.println("Stampo la map con un po' di elementi: " + prova.size());
        System.out.println(prova.toString());


        System.out.println("contiene la chiave 2: " + prova.containsKey(2));
        System.out.println("contiene la chiave 14: " + prova.containsKey(14));

        System.out.println("contiene il valore ciao: " + prova.containsValue("ciao"));
        System.out.println("contiene il valore ciao3: " + prova.containsValue("ciao3"));
        System.out.println("contiene il valore ciao2: " + prova.containsValue("ciao2"));

        System.out.println("valore con chiave 3: " + prova.getValue(3));

        System.out.println("valore con chiave 1: " + prova.getValue(1));
        System.out.println("valore con chiave 11: " + prova.getValue(11));





        OurNodeList<Couple<Integer, String>> l = new OurNodeList<>();
        l.add(new Couple<>(21, "ciao4"));
        l.add(new Couple<>(7, "elemento7"));
        l.add(new Couple<>(43, "stai2"));
        l.add(new Couple<>(58, "ciao4"));


        System.out.println("lista da aggiungere: " + l.toString());



        prova.putAll(l);

        System.out.println("Stampo la mappa con tanti elementi: " + prova.size());
        System.out.println(prova.toString());

        prova.put(4, "ciao");
        l.add(new Couple<>(4, "ciao"));
        System.out.println("aggiungo (4, ciao)");

        System.out.println("Stampo la mappa prima di rimuovere: " + prova.size());
        System.out.println(prova.toString());


        prova.removeAllFrom(l);

        System.out.println("lista da rimuovere: " + l.toString());

        System.out.println("Stampo la mappa dopo aver rimosso: " + prova.size());
        System.out.println(prova.toString());

        System.out.println("la mappa è vuota: " + prova.isEmpty());


        prova.removeAll();
        System.out.println("mappa svuotata");
        System.out.println(prova.toString());


        System.out.println("la mappa è vuota: " + prova.isEmpty() + ", size = " + prova.size());

        prova.putAll(l);

        System.out.println("mappa ripopolata: " + prova.size());
        System.out.println(prova.toString());


        System.out.println("elemento con chiave 7: " + prova.getElem(7));
        try{
            System.out.println("elemento con chiave 9: " + prova.getElem(9));
        } catch (OurNotFoundException c){
            System.out.println("eccezione 1 catturata con successo");
        }

        try{
            System.out.println("valore con chiave 9: " + prova.getValue(9));
        } catch (OurNotFoundException c){
            System.out.println("eccezione 2 catturata con successo");
        }

        System.out.println("metto \"stringa\" nella chiave 7 che aveva: " + prova.replace(7,"stringa"));

        System.out.println("mappa dopo il rimpiazzo: " + prova.size());
        System.out.println(prova.toString());

        System.out.println("adesso tolgo l'elemento con chiave 7: " + prova.remove(7));

        System.out.println("mappa dopo l'eliminazione: " + prova.size());
        System.out.println(prova.toString());

        HashMap<Integer, String> prova2 = new HashMap<>(15 ,(Integer key) -> { return (key+1)%15; });

        prova2.putAll(l);
        prova2.remove(7);

        System.out.println("prova2: " + prova2.size());
        System.out.println(prova2.toString());

        System.out.println("prova e prova2 sono equals: " + prova.equals(prova2));
        System.out.println("prova2 e prova sono equals: " + prova2.equals(prova));

        System.out.println("aggiungo un elemento a prova2: ");
        prova2.put(227, "cia");

        System.out.println("prova2: " + prova2.size());
        System.out.println(prova2.toString());


        System.out.println("prova e prova2 sono equals: " + prova.equals(prova2));
        System.out.println("prova2 e prova sono equals: " + prova2.equals(prova));


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

        System.out.println("prova è equals con se stessa: " + prova.equals(prova));


        HashMap<Integer, String> prova4 = new HashMap<>();

        HashMap<Integer, String> prova5 = new HashMap<>();

        HashMap<Integer, String> prova6 = new HashMap<>(15 ,(Integer key) -> { return (key+1)%15; });

        prova.removeAll();

        System.out.println("due mappe vuote appena create sono equals: " + prova4.equals(prova5) + ", " + prova5.equals(prova4));

        System.out.println("due mappe vuote con dimensioni diverse appena create sono equals: " + prova4.equals(prova6) + ", " + prova6.equals(prova4));

        System.out.println("una mappa appena creata e una svuotata sono uguali: " + prova6.equals(prova) + ", " + prova.equals(prova6));

    }
}
