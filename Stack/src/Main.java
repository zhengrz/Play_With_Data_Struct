
public class Main {

    public static void main(String[] args) {

        ArrayStack<Integer> arr = new ArrayStack<>();
        for (int i = 0; i < 10; i ++)
            arr.push(i);

        System.out.println("current statck: " + arr);

        System.out.println("Using [pop] method to pop Element("+ arr.pop() +") stack, current statck: " + arr);

    }
}
