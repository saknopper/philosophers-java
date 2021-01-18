package nl.sanderknopper.diningphilosophersjava;

public class Philosopher implements Runnable {
    private final int id;
    private final Waiter waiter;

    public Philosopher(int id, Waiter waiter) {
        this.id = id;
        this.waiter = waiter;
    }

    @Override
    public void run() {
        for (int timesEaten = 0; timesEaten < 3; timesEaten++) {
            try {
                waiter.waitForAndGiveChopsticks(id);
                System.out.println("Philosopher " + id + " is eating. (time: " + (timesEaten + 1) + ")");
                Thread.sleep(1000);
                System.out.println("Philosopher " + id + " is finished eating. (time: " + (timesEaten + 1) + ")");
                waiter.returnChopsticks(id);
            } catch (InterruptedException e) {
                return;
            }
        }

        System.out.println("Philosopher " + id + " had enough.");
    }
}
