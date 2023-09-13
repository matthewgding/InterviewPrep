import java.util.LinkedList;

public class LinkedListProblems {
    static class Node {
        int val;
        Node next;

        Node(int inVal, Node inNext) {
            val = inVal;
            next = inNext;
        }
    }

    static void printLinkedList(Node head) {
        Node it = head;
        while (it.next != null) {
            System.out.print(it.val + ", ");
            it = it.next;
        }
        System.out.println(it.val);
    }

    // Problem 2.1: Remove Dups
    // Not using Node class
    static void removeDups(LinkedList<?> list) {
        if (list.size() <= 1) {
            return;
        }
        System.out.println("List has more than one node");

        int currNode = 0, fwdNode;
        while (currNode < list.size()) {
            fwdNode = currNode + 1;
            while (fwdNode < list.size()) {
                if (list.get(currNode).equals(list.get(fwdNode))) {
                    list.remove(fwdNode);
                } else {
                    fwdNode++;
                }
            }
            currNode++;
        }
    }

    // Problem 2.2: Return Kth to Last
    // Not using Node class
    static Object returnKthToLast(LinkedList<?> list, int k) {
        // Work under assumption that it isn't possible to know the size beforehand
        // i.e. Solve the problem as if using a basic singly linked list

        // Assume Kth to last means (last + 1) - k
        // i.e. 1st to last is the last element, 2nd to last is right before the last element,
        // and if size is 5 then 5th to last would be the first element

        int length = 0;
        for (Object o : list) {
            length++;
        }

        if (length - k < 0) {
            System.out.println("Error: the list has fewer than k elements");
            return null;
        }

        int curr = 0;
        while (curr < length - k) {
            curr++;
        }
        return list.get(curr);
    }

    // Problem 2.3: Delete Middle Node
    static void deleteMiddleNode(Node toDelete) {
        // Error case: toDelete is last node
        if (toDelete == null || toDelete.next == null) {
            System.out.println("Error: cannot delete this node");
            return;
        }

        toDelete.val = toDelete.next.val;
        if (toDelete.next.next != null) {
            toDelete.next = toDelete.next.next;
        } else {
            toDelete.next = null;
        }
    }

    // Problem 2.4: Partition
    static void partition() {

    }

    public static void main(String[] args) {
        Node head = new Node(1, new Node(2, new Node(3, null)));
        printLinkedList(head);

        deleteMiddleNode(head.next);
        printLinkedList(head);
        deleteMiddleNode(null);
        deleteMiddleNode(head.next);
    }
}
