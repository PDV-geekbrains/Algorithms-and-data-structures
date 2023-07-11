
public class task_03_01 {
    public static void main(String[] args) {
        /** Создать исходный список. */
        LinkedList list = GetList();
        list.Print();

        /** Развернуть список. */
        list.Reverse();
        list.Print();
    }

    /** Создаёт список с фиксированными значениями. */
    static LinkedList GetList() {
        LinkedList list = new LinkedList();
        list.Add(1);
        list.Add(2);
        list.Add(3);
        list.Add(4);
        list.Add(5);
        return list;
    }
}

class LinkedList {
    Node head;
    int size;

    /** Добавляет новый элемент в конец списка. */
    public void Add(int value) {
        if (head == null) {
            head = new Node(value);
            size = 1;
            return;
        }
        Node currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = new Node(value);
        size++;
    }
    
    /** Разворачивает список. */
    public void Reverse() {
        Node previous = null;
        Node current = head;
        Node next = head.next;

        while (next != null) {
            current.next = previous;
            previous = current;
            current = next;
            next = current.next;
        }
        current.next = previous;
        head = current;
    }

    /** Выводит список на консоль. */
    public void Print() {
        Node currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }
        
    class Node {
        private Integer data;
        private Node next;
        public Node() {
        }
        public Node(Integer data) {
            this.data = data;
        }
        public Node(Integer data, Node next) {
            this.data = data;
            this.next = null;
        }
    }
}
