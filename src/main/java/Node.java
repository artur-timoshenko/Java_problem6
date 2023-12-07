import java.util.concurrent.atomic.AtomicReference;

public class Node<E> {
    final E item;
    AtomicReference<Node<E>> next;

    Node(E item) {
        this.item = item;
        this.next = new AtomicReference<>(null);
    }
}
