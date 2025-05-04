public class LinkedList<E> extends AbstractList<E> {

    private Node<E> head, current, tail;

    public LinkedList() {
        head = current = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(E element) {
        Node<E> newNode = new Node<E>(element);
        newNode.next = head;
        head = newNode;
        size++;
        if(tail == null) {
            tail = head;
        }
    }

    public void addLast(E element) {
        Node<E> newNode = new Node<E>(element);

        if(tail == null) {
            head = tail = newNode;
        }
        else {
            tail.next = newNode;
            tail = tail.next;
        }
        size++;
    }

    @Override
    public void add(int index, E element) {
        if (index == 0) {
            addFirst(element);
        }
        else if (index >= size) {
            addLast(element);
        }

        else {
            current = head;
            for (int i=1; i<index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            Node<E> newNode = new Node<E>(element);
            current.next = newNode;
            newNode.next = temp;
            size++;
        }

    }

    public E getFirst() {
        if (isEmpty()) {
            return null;
        }
        else {
            current = head;
            return current.element;
        }
    }

    public E getLast() {
        if (isEmpty()) {
            return null;
        }
        else {
            return tail.element;
        }
    }

    public E getNext() {
        if (current == tail) {
            return null;
        }
        else {
            current = current.next;
            return current.element;
        }
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        else {
            current = head;
            for (int i=0; i< index; i++) {
                current = current.next;
            }
            return current.element;
        }
    }

    @Override   /***Replace element of node*/
    /***
     public E set(int index, E element) {
     if  (index < 0 || index >= size)
     return null;
     current = head;
     for (int i=0; i<index; i++) {
     current = current.next;
     }

     E oldElement = current.element;
     current.element = element;
     return oldElement;
     }
     ***/


    /***Replace element (node) of LinkedList*/
    public E set(int index, E element) {
        if  (index < 0 || index >= size)
            return null;

        Node<E> newNode = new Node<E>(element);
        current = head;
        Node<E> previous = null;

        for (int i=0; i<index; i++) {
            previous = current;
            current = current.next;
        }

        previous.next = newNode;
        newNode.next = current.next;
        current.next = null;

        Node<E> oldNode = current;
        return oldNode.element;
    }


    @Override
    public void clear() {
        head = current = tail = null;

    }

    @Override
    public boolean contains(E element) {
        boolean isContain = false;
        current = head;
        for (int i=0; i<size; i++) {
            if(element.equals(current.element)) {
                isContain = true;
                break;
            }
        }
        return isContain;
    }

    @Override
    public int indexOf(E element) {
        int index = 0;
        current = head;
        for (int i=size; i>=0; i--) {
            if(element.equals(current.element)) {
                index = i;
                break;
            }
            current = current.next;
        }
        return index;
    }

    public int lastIndexOf(E element) {
        int index = 0;
        current = head;
        for (int i=size; i>=0; i--) {
            if (element.equals(current.element)) {
                index = i;
                break;
            }
            current = current.next;
        }
        return index;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        else {
            Node<E> temp = head;
            head = head.next;
            size--;
            if (head == null)
                tail = null;
            return temp.element;
        }
    }


    public E removeLast() {
        if (isEmpty())
            return null;
        else if (size == 1) {
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        }
        else {
            current = head;
            for (int i=0; i<size-2; i++) {
                current = current.next;
            }

            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        else if (index == 0) {
            return removeFirst();
        }
        else {
            Node<E> previous = head;

            for (int i=1; i<index; i++) {
                previous = previous.next;
            }

            Node <E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        if (isEmpty()) {
            result.append("The list is empty]");
        }
        else {
            for (int i=0; i<size; i++) {
                result.append(current.element);
                current = current.next;
                if (current != null)
                    result.append(", ");
                else
                    result.append("]");
            }
        }
        return result.toString();
    }

}
