import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReadWriteLock;

public class SharedResource {
    private final List<Integer> sharedList = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void addItem(int item) {
        lock.writeLock().lock();
        try {
            sharedList.add(item);
            System.out.println(Thread.currentThread().getName() + " added: " + item);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void readItems() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " reading: " + sharedList);
        } finally {
            lock.readLock().unlock();
        }
    }
}
