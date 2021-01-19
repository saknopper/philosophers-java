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
        for (int timesEaten = 1; timesEaten < 4; timesEaten++) {
            try {
                waiter.waitForAndObtainChopsticks(id);
                System.out.println("Philosopher " + id + " is eating. (time: " + timesEaten + ")");
                Thread.sleep(1000);
                System.out.println("Philosopher " + id + " is finished eating. (time: " + timesEaten + ")");
                waiter.returnChopsticks(id);
            } catch (InterruptedException e) {
                return;
            }
        }

        System.out.println("Philosopher " + id + " had enough.");
    }
}
