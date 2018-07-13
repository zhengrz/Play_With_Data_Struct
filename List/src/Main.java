public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 5; i >= 0; i--)
            linkedList.addFirst(i);
        System.out.println(linkedList);


        linkedList.add(2, 666);
        System.out.println(linkedList);

        System.out.println("Using [get] method to get Node-Value: " + linkedList.get(2));

        System.out.println("Using [getFirst] method to get Node-Value: " + linkedList.getFirst());

        System.out.println("Using [getLast] method to get Node-Value: " + linkedList.getLast());

        int v = linkedList.get(2);

        System.out.println("Using [contains] method to check elem("+ v +") is exsit or not: " + linkedList.contains(v));

        linkedList.set(2, 100);
        System.out.println("Using [set] method to set elem("+ v +"), current value: " + linkedList.get(2));

        linkedList.remove(2);
        System.out.println("Using [remove] method to remove ("+ v +"), " + linkedList);

        System.out.println("Using [removeFirst] method to remove ("+ linkedList.removeFirst() +"), " + linkedList);
//
        System.out.println("Using [removeLast] method to remove ("+ linkedList.removeLast() +"), " + linkedList);

        System.out.println("Using [removeElement] method to remove ("+ linkedList.removeElement(linkedList.getLast()) +"), " + linkedList);
    }
}
