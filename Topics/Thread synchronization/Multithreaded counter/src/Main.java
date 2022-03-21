class Counter {

    int count = 0;

    public synchronized void inc() {
        count++;
    }

    public synchronized int getValue() {
        return count;
    }
}