import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class Lesson3LockingFreeDataStructure {
    /*
        1. AtomicReference<T>
           Allow to perform atomic operations on that reference (passed by constructor)
           AtomicReference(V value)

        2. Build Lock free data structure

        3.  CAS - CompareAndSet operation

        4. Compare performance with the blocking implementation
    */

    //CAS
    public static void main(String[] args) {
        String oldName = "old name";
        String newName = "new name";

        AtomicReference<String> atomicReference = new AtomicReference<>(oldName);
        if (atomicReference.compareAndSet(oldName, newName))
            System.out.println("New Value is" + atomicReference.get()); //new value
        else System.out.println("Nothing changed");
    }

    //Stack as Locking Free Algorithm
    public static class StackNode<T> {
        public T value;
        public StackNode<T> next;

        public StackNode(T value) {
            this.value = value;
            this.next = next;
        }
    }

    public static class StandardStack<T> {
        private StackNode<T> head;
        private int counter = 0;

        public synchronized void push(T value) {
            StackNode<T> newHead = new StackNode<>(value);
            newHead.next = head;
            head = newHead;
            counter++;
        }

        public synchronized T pop() {
            if (head == null) {
                counter++;
                return null;
            }
            T value = head.value;
            head = head.next;
            counter++;
            return value;
        }

        public int getCounter() {
            return counter;
        }
    }

    public static class LockFreeStack<T> {
        private AtomicReference<StackNode<T>> head = new AtomicReference<>();
        private AtomicInteger counter = new AtomicInteger(0);

        public synchronized void push(T value) {
            StackNode<T> newHeadNode = new StackNode<>(value);
            while (true) {
                StackNode<T> currentHeadNode = head.get();
                newHeadNode.next = currentHeadNode;
                if (head.compareAndSet(currentHeadNode, newHeadNode)) { //Old head is still being the same yes no problem
                    break;
                } else {
                    //Between this thread is getting head setting newHeadNode.next other thread modifies the stack
                    //Then wait and repeat
                    LockSupport.parkNanos(1);
                }
            }
            counter.incrementAndGet();
        }

        public synchronized T pop() {
            StackNode<T> currentHeadNode = head.get();
            StackNode<T> newHeadNode;

            while (currentHeadNode != null) {
                newHeadNode = currentHeadNode.next;
                if (head.compareAndSet(currentHeadNode, newHeadNode)) {
                    break;
                } else {
                    LockSupport.parkNanos(1);
                    currentHeadNode = head.get();
                }
            }
            counter.incrementAndGet();
            return currentHeadNode != null ? currentHeadNode.value : null;
        }

        public int getCounter() {
            return counter.get();
        }
    }
}
