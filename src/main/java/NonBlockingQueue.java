import java.util.concurrent.atomic.AtomicReference;
public class NonBlockingQueue<T> {
    private final Node<T> dummy = new Node<>(null);
    private final AtomicReference<Node<T>> head = new AtomicReference<>(dummy);
    private final AtomicReference<Node<T>> tail = new AtomicReference<>(dummy);

    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);
        while (true) {
            Node<T> currentTail = tail.get();
            Node<T> tailNext = currentTail.next.get();
            if (currentTail == tail.get()) {
                if (tailNext != null) {
                    tail.compareAndSet(currentTail, tailNext);
                } else {
                    if (currentTail.next.compareAndSet(null, newNode)) {
                        tail.compareAndSet(currentTail, newNode);
                        return;
                    }
                }
            }
        }
    }

    public T dequeue() {
        while (true) {
            Node<T> currentHead = head.get();
            Node<T> currentTail = tail.get();
            Node<T> first = currentHead.next.get();

            if (currentHead == head.get()) {
                if (currentHead == currentTail) {
                    if (first == null) {
                        return null;
                    }
                    tail.compareAndSet(currentTail, first);
                } else {
                    T value = first.item;
                    if (head.compareAndSet(currentHead, first)) {
                        return value;
                    }
                }
            }
        }
    }
}
