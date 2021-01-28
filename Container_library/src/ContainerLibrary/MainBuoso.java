package ContainerLibrary;

import ContainerLibrary.Iterators.OurIterator;
import ContainerLibrary.Iterators.TwoSidesIterator;
import ContainerLibrary.List.NodeList.OurLinkedList;
import ContainerLibrary.List.NodeList.OurNodeList;
import ContainerLibrary.List.NodeList.OurSortableLinkedList;
import ContainerLibrary.StackAndQueues.NodeStructures.NodeDequeue;
import ContainerLibrary.StackAndQueues.NodeStructures.NodeQueue;

public class MainBuoso {
    public static void main(String[] args){
        System.out.println("//////QUEUE/////");
        NodeQueue<Integer> q1 = new NodeQueue<Integer>();
        NodeQueue<Integer> q2 = new NodeQueue<Integer>();

        System.out.println("q1 è vuota?" + q1.isEmpty());
        for(Integer i = 0; i <20; i++)
            q1.add(i);
        System.out.println("q1 varie add: " + q1.toString());

        System.out.println("elem in fondo a q1: " + q1.top());
        System.out.println("estrazione elemento dalla coda q1: " + q1.remove());

        System.out.println("aggiungo q1 a q2 ");
        q2.addAll(q1);
        System.out.println("q1 uguale a q2? " + q1.equals(q2));

        q1.removeAll();
        System.out.println("q1 dopo la removeAll: " + q1.toString());

        OurIterator<Integer> it = q2.iterator();
        int sum = 0;
        while (it.hasNext()) {
            sum += it.next();
        }
        System.out.println("somma degli elementi in q2: " + sum);



        System.out.println("/////DEQUEUE/////");

        NodeDequeue<Integer> d1 = new NodeDequeue<Integer>();
        NodeDequeue<Integer> d2 = new NodeDequeue<Integer>();
        NodeDequeue<Integer> d3 = new NodeDequeue<Integer>();
        System.out.println("d1 vuoto? " + d1.isEmpty());

        for(Integer i = 0; i < 20; i++){
            d1.add(i);
            d2.addLast(i);
        }
        System.out.println("d1 dopo varie add: " + d1.toString());
        System.out.println("d2 dopo varie addLast: " + d2.toString());

        System.out.println("rimuovo l'ultimo elemento di d1: " + d1.removeLast());
        System.out.println("ora l'ultimo elemento è " + d1.last());
        System.out.println("e il primo è " + d1.top());
        System.out.println("rimuovo l'elemento in testa: " + d1.remove());
        System.out.println("d1 aggiornato: " + d1.toString());

        System.out.println("d1 è uguale a d2? " + d1.equals(d2));
        d3.addAll(d1);
        System.out.println("d3: " + d3.toString());
        System.out.println("d1 è uguale a d3? " + d1.equals(d3));

        OurIterator<Integer> it1 = d2.iterator();
        TwoSidesIterator<Integer> it2 = d2.iterator();
        int sum1 = 0;
        int sum2 = 0;
        while(it1.hasNext())
            sum1 += it1.next();
        while(it2.hasNextSecondSide())
            sum2 += it2.nextSecondSide();
        System.out.println("sum1 : " + sum1 + "sum2: " + sum2);


        System.out.println("/////LIST/////");
        OurNodeList<Integer> l1 = new OurNodeList<Integer>(69);
        OurNodeList<Integer> l2 = new OurNodeList<Integer>();
        System.out.println("l1 vuota? " + l1.isEmpty() + ", e l2? " + l2.isEmpty());

        for(Integer i = 0; i < 10; i++)
            l1.add(i);
        System.out.println("l1 dopo varie add: " + l1.toString());
        l1.insertAt(5, 666);
        System.out.println("l1 dopo aggiunta in pos 5: " + l1.toString());
        l1.insertAt(l1.size(), 88);
        System.out.println("aggiungo 88: " + l1.toString());

        System.out.println("rimuovo elemento in pos 5: " + l1.removeAt(5));
        System.out.println("rimuovo elemento col valore 69");
        l1.remove(69);
        System.out.println("l1 aggiornata: " + l1.toString());

        l2.addAll(l1);
        System.out.println("l1 uguale a l2?" + l1.equals(l2));
        System.out.println("svuoto l1");
        l1.removeAll();


        System.out.println("/////LINKED LIST/////");
        OurSortableLinkedList<Integer> ll1 = new OurSortableLinkedList<Integer>();
        OurLinkedList<Integer> ll2 = new OurLinkedList<Integer>();
        OurLinkedList<Integer> ll3 = new OurLinkedList<Integer>();
        System.out.println("ll1 vuota? " + ll1.isEmpty());

        for(Integer i = 0; i < 10 ; i++){
            ll1.insertHead(i);
            ll2.insertTail(i);
            ll3.insertTail(i);
        }
        System.out.println("ll1: " + ll1.toString());
        System.out.println("ll2: " + ll2.toString());

        System.out.println("inserisco 2 in pos 6");
        ll1.insertAt(6, 2);
        System.out.println("ll1: " + ll1.toString());
        System.out.println("imposto il valore in pos 5 a 666");
        ll1.setAt(5, 666);
        System.out.println("ll1: " + ll1.toString());
        System.out.println("ultimo indice di 2: " + ll1.lastIndexOf(2));

        System.out.println("getAt in pos size - 1: " + ll1.getAt(ll1.size()-1));
        System.out.println("rimuovo elemento in pos 5: " + ll1.removeAt(5));
        System.out.println("rimuovo ultimo elemento: " + ll1.removeLast());
        System.out.println("rimuovo primo elemento: " + ll1.removeHead());
        ll1.remove(2);
        System.out.println("rimuovo elemento 2: " + ll1.toString());


        System.out.println("ll2: " + ll2.toString());
        System.out.println("ll3: " + ll3.toString());
        System.out.println("ll2 uguale a ll3? " + ll2.equals(ll3));

        ll1.add(1000);
        System.out.println("ll1: " + ll1.toString());
        ll1.sort();
        System.out.println("ll1 dopo il sort: " + ll1.toString());
    }
}