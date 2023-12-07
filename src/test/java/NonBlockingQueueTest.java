import org.junit.Test;
import static org.junit.Assert.*;

public class NonBlockingQueueTest {

    @Test
    public void testEnqueueDequeue() {
        NonBlockingQueue<Integer> queue = new NonBlockingQueue<>();

        assertNull(queue.dequeue()); // Пустая очередь должна возвращать null при извлечении

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());

        assertNull(queue.dequeue()); // После извлечения всех элементов, очередь должна быть пустой
    }

    @Test
    public void testEnqueueAndPeek() {
        NonBlockingQueue<String> queue = new NonBlockingQueue<>();

        queue.enqueue("Test");

        assertEquals("Test", queue.dequeue()); // Проверка извлечения элемента
        assertNull(queue.dequeue()); // Проверка, что извлечение из пустой очереди возвращает null
    }

}
