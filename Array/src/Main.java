public class Main {

    public static void main(String[] args) {

        Array<Integer> arr = new Array<>();

        for(int i = 0; i < 10; i++)
            arr.addLast(i);
        System.out.println(arr + "\n");

        arr.add(2, 100);
        System.out.println("Using [add] method to add Element, current arr: " + arr + "\n");

        arr.addFirst(-1);
        System.out.println("Using [addFirst] method to add Element in first, current arr: " + arr  + "\n");

        System.out.println("Using [contains] method to Element(1) exsit or no: " + arr.contains(1) + "\n");

        System.out.println("Using [Find] method to find Element(8) in arr: " + arr.find(8) + "\n");

        System.out.println("Using [Remove] method to remove Element("+ arr.remove(3)  +")"+ ", current arr: " + arr + "\n");

        System.out.println("Using [RemoveFirst] method to remove Element("+ arr.removeFirst()  +")"+ ", current arr: " + arr + "\n");

        System.out.println("Using [RemoveLast] method to remove Element("+ arr.removeLast()  +")"+ ", current arr: " + arr + "\n");

        arr.set(0, 10);
        System.out.println("Using [Set] method"+ ", current arr: " + arr + "\n");
    }
}
