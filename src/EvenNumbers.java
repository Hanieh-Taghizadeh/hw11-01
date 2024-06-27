import java.util.List;

class EvenNumbers implements Runnable {
    private final List<Integer> sharedList;
    private final int max;
    private final Object lock;

    EvenNumbers(List<Integer> sharedList, int max, Object lock) {
        this.sharedList = sharedList;
        this.max = max;
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i <= max; i += 2) {
            synchronized (lock) {
                sharedList.add(i);
                System.out.println("Even thread added: " + i);
                lock.notify();
                try {
                    if (i + 2 <= max) {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}