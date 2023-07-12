public class task_04_01 {
    public static void main(String[] args) {
        BinTree tree = new BinTree();
        tree.Add(11);
        tree.Add(57);
        tree.Add(128);
        tree.Add(41);
        tree.Add(13);
        tree.Add(27);

        tree.Print(tree.root);
    }
}

/** Полноценное левостороннее красно-черное дерево. */
class BinTree {
    Node root;

    /** Добавляет новый узел к дереву. */
    public boolean Add(int value) {
        if (root == null) {
            root = new Node(value);
            root.color = Color.black;
            return true;
        }
        if (AddNode(root, value) != null)
            return true;
        return false;
    }

    /** Добавляет новый узел к дереву, если
     * узел не корневой. */
    private Node AddNode(Node node, int value) {
        if (node.value == value)
            return null;
        if (node.value > value) {
            // Если нет левого потомка,
            // создать левого потомка.
            if (node.left == null) {
                node.left = new Node(value);
                return node.left;
            } else
                // Опуститься на уровень ниже.
                return AddNode(node.left, value);
        }
        // Не удалось вставить в качестве левого потомка,
        //  попытаться вставить в качестве правого потомка.
        else {
            // Если нет правого потомка,
            //  создать правого потома.
            if (node.right == null) {
                node.right = new Node(value);
                return node.right;
            } else
                // Опуститься на уровень ниже.
                return AddNode(node.right, value);
        }

        /** 1. Правый потомок красный, а левый - чёрный 
         * или отсутствует. */
        if (IsNodeRed(node.right) && !IsNodeRed(node.left)) {
            node = MoveLeft(node);
            SwapNodesColor(node, node.left);
        }

        /** 2. Левый потомок и его потомок красные. */
        if (IsNodeRed(node.left) && IsNodeRed(node.left.left)) {
            node = MoveRight(node);
            SwapNodesColor(node, node.right);
        }

        /** 3. Левый и правый потомки - красные. */
        if (IsNodeRed(node.left) && IsNodeRed(node.right)) {
            /** Поменять цвет родителя. */
            if(node.color == Color.black)
                node.color = Color.red;
            else
                node.color = Color.black;
            
            node.left.color = Color.black;
            node.right.color = Color.black;
        }
        return node;
    }
    
    /** Левый поворот. */
    public Node MoveLeft(Node parent) {
        LogOperation("Левый поворот");

        Node rightChild = parent.right;
        Node leftChild = rightChild.left;

        rightChild.left = parent;
        parent.right = leftChild;

        return rightChild;
    }

    /** Правый поворот. */
    public Node MoveRight(Node parent) {
        LogOperation("Правый поворот.");

        Node leftChild = parent.left;
        Node rightChild = leftChild.right;

        leftChild.right = parent;
        parent.left = rightChild;

        return leftChild;
    }

    /** Проверка, является ли узел красным. */
    public boolean IsNodeRed(Node parent) {
        if (parent == null)
            return false;
        if (parent.color == Color.black)
            return false;
        return true;
    }
    
    /** Перестановка цветов между родительским и
     * дочерними узлами. */
    public void SwapNodesColor(Node node_1, Node node_2) {
        Color tmp = node_1.color;
        node_1.color = node_2.color;
        node_2.color = tmp;
    }

    /** Выводит на консоль итоговый упорядоченный список. */
    public void Print(Node node) {
        if (node != null) {
            Print(node.left);
            System.out.println(node.value + " ");
            Print(node.right);
        }
    }

    /** Вывод на консоль. */
    private void LogOperation(String text) {
        System.out.println(text);
    }
    
    public boolean contain(int value) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.value == value)
                return true;
            if (currentNode.value > value)
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
        }
        return false;
    }

    private class Node {
        int value;
        Node left;
        Node right;
        Color color;

        Node() {
            this.color = Color.red;
        }

        Node(int _value) {
            this.value = _value;
            this.color = Color.red;
        }
    }
    
    enum Color {red, black}
}