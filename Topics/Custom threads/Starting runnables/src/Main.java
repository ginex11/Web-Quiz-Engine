class Starter {

    public static void startRunnables(Runnable[] runnables) {
        // implement the method
        for (Runnable t :
                runnables) {
            Thread t2 = new Thread(t);
            t2.start();
        }
    }
}