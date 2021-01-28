package ContainerLibrary;

import java.util.*;

import ContainerLibrary.List.ArrayList.OurArrayList;
import ContainerLibrary.List.OurList;

public class MainCazzaro {

    public static void main(String[] args) {

        //MAIN CAZZARO
        /*List.NodeList.OurArrayList<Integer> prova = new List.NodeList.OurArrayList<>();
        prova.ensuredSize(15);
        for(int i = 0; i < 20; i++) {
            prova.add(i);
            System.out.println(prova.arraySize());
        }*/

        //Random r = new Random(3);

        //System.out.println();
        //int index;
        /*for(int i = 0; i < 20; i++) {
            System.out.println(index = r.nextInt(20));
            prova.insertAt(i, index);
            //System.out.println(prova.arraySize());
        }*/

        //System.out.println(prova.indexOf(3));
        /*for(int i = 0; i < 20; i++) {
            System.out.println(index = r.nextInt(prova.size()));
            prova.removeAt(index);
            System.out.println(prova.arraySize());
            System.out.println(prova.toString());
            System.out.println();
            //prova.removeAt(0);
        }*/

        //System.out.println(prova.contains(21));

        /*System.out.println(prova.arraySize());
        System.out.println(prova.size());
        prova.removeAt(0);*/
        /*System.out.println(prova.getAt(0));
        System.out.println(prova.remove(6));
        System.out.println(prova.getAt(6));
        System.out.println(prova.size());*/
        //System.out.println(prova.toString());

        //prove per equals
        /*ClasseProva1 a = new ClasseProva1(1);
        ClasseProva2 b = new ClasseProva2(1, 'b');
        ClasseProva3 c = new ClasseProva3(1, true);
        ClasseProva1 d = b;
        ClasseProva1 e = c;
        System.out.println(d.equals(e));*/

        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        ListIterator<Integer> b = a.listIterator();
        //a.add(0,0);
        System.out.println(b.next());
        OurList<Object> [] c = new OurArrayList[10];
        for(int i = 0; i < c.length; i++) {

        }
    }
}
