import util.collections.MyArrayList;
import util.collections.MyLinkedList;
import util.collections.MyList;
import util.maps.MyHashMap;

/**
 * This class contains methods for performing functionality of
 * MyArrayList, MyLinkedList and MyHashMap.
 */
class Sample {

    /**
     * Performing such methods of LinkedList, as:
     * - add / addFirst / addLast;
     * - remove / removeFirst / removeLast;
     * - set.
     */
    static void showLinkedList() {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();

        myLinkedList.add("One");
        myLinkedList.add("Two");
        myLinkedList.add("Three");
        myLinkedList.add("Four");
        myLinkedList.add("Five");
        myLinkedList.addFirst("First");
        myLinkedList.addLast("Last");

        myLinkedList.remove(2);
        myLinkedList.remove("Four");

        myLinkedList.add(1, "Hi");

        myLinkedList.set(2, "NewValue");

        System.out.println("\n\nFirst element = " + myLinkedList.getFirst() +
                "\nLast element = " + myLinkedList.getLast());

        myLinkedList.removeFirst();
        myLinkedList.removeLast();

        System.out.println("My Linked List: ");
        printList(myLinkedList);
    }

    /**
     * Performing such methods of ArrayList, as:
     * - add;
     * - remove;
     * - set;
     * - isEmpty.
     */
    static void showArrayList() {
        MyArrayList<String> myArrayList = new MyArrayList<>();

        if (myArrayList.isEmpty()) {
            System.out.println("Array list is empty now");
        }

        myArrayList.add("One");
        myArrayList.add("Two");
        myArrayList.add("Three");
        myArrayList.add("Four");
        myArrayList.add("Five");
        myArrayList.add("Six");
        myArrayList.add("Seven");
        myArrayList.add("Eight");
        myArrayList.add("Nine");
        myArrayList.add("Ten");
        myArrayList.add("Eleven");
        myArrayList.add(10, "Ten-Eleven");

        myArrayList.set(2, "NewThree");

        myArrayList.remove(5);
        myArrayList.remove("Seven");

        System.out.println("My Array List: ");
        printList(myArrayList);
    }

    /**
     * Performing such methods of HashMap, as:
     * - put;
     * - get;
     * - remove;
     * - size.
     */
    static void showHashMap() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();

        myHashMap.put(1, "One");
        myHashMap.put(2, "Two");

        myHashMap.remove(1);

        System.out.println("\n\nMy HashMap:");
        System.out.println(" key = 1, value = " + myHashMap.get(1) +
                ",\n key = 2, value = " + myHashMap.get(2));
        System.out.println(" Size = " + myHashMap.size());
    }

    private static void printList(MyList list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }
}
